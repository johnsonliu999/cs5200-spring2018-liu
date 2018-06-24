package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import edu.northeastern.cs5200.cs5200spring2018liu.models.Page;
import edu.northeastern.cs5200.cs5200spring2018liu.models.Website;
import edu.northeastern.cs5200.cs5200spring2018liu.utils.ConnectDB;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class WebsiteDao {

    private static WebsiteDao instance = null;
    private WebsiteDao () {}

    public static WebsiteDao getInstance() {
        if (instance == null) instance = new WebsiteDao();
        return instance;
    }

    private PageDao pageDao = PageDao.getInstance();

    private static String CREATE_WEBSITE = "INSERT INTO website (id, name, description, created, updated, visits, developer) VALUES (?, ?, ?, ?, ?, ?, ?)";
    /**
     * create website and its page, if exists then do nothing
     * @param website
     * @return executeUpdate
     */
    public int createWebsiteForDeveloper(int developerId, Website website) {
        Connection connection = null;
        website.setDeveloperId(developerId);
        int res = -1;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_WEBSITE);
            setWebiste(statement, website);
            res = statement.executeUpdate();

            website.getPages().forEach(page -> pageDao.createPageForWebsite(website.getId(), page));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return res;
    }

    private static final String FIND_ALL_WEBSITES = "SELECT * FROM website";

    public Collection<Website> findAllWebsites() {
        Collection<Website> websites = new LinkedList<>();
        try (Connection conn = ConnectDB.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(FIND_ALL_WEBSITES);

            while (results.next())
                websites.add(readWebsite(results));

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return websites;
    }

    private static final String FIND_WEBSITES_FOR_DEVELOPER = "SELECT * FROM website WHERE developer=?";

    public Collection<Website> findWebsitesForDeveloper(int developerId) {
        Collection<Website> websites = new LinkedList<>();
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_WEBSITES_FOR_DEVELOPER);
            statement.setInt(1, developerId);
            ResultSet results = statement.executeQuery();

            while (results.next())
                websites.add(readWebsite(results));

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return websites;
    }

    private static final String FIND_WEBSITE_BY_ID = "SELECT * FROM website WHERE id=?";

    public Website findWebsiteById(int websiteId) {
        Website website = null;
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_WEBSITE_BY_ID);
            statement.setInt(1, websiteId);
            ResultSet results = statement.executeQuery();

            if (results.next())
                website = readWebsite(results);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return website;
    }

    private static final String UPDATE_WEBSITE = "UPDATE website SET id=?, name=?, description=?, created=?, updated=?, visits=?, developer=? WHERE id=?";

    /**
     * update websites but NOT its pages
     * @param websiteId
     * @param website
     * @return
     */
    public int updateWebsite(int websiteId, Website website) {
        int res = -1;

        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_WEBSITE);
            int pos = setWebiste(statement, website);
            statement.setInt(pos, websiteId);
            res = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return res;
    }

    private static final String DELETE_WEBSITE = "DELETE FROM website WHERE id=?";

    @SuppressWarnings("Duplicates")
    public int deleteWebsite(int websiteId) {
        int res = -1;

        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_WEBSITE);
            statement.setInt(1, websiteId);
            res = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return res;
    }

    private Website readWebsite(ResultSet results) throws SQLException {
        int id = results.getInt("id");
        String name = results.getString("name");
        String description = results.getString("description");
        Date created = results.getDate("created");
        Date updated = results.getDate("updated");
        int visits = results.getInt("visits");
        int developerId = results.getInt("developer");

        Collection<Page> pages = pageDao.findPagesForWebsite(id);

        return new Website(id, name, description, created, updated, visits, pages, developerId);
    }

    private int setWebiste(PreparedStatement statement, Website website) throws SQLException {
        int pos = 0;
        if (website.getId() == 0)
            statement.setNull(++pos, Types.INTEGER);
        else
            statement.setInt(++pos, website.getId());
        statement.setString(++pos, website.getName());
        statement.setString(++pos, website.getDescription());
        statement.setDate(++pos, website.getCreated());
        statement.setDate(++pos, website.getUpdated());
        statement.setInt(++pos, website.getVisits());
        statement.setInt(++pos, website.getDeveloperId());

        return pos+1;
    }
}

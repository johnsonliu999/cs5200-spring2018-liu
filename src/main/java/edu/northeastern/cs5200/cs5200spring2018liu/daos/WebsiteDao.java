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
     *
     * @param website
     * @return executeUpdate
     */
    public int createWebsiteForDeveloper(int developerId, Website website) {
        Connection connection = null;
        int res = -1;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_WEBSITE);
            statement.setInt(1, website.getId());
            statement.setString(2, website.getName());
            statement.setString(3, website.getDescription());
            statement.setDate(4, website.getCreated());
            statement.setDate(5, website.getUpdated());
            statement.setInt(6, website.getVisits());
            statement.setInt(7, developerId);
            res = statement.executeUpdate();

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

    private static final String FIND_WEBSITES_FOR_DEVELOPER = "SELECT * FROM website WHERE developerId=?";

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

    public Website findWebsiteById(int websiteId) {
        // TODO
        return null;
    }

    public int updateWebsite(int websiteId, Website website) {
        // TODO
        return -1;
    }

    public int deleteWebsite(int websiteId) {
        // TODO
        return -1;
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
}

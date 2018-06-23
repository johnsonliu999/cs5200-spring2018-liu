package edu.northeastern.cs5200.cs5200spring2018liu.daos;


import edu.northeastern.cs5200.cs5200spring2018liu.models.Page;
import edu.northeastern.cs5200.cs5200spring2018liu.models.Widget;
import edu.northeastern.cs5200.cs5200spring2018liu.utils.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.util.LinkedList;

public class PageDao {

    private static PageDao instance = null;
    private WidgetDao widgetDao = WidgetDao.getInstance();

    private PageDao() {}

    public static PageDao getInstance() {
        if (instance == null) instance = new PageDao();
        return instance;
    }

    private static String CREATE_PAGE = "INSERT INTO page (id, title, description, created, updated, views, website) VALUES (?, ?, ?, ?, ?, ?, ?)";

    /**
     *
     * @param websiteId
     * @param page
     * @return -1: error, 0: duplicate
     */
    public int createPageForWebsite(int websiteId, Page page) {
        int res = -1;
        page.setWebsitdId(websiteId);

        // if already exists
        if (findPageById(page.getId()) != null) return 0;

        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement =  conn.prepareStatement(CREATE_PAGE);
            setPage(statement, page);
            res = statement.executeUpdate();

            page.getWidgets().forEach(widget -> widgetDao.createWidgetForPage(page.getId(), widget));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    private static final String FIND_ALL_PAGES = "SELECT * FROM page";

    public Collection<Page> findAllPages() {
        Collection<Page> pages = new LinkedList<>();

        try (Connection conn = ConnectDB.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(FIND_ALL_PAGES);
            while (results.next())
                pages.add(readPage(results));

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pages;
    }

    private static final String FIND_PAGE_BY_ID = "SELECT * from `page` WHERE id=?";

    public Page findPageById(int pageId) {
        Page page = null;
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_PAGE_BY_ID);
            statement.setInt(1, pageId);
            ResultSet results = statement.executeQuery();
            if (results.next())
                page = readPage(results);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return page;
    }

    private static final String FIND_PAGES_FOR_WEBSITES = "SELECT * FROM `page` WHERE website=?";

    public Collection<Page> findPagesForWebsite(int websiteId) {
        Collection<Page> pages = new LinkedList<>();
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_PAGES_FOR_WEBSITES);
            statement.setInt(1, websiteId);
            ResultSet results = statement.executeQuery();
            while (results.next())
                pages.add(readPage(results));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pages;
    }

    private static final String UPDATE_PAGE = "UPDATE `page` SET id=?, title=?, description=?, created=?, updated=?, views=?, website=? WHERE id=?";

    public int updatePage(int pageId, Page page) {
        int res = -1;

        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_PAGE);
            int pos = setPage(statement, page);
            statement.setInt(pos, pageId);
            res = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return res;
    }

    private static final String DELETE_PAGE = "DELETE FROM `page` WHERE id=?";
    public int deletePage(int pageId) {
        int res = -1;

        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_PAGE);
            statement.setInt(1, pageId);
            res = statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return res;
    }

    private int setPage(PreparedStatement statement, Page page) throws SQLException {
        int pos = 0;
        statement.setInt(++pos, page.getId());
        statement.setString(++pos, page.getTitle());
        statement.setString(++pos, page.getDescription());
        statement.setDate(++pos, page.getCreated());
        statement.setDate(++pos, page.getUpdated());
        statement.setInt(++pos, page.getViews());
        statement.setInt(++pos, page.getWebsitdId());

        return pos+1;
    }

    private Page readPage(ResultSet results) throws SQLException {
        int id = results.getInt("id");
        String title = results.getString("title");
        String description = results.getString("description");
        Date created = results.getDate("created");
        Date updated = results.getDate("updated");
        int views = results.getInt("views");
        int websiteId = results.getInt("website");

        // load the widgets for the page
        Collection<Widget> widgets = widgetDao.findWidgetsForPage(id);
        return new Page(id, title, description, created, updated, views, widgets, websiteId);

    }
}

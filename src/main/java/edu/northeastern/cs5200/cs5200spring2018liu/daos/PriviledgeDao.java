package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import edu.northeastern.cs5200.cs5200spring2018liu.models.Priviledge;
import edu.northeastern.cs5200.cs5200spring2018liu.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PriviledgeDao {

    private static PriviledgeDao instance = null;
    private PriviledgeDao() {}

    public static PriviledgeDao getInstance() {
        if (instance == null) instance = new PriviledgeDao();
        return instance;
    }

    private static final String CREATE_PRIVILEDGE = "INSERT INTO priviledge(id, name) VALUES (?, ?)";

    @SuppressWarnings("Duplicates")
    public int createPriviledge(Priviledge priviledge) {
        int res = -1;
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_PRIVILEDGE);
            statement.setInt(1, priviledge.getId());
            statement.setString(2, priviledge.getName());
            res = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return res;
    }

    private static final String ASSIGN_WEBSITE_PRIVILEDGE = "INSERT INTO website_priviledge(developer, website, priviledge) VALUES (?, ?, ?)";

    public int assignWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
        return DaoTools.updateInts(ASSIGN_WEBSITE_PRIVILEDGE, developerId, websiteId, priviledgeId);
    }

    private static final String ASSIGN_PAGE_PRIVILEDGE = "INSERT INTO page_priviledge(developer, page, priviledge) VALUES (?, ?, ?)";

    public int assignPagePriviledge(int developerId, int pageId, int priviledgeId) {
        return DaoTools.updateInts(ASSIGN_PAGE_PRIVILEDGE, developerId, pageId, priviledgeId);
    }

    private static final String DELETE_WEBSITE_PRIVILEDGE = "DELETE FROM website_priviledge WHERE developer=? AND website=? AND priviledge=?";


    public int deleteWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
        return DaoTools.updateInts(DELETE_WEBSITE_PRIVILEDGE, developerId, websiteId, priviledgeId);
    }

    private static final String DELETE_PAGE_PRIVILEDGE = "DELETE FROM page_priviledge WHERE developer=? AND page=? AND priviledge=?";

    public int deletePagePriviledge(int developerId, int pageId, int priviledgeId) {
        return DaoTools.updateInts(DELETE_PAGE_PRIVILEDGE, developerId, pageId, priviledgeId);
    }
}

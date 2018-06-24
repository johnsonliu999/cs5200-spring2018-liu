package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import edu.northeastern.cs5200.cs5200spring2018liu.models.Role;
import edu.northeastern.cs5200.cs5200spring2018liu.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleDao {

    private static RoleDao instance = null;
    private RoleDao() {}

    public static RoleDao getInstance() {
        if (instance == null) instance = new RoleDao();
        return instance;
    }

    private static final String CREATE_ROLE = "INSERT INTO role(id, name) VALUES (?, ?)";

    @SuppressWarnings("Duplicates")
    public int createRole(Role role) {
        int res = -1;
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_ROLE);
            statement.setInt(1, role.getId());
            statement.setString(2, role.getName());
            res = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return res;
    }

    private static final String ASSIGN_WEBSITE_ROLE = "INSERT INTO website_role(developer, website, role) VALUES (?, ?, ?)";

    public int assignWebsiteRole(int developerId, int websiteId, int roleId) {
        return DaoTools.updateInts(ASSIGN_WEBSITE_ROLE, developerId, websiteId, roleId);
    }

    private static final String ASSIGN_PAGE_ROLE = "INSERT INTO page_role(developer, page, role) VALUES (?, ?, ?)";

    public int assignPageRole(int developerId, int pageId, int roleId) {
        return DaoTools.updateInts(ASSIGN_PAGE_ROLE, developerId, pageId, roleId);

    }

    private static final String DELETE_WEBSITE_ROLE = "DELETE FROM website_role WHERE developer=? AND website=? AND role=?";

    public int deleteWebsiteRole(int developerId, int websiteId, int roleId) {
        return DaoTools.updateInts(DELETE_WEBSITE_ROLE, developerId, websiteId, roleId);
    }

    private static final String DELETE_PAGE_ROLE = "DELETE FROM page_role WHERE developer=? AND page=? AND role=?";

    public int deletePageRole(int developerId, int pageId, int roleId) {
        return DaoTools.updateInts(DELETE_PAGE_ROLE, developerId, pageId, roleId);
    }
}

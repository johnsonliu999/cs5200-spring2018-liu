package edu.northeastern.cs5200.cs5200spring2018liu.daos;

public class RoleDao {

    private static RoleDao instance = null;
    private RoleDao() {}

    public static RoleDao getInstance() {
        if (instance == null) instance = new RoleDao();
        return instance;
    }

    public void assignWebsiteRole(int developerId, int websiteId, int roleId) {
        // TODO
    }

    public void assignPageRole(int developerId, int pageId, int roleId) {
        // TODO
    }

    public void deleteWebsiteRole(int developerId, int websiteId, int roleId) {
        // TODO
    }

    public void deletePageRole(int developerId, int pageId, int roleId) {
        // TODO
    }
}

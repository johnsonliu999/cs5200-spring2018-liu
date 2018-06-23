package edu.northeastern.cs5200.cs5200spring2018liu.daos;

public class PriviledgeDao {

    private static PriviledgeDao instance = null;
    private PriviledgeDao() {}

    public static PriviledgeDao getInstance() {
        if (instance == null) instance = new PriviledgeDao();
        return instance;
    }

    public void assignWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
        // TODO
    }

    public void assignPagePriviledge(int developerId, int pageId, int priviledgeId) {
        // TODO
    }

    public void deleteWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
        // TODO
    }

    public void deletePagePriviledge(int developerId, int pageId, int priviledgeId) {
        // TODO
    }
}

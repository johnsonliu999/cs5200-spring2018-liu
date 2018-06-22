package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import edu.northeastern.cs5200.cs5200spring2018liu.models.Website;
import edu.northeastern.cs5200.cs5200spring2018liu.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WebsiteDao {

    private static WebsiteDao instance = null;
    private WebsiteDao () {}

    public static WebsiteDao getInstance() {
        if (instance == null) instance = new WebsiteDao();
        return instance;
    }

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
//
//    private static String FIND_ALL_WEBSITES = "SELECT * FROM website";
//    /**
//     *
//     * @return Website collection
//     */
//    public Collection<Website> findAllWebsites() {
//        Connection connection = null;
//        Collection<Website> websites = new ArrayList<>();
//        try {
//            connection = ConnectDB.getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet results = statement.executeQuery(FIND_ALL_WEBSITES);
//            while(results.next()) {
//                websites.add(readWebsite(results));
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return websites;
//    }
//
//    private static String FIND_Website_BY_ID = "SELECT * FROM Website, person WHERE Website.id=person.id AND person.id=?";
//    /**
//     *
//     * @param WebsiteId
//     * @return Website (null if not found)
//     */
//    public Website findWebsiteById(int WebsiteId) {
//        Connection connection = null;
//        Website Website = null;
//        try {
//            connection = getConnection();
//            PreparedStatement statement = connection.prepareStatement(FIND_Website_BY_ID);
//            statement.setInt(1, WebsiteId);
//            ResultSet results = statement.executeQuery();
//            if (results.next()) {
//                Website = readWebsite(results);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return Website;
//    }
//
//    private static String FIND_Website_BY_USERNAME = "SELECT * FROM Website, person WHERE Website.id=person.id and person.userName=?";
//
//    /**
//     *
//     * @param username
//     * @return
//     */
//    public Website findWebsiteByUsername(String username) {
//        Connection connection = null;
//        Website Website = null;
//        try {
//            connection = getConnection();
//            PreparedStatement statement = connection.prepareStatement(FIND_Website_BY_USERNAME);
//            statement.setString(1, username);
//            ResultSet results = statement.executeQuery();
//            if (results.next()) {
//                Website = readWebsite(results);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return Website;
//    }
//
//    private static String FIND_Website_BY_CREDENTIALS = "SELECT * FROM Website, person WHERE Website.id=person.id AND person.userName=? AND person.password=?";
//    /**
//     *
//     * @param username
//     * @param password
//     * @return Website
//     */
//    public Website findWebsiteByCredentials(String username, String password) {
//        Connection connection = null;
//        Website Website = null;
//        try {
//            connection = getConnection();
//            PreparedStatement statement = connection.prepareStatement(FIND_Website_BY_CREDENTIALS);
//            statement.setString(1, username);
//            statement.setString(2, password);
//            ResultSet results = statement.executeQuery();
//            if (results.next()) {
//                Website = readWebsite(results);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return Website;
//    }
//
//    private static String UPDATE_Website = "UPDATE Website, person SET person.id=?, firstName=?, lastName=?, userName=?, password=?, email=?, dob=?, WebsiteKey=? WHERE Website.id=person.id AND person.id=?";
//
//    /**
//     *
//     * @param WebsiteId
//     * @param Website
//     * @return Website's id
//     */
//    public int updateWebsite(int WebsiteId, Website Website) {
//        Connection connection = null;
//        int res = -1;
//        try {
//            connection = getConnection();
//            PreparedStatement statement = connection.prepareStatement(UPDATE_Website);
//            statement.setInt(1, Website.getId());
//            statement.setString(2, Website.getFirstName());
//            statement.setString(3, Website.getLastName());
//            statement.setString(4, Website.getUsername());
//            statement.setString(5, Website.getPassword());
//            statement.setString(6, Website.getEmail());
//            statement.setDate(7, Website.getDob());
//            statement.setString(8, Website.getWebsiteKey());
//            statement.setInt(9, WebsiteId);
//            res = statement.executeUpdate();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return res;
//    }
//
//    private static String DELETE_Website = "DELETE FROM person WHERE person.id=?";
//
//    public int deleteWebsite(int WebsiteId) {
//        Connection connection = null;
//        int res = -1;
//        try {
//            connection = getConnection();
//            PreparedStatement statement = connection.prepareStatement(DELETE_Website);
//            statement.setInt(1, WebsiteId);
//            res = statement.executeUpdate();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return res;
//    }
//
//    private Website readWebsite(ResultSet results) throws SQLException {
//        int id = results.getInt("id");
//        String name = results.getString("name");
//        String description = results.getString("description");
//        Date created = results.getDate("created");
//        Date updated = results.getDate("updated");
//        int visits = results.getInt("visits");
//
//
//        String developerKey = results.getString("developerKey");
//
//        return new Website(id, name, description, created, updated, visits, pages);
//    }
}

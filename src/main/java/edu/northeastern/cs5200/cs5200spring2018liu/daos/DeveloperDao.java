package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import edu.northeastern.cs5200.cs5200spring2018liu.models.Developer;
import edu.northeastern.cs5200.cs5200spring2018liu.utils.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DeveloperDao {

    private static DeveloperDao instance = null;
    private static String CREATE_DEVELOPER = "INSERT INTO developer (id, developerKey) VALUES (?, ?)";
    private static String CREATE_PERSON = "INSERT INTO person (id, firstName, lastName, userName, password, email, dob) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private DeveloperDao () {}

    public static DeveloperDao getInstance() {
        if (instance == null) instance = new DeveloperDao();
        return instance;
    }

    /**
     *
     * @param developer
     * @return executeUpdate
     */
    public int createDeveloper(Developer developer) {
        Connection connection = null;
        int res = -1;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_PERSON);
            statement.setInt(1, developer.getId());
            statement.setString(2, developer.getFirstName());
            statement.setString(3, developer.getLastName());
            statement.setString(4, developer.getUsername());
            statement.setString(5, developer.getPassword());
            statement.setString(6, developer.getEmail());
            statement.setDate(7, developer.getDob());
            statement.executeUpdate();

            statement = connection.prepareStatement(CREATE_DEVELOPER);
            statement.setInt(1, developer.getId());
            statement.setString(2, developer.getDeveloperKey());
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

    private static String FIND_ALL_DEVELOPERS = "SELECT * FROM developer, person WHERE developer.id=person.id";
    /**
     *
     * @return developer collection
     */
    public Collection<Developer> findAllDevelopers() {
        Connection connection = null;
        Collection<Developer> developers = new ArrayList<>();
        try {
            connection = ConnectDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(FIND_ALL_DEVELOPERS);
            while(results.next()) {
                developers.add(readDeveloper(results));
            }
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

        return developers;
    }

    private static String FIND_DEVELOPER_BY_ID = "SELECT * FROM developer, person WHERE developer.id=person.id AND person.id=?";
    /**
     *
     * @param developerId
     * @return developer (null if not found)
     */
    public Developer findDeveloperById(int developerId) {
        Connection connection = null;
        Developer developer = null;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_DEVELOPER_BY_ID);
            statement.setInt(1, developerId);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                developer = readDeveloper(results);
            }
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

        return developer;
    }

    private static String FIND_DEVELOPER_BY_USERNAME = "SELECT * FROM developer, person WHERE developer.id=person.id and person.userName=?";

    /**
     *
     * @param username
     * @return
     */
    public Developer findDeveloperByUsername(String username) {
        Connection connection = null;
        Developer developer = null;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_DEVELOPER_BY_USERNAME);
            statement.setString(1, username);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                developer = readDeveloper(results);
            }
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

        return developer;
    }

    private static String FIND_DEVELOPER_BY_CREDENTIALS = "SELECT * FROM developer, person WHERE developer.id=person.id AND person.userName=? AND person.password=?";
    /**
     *
     * @param username
     * @param password
     * @return developer
     */
    public Developer findDeveloperByCredentials(String username, String password) {
        Connection connection = null;
        Developer developer = null;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_DEVELOPER_BY_CREDENTIALS);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                developer = readDeveloper(results);
            }
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

        return developer;
    }

    private static String UPDATE_DEVELOPER = "UPDATE developer, person SET person.id=?, firstName=?, lastName=?, userName=?, password=?, email=?, dob=?, developerKey=? WHERE developer.id=person.id AND person.id=?";

    /**
     *
     * @param developerId
     * @param developer
     * @return developer's id
     */
    public int updateDeveloper(int developerId, Developer developer) {
        Connection connection = null;
        int res = -1;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_DEVELOPER);
            statement.setInt(1, developer.getId());
            statement.setString(2, developer.getFirstName());
            statement.setString(3, developer.getLastName());
            statement.setString(4, developer.getUsername());
            statement.setString(5, developer.getPassword());
            statement.setString(6, developer.getEmail());
            statement.setDate(7, developer.getDob());
            statement.setString(8, developer.getDeveloperKey());
            statement.setInt(9, developerId);
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

    private static String DELETE_DEVELOPER = "DELETE FROM person WHERE person.id=?";

    @SuppressWarnings("Duplicates")
    public int deleteDeveloper(int developerId) {
        Connection connection = null;
        int res = -1;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_DEVELOPER);
            statement.setInt(1, developerId);
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

    private Developer readDeveloper(ResultSet results) throws SQLException {
        int id = results.getInt("id");
        String firstName = results.getString("firstName");
        String lastName = results.getString("lastName");
        String userName = results.getString("userName");
        String password = results.getString("password");
        String email = results.getString("email");
        Date dob = results.getDate("dob");

        String developerKey = results.getString("developerKey");

        return new Developer(id, firstName, lastName, userName, password, email, dob,
                null, null, developerKey, null);
    }
}

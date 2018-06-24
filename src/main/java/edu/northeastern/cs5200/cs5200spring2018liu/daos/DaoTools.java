package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import edu.northeastern.cs5200.cs5200spring2018liu.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoTools {

    public static int updateInts(String sql, int... args) {
        int res = -1;
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            int pos = 0;
            for (int arg : args)
                statement.setInt(++pos, arg);
            res = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return res;
    }
}

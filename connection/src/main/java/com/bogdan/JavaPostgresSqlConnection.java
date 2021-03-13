package com.bogdan;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgresSqlConnection {

    public static void main(String[] args) {

        final String URL = "jdbc:postgresql://localhost:5432/postgres";
        final String USER = "bogdan";
        final String PASSWORD = "252525";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT VERSION()")) {

            if (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }

        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(JavaPostgresSqlConnection.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}

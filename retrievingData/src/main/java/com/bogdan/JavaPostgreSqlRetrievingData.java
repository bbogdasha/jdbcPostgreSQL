package com.bogdan;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlRetrievingData {

    public static void main(String[] args) {

        final String URL = "jdbc:postgresql://localhost:5432/postgres";
        final String USER = "bogdan";
        final String PASSWORD = "252525";

        String query = "SELECT * FROM authors";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                System.out.print(resultSet.getInt(1) + ": ");
                System.out.println(resultSet.getString(2));
            }

        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(JavaPostgreSqlRetrievingData.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}

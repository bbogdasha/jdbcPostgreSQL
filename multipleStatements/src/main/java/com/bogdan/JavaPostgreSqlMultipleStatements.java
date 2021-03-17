package com.bogdan;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlMultipleStatements {

    public static void main(String[] args) {

        final String URL = "jdbc:postgresql://localhost:5432/postgres";
        final String USER = "bogdan";
        final String PASSWORD = "252525";

        String query = "SELECT id, name FROM authors WHERE id=1;" +
                "SELECT id, name FROM authors WHERE id=2;" +
                "SELECT id, name FROM authors WHERE id=3;";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            boolean isResult = preparedStatement.execute();

            do {
                try (ResultSet resultSet = preparedStatement.getResultSet()) {
                    while (resultSet.next()) {
                        System.out.print(resultSet.getInt(1) + ": ");
                        System.out.println(resultSet.getString(2));
                    }
                }

                isResult = preparedStatement.getMoreResults();

            } while (isResult);

        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(JavaPostgreSqlMultipleStatements.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}

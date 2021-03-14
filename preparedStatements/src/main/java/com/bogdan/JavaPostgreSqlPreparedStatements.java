package com.bogdan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlPreparedStatements {

    public static void main(String[] args) {

        final String URL = "jdbc:postgresql://localhost:5432/postgres";
        final String USER = "bogdan";
        final String PASSWORD = "252525";

        int id = 6;
        String author = "Mark Twain";
        String query = "INSERT INTO authors(id, name) VALUES(?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, author);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(JavaPostgreSqlPreparedStatements.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}

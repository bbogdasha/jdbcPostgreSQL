package com.bogdan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlTransaction {

    public static void main(String[] args) {

        final String URL = "jdbc:postgresql://localhost:5432/postgres";
        final String USER = "bogdan";
        final String PASSWORD = "252525";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            try (Statement statement = connection.createStatement()) {

                connection.setAutoCommit(false);

                statement.executeUpdate("UPDATE authors SET name = 'New author' WHERE id=2");
                statement.executeUpdate("UPDATE books SET title = 'New book #1' WHERE id=1");
                statement.executeUpdate("UPDATE books SET t 'New book #2' WHERE id=6");

                connection.commit();

            } catch (SQLException ex) {

                try {
                    connection.rollback();
                    System.out.println("Successful rollback!");
                } catch (SQLException conEx) {
                    Logger logger = Logger.getLogger(JavaPostgreSqlTransaction.class.getName());
                    logger.log(Level.SEVERE, conEx.getMessage(), conEx);
                }

                Logger logger = Logger.getLogger(JavaPostgreSqlTransaction.class.getName());
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(JavaPostgreSqlTransaction.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}

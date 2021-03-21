package com.bogdan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlBatchUpdate {

    public static void main(String[] args) {

        final String URL = "jdbc:postgresql://localhost:5432/postgres";
        final String USER = "bogdan";
        final String PASSWORD = "252525";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            try (Statement statement = connection.createStatement()) {

                connection.setAutoCommit(false);

                statement.addBatch("DROP TABLE IF EXISTS readers");
                statement.addBatch("CREATE TABLE readers(id SERIAL, name VARCHAR(20))");
                statement.addBatch("INSERT INTO readers(name) VALUES ('Carl')");
                statement.addBatch("INSERT INTO readers(name) VALUES ('Emily')");
                statement.addBatch("INSERT INTO readers(name) VALUES ('Tom')");
                statement.addBatch("INSERT INTO readers(name) VALUES ('Ban')");
                statement.addBatch("INSERT INTO readers(name) VALUES ('Jany')");

                int[] count = statement.executeBatch();

                connection.commit();

                System.out.println("Finished! Batch updates: " + count.length + " updates");

            } catch (SQLException ex) {

                try {
                    connection.rollback();
                } catch (SQLException conEx) {
                    Logger logger = Logger.getLogger(JavaPostgreSqlBatchUpdate.class.getName());
                    logger.log(Level.SEVERE, conEx.getMessage(), conEx);
                }

                Logger logger = Logger.getLogger(JavaPostgreSqlBatchUpdate.class.getName());
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(JavaPostgreSqlBatchUpdate.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}

package com.bogdan;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlDataExportImport {

    public static void main(String[] args) {

        final String URL = "jdbc:postgresql://localhost:5432/postgres";
        final String USER = "bogdan";
        final String PASSWORD = "252525";

        //IMPORT
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            CopyManager copyManager = new CopyManager((BaseConnection) connection);

            String file = "src/main/resources/readers.txt";

            try (FileOutputStream fos = new FileOutputStream(file);
                 OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {

                copyManager.copyOut("COPY readers TO STDOUT WITH DELIMITER AS ')'", osw);
            }

        } catch (SQLException | IOException ex) {
            Logger logger = Logger.getLogger(JavaPostgreSqlDataExportImport.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }


        //EXPORT
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.execute("DELETE FROM readers");

            CopyManager copyManager = new CopyManager((BaseConnection) connection);

            String file = "src/main/resources/newReaders.txt";

            try (FileInputStream fis = new FileInputStream(file);
                 InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8)) {

                copyManager.copyIn("COPY readers FROM STDIN WITH DELIMITER AS ')'", isr);
            }

        } catch (SQLException | IOException ex) {
            Logger logger = Logger.getLogger(JavaPostgreSqlDataExportImport.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}

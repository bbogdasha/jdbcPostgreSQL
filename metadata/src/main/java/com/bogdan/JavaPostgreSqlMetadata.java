package com.bogdan;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlMetadata {

    public static void main(String[] args) {

        final String URL = "jdbc:postgresql://localhost:5432/postgres";
        final String USER = "bogdan";
        final String PASSWORD = "252525";

        String query = "SELECT name, title FROM authors, books WHERE authors.id=books.author_id";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            ResultSetMetaData metaData = resultSet.getMetaData();

            String columnName = metaData.getColumnName(1);
            String columnTitle = metaData.getColumnName(2);

            Formatter formatter1 = new Formatter();
            formatter1.format("%-30s%s", columnName, columnTitle);
            System.out.println(formatter1);

            while (resultSet.next()) {
                Formatter formatter2 = new Formatter();
                formatter2.format("%-30s", resultSet.getString(1));
                System.out.print(formatter2);
                System.out.println(resultSet.getString(2));
            }

        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(JavaPostgreSqlMetadata.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}

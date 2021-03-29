# Metadata

Database metadata refers to data about database data or, more elaborately, the information about tables, views, column types, column names, result sets, 
stored procedures, and databases. Java's JDBC metadata API provides the means to retrieve that information through Java code.

```java
    String query = "SELECT name, title FROM authors, books WHERE authors.id=books.author_id";
```

This step we select authors and their books from tables: authors and books.

---

```java
            ResultSetMetaData metaData = resultSet.getMetaData();

            String columnName = metaData.getColumnName(1);
            String columnTitle = metaData.getColumnName(2);
```

1. We used ```ResultSetMetaData``` object to get table names;
2. From the obtained metadata we get the column names ```getColumnName()``` in parameter number of column.

---

As a result we get the lines:

<img src="https://github.com/bbogdasha/jdbcPostgreSQL/blob/main/metadata/screen/metadata.png" width="400">

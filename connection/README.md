# Connection

This step connects to the database and get information about the PostgreSQL server.

```java
     try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
     Statement statement = connection.createStatement();
     ResultSet resultSet = statement.executeQuery("SELECT VERSION()")) {
```
1. Setting up connection to the database first, with method ```getConnection()``` with parameters: URL, UESR and PASSWORD.

2. Object ```Statement``` is created by the ```connection.createStatement``` method.

3. ```Statement``` - is intended for work with simple SQL queries without parameters, conteins basic methods for work with queries and retrieving results. 
Has method ```executeQuery()``` witch returns a single object ```ResultSet```.

4. ```ResultSet``` - is a table of data returned by particular SQL statement.

---

After connectioned and created statement:

```java
      if (resultSet.next()) {
          System.out.println(resultSet.getString(1));
      }
```
1. ResultSet pointing cursor before the first row, method ```next()``` moves cursor to the next row until it returns the folse.

2. Method ```getString(1)``` retrieves the value of column, from first column.

---

As a result we get this line:

```
PostgreSQL 13.2 on arm-apple-darwin20.3.0, compiled by Apple clang version 12.0.0 (clang-1200.0.32.29), 64-bit
```

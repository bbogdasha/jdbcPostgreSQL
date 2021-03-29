# Retrieving Data

This step we retrieve data from a database table (from "authors" table) and print result to the console.

```java
  String query = "SELECT * FROM authors";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
```

Use this method ```executeQuery()``` when we expect to get a set of results.

---

Then we display a few results:

```java
        while (resultSet.next()) {
                System.out.print(resultSet.getInt(1) + ": ");
                System.out.println(resultSet.getString(2));
            }
```

1. The ```next()``` method moves the cursor to the next record. It returns ```false``` when are no more rows in result set.
2. The ```getInt()``` and ```getString()``` methods get the value of the designated column in the current row of the ResultSet object as an int and String.

---

As a result we get the lines:

<img src="https://github.com/bbogdasha/jdbcPostgreSQL/blob/main/retrievingData/screen/retrievingData.png" width="350">

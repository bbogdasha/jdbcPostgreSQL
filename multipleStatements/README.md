# Multiple Statements

It is possible to run multiple SQL statements in one query.

As an example, we run three SELECT operators to get three authors:

```java
 String query = "SELECT id, name FROM authors WHERE id=1;" +
                "SELECT id, name FROM authors WHERE id=2;" +
                "SELECT id, name FROM authors WHERE id=3;";
```

---

```boolean isResult = preparedStatement.execute();```

Then we cteated boolean variable with method ```execute()``` which returns a boolean value indicating if the first result is a ResultSet object.

---

```java
              do {
                try (ResultSet resultSet = preparedStatement.getResultSet()) {
                    while (resultSet.next()) {
                        System.out.print(resultSet.getInt(1) + ": ");
                        System.out.println(resultSet.getString(2));
                    }
                }

                isResult = preparedStatement.getMoreResults();

            } while (isResult);
```

1. The ResultSet is retrieved with the ```getResultSet()``` method call. 
2. To find out if there are other results, we call the ```getMoreResults()``` method.

---

As a result we get the lines:

<img src="https://github.com/bbogdasha/jdbcPostgreSQL/blob/main/multipleStatements/screen/multipleStatements.png" width="350px">

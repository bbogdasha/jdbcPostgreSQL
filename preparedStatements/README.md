# Prepared Statements

This interface is used when we plan to use SQL statements multiple times. It accepts parameters while the program is running.

Add new author to the database:

```java
  String query = "INSERT INTO authors(id, name) VALUES(?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
```

To create a PreparedStatement object, use the ```preparedStatement()``` method of the Connection class. The sql query passed to this method
```"INSERT INTO authors(id, name) VALUES(?, ?)"```. This query can contain question marks as "?" - is a placeholder, instead of which the real values will be
inserted.

---

Fill in the empty spaces marked as "?":

```java
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, author);
        preparedStatement.executeUpdate();
```

1. An integer value is bound to the placeholder;
2. An string value is bound to the placeholder;
3. Runs SQL commands such INSERT, UPDATE, DELETE, CREATE and do not expect any data to be returned.

---

As a result we get new author in the database:

```
postgres=# SELECT * FROM authors;
 id |      name
----+-----------------
  1 | Jack London
  2 | Alexandre Dumas
  3 | Leo Tolstoy
  4 | Ronald Tolkien
  5 | Caesar
  6 | Mark Twain
(6 rows)
```

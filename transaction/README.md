# Transaction

When we work with JDBC, by defoult our connection works in auto-commit mode, every SQL query will be executed and the results will be saved in the database.

Transactions give us control over when and where to save changes in the database. Thanks to this we can, for exemple, combine a group of SQL queries into one 
logical group and if one of the queries fails we cancel the entire transaction.

We must use the ```setAutoCommit()``` method:

```java
                connection.setAutoCommit(false);
```

---

The third SQL query has an error, there is no title column in the table:

```java
                statement.executeUpdate("UPDATE authors SET name = 'New author' WHERE id=2");
                statement.executeUpdate("UPDATE books SET title = 'New book #1' WHERE id=1");
                statement.executeUpdate("UPDATE books SET t 'New book #2' WHERE id=6");
```

---

If the autocommit is turned off, we must call the ```commit()``` method:

```java
                connection.commit();
```

---

But we catch an exception and call ```rollback()``` method:

```java
                try {
                    connection.rollback();
                    System.out.println("Successful rollback!");
                } catch (SQLException conEx) {
                    Logger logger = Logger.getLogger(JavaPostgreSqlTransaction.class.getName());
                    logger.log(Level.SEVERE, conEx.getMessage(), conEx);
                }
```

No changes are committed to the database.

---

As a result we get this message:

<img src="https://github.com/bbogdasha/jdbcPostgreSQL/blob/main/transaction/screen/transaction.png" width="600">

And checked the database:

```postgres=# select name, title from authors, books where authors.id=books.author_id;
      name       |           title
-----------------+---------------------------
 Alexandre Dumas | The Count of Monte Cristo
 Caesar          | The conquest of Gaul
 Jack London     | Martin Eden
 Ronald Tolkien  | The Lord of the ring
 Leo Tolstoy     | War and Peace
 Alexandre Dumas | Queen Margot
 Jack London     | The Sea-Wolf
 Jack London     | Hearts of Three
 Ronald Tolkien  | Hobbit
(9 rows)
```

# Batch Update

When we need to update data with multiple statements we can use batch updates. Batch updates are available for INSERT, UPDATE and DELETE statements as well as for
CREATE TABLE and DROP TABLE statements.

We create a new table in database called "readers" and insert five rows into it:

```java
                statement.addBatch("DROP TABLE IF EXISTS readers");
                statement.addBatch("CREATE TABLE readers(id SERIAL, name VARCHAR(20))");
                statement.addBatch("INSERT INTO readers(name) VALUES ('Carl')");
                statement.addBatch("INSERT INTO readers(name) VALUES ('Emily')");
                statement.addBatch("INSERT INTO readers(name) VALUES ('Tom')");
                statement.addBatch("INSERT INTO readers(name) VALUES ('Ban')");
                statement.addBatch("INSERT INTO readers(name) VALUES ('Jany')");
```

We used ```addBatch()``` method to add a new command to the statement.

---

For illustrate we call ```executeBatch()``` method:

```java
                int[] count = statement.executeBatch();
                connection.commit();
                System.out.println("Finished! Batch updates: " + count.length + " updates");
```

This method returns an array of committed changes.

---

As a result we get the message:

<img src="https://github.com/bbogdasha/jdbcPostgreSQL/blob/main/batchUpdate/screen/batchUpdate.png" width="400">

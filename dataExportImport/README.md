# Data export and import

PostgreSQL has copy statement which can be used to copy data between a table and a file.

At first we tried copy the readers table to a file:

```java
            CopyManager copyManager = new CopyManager((BaseConnection) connection);

            String file = "src/main/resources/readers.txt";

            try (FileOutputStream fos = new FileOutputStream(file);
                 OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {

                copyManager.copyOut("COPY readers TO STDOUT WITH DELIMITER AS ')'", osw);
            }
```

1. ```CopyManager``` is the API for PostgreSQL COPY data transfer.

2. Next we created the path to the file and call process of writing data to the file.

3. We pass the results of a ```COPY TO STDOUT``` query from database into a writer using the ```copyOut()``` method. And the columns will be delimited with the ")" character.

As a result we get readers in file.txt from database:

```
1)Carl
2)Emily
3)Tom
4)Ban
5)Jany
```

---

In the second example, we copy the data from the file into the database table:

```java
            statement.execute("DELETE FROM readers");

            CopyManager copyManager = new CopyManager((BaseConnection) connection);

            String file = "src/main/resources/newReaders.txt";

            try (FileInputStream fis = new FileInputStream(file);
                 InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8)) {

                copyManager.copyIn("COPY readers FROM STDIN WITH DELIMITER AS ')'", isr);
            }
```

1. Making sure the database is empty.

2. ```CopyManager``` is the API for PostgreSQL COPY data transfer.

3. We read from the newReaders.txt file.

4. And we copy the data from the file using ```COPY FROM STDIN``` method.

As a result we checked the database:

```
postgres=# select * from readers;
 id | name
----+-------
  1 | Ann
  2 | Den
  3 | Tommy
  4 | Margo
  5 | Bart
(5 rows)
```

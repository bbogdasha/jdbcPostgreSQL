DROP TABLE IF EXISTS books, authors, testing, images;

CREATE TABLE IF NOT EXISTS authors (
    id serial PRIMARY KEY,
    name VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS books (
    id serial PRIMARY KEY,
    author_id INT REFERENCES authors(id), title VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS testing(id INT);
CREATE TABLE IF NOT EXISTS images(id serial, data bytea);

INSERT INTO authors(id, name) VALUES(1, 'Jack London');
INSERT INTO authors(id, name) VALUES(2, 'Alexandre Dumas');
INSERT INTO authors(id, name) VALUES(3, 'Leo Tolstoy');
INSERT INTO authors(id, name) VALUES(4, 'Ronald Tolkien');
INSERT INTO authors(id, name) VALUES(5, 'Caesar');

INSERT INTO books(id, author_id, title) VALUES(1, 2, 'The Count of Monte Cristo');
INSERT INTO books(id, author_id, title) VALUES(2, 5, 'The conquest of Gaul');
INSERT INTO books(id, author_id, title) VALUES(3, 1, 'Martin Eden');
INSERT INTO books(id, author_id, title) VALUES(4, 4, 'The Lord of the ring');
INSERT INTO books(id, author_id, title) VALUES(5, 3, 'War and Peace');
INSERT INTO books(id, author_id, title) VALUES(6, 2, 'Queen Margot');
INSERT INTO books(id, author_id, title) VALUES(7, 1, 'The Sea-Wolf');
INSERT INTO books(id, author_id, title) VALUES(8, 1, 'Hearts of Three');
INSERT INTO books(id, author_id, title) VALUES(9, 4, 'Hobbit');
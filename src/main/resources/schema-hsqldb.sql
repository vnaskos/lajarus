DROP TABLE player IF EXISTS;

CREATE TABLE player(
	id INTEGER PRIMARY KEY,
	name VARCHAR(255),
	latitude DOUBLE,
	longitude DOUBLE
);
CREATE TABLE users (
	id			serial PRIMARY KEY,
	username 	varchar(40) UNIQUE NOT NULL 
);
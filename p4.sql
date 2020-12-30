/*
*		Kevin Lunden & Carter Mooring
*		CPSC 321: Databases
*		Updated - Dec. 16th, 2020
* 	Description - This file is ran ON ADA to connect to our database stored there as well.
*									 Creates the Tables and Indexes to be user in our database.
*/

SET sql_mode = STRICT_ALL_TABLES;

DROP TABLE if EXISTS contains;
DROP TABLE if EXISTS photo;
DROP TABLE if EXISTS photoAlbum;
DROP TABLE if EXISTS home;
DROP TABLE if EXISTS person;

CREATE TABLE person(
	username VARCHAR (30),
	email VARCHAR (50) DEFAULT NULL,
	password VARCHAR (30),
	PRIMARY KEY (username)
);

CREATE TABLE home(
	username VARCHAR (30),
	home_id INT UNSIGNED NOT NULL,
	address VARCHAR(50),
	PRIMARY KEY (username),
	FOREIGN KEY (username) REFERENCES person (username) ON DELETE CASCADE
);

CREATE TABLE photoAlbum(
	album_name VARCHAR (30),
	username VARCHAR (30),
	PRIMARY KEY (album_name, username),
	FOREIGN KEY (username) REFERENCES person (username) ON DELETE CASCADE
);

CREATE TABLE photo(
	photo_id INT UNSIGNED NOT NULL,
	views INT UNSIGNED,
	username VARCHAR (30),
	date_added VARCHAR (10),
	/*IMAGE blob,*/
	PRIMARY KEY (photo_id),
	FOREIGN KEY (username) REFERENCES person (username) ON DELETE CASCADE
);

CREATE TABLE contains(
	photo_id INT UNSIGNED NOT NULL,
	username VARCHAR (30),
	album_name VARCHAR (30),
	PRIMARY KEY (photo_id, album_name),
	FOREIGN KEY (username) REFERENCES person (username) ON DELETE CASCADE,
	FOREIGN KEY (photo_id) REFERENCES photo (photo_id) ON DELETE CASCADE,
	FOREIGN KEY (album_name) REFERENCES photoAlbum (album_name) ON DELETE CASCADE
);

CREATE INDEX Views ON photo(views);
CREATE INDEX Users ON person(username);
CREATE INDEX avgViews ON photo(username, views);


INSERT INTO person VALUES("CarterKekoa", "carterkekoa@gmail.com", 1234);
INSERT INTO person VALUES("Kevin.Lund", "Kevin.Lunden@gmail.com", 12345);
INSERT INTO home VALUES("CarterKekoa", 1, "123 Sesame St");
INSERT INTO home VALUES("Kevin.Lund", 2, "234 Drury Ln");
INSERT INTO photoAlbum VALUES("FaceShots", "CarterKekoa");
INSERT INTO photoAlbum VALUES("Facials", "CarterKekoa");
INSERT INTO photoAlbum VALUES("FacE", "Kevin.Lund");
INSERT INTO photoAlbum VALUES("Headshots", "Kevin.Lund");
INSERT INTO photo VALUES(1, 9, "CarterKekoa", '2020-10-21');
INSERT INTO photo VALUES(2, 32, "Kevin.Lund", '2020-10-20');
INSERT INTO photo VALUES(3, 45, "CarterKekoa", '2020-11-2');
INSERT INTO photo VALUES(4, 33, "Kevin.Lund", '2020-11-3');
INSERT INTO contains VALUES(1, "CarterKekoa", "FaceShots");
INSERT INTO contains VALUES(2, "Kevin.Lund", "FacE");
INSERT INTO contains VALUES(3, "CarterKekoa", "Facials");
INSERT INTO contains VALUES(4, "Kevin.Lund", "Headshots");

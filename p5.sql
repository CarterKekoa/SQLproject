/*
*		Kevin Lunden & Carter Mooring
*		CPSC 321: Databases
*		Updated - Dec. 16th, 2020
* 	Description - This file is ran ON ADA to connect to our database stored there as well.
*									 Just a set of simple queries to run on our tables values.
*/

/* Necessary to get the specific home that is owned by a certain user */
SELECT home_id
FROM home
WHERE person = "CarterKekoa";

/* Used to get every photo album that belongs to a certain user */
SELECT album_name
FROM photoAlbum
WHERE person = "CarterKekoa";

/* Used to get the photo_id of every photo belonging to a certain user */
SELECT photo_id
FROM photo
WHERE person = "CarterKekoa";

/* Used to get photo_id of every photo in a certain album */
SELECT photo_id
FROM contains
WHERE album_name = "FaceShots";

/* Used to get the amount of views that a particular photo has */
SELECT views
FROM photo
WHERE photo_id = 1;

/* Used to get the date added of a certain photo */
SELECT date_added
FROM photo
WHERE photo_id = 1;

/* Used to find which album a certain photo belongs to */
SELECT album_name
FROM contains
WHERE photo_id = 1;

/* Used to get all photos with more than a certain number of views */
SELECT photo_id
FROM photo
WHERE views > 10;

/* Used to get all photos that were added by a certain user that have more than a certain amount of views */
SELECT photo_id
FROM photo
WHERE person = "CarterKekoa" AND views > 10;

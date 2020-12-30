# CPSC 321, Group Project, Fall 2020
This program is ran with MySQL and Java. Additional steps will need to be take than listed here to use MySQL Properly.
In addition to this, you will also need to make a `config.properties` file that will be used to login to you database in java files. Ex:
```
host = cps-database.gonzaga.edu
user = yourUserName
password = yourPassword
```

## Run Instructions 
#### First:
  You want to make the tables first. This can be done using p4.sql which is where the tables & indexes are created. 
  When in MySQL you can `use databaseName;` to switch to a database name `databaseName` that you need to create.
  Run: 
  ```
  mysql> source p4.sql;
  ``` 
  which will create the new tables in your MySQL Database.
  
#### Second:
  Now that your tables are created you can populate them. The `project_insert.java` is made to fill these tables with 1000 values. 
  The values added are random but all Primary and Foreign Keys are correct. What this file does is autopopulate 5 files (one per table) with 1000
  INSERT statements/values. NOTE: This step does not occur in MySQL, but rather in the directory where the files are stored.
  To compile this file:
  ```
  javac project_insert.java
  ```
  then to run,
  ```
  java project_insert
  ```

#### Third:
  The INSERT statmements how now been made and are stored in 5 different files. These files now need to be sourced in MySQL to be INSERTed into the 
  tables we created. In the same location as the **First** step, you want to source each of the 5 files:
  ```
  mysql> source project-person-data-1000.sql;
  mysql> source project-home-data-1000.sql
  mysql> source project-pa-data-1000.sql
  mysql> source project-photo-data-1000.sql
  mysql> source project-contains-data-1000.sql
  ``` 
  Now your tables should be populated, this can be checked by running a simple query in the same location:
  ```
  mysql> SELECT * FROM person;
  ``` 
  
#### Fourth:
  Lastly, now that everything is set up you can run the main program that gives the user a list of options and performs actions (queries) on the 
  MySQL tables/datasets accordingly. To run the main file `project.java` you again need to be in the same directory as the file.
  To run: 
  ```
  javac project.java
  ```
  then to run,
  ```
  java project
  ```
  You will then be prompted with the program! Sucess!

## End

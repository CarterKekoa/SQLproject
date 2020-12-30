/*
*   Carter Mooring
*   CSPC 321: Databases
*   project_insert.java
*   Dec. 15th, 2020
*
*   Description: This file will auto genereate insert statements for the sql file.
*/

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.*;
import java.util.*;

public class project_insert {
  public static void main(String[] args) {
    int one = 1000;
    String personFile = "project-person-data-1000.sql";
    String homeFile = "project-home-data-1000.sql";
    String paFile = "project-pa-data-1000.sql";
    String photoFile = "project-photo-data-1000.sql";
    String containsFile = "project-contains-data-1000.sql";

    try {
      //Populate files
      insert(one, personFile, homeFile, paFile, photoFile, containsFile);
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static String createRandomWord(int len) {
        String name = "";
        for (int i = 0; i < len; i++) {
            int v = 1 + (int) (Math.random() * 26);
            char c = (char) (v + (i == 0 ? 'A' : 'a') - 1);
            name += c;
        }
        return name;
  }

  //Generates a random number between 12000 and 150000
  public static int randomSalary(){
    return (int) ((Math.random() * 138000) + 1);
  }

  //Generates a random number between a given min and max
  public static int randomNum(int min, int max){
    return (int) ((Math.random() * (max-min)) + min);
  }

  //This function is to make the form of date entries.
  public static String monthDay(int max){
    int tempI;
    String tempS;

    tempI = randomNum(1, max);

    //Check if it is a single digit value. If so it needs a 0 in front to be valid
    if(tempI < 10){
      tempS = "0" + Integer.toString(tempI);
      //System.out.println("0+ " + tempS);
    } else {
      tempS = Integer.toString(tempI);
    }
    return tempS;
  }

  //This function stores the values of photo_id and ensure no duplicates occur (Primary Key)
  public static int randomPhotoId(HashSet<Integer> pid){
    int photo_id = randomNum(100, 100000);

    while(pid.contains(photo_id)){
      photo_id = randomNum(100, 100000);
    }
    pid.add(photo_id);

    return photo_id;
  }

  //Uses variables and randomSalary to populate the file and file size passed in
  public static void insert(int sqlSize, String personFile, String homeFile, String paFile, String photoFile, String containsFile) throws IOException {
    String username;
    String email;
    int password;
    int home_id;
    String address;
    String album_name;
    int photo_id;
    int views;
    String month;
    String day;
    int year;

    HashSet<Integer> pid = new HashSet<Integer>();

    // INSERT person Script
    //FileWriter to write to the files inserted
    FileWriter myPersonWriter = new FileWriter(personFile);
    FileWriter myHomeWriter = new FileWriter(homeFile);
    FileWriter myPaWriter = new FileWriter(paFile);
    FileWriter myPhotoWriter = new FileWriter(photoFile);
    FileWriter myContainsWriter = new FileWriter(containsFile);

    myPersonWriter.write("INSERT INTO person VALUES\n");
    myHomeWriter.write("INSERT INTO home VALUES\n");
    myPaWriter.write("INSERT INTO photoAlbum VALUES\n");
    myPhotoWriter.write("INSERT INTO photo VALUES\n");
    myContainsWriter.write("INSERT INTO contains VALUES\n");

    System.out.println("Successfully added INSERT statement for " + personFile + ". Now Adding VALUES...");
    System.out.println("Successfully added INSERT statement for " + homeFile + ". Now Adding VALUES...");
    System.out.println("Successfully added INSERT statement for " + paFile + ". Now Adding VALUES...");
    System.out.println("Successfully added INSERT statement for " + photoFile + ". Now Adding VALUES...");
    System.out.println("Successfully added INSERT statement for " + containsFile + ". Now Adding VALUES...");

    //Population loop, jumps 4 spaces to maintain 25% per title
    for(int i = 1; i < sqlSize; i++){
      //Variables
      username = createRandomWord(6);
      email = username + "@gmail.com";
      password = randomSalary();

      home_id = randomSalary();
      address = createRandomWord(10) + " Ave";

      album_name = createRandomWord(3);

      photo_id = randomPhotoId(pid);
      views = randomNum(1, 8000);
      month = monthDay(12);
      day = monthDay(30);
      year = randomNum(1960, 2020);

      //This loop will print the last line with a semicolon or comma otherwise
      if((i+1) == sqlSize){
        myPersonWriter.write("(\"" + username + "\", \"" + email + "\", " + password + ");");
        myHomeWriter.write("(\"" + username + "\", " + home_id + ", \"" + address + "\");");
        myPaWriter.write("(\"" + album_name + "\", \"" + username + "\");");
        myPhotoWriter.write("(" + photo_id + ", " + views + ", \"" + username + "\", '" + year + "-" + month + "-" + day + "');");
        myContainsWriter.write("(" + photo_id + ", \"" + username + "\", \"" + album_name + "\");");
      }else{
        myPersonWriter.write("(\"" + username + "\", \"" + email + "\", " + password + "), \n");
        myHomeWriter.write("(\"" + username + "\", " + home_id + ", \"" + address + "\"), \n");
        myPaWriter.write("(\"" + album_name + "\", \"" + username + "\"), \n");
        myPhotoWriter.write("(" + photo_id + ", " + views + ", \"" + username + "\", '" + year + "-" + month + "-" + day + "'), \n");
        myContainsWriter.write("(" + photo_id + ", \"" + username + "\", \"" + album_name + "\"), \n");
      }
    }
    myPersonWriter.close();
    myHomeWriter.close();
    myPaWriter.close();
    myPhotoWriter.close();
    myContainsWriter.close();

    System.out.println("Successfully populated INSERT of " + personFile + " with " + sqlSize + " values. \n");
    System.out.println("Successfully populated INSERT of " + homeFile + " with " + sqlSize + " values. \n");
    System.out.println("Successfully populated INSERT of " + paFile + " with " + sqlSize + " values. \n");
    System.out.println("Successfully populated INSERT of " + photoFile + " with " + sqlSize + " values. \n");
    System.out.println("Successfully populated INSERT of " + containsFile + " with " + sqlSize + " values. \n");
  }
}

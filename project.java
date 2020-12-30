/*
*		Kevin Lunden & Carter Mooring
*		CPSC 321: Databases
*		Updated - Dec. 16th, 2020
* 	Description - This file is ran ON ADA to connect to our database stored there as well.
*									 gives the user options and performs queries based on user inputs.
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;

public class project {
	public static void main(String[] args) {
		try {
			// connection info
    			Properties prop = new Properties();
    			FileInputStream in = new FileInputStream("config.properties");
    			prop.load(in);
    			in.close();

    			// connect to database
    			String hst = prop.getProperty("host");
    			String usr = prop.getProperty("user");
    			String pwd = prop.getProperty("password");
    			String dab = "cmooring_DB";
    			String url = "jdbc:mysql://" + hst + "/" + dab;
    			Connection con = DriverManager.getConnection(url, usr, pwd);

    			// Values
    			String q;
					String q1;
					String q2;
					String q3;
					String q4;
					String q5;
    			ResultSet rs;
					ResultSet rs1;
					ResultSet rs2;
    			int userInput;
    			Scanner reader = new Scanner(System.in);

					// Print out users operation options
    			do {
      				System.out.println(" 1. List users\n 2. Add user\n 3. Delete user\n 4. Find photos based on views\n 5. Update user's email and password\n 6. Get Address and ID of home for user\n 7. Average photo views per user\n 8. Exit");
      				System.out.print("Enter your choice (1-8): ");
      				userInput = Integer.parseInt(reader.next());
							System.out.println("\n");

							//Option 1: List users
      				if(userInput == 1) {
        				q = "SELECT username FROM person";
        				PreparedStatement pstmt = con.prepareStatement(q);
        				rs = pstmt.executeQuery(q);

								// print results
        				while(rs.next()) {
          					String name = rs.getString("username");
          					System.out.println(name);
        				}

								System.out.println("\n");
        				pstmt.close();
        				rs.close();
      				}
      				else if (userInput == 2) {
								//Option 2: Add User
        				System.out.print("Username................:");
        				String username = reader.next();
        				System.out.print("Email...................:");
        				String email = reader.next();
        				System.out.print("Password................:");
        				String password = reader.next();

        				q = "INSERT INTO person VALUES(?,?,?)";
        				PreparedStatement pstmt = con.prepareStatement(q);
        				pstmt = con.prepareStatement(q);
        				pstmt.setString(1,username);
        				pstmt.setString(2,email);
        				pstmt.setString(3,password);

        				pstmt.execute();
        				pstmt.close();
      				}
							else if (userInput == 3) {
								//Option 3: Delete User
				    		System.out.print("Username to Delete................:");
                String username = reader.next();
                System.out.print("Password to Delete................:");
                String password = reader.next();

                q1 = "DELETE FROM person WHERE username=? AND password=?;";
								q2 = "DELETE FROM home WHERE username=?;";
								q3 = "DELETE FROM photoAlbum WHERE username=?;";
								q4 = "DELETE FROM photo WHERE username=?;";
								q5 = "DELETE FROM contains WHERE username=?;";

								PreparedStatement pstmt5 = con.prepareStatement(q5);
                pstmt5.setString(1,username);
                pstmt5.execute();
                pstmt5.close();

								PreparedStatement pstmt4 = con.prepareStatement(q4);
                pstmt4.setString(1,username);
                pstmt4.execute();
                pstmt4.close();

								PreparedStatement pstmt3 = con.prepareStatement(q3);
                pstmt3.setString(1,username);
								pstmt3.execute();
								pstmt3.close();

								PreparedStatement pstmt2 = con.prepareStatement(q2);
								pstmt2.setString(1,username);
								pstmt2.execute();
								pstmt2.close();

								PreparedStatement pstmt1 = con.prepareStatement(q1);
								pstmt1.setString(1,username);
								pstmt1.setString(2,password);
								pstmt1.execute();
								pstmt1.close();

								System.out.print(username + "'s account deleted.");
								System.out.println("\n");
							}
      				else if (userInput == 4) {
								//Option 4: Find Photos Based On Views
        				System.out.print("Minimum views..............: ");
        				int views = Integer.parseInt(reader.next());

        				q = "SELECT * FROM photo WHERE views >= ? ORDER BY views DESC";
        				PreparedStatement pstmt = con.prepareStatement(q);
        				pstmt.setInt(1,views);
        				rs = pstmt.executeQuery();

        				while(rs.next()) {
          					String photo_id = rs.getString("photo_id");
          					String user = rs.getString("username");
          					int v = rs.getInt("views");
          					System.out.println("Photo ID: " + photo_id + ", User: " + user + ", Views: " + v);
        				}

								System.out.println("\n");
        				rs.close();
        				pstmt.close();
      				}
      				else if(userInput == 5) {
								//Option 5: Update User email & password
        				System.out.print("Username: ");
        				String user = reader.next();
        				System.out.print("Email...........: ");
        				String email = reader.next();
        				System.out.print("Password........: ");
        				String password = reader.next();

        				q = "UPDATE person SET email=?, password=? WHERE username=?";
        				PreparedStatement pstmt = con.prepareStatement(q);
        				pstmt.setString(1,email);
        				pstmt.setString(2,password);
        				pstmt.setString(3,user);
        				pstmt.execute();
      				}
							else if(userInput == 6) {
								//Option 6: Get address and Id of Home for user
								System.out.print("Username: ");
								String nameOfUser = reader.next();

								q = "SELECT h.home_id, h.address FROM person p JOIN home h USING(username) WHERE username = ?";
								PreparedStatement pstmt = con.prepareStatement(q);
								pstmt.setString(1,nameOfUser);
								rs = pstmt.executeQuery();

								while(rs.next()) {
									String home_id = rs.getString("home_id");
									String address = rs.getString("address");
									System.out.println("Home ID: " + home_id + ", Address: " + address);
								}

								System.out.println("\n");
								rs.close();
								pstmt.close();
							}
							else if(userInput == 7) {
								//Option 7: Avg photo views per user
								q = "SELECT username, AVG(views) FROM photo GROUP BY username HAVING AVG(views) > 0";
								PreparedStatement pstmt = con.prepareStatement(q);
								rs = pstmt.executeQuery(q);

								while(rs.next()) {
									String views = rs.getString("AVG(views)");
									String user = rs.getString("username");
									System.out.println("User: " + user + ", Average Photo Views: " + views);
								}

								System.out.println("\n");
								rs.close();
								pstmt.close();
							}
    				} while(userInput != 8);
						//Option 8: Java file will run until option 8 is selected, in which it exits the program
    				con.close();
  			} catch(Exception err) {
    			err.printStackTrace();
    		}
  	}
}

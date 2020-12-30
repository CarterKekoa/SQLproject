<!--
/*
* Carter Mooring
* delete.php
* CPSC321 Databases
* Dec. 12th, 2020
* Description: This serves as the user logout script
* Info:	This is displayed on https://barney.gonzaga.edu/~cmooring/delete.php
*/
-->
<html>
<body>
	<form action='home.php' method='post'>
		<input type='submit' name="home" value='Home'></input>
	</form>

	<h1> Delete Your Account </h1>
	<form action='logout' method='post'>
		<input type="text" name="pass" placeholder="Password">
			<?php
        // Initialize the session
        session_start();

        // Check if the user is logged in, if not then redirect him to login page
        if($_SESSION["loggedin"] !== true){
            header("location: login.php");
            exit;
        }else{
          echo '<p> User: ' . $_SESSION["username"] . '</p>';

  				$config = parse_ini_file("../private/config.ini");
  				$server = $config["servername"];
  				$username = $config["username"];
  				$password = $config["password"];
  				$database = "cmooring_DB";

  				$conn = mysqli_connect($server, $username, $password, $database); // Database Coneection

  				// Runs if connection fails
  				if (!$conn) {
  					die("Connection failed: " . mysqli_connect_error());
  				}

          $user = $_SESSION["username"];
  				$pass = $_POST['pass'];

          //TODO: Delete is not working
  				//prepare query
  				$query = "DELETE FROM person WHERE password=? AND username=" . $user;
  				$stmt = $conn->stmt_init();
  				$stmt->prepare($query);
  				$stmt->bind_param("s", $pass);	//add values to query

  				// Execute query above
  				$stmt->execute();

  				// finished so close
  				$stmt->close();
  				$conn->close();
        }
			?>
		<input type='submit' name='delete' value='Delete'></input>
	</form>
</body>
</html>

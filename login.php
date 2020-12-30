<!--
/*
* Carter Mooring
* login.php
* CPSC321 Databases
* Dec. 12th, 2020
* Description: This serves as the user login screen
* Info:	This is displayed on https://barney.gonzaga.edu/~cmooring/login.php
*/
-->
<html>
<body>
	<form action='register.php' method='post'>
		<input type='submit' name="Register" value='Register'></input>
	</form>
	
	<h1> Login </h1>
	<form action='' method='post'>
		<input type="text" name="user" placeholder="Username">
		<input type="text" name="pass" placeholder="Password">
			<?php
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

				$user = $_POST['user'];
				$pass = $_POST['pass'];
				echo '<p> user: ' . $user . '</p>';

				//prepare query
				$query = "SELECT username FROM person WHERE username=? AND password=?";
				$stmt = $conn->stmt_init();
				$stmt->prepare($query);
				$stmt->bind_param("ss", $user, $pass);	//add values to query

				// Execute query above
				$stmt->execute();
				$stmt->bind_result($user, $pass);

				$stmt->store_result();
				$rows_left = $stmt->num_rows();

				if($rows_left < 1) {
					//TODO why is this printing all the time
					echo '<h2>Login information was incorrect, please try again.';
				} else {
					session_start();
					$_SESSION["loggedin"] = true;
					$_SESSION["username"] = $user;

					// Redirect user to welcome page
					header("location: home.php");
				}

					// finished so close
					$stmt->close();
					$conn->close();

			?>
		<input type='submit' name='login' value='submit'></input>
	</form>
</body>
</html>

<!--
/*
* Carter Mooring
* register.php
* CPSC321 Databases
* Dec. 12th, 2020
* Description: This serves as the user registration screen
* Info:	This is displayed on https://barney.gonzaga.edu/~cmooring/register.php
*/
-->
<html>
<body>
	<form action='login.php' method='post'>
		<input type='submit' name="login" value='Login'></input>
	</form>

	<h1> Register </h1>
	<form action='home.php' method='post'>
		<input type="text" name="user" placeholder="Username">
		<input type="text" name="email" placeholder="Email">
		<input type="text" name="pass" placeholder="Password">
			<?php

				// Check if the user is logged in, if not then redirect him to login page
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
				$email = $_POST['email'];
				$pass = $_POST['pass'];

				//prepare query
				$query = "INSERT INTO person VALUES(?,?,?)";
				$stmt = $conn->stmt_init();
				$stmt->prepare($query);
				$stmt->bind_param("sss", $user, $email, $pass);	//add values to query

				// Execute query above
				$stmt->execute();
				$stmt->bind_result($user, $email, $pass);
				$stmt->fetch();

				session_start();
				$_SESSION["loggedin"] = true;
				$_SESSION["username"] = $user;

				// finished so close
				$stmt->close();
				$conn->close();

			?>
		<input type='submit' name='register' value='submit'></input>
	</form>
</body>
</html>

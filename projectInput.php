<!--
/*
* Carter Mooring
* projectInput.php
* CPSC321 Databases
* Dec. 12th, 2020
* Description: This serves as landing page for our Project Database on barney
* Info:	This is displayed on https://barney.gonzaga.edu/~cmooring/projectInput.php
*/
-->
<html>
<body>
	<h1> Project Input </h1>
	<form action='register.php' method='post'>
		<input type='submit' name="Register" value='Register'></input>
	</form>

	<form action='login.php' method='post'>
		<input type='submit' name="login" value='Login'></input>
	</form>

	<form action='' method='post'>
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

			$query = "SELECT username FROM person";
			$stmt = $conn->stmt_init();
			$stmt->prepare($query);
			$stmt->execute();
			$stmt->bind_result($users);
			$stmt->store_result();
			$rows_left = $stmt->num_rows();

			if($rows_left < 1) {
				echo '<h2>No users</h2>';
			} else {
				//Display
				echo '<table>';
				echo '<tr> <th>User Usernames:</tr>';
				while($stmt->fetch()) {
					echo '<tr>';
					echo '<td>' . $users . '</td>';
					echo '</tr>';
				}
				echo '</table>';
			}
			$stmt->close();
			$conn->close();
		?>
	</form>

</body>
</html>

<!--
/*
* Carter Mooring
* home.php
* CPSC321 Databases
* Dec. 12th, 2020
* Description: This serves as the users home screen
* Info:	This is displayed on https://barney.gonzaga.edu/~cmooring/home.php
*/
-->
<html>
<body>
  <form action='logout.php' method='post'>
		<input type='submit' name="logout" value='Logout'></input>
	</form>

  <form action='delete.php' method='post'>
		<input type='submit' name="delete" value='Delete Your Account'></input>
	</form>

  <h1> Home </h1>
  <form>
    <?php
      // Initialize the session
      session_start();

      // Check if the user is logged in, if not then redirect him to login page
      if($_SESSION["loggedin"] !== true){
          header("location: login.php");
          exit;
      }else{
        echo '<p> User: ' . $_SESSION["username"] . '</p>';
      }
    ?>
  </form>
</html>
</body>

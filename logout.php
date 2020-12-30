<!--
/*
* Carter Mooring
* logout.php
* CPSC321 Databases
* Dec. 12th, 2020
* Description: This serves as the user logout script
* Info:	This is displayed on https://barney.gonzaga.edu/~cmooring/logout.php
*/
-->
<?php
  // Initialize the session
  session_start();

  // Unset all of the session variables
  $_SESSION = array();

  // Destroy the session.
  session_destroy();

  // Redirect to login page
  header("location: projectInput.php");
  exit;
?>

<?php 
if (isset($_SESSION['userName']))
	LoggedIn();
else
	NotLoggedIn();

function LoggedIn(){
	echo "<span class='regLinks' style='display: block; text-align: right; color: blue;'>
		<a href='../authentification/logout.php' >[Logout]</a>
		</span>";
}

function NotLoggedIn(){
	echo "<span class='regLinks' style='display: block; text-align: right; color: blue;'>
		<a href='../Views/login.php' >[Login]</a>
		<a href='register.php' >[Register]</a>
		</span>";
}
?>
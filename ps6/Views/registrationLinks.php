<?php 
if (isset($_SESSION['login']))
	LoggedIn();
else
	NotLoggedIn();

if (isset($_SESSION['role']) && $_SESSION['role'] == "admin")
	ShowAdmin();

function LoggedIn(){
	echo "<span class='regLinks' style='display: block; text-align: right; color: blue;'>
		<span style='display: block; text-align: right; color: black;'>Hello ".$_SESSION['fullName']."</span>
		<a href='../authentification/logout.php' >[Logout]</a>
		</span>";
}

function NotLoggedIn(){
	echo "<span class='regLinks' style='display: block; text-align: right; color: blue;'>
		<a href='../Views/login.php' >[Login]</a>
		<a href='register.php' >[Register]</a>
		</span>";
}

function ShowAdmin(){
	echo "<span class='regLinks' style='display: block; text-align: right; color: blue;'>
		<a href='admin.php' >[Admin]</a>
		</span>";
}
?>
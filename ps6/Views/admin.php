<?php
if(!isset($_SESSION))
	session_start();
$_SESSION['destination'] = $_SERVER['PHP_SELF'];
require_once("../authentification/auth_config.php");
if(!isset($_SESSION['login']))
{
	$resumeSite->RedirectToURL("login.php");
	exit;
}
else{
	if(!$resumeSite->CheckAdmin())
		$resumeSite->RedirectToURL("badRole.php");
}

if(isset($_POST['submitted']))
{
	if($_POST['role'] >= 0){
		$resumeSite->ChangeRole($_POST['role']);
	}
	if($_POST['delete'] >= 0){
		$resumeSite->DeleteUser($_POST['delete']);
	}
}
$users = $resumeSite->GetUsers();
include 'header.php';
include 'registrationLinks.php';
?>
<body>
<fieldset >
<legend>User Administration</legend>	
	<form method="post" action="admin.php">
		<input type="hidden" name="submitted" value='-1' />
		<input id='role' type="hidden" name="role" value='-1' />
		<input id='delete' type="hidden" name="delete" value='-1' />
		<table>
		<?php 
		foreach ($users as $user){
			echo 	"<tr>
						<th>Full Name</th><th>Login</th><th>Role</th>
					</tr>";
			echo 	"<tr>
						<td>".$user['RealName']."</td>
						<td>".$user['Login']."</td>
						<td>".$user['Role']."</td>
						<td><input type='button' value='Change Role' onclick='ChangeRole(".$user['ID'].")'/></td>
						<td><input type='button' value='Delete User' onclick='DeleteUser(".$user['ID'].")'/></td>
					</tr>";		
		}			
		?>
		</table>
		<script src="../scripts/resume.js" type="text/javascript"></script>
	</form>
	<p><a href="positionSought.php" >Position Sought</a></p>
	<p><a href="employmentHistory.php" >Employment History</a></p>
	<?php include 'goToArchive.php'?>
	<p><a href="viewResume.php" >View Resume</a></p>
</fieldset>
</body>
</html>
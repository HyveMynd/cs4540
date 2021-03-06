<?php
require_once("../authentification/auth_config.php");
if(isset($_POST['submitted']))
{
	if($resumeSite->RegisterUser())
	{
		$resumeSite->RedirectToOrigin();
	}
	else{
		echo "<script type=text/javascript >alert('Operation Failed. Login is not unique.')</script>";
	}
}
else{
	$resumeSite->SetPageOrigin();
}
include 'header.php';
?>
<body>
<form action="register.php" method="post">
	<fieldset >
		<legend>Register</legend>	 
		<input type='hidden' name='submitted' value='1'/>
		<label for='login' >Full Name*:</label>
		<input type='text' name='fullName' id='fullName'  maxlength="50" required />
		 
		<label for='login' >Login Name*:</label>
		<input type='text' name='login' id='login'  maxlength="50" required />
		 
		<label for='password' >Password*:</label>
		<input type='password' id="password" name='password' id='password' maxlength="50" required />
		 
		<label for='password' >Retype Password*:</label>
		<input type='password' id="retype" name='password' id='password' maxlength="50" required />
		 
		<input type='button' value='Register' onclick='validateRegistration()' />
		<input type='button' value='Cancel' onclick="javascript: history.go(-1)" />
	</fieldset>
</form>
</body>
<script type="text/javascript" src="../scripts/resume.js"></script>
</html>
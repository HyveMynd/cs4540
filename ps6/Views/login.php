<?PHP
require_once("../authentification/auth_config.php");

if(isset($_POST['submitted']))
{
   if($resumeSite->Login())
   {
        $resumeSite->RedirectToOrigin();
   }
   else{
   		echo "<script type=text/javascript >alert('Operation Failed. Bad Login.')</script>"; 	
   }
}
else{
	$resumeSite->SetPageOrigin();
}
require_once('../helper/functions.php');
if (!usingHTTPS())
	redirectToHTTPS();
include '../Views/header.php';
?>
</head>
<body>

<form id='login' action='login.php' method='post' accept-charset='UTF-8'>
	<fieldset >
		<legend>Login</legend>	 
		<input type='hidden' name='submitted' value='1'/>
		<label for='username' >UserName*:</label>
		<input type='text' name='loginName' id='username'  maxlength="50" required />
		 
		<label for='password' >Password*:</label>
		<input type='password' name='loginPass' id='password' maxlength="50" required />
		 
		<input type='button' value='Login' onclick='validate()' />
		<input type='button' value='Cancel' onclick="javascript: window.location = '<?php echo $_SESSION['origin']?>'" />
	</fieldset>
</form>

</body>
<script src="../scripts/resume.js" type="text/javascript"></script>
</html>
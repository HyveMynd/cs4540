<?PHP
require_once("../authentification/auth_config.php");

if(isset($_SESSION['role']))
{
   if(!$resumeSite->CheckRole())
   {
        $resumeSite->RedirectToURL("authentification/login.php");
   }
}

include '../Views/header.php';
?>
</head>
<body>

<form id='login' action='../authentification/login.php' method='post' accept-charset='UTF-8'>
	<fieldset >
		<legend>Login</legend>	 
		<label for='username' >UserName*:</label>
		<input type='text' name='loginName' id='username'  maxlength="50" required />
		 
		<label for='password' >Password*:</label>
		<input type='password' name='loginPass' id='password' maxlength="50" required />
		 
		<input type='button' value='Submit' onclick='validate()' />
		<input type='button' value='Cancel' onclick='history.go(-1)' />
	</fieldset>
</form>

</body>
<script src="../scripts/resume.js" type="text/javascript"></script>
</html>
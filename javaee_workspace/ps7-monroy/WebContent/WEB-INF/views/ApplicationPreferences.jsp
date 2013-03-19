<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<title>Application Preferences</title>
</head>
<body>
<form method="get" action="ApplicationPreferences">
	<table>
	<tr>
		<td>Background Color: <select id="bgColor" name="bgColor">
			<option value="white" selected >White</option>
			<option value="black">Black</option>
			<option value="blue">Blue</option>
			<option value="red">Red</option> 
		</select></td>
	</tr>
	<tr>
		<td>Font Color: <select id="color" name="fontColor">
			<option value="white">White</option>
			<option value="black" selected >Black</option>
			<option value="blue">Blue</option>
			<option value="red">Red</option> 
		</select></td>
	</tr>
	<tr>
		<td>Font Size: <select id="size" name="fontSize">
			<option value="small">Small</option>
			<option value="medium" selected >Medium</option>
			<option value="large">Large</option>
			<option value="x-large">X-Large</option> 
			
		</select></td>
	</tr>
	<tr><td><input type="submit" value="Submit"/></td></tr>
	</table>
</form>
</body>
<script type="text/javascript">
	$(document).ready(function (){
		// Sytles
		$("body").css("background-color", "${preferences.bgColor}");
		$("body").css("color", "${preferences.fontColor}");
		$("body").css("font-size", "${preferences.fontSize}");
		
		// Sticky
		$("#bgColor").val('${preferences.bgColor}');
		$("#color").val('${preferences.fontColor}');
		$("#size").val('${preferences.fontSize}');
	});
</script>
</html>
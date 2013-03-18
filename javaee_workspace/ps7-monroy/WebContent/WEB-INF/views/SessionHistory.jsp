<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<title>Session History</title>
</head>
<style>
table,th,td {
	border:1px solid black;
}
</style>
<body>
	<table>
		<tr>
			<th>Browser Type</th>
			<th>Date of Access</th>
			<th>Url</th>
			<th>Full Url</th>	
		</tr>
		<c:forEach var="sessionInfo" items="${sessionHistory}">
		<tr>
			<td><c:out value="${sessionInfo.browser}"/><br></td>
			<td><c:out value="${sessionInfo.dateTime}"/><br></td>
			<td><c:out value="${sessionInfo.url}"/><br></td>
			<td><c:out value="${sessionInfo.fullUrl}"/><br></td>
	 	</tr>
		</c:forEach>
	</table>
<form method="post" action="SessionHistory">
	<table>
		<tr>
			<td>Rate: 
				<select id="rateMenu" name="rate">
					<option value="no">No</option>
					<option value="5">5</option>
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="60">60</option>
				</select>
			</td>
			<td><input name="submit" type="submit" value="Refresh"/></td>
			<td><input name="submit" type="submit" value="Reset"/></td>
		</tr>	
	</table>
</form>
</body>
<script type="text/javascript">
$(document).ready(function (){
	$("#rateMenu").val('${param.rate}');
});
</script>
</html>
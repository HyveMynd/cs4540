<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Session History</title>
</head>
<body>
	<table>
		<c:forEach var="sessionInfo" items="${sessionHistory}">
  			<c:out value="${sessionInfo.sessionId}"/><br>
		</c:forEach>
	</table>
	<input type="button" value="Reset Session" />
</body>
</html>
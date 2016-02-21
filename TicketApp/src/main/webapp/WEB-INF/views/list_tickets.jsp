<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Tickets</title>
</head>
<body>
	<div align="center">
		<h1>List Tickets</h1>
		<table border="1">
			<tr>
				<th>No</th>
				<th>Title</th>
			</tr>

			<c:forEach var="ticket" items="${listTicket}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${ticket.title}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>

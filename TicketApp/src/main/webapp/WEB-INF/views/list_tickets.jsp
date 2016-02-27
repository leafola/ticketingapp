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
	<c:if test="${pageContext.request.userPrincipal.name != null}"> 
		<h1>List Tickets</h1>
		<h3><a href="newTicket">Create New Ticket</a></h3>
		<table border="1">
			<tr>
				<th>No</th>
				<th>Title</th>
				<th>Actions</th>
			</tr>

			<c:forEach var="ticket" items="${listTicket}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${ticket.title}</td>
					<td>
						<a href="deleteTicket?id=${ticket.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>
</body>
</html>

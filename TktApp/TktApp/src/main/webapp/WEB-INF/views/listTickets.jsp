<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>List of Users</title>
	</head>
	<body>
		<table border="1" align="center" style="width: 50%">
			<thead>
				<tr>
					<th>Ticket Id</th>
					<th>Title</th>
					<th>Date Created</th>
					<th>Customer Id</th>
					<th>Agent Id</th>
					<th>Closed</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tickets" items="${tickets}">
					<tr>
						<td>${tickets.id}</td>
						<td>${tickets.title}</td>
						<td>${tickets.dateCreated}</td>
						<td>${tickets.customerId}</td>
						<td>${tickets.agentId}</td>
						<td>${tickets.closed}</td>
					</tr>
            	</c:forEach>
			</tbody>
		</table>
	</body>
</html>
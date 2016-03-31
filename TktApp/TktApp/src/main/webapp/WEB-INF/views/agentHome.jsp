<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<html>
	<body>
		<h2>Hello ${username}</h2>
		<form:form method="post" action="${logoutUrl}">
				<input type="submit" value="Logout"/>
		</form:form>
	</body>
</html>

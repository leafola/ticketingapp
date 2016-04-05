<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/j_spring_security_check" var="loginUrl" />
<c:url value="/web/register" var="registerUrl" />
<html>
<head>
<title>Login</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value="/static/css/login.css" />" rel="stylesheet">

</head>
<body>
	<div class="jumbotron vertical-center">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-md-4 col-md-offset-4">
					<div class="account-wall">
						<form:form class="form-signin" method="post" action="${loginUrl}"
							modelAttribute="account">
							<h4 class="login-class">Ticketing App: Login</h4>
							<div class="form-group input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span>
								<form:input type="text" class="form-control" path="username"
									name='username' placeholder="Username" />
							</div>
							<div class="form-group input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span>
								<form:input type="password" class="form-control" path="password"
									name='password' placeholder="Password" />
							</div>
							<div class="form-group">
								<input type="submit" class="btn btn-def btn-block" value="Login" />
							</div>
							<a href="${registerUrl}" class="text-left new-account">Create
									an account </a>
						</form:form>
					</div>
					<div class="row" style="display: inline; height: 20px">
						<div class="col-sm-12">
							<span class="text-right message">${message}&nbsp;</span>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>


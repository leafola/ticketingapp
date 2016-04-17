<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/web/login" var="loginUrl" />
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
					<div class="account-wall" style="padding:10px;">
					<div style="background:white;height:100px;padding:10px;">
					<div>
							<span class="message">${message}&nbsp;</span>
						</div> Please login again.<a href="${loginUrl}" style="padding:30px;"><button type="button" class="btn btn-success pull-right">Login</button>
					</div></a></div>
					
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>

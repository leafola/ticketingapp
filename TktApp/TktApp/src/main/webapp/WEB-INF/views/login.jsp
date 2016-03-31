<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/j_spring_security_check" var="loginUrl" />
<c:url value="/web/register" var="registerUrl" />
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<title>Login | Register</title>
<style>
.form-signin
{
    max-width: 330px;
    padding: 15px;
    margin: 0 auto;
}
.form-signin .form-signin-heading, .form-signin 
{
    margin-bottom: 10px;
}

.form-signin .form-control
{
    position: relative;
    font-size: 16px;
    height: auto;
    padding: 10px;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
.form-signin .form-control:focus
{
    z-index: 2;
}
.account-wall
{
    margin-top: 20px;
    padding: 40px 0px 20px 0px;
    background-color: #f7f7f7;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
.login-title
{
    color: #555;
    font-size: 18px;
    font-weight: 400;
    display: block;
}
.new-account
{
    display: block;
    margin-top: 10px;
}
.message
{
    display: block;
    margin-top: 10px;
    color: red;
}


</style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Sign in to your Ticketing App</h1>
            <div class="account-wall">
                <form:form  class="form-signin" method="post" action="${loginUrl}" modelAttribute="account" >
						<div class="form-group input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<form:input type="text" class="form-control" path="username"
								name='username' placeholder="Email" />
						</div>
						<div class="form-group input-group">
           				<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                		<form:input type="password" class="form-control" path="password" name='password' placeholder="Password" />
                	</div>
                	<div class="form-group">
                	<input type="submit" class="btn btn-def btn-block" value="Login"/>
                	</div>
                </form:form>
            </div>
            <div class="row">
	            <div class="col-sm-6">
	            	<a href="${registerUrl}" class="text-left new-account">Create an account </a>
	            </div>
	            <div class="col-sm-6">
            	<span class="text-right message" >${message}</span>
            	</div>
            </div>
        </div>
    </div>
    
</div>
</body>
</html>


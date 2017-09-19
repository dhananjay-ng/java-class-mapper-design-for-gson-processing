<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">

<title>Registration</title>
</head>
<body>
	<div class="container">
	<div class="row">
	<div class="col-lg-3">
</div>
		<div class="col-lg-6">
			<h2 class="text-center login-title">Registration</h2>
			<hr/>
	<jsp:include page="error_messages.jsp" />

			<div class="account-wall">
				<form method="post" action="register">
					User Id:<br> 
					<input type="text" name="userid" id="userid" class="form-control"
						value="${user.userId}" required autofocus> <br> User Name:<br> 
					<input
						type="text" name="username" id="username" class="form-control" value="${user.userName}" required autofocus>
					<br> Password:<br> 
					<input type="password" class="form-control" name="password"
						id="password" value="" required autofocus> <br> <br> <input
						type="submit" class="btn btn-lg btn-primary btn-block" value="Register">
				</form>
				<div>
					Already registered? <a href="/login">Login</a>
				</div>
			</div>

		</div>

	</div>
	
	</div>


	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>

</body>
</html>
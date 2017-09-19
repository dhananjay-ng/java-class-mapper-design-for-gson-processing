<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">

<title>Login</title>
</head>
<body>
	<br><br><br>
	<div class="container">
    <div class="row">
    <div class="col-lg-3">
    
    </div>
            <div class="col-lg-6">
            <h2 class="text-center login-title">Log in </h2>
            	<jsp:include page="error_messages.jsp" />
            
            <div class="account-wall">
                <form class="form-signin" method="post" action="login" >
                <input type="text" class="form-control" name="userid" id="userid" required autofocus>
                <input type="password" class="form-control" name="password" id="password"  required autofocus><br>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
               
                </form>
            </div><br>
            <a href="/register" class="btn btn-lg btn-primary btn-block text-center new-account"> Create an account </a>
        </div>
    </div>
  </div>
    

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</body>
</html>
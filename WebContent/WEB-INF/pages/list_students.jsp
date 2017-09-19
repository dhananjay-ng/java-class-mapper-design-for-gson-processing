<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">

<title>List Students Page</title>
</head>
<body>
	    

	
	
	<jsp:include page="error_messages.jsp" />
	<div class="container">
		<h2 class="text-center login-title">Students</h2>
		<hr>
	<div class="row">
	<div class="col-lg-3">
				<a href="newStudent" class="btn btn-sm btn-primary btn-block text-center new-account">Add User</a>
				<br>
	</div>
	
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Student Name</th>
					<th>Student Id</th>
					<th></th>
					<th></th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${students}" var="student">
					<tr>
						<td><c:out value="${student.name}" /></td>
						<td><c:out value="${student.id}" /></td>
						<td><a href="viewStudent?id=${student.id}"  dialog="" class="btn btn-sm btn-success btn-block text-center new-account" data-toggle="confirmation" data-title="Sure To Remove?">View</a></td>
						<td><a href="deleteStudent?id=${student.id}" delete="" class="btn btn-sm btn-danger btn-block text-center new-account" data-toggle="confirmation" data-title="Sure To Remove?">Remove</a></td>


					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
		</div>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
		<script src="http://bootstrap-confirmation.js.org/dist/bootstrap-confirmation2/bootstrap-confirmation.min.js"></script>
<script type="text/javascript">


$(function() {
	$('[data-toggle=confirmation]').confirmation({
		  rootSelector: '[data-toggle=confirmation]',
		  // other options
		});
	
	$("a[dialog]").click(function(){
	 //   return confirm('Are you sure want to continue?');
		$('#element').confirmation('show');

	});    
	});
	

</script>
</body>
</html>
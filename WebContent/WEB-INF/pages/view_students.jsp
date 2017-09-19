<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<link rel="stylesheet" href="css/main.css">

<title>Student Info</title>
</head>
<body>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<h2 class="text-center login-title">Student Details</h2>
			<hr>
			<br> <br>
			<br>
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td>Name</td>
						<td><c:out value="${student.name}" /></td>
					</tr>
					<tr>
						<td>Id</td>
						<td><c:out value="${student.id}" /></td>
					</tr>
					<tr>
						<td>Gender</td>
						<td><c:out value="${student.gender}" /></td>
					</tr>
					<tr>
						<td>Birth Date</td>

												<td><c:out value="${student.birthDate}" /></td>
											</tr>
					<tr>
						<td>Join Date</td>
						<td><c:out value="${student.joinDate}" /></td>

					</tr>
					<tr>
						<td>Standard</td>
						<td><c:out value="${student.standard}" /></td>
					</tr>
					<tr>
						<td>Division</td>
						<td><c:out value="${student.division}" /></td>
					</tr>
					<tr>
						<td>Roll Number</td>
						<td><c:out value="${student.rollNumber}" /></td>
					</tr>


				</tbody>
			</table>
			<br>
			
			
			<div class="col-lg-2">
				<a href="/deleteStudent?id=${student.id}"
					class="btn btn-md btn-danger btn-block text-center new-account" delete>Remove</a>
			</div>
			<div class="col-lg-2">
				<a href="/editStudent?id=${student.id}"
					class="btn btn-md btn-primary btn-block text-center new-account">Edit</a>
			</div>
			<div class="col-lg-2">
				<a href="/listStudents"
					class="btn btn-md btn-primary btn-block text-center new-account"><span class="glyphicon glyphicon-arrow-left aria-hidden="true"">Back</span></a>

			</div>
		</div>
	</div>
	

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<script type="text/javascript">


$(function() {
	$("a[delete]").click(function(){
	    return confirm('Are you sure want to continue?');
	});});

</script>
</body>
</html>
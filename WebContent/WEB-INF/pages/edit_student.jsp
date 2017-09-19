<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">

<title>Update Student Details</title>
</head>
<body>


                <div class="container">
                    <br>
                    <div class="row">
                        <div class="col-lg-3">
                        </div>
                        <div class="col-lg-6">

                            <h2 class="text-center login-title">Edit Student</h2>
                            <hr>
                            <jsp:include page="error_messages.jsp" />
                            
                            <form method="post" action="/editStudent" >
                                <div class="form-group">

                                    <input type="text" class="form-control" id="id" placeholder="id" name="id" hidden value="${student.id}">
                                </div>
                              
                                 <div class="form-group">
                                    <label for="name"><b>Name</b>
                                    </label>
                                    <input type="text" class="form-control" id="name" placeholder="name" name="name" value="${student.name}">
                                </div>
                                <div class="form-group">

                                    <label for="gender"><b>Gender</b>
                                    </label>&nbsp;&nbsp;
                                    <label class="radio-inline">
                                        <input type="radio" class="" name="gender" id="gender" value="M"><b>Male</b>
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" class="" name="gender" id="gender" value="F"><b>Female</b>
                                    </label>
                                </div>
                                <div class="form-group">

                                    <label for="birthDate"><b>Birth Date</b>
                                    </label>
                                    <input type="date" class="form-control" name="birthDate" id="birthDate" value="${student.birthDate}"  />
                                </div>
                                <div class="form-group">

                                    <label for="joinDate"><b>Join Date</b>
                                    </label>
                                    <input type="date" class="form-control" name="joinDate" id="joinDate" value="${student.joinDate}"  />
                                </div>

                                <div class="form-group">
                                    <label for="standard"><b>Standard</b></label>

                                        <select class="form-control" id="standard" name="standard" value="${student.standard}">
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                            <option>6</option>
                                            <option>7</option>
                                            <option>8</option>
                                            <option>9</option>
                                            <option>10</option>
                                            <option>11</option>
                                            <option>12</option>
                                        </select>
                                </div>
                                
                                <div class="form-group">
                                    <label for="division"><b>Division</b>
                                    </label>
                                    <select class="form-control" id="division" name="division" value="${student.division} ">
                                        <option>A</option>
                                        <option>B</option>
                                        <option>C</option>
                                        <option>D</option>
                                    </select>
                                </div>

                        <div class="form-group">

                                <label for="rollNumber"><b>Roll Number</b>
                                </label>
                                <input type="text" class="form-control" name="rollNumber" id="rollNumber" value="${student.rollNumber}"/>
                        </div>
                        <div class="form-group">
                            <div class="row">
                                <div class="col-lg-6">
                                    <input type="submit" name="action" value="Update" class="btn btn-md btn-primary btn-block text-center " />

                                </div>
                                <div class="col-lg-6">
                                    <input type="submit" name="action" value="Cancel" class="btn btn-md btn-primary btn-block text-center " />

                                </div>
                            </div>
                        </div>
                        </form>
                       </div>
                        
                    </div>
                </div>


	

                <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>

</body>
</html>
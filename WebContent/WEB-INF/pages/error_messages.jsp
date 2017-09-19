<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

  <c:if test="${fn:length(messages) > 0 }">
	
		<div class="container">
			<div class="alert alert-danger">
				<c:forEach items="${messages}" var="message">
					<div>${message}</div>
				</c:forEach>
			</div>
		</div>
	</c:if>

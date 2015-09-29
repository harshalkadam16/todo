<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
	<head>
	<title>Todo Aplication</title>
	 	 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
		
	  	<!--  CSS -->
		<spring:url value="/resources/css/style.css" var="styleCss"/>
		<link href="${styleCss}" rel="stylesheet"/>
		
	 	<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss"/>
		<link href="${bootstrapCss}" rel="stylesheet"/>
		
		<spring:url value="/resources/css/bootstrap-theme.min.css" var="bootstrapTheamCss"/>
		<link href="${bootstrapTheamCss}" rel="stylesheet"/>
		
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  		 
		<!-- Javascript -->
		<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
		
	  	<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs"/>
		<script src="${bootstrapJs}"></script>
		
		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  	</head> 
	<body>
  	<div class="container">
     <div class="row">
      	<h1>Welcome ..Todo Application</h1>
      	<a href='/todo/todo-list.htm'>click here</a> to add todo.
     </div>
    </div>
   </body>  
</html>
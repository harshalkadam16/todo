<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
	<head>
	<title>Todo List</title>
	 	 
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
        <div class="col-md-6">
            <div class="todolist not-done">
             <h1>Todo</h1>
             	<!-- <div class="alert alert-success" style="display: none;">
				  <strong>Success!</strong> Todo updated successfully.
				</div> -->
				<form action="/todo/addTodo" method="POST">
					<input type="hidden" value="${not empty todo ? todo.id : ''}" name="id" id="id">
			 		<div class="form-group">
						<input type="text" class="form-control" id="text" name="text" value="${not empty todo && not empty todo.text ? todo.text : ''}" placeholder="Enter todo here" required="required">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="datepicker" name="dueDate" value="${not empty todo && not empty todo.dueDate ? todo.dueDate : ''}" placeholder="Select due date" required="required">
					</div>
					<div class="form-group">
							<button id="addTodo" class="btn btn-success" type="submit">
								<c:choose>
									<c:when test="${empty todo}"> Save </c:when>
									<c:otherwise>Update</c:otherwise>
								</c:choose>
							</button>
						</div>
				</form>
				<!-- <div class="todo-footer">
                   	<strong><span class="count-todos"></span></strong> Items Left
                </div> -->
            </div>
        </div>
        <div class="col-md-6"> </div>
     </div>         
    </div>
   </body>
    <spring:url value="/resources/js/todo-list.js" var="todoJs"/>
    <script src="${todoJs}"></script> 
</html>
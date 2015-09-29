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
             <h1>Add Todo</h1>
             	<div class="alert alert-success" style="display: none;">
				  <strong>Success!</strong> Todo added successfully.
				</div>
		 		<div class="form-group">
					<input type="text" class="form-control" id="text" name="text" placeholder="Enter todo here">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="datepicker" name="dueDate" placeholder="Select due date">
				</div>
				<div class="form-group">
					<button id="addTodo" class="btn btn-success">Add</button>
				</div>

				<!-- <div class="todo-footer">
                   	<strong><span class="count-todos"></span></strong> Items Left
                </div> -->
            </div>
        </div>
        <div class="col-md-6">
            <div class="todolist">
               <h1>Todo Items</h1>
	           <div class="col-lg-6">
					<div class="form-group">
						<select class="form-control" id="filter">
							<option value="ALL" selected="selected">ALL</option>
							<option value="Completed">Completed</option>
							<option value="Pending">Pending</option>
					 	</select>	
					</div>
				</div>
				<div class="col-lg-6">
				    <div class="input-group">
				      <input type="text" class="form-control" placeholder="Search for...">
				      <span class="input-group-btn">
				        <button class="btn btn-default" type="button">Go!</button>
				      </span>
				    </div><!-- /input-group -->
				</div><!-- /.col-lg-6 -->
				<div class="col-lg-12">
	                <ul id="done-items" class="list-unstyled">
	                 	<c:forEach var="todo" items="${todos}" varStatus="cnt">
						  		                 	
						  <li id="todo${cnt.index}"> ${todo.text}  <fmt:formatDate type="date" value="${todo.dueDate}" /> <%-- ${todo.status} --%>
	                      <form action="/todo/editTodo.htm" id="form${cnt.index}">
	                      	<input type="hidden" id="id${cnt.index}" name="id" value="${todo.id}"/>
	                      </form>
	                      <button class="edit-item btn btn-default btn-xs pull-right" onclick="$('#form${cnt.index}').submit();">
	                         <span class="glyphicon glyphicon-edit"> </span>
	                      </button>
	                      &nbsp;&nbsp;
	                      
	                      <button class="remove-item btn btn-default btn-xs pull-right" id="deleteTodo('id${cnt.index}');">
	                      <span class="glyphicon glyphicon-remove"></span></button>
	                    </li>
	                   </c:forEach> 
	                 </ul>
	               </div>  
            </div>
        </div>
     </div>
    </div>
   </body>
   
   <spring:url value="/resources/js/todo-list.js" var="todoJs"/>
   <script src="${todoJs}"></script> 
</html>
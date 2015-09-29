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
		
		<spring:url value="/resources/js/todo.js" var="todoJs"/>
		<script src="${todoJs}"></script>
		
	  	<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs"/>
		<script src="${bootstrapJs}"></script>
		
		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
		
		 
	</head> 
	<body>
 	 
	<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="todolist not-done">
             <h1>Todos</h1>
                <input type="text" class="form-control add-todo" placeholder="Add todo">
                    <button id="checkAll" class="btn btn-success">Mark all as done</button>
                    
                 <hr>
                 <ul id="sortable" class="list-unstyled">
                    <li class="ui-state-default">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="" />Take out the trash</label>
                        </div>
                    </li>
                    <li class="ui-state-default">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="" />Buy bread</label>
                        </div>
                    </li>
                    <li class="ui-state-default">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="" />Teach penguins to fly</label>
                        </div>
                    </li>
                </ul>
                <div class="todo-footer">
                    <strong><span class="count-todos"></span></strong> Items Left
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="todolist">
             <h1>Already Done</h1>
                <ul id="done-items" class="list-unstyled">
                    <li>Some item <button class="remove-item btn btn-default btn-xs pull-right"><span class="glyphicon glyphicon-remove"></span></button></li>
                    
                </ul>
            </div>
        </div>
    </div>
</div>
	</body> 
</html>
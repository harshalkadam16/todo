  var dateToday = new Date(); 
  $(function() {
      $( "#datepicker" ).datepicker({
    //    numberOfMonths: 3,
          showButtonPanel: true,
          minDate: dateToday
      });
  });
  
   
  $("#filter").change(function(){
	   $('#filter').val($('#filtr').val());
	   $('#filterForm').submit();
  });
  
/*$("#addTodo").click(function(){
 if(validate('text') && validate('datepicker'))
	$.ajax({
		url: "/todo/addTodo",
		async: false,
		type : "POST",
		data :{ 
			   text : $('#text').val(),
 		       dueDate : $('#datepicker').val()
 		      },
		success: function(result){
			$(".alert-success").show();
		},
		error: function(xhr){
 			console.log("error in todo/addTodo :"+xhr);
 		}
	}); 
});
*/
  
$("#filter").change(function(){
  	$.ajax({
		url: "/todo/filterTodo",
		async: false,
		type : "POST",
		data :{  filter : $('#filter').val() },
		success: function(result){
	 		//$(".alert-success").show();
		},
		error: function(xhr){
 			console.log("error in todo/filter :"+xhr);
 		}
	}); 
});

/**
 * 
 * @param ele
 */
function deleteTodo(ele){
  	$.ajax({
		url: "/todo/deleteTodo",
		async: false,
		type : "POST",
		data :{  id : $('#id'+ele).val() },
		success: function(response){
			if(response == "SUCCESS")
				$('#todo'+ele).remove();
				//alert("item deleted");
			else
				alert("item was not deleted");
			  //$(".alert-success").show();
		},
		error: function(xhr){
 			console.log("error in todo/deleteTodo :"+xhr);
 		}
	}); 
}  

function mark(ele){
   	$.ajax({
 		url: "/todo/markCompleted",
 		async: false,
 		type : "POST",
 		data :{ id : $('#todo'+ele).val() , status : $('#mark'+ele).val() },
 		success: function(result){
 			$(".alert-success").show();
 			$('#mark'+ele).attr('checked','checked');
 	 	},
 		error: function(xhr){
  			console.log("error in todo/markCompleted :"+xhr);
  		}
 	}); 
} 
 
/**
 * 
 * @param ele
 * @returns {Boolean}
 */
function validate(ele){
	if($('#'+ele).val() == "" && $('#'+ele).val().length == 0){
		$('#'+ele).css("border-color","red");
		//$('#'+ele).attr("placeholder","Enter todo here");
		return false;
	} else{
		$('#'+ele).css("border-color","green");
		return true;
	}	
}
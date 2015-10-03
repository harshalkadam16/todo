package com.java.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.beans.Status;
import com.java.dao.pojo.Todo;
import com.java.dao.pojo.tp.ITodo;
import com.java.form.TodoForm;
import com.java.services.TodoService;

@Controller
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listEmployee(ModelMap model) {

		return "index";
	}

	/**
	 * 	
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/todo-list.htm", method = RequestMethod.GET)
	public ModelAndView getTodoList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

 		ModelAndView view = new ModelAndView("todolist");
   		view.addObject("todos", todoService.getTodoList());
 
		return view;
 	}
	

	/**
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editTodo.htm", method = RequestMethod.GET)
	public ModelAndView editTodo(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

 		ModelAndView view = new ModelAndView("addTodo");
   		view.addObject("todo", todoService.getTodo(id));
 
		return view;
 	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/markCompleted", method = RequestMethod.POST)
	@ResponseBody
	public String mark(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String id = request.getParameter("id");
		String status = request.getParameter("status");
 	  	if(todoService.markCompleted(id,status)){
   			return "SUCCESS";
   		}else{
   			return "FAILURE";
   		} 
 	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addTodo", method = RequestMethod.GET)
  	public ModelAndView addTodo(ModelMap model) {
		return new ModelAndView("addTodo"); 
	}	 	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param todoForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addTodo", method = RequestMethod.POST)
	//@ResponseBody
	public ModelAndView addTodo(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("todoForm") TodoForm todoForm, ModelMap model) {

	  	ITodo todo = new Todo();
		todo.setText(todoForm.getText());
		todo.setDueDate(todoForm.getDueDate());
		todo.setCreatedDate(new Date());
		todo.setStatus(Status.PENDING.toString());
	 	
		if(todoForm.getId() != null && todoForm.getId().length() > 0){
			todo.setId(todoForm.getId());
		}
		
		//todoService.saveTodo(todo);  
		
		 if (todoService.saveTodo(todo)) {
			//return "SUCCESS&"+todo.getId();
			 return new ModelAndView("redirect:/todo-list.htm");
		}else{
			//return "SUCCESS";
			return new ModelAndView("redirect:/todo-list.htm");
		} 		
 	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public ModelAndView filter(@RequestParam("filter") String filter, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
 		  
 		ModelAndView view = new ModelAndView("todolist");
 		if(filter.equals("ALL")){
 			view.addObject("todos", todoService.getTodoList());
 		}else{
 			view.addObject("todos", todoService.getFilterTodos(filter));
 		}
 
		return view;
 	}	

	/**
	 * 
	 * @param request
	 * @param response
	 * @param todoForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteTodo", method = RequestMethod.POST)
	@ResponseBody
	public String deleteTodo(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

	 	if (todoService.deleteTodo(request.getParameter("id"))) {
			return "SUCCESS";
		}else{
			return "SUCCESS";
		} 		
 	}
  }	
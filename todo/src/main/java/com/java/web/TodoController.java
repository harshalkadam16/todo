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
	

/*	@RequestMapping(value = "/editTodo.htm", method = RequestMethod.GET)
	public ModelAndView editTodo(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

 		ModelAndView view = new ModelAndView("editTodo");
   		view.addObject("todos", todoService.getTodo(request.getParameter("id")));
 
		return view;
 	}
	
	@RequestMapping(value = "/editTodo.htm", method = RequestMethod.POST)
	public ModelAndView editTodo(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

 		ModelAndView view = new ModelAndView("redirect:/todo/todo-list.htm");
 		
   		view.addObject("todos", todoService.updateTodo(request.getParameter("id")));
 
		return view;
 	}
*/	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param todoForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addTodo", method = RequestMethod.POST)
	@ResponseBody
	public String addTodo(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("todoForm") TodoForm todoForm, ModelMap model) {

	  	ITodo todo = new Todo();
		todo.setText(todoForm.getText());
		todo.setDueDate(todoForm.getDueDate());
		todo.setCreatedDate(new Date());
		todo.setStatus(Status.PENDING.toString());
	 	
 	 
		if (todoService.saveTodo(todo)) {
			return "SUCCESS&"+todo.getId();
		}else{
			return "SUCCESS";
		} 		
 	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/filterTodo", method = RequestMethod.GET)
	public ModelAndView filter(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		String filter = request.getParameter("filter");
 		ModelAndView view = new ModelAndView("todolist");
   		view.addObject("todos", todoService.getTodoList());
 
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
			return "SUCCESS&";
		}else{
			return "SUCCESS";
		} 		
 	}
  }	
package com.java.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.dao.TodoDAO;
import com.java.dao.pojo.Todo;
import com.java.dao.pojo.tp.ITodo;

/**
 * 
 * @author Harshal
 *
 */
public class TodoService {

	@Autowired
	private TodoDAO todoDAO;
	
	/**
	 * 
	 * @param todo
	 * @return
	 */
	public boolean saveTodo(ITodo todo){
		return todoDAO.saveTodo(todo);
	}

 	
	/**
	 * 
	 * @return
	 */
	public List<Todo> getTodoList() {
	 	return todoDAO.getTodoList();
	}

	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteTodo(String id) {
		 return todoDAO.deleteTodo(id);
	}


	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * 
	 * @throws Exception
	 */
	public Todo getTodo(String id) throws Exception {
		 return todoDAO.getTodo(id);
	}


	/**
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean markCompleted(String id, String status) {
 		return todoDAO.markCompleted(id,status);
	}


	public List<Todo> getFilterTodos(String filter) {
		// TODO Auto-generated method stub
		return todoDAO.getFilterTodos(filter);
	}  
 }
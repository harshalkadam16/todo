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


	public boolean deleteTodo(String id) {
		 return todoDAO.deleteTodo(id);
	}
	
 }


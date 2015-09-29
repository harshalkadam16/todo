package com.java.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.dao.pojo.Todo;
import com.java.dao.pojo.tp.ITodo;
import com.orientechnologies.orient.core.id.ORecordId;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

/**
 * 
 * @author Harshal
 * 
 */
public class TodoDAO {

	@Autowired
	private DBConnectionFactory dataSource;

	/**
	 * 
	 * @param todo
	 * @return
	 */
	public boolean saveTodo(ITodo todo) {
		boolean success = true;
		OrientGraph graph = null;

		try {
			// OrientGraphFactory factory = new
			// OrientGraphFactory("remote:localhost/demo", "admin", "admin");

			graph = dataSource.getConnection();
			Vertex todoVtx = graph.addVertex("class:Todo",
					todo.getPropertyMap());

			graph.commit();
			if (todoVtx != null) {
				todo.setId(todoVtx.getId().toString());
			}
		} catch (Exception orientDBException) {
			success = false;
			orientDBException.printStackTrace();
		} finally {
			dataSource.closeConnection(graph);
		}
		return success;
	}

	/**
	 * 
	 * @return
	 */
	public List<Todo> getTodoList() {
		OrientGraph graph = null;
		List<Todo> todos = new ArrayList<Todo>();
		try {
			graph = dataSource.getConnection();
			Iterable<Vertex> todoVtxItr = graph.getVerticesOfClass("Todo");

			for (Vertex todoVtx : todoVtxItr) {
				Todo todo = new Todo();

				todo.setText((String) todoVtx.getProperty("text"));
				todo.setCreatedDate((Date) todoVtx.getProperty("createdDate"));
				todo.setDueDate((Date) todoVtx.getProperty("dueDate"));
				todo.setStatus((String) todoVtx.getProperty("status"));
				todos.add(todo);
			}

			graph.commit();
		} catch (Exception orientDBException) {
			orientDBException.printStackTrace();
		} finally {
			dataSource.closeConnection(graph);
		}

		return todos;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteTodo(String id) {
		boolean success = true;
		OrientGraph graph = null;

		try {

			graph = dataSource.getConnection();
			Vertex todoVtx = graph.getVertex(new ORecordId(id));
			todoVtx.remove();
			graph.commit();
		} catch (Exception orientDBException) {
			success = false;
			orientDBException.printStackTrace();
		} finally {
			dataSource.closeConnection(graph);
		}
		return success;
	}
 }
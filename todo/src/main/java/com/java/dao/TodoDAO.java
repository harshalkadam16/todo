package com.java.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.dao.pojo.Todo;
import com.java.dao.pojo.tp.ITodo;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

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

			Vertex todoVtx = cretateOrUpdate(graph, todo);

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
	 * @param graph
	 * @param todo
	 * @return todo vertex
	 */
	private Vertex cretateOrUpdate(OrientGraph graph, ITodo todo) {
		Vertex todoVtx = null;
		
		if (todo.getId() != null && todo.getId().length() > 0) {
			todoVtx = graph.addVertex("class:Todo", todo.getPropertyMap());
		} else {
			todoVtx = graph.getVertex(new ORecordId(todo.getId()));
			
			todoVtx.setProperty("text", todo.getText() != null ? todo.getText() : "");
			todoVtx.setProperty("createdDate", todo.getCreatedDate());
			todoVtx.setProperty("dueDate", todo.getDueDate() != null ? todo.getDueDate() : new Date());
			todoVtx.setProperty("status", todo.getStatus() != null ? todo.getStatus() : "");
		}

		return todoVtx;
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
				todo.setId(todoVtx.getId().toString());
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
			if (todoVtx == null) {
				throw new Exception("Todo id is missing while deleting todo "
						+ id);
			}

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

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Todo getTodo(String id) throws Exception {
		OrientGraph graph = null;
		Todo todo = new Todo();

		try {
			graph = dataSource.getConnection();
			Vertex todoVtx = graph.getVertex(new ORecordId(id));

			if (todoVtx == null) {
				throw new Exception("Todo id is missing while getting todo "
						+ id);
			}

			todo.setText((String) todoVtx.getProperty("text"));
			todo.setCreatedDate((Date) todoVtx.getProperty("createdDate"));
			todo.setDueDate((Date) todoVtx.getProperty("dueDate"));
			todo.setStatus((String) todoVtx.getProperty("status"));

			graph.commit();
		} catch (Exception orientDBException) {
			orientDBException.printStackTrace();
		} finally {
			dataSource.closeConnection(graph);
		}

		return todo;
	}
 
	/**
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean markCompleted(String id,String status) {
		OrientGraph graph = null;
		boolean success = false;

		try {
			graph = dataSource.getConnection();
			Vertex todoVtx = graph.getVertex(new ORecordId(id));

			if (todoVtx == null) {
				throw new Exception("Todo id is missing while todo markCompleted" + id);
			}
 
			todoVtx.setProperty("status",status);

			graph.commit();
		} catch (Exception orientDBException) {
			orientDBException.printStackTrace();
		} finally {
			dataSource.closeConnection(graph);
		}

		return success;
 	}

	/**
	 * 
	 * @param filter
	 * @param search
	 * @return
	 */
	public List<Todo> getFilterTodos(String filter) {
		OrientGraph graph = null;
		List<Todo> todos = new ArrayList<Todo>();
		try {
			graph = dataSource.getConnection();
			String query = String.format("select * from Todo where status = '"+filter.toUpperCase()+"';");  //and text \"%s\"", search != null ? search : "");
			OSQLSynchQuery<OrientVertex> qr = new OSQLSynchQuery<OrientVertex>(query);
			
			Iterable<OrientVertex> todoVtxItr = graph.command(qr).execute();


			for (Vertex todoVtx : todoVtxItr) {
				Todo todo = new Todo();

				todo.setText((String) todoVtx.getProperty("text"));
				todo.setCreatedDate((Date) todoVtx.getProperty("createdDate"));
				todo.setDueDate((Date) todoVtx.getProperty("dueDate"));
				todo.setStatus((String) todoVtx.getProperty("status"));
				todo.setId(todoVtx.getId().toString());
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
}
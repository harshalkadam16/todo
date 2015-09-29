package com.java.dao.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.java.dao.pojo.tp.ITodo;

/**
 * 
 * @author Harshal
 * 
 */
public class Todo implements ITodo {

	private String id;

	private String text;

	private String status;

	private Date createdDate;

	private Date dueDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Map<Object, Object> getPropertyMap() {
		Map<Object, Object> returnMap = new HashMap<Object, Object>();
		returnMap.put("text", text != null ? text : "");
		returnMap.put("createdDate", createdDate != null ? createdDate : "");
		returnMap.put("dueDate", dueDate != null ? dueDate : "");
		returnMap.put("status", status != null ? status : "");

		return returnMap;

	}

}
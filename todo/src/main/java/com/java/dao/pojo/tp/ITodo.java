package com.java.dao.pojo.tp;

import java.util.Date;

import com.tinkerpop.frames.Property;

public interface ITodo extends IBaseModel{

	@Property("text")
	public String getText();
	
	@Property("dueDate")
	public Date getDueDate();

	@Property("createdDate")
	public Date getCreatedDate();

  	@Property("text")
	public void setText(String text);

	@Property("dueDate")
	public void setDueDate(Date dueDate);

	@Property("createdDate")
	public void setCreatedDate(Date createdDate);
 
	@Property("status")
	public String getStatus();
	
	@Property("status")
	public void setStatus(String status); 
}

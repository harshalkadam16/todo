package com.java.dao.pojo.tp;

import java.util.Map;

import com.tinkerpop.frames.Property;

/**
 * 
 * @author Harshal
 *
 */
public interface IBaseModel {

	@Property("rId")
	public String getId();
	
	@Property("rId")
	public void setId(String id);
	
	public Map<Object, Object> getPropertyMap();
}
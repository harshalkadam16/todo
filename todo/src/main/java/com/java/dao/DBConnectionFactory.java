package com.java.dao;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

 /**
  * 
  * @author Harshal
  *
  */
public class DBConnectionFactory {
 	
	@SuppressWarnings("unused")
	private String url;
	@SuppressWarnings("unused")
	private String userName;
	@SuppressWarnings("unused")
	private String password;
	@SuppressWarnings("unused")
	private int minPooledConnectioCount;
	@SuppressWarnings("unused")
	private int maxPooledConnectioCount;
	
	private OrientGraphFactory factory ;

	
	public DBConnectionFactory(String url, String userName, String password, int minPooledConnectioCount, int maxPooledConnectioCount) {
		super();
		this.url = url;
		this.userName = userName;
		this.password = password;
		this.minPooledConnectioCount = minPooledConnectioCount;
		this.maxPooledConnectioCount = maxPooledConnectioCount;
		this.factory = new OrientGraphFactory(url,userName,password).setupPool(minPooledConnectioCount,maxPooledConnectioCount);
	}
	
	
	public OrientGraph getConnection(){
 		return factory.getTx();
	}
	
	public boolean closeConnection(OrientGraph graph){
 		if(graph==null) return true;
		try{
			graph.shutdown();
			return true;
		} catch(Exception e){
			e.printStackTrace();
 			return false;
		}
	}
	
	public void closeConnectionPool(){
		try{
			if (factory!=null){
				factory.close();
			}
		} catch(Exception connectionPoolClosureException){
			connectionPoolClosureException.printStackTrace();
 		}
	}
 }
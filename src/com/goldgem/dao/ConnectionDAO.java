package com.goldgem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class that implements a connection (session) with a specific database. 
 * SQL statements are executed and results are returned within the context of a connection.
 * @author Mariana Azevedo
 * @since 18/04/2016
 */
public class ConnectionDAO {
	
	private Properties connectionProps;
	private Connection conn;
	private String dbms;
	private String dbName;
	private String serverName;
	private String portNumber;
	
	public ConnectionDAO (){
		super();
	}
	
	public ConnectionDAO (String username, String password){
		super();
		connectionProps = new Properties();
	    connectionProps.put("user", username);
	    connectionProps.put("password", password);
	}
	
	public Properties getConnectionProps() {
		return connectionProps;
	}

	public void setConnectionProps(Properties connectionProps) {
		this.connectionProps = connectionProps;
	}
	
	public Connection getConnection() {
		return conn;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public String getDbms() {
		return dbms;
	}

	public void setDbms(String dbms) {
		this.dbms = dbms;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
	
	/**
	 * Method to create a SQL connection
	 * @author Mariana Azevedo
	 * @since 25/03/2016
	 * @param
	 * @returns Connection
	 * @throws SQLException
	 */
	public Connection createConnection() throws SQLException {
		Connection conn = null;
	    if (getDbms().equals("mysql")) {
	        conn = DriverManager.getConnection("jdbc:" + getDbms() + "://" + getServerName() + ":" + getPortNumber() 
	        + "/" + getDbName() + "?useSSL=false", getConnectionProps());
	    }else if (getDbms().equals("postgreSQL")){
	    	conn = DriverManager.getConnection("jdbc:" + getDbms() + "://" + getServerName() + ":" + getPortNumber() 
	    	+ "/" + getDbName() + "?useSSL=false", getConnectionProps());
	    }else if (getDbms().equals("derby")) {
	        conn = DriverManager.getConnection("jdbc:" + getDbms() + ":" + getDbName() + ";create=true", getConnectionProps());
	    }
	    System.out.println("Connected to database");
	    setConnection(conn);
	    return conn;
	}
}

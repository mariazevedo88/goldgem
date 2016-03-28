package com.goldgem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.goldgem.dao.interfaces.InterfaceJDBCDAO;

public class GenericJDBCDAO<T> implements InterfaceJDBCDAO {
	
	private Properties connectionProps;
	private String dbms;
	private String dbName;
	private String serverName;
	private String portNumber;
	
	/**
	 * Constructor that receives as parameter a class and instantiates a JDBC session
	 * @author Mariana Azevedo
	 * @since 25/03/2016
	 * @param username
	 * @param password
	 */
	public GenericJDBCDAO(String username, String password) {
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
	@Override
	public Connection createConnection() throws SQLException {
		Connection conn = null;
	    if (getDbms().equals("mysql")) {
	        conn = DriverManager.getConnection("jdbc:" + getDbms() + "://" + getServerName() + ":" + getPortNumber() + "/", connectionProps);
	    }else if (getDbms().equals("postgreSQL")){
	    	conn = DriverManager.getConnection("jdbc:" + getDbms() + "://" + getServerName() + ":" + getPortNumber() + "/", connectionProps);
	    }else if (getDbms().equals("derby")) {
	        conn = DriverManager.getConnection("jdbc:" + getDbms() + ":" + getDbName() + ";create=true", connectionProps);
	    }
	    System.out.println("Connected to database");
	    return conn;
	}

	@Override
	public GenericJDBCDAO getByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GenericJDBCDAO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(GenericJDBCDAO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(GenericJDBCDAO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(GenericJDBCDAO entity) {
		// TODO Auto-generated method stub
		return false;
	}

}

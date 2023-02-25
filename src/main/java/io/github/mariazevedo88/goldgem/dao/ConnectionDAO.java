package io.github.mariazevedo88.goldgem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that implements a connection (session) with a specific database. SQL statements are executed 
 * and results are returned within the context of a connection.
 * 
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
	
	private static final String JDBC = "jdbc:";
	private static final Logger logger = LogManager.getLogger(ConnectionDAO.class);
	
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
	 * 
	 * @author Mariana Azevedo
	 * @since 25/03/2016
	 * @returns Connection
	 * @throws SQLException
	 */
	public Connection createConnection() {
		Connection newConnection = null;
        try {
        	
	        switch (getDbms()) {
	            case "mysql" -> newConnection = DriverManager.getConnection(JDBC + getDbms() + "://" + getServerName() + ":" + getPortNumber() 
				+ "/" + getDbName() + "?useSSL=false&useTimezone=true&serverTimezone=America/Sao_Paulo", getConnectionProps());
	            case "postgreSQL" -> newConnection = DriverManager.getConnection(JDBC + getDbms() + "://" + getServerName() + ":" + getPortNumber() 
		    	+ "/" + getDbName() + "?useSSL=false&useTimezone=true&serverTimezone=America/Sao_Paulo", getConnectionProps());
	            case "derby" -> newConnection = DriverManager.getConnection(JDBC + getDbms() + ":" + getDbName() + ";create=true", getConnectionProps());
	            default -> newConnection = DriverManager.getConnection(JDBC + getDbms() + ":mem:" + getDbName(), getConnectionProps());
	        };
        
        	setConnection(newConnection);
        	logger.info("Connected to database {}" + getDbName());
		} catch (SQLException e) {
			logger.error("Error on database connection: " + e);
		}
	    
	    return newConnection;
	}
}

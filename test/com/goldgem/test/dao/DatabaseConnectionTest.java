package com.goldgem.test.dao;

import java.sql.Connection;

import org.junit.Test;

import com.goldgem.dao.ConnectionDAO;

public class DatabaseConnectionTest {

	private static Connection testConnection;
	private static ConnectionDAO genericJDBCDao;
	
	@Test
	public void connectDatabase(){
		genericJDBCDao = new ConnectionDAO("root", "");
		genericJDBCDao.setDbms("mysql");
		genericJDBCDao.setDbName("library");
		genericJDBCDao.setServerName("localhost");
		genericJDBCDao.setPortNumber("3306");
		
		testConnection = genericJDBCDao.createConnection();
		genericJDBCDao.setConnection(testConnection);
	}
	
	public static Connection getConnectionTest(){
		return testConnection;
	}
	
	public static ConnectionDAO getGenericJDBCDAO(){
		return genericJDBCDao;
	}
}

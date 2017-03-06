package com.goldgem.test.dao;

import java.sql.Connection;
import java.sql.SQLException;

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
		
		try {
			testConnection = genericJDBCDao.createConnection();
			genericJDBCDao.setConnection(testConnection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnectionTest(){
		return testConnection;
	}
	
	public static ConnectionDAO getGenericJDBCDAO(){
		return genericJDBCDao;
	}
}

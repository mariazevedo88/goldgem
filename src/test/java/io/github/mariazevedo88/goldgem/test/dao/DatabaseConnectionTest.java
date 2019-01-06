package io.github.mariazevedo88.goldgem.test.dao;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;

import io.github.mariazevedo88.goldgem.dao.ConnectionDAO;

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
		
		assertTrue(genericJDBCDao.getConnection() != null);
	}
	
	public static Connection getConnectionTest(){
		return testConnection;
	}
	
	public static ConnectionDAO getGenericJDBCDAO(){
		return genericJDBCDao;
	}
}

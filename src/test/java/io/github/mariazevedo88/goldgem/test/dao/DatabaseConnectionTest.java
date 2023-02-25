package io.github.mariazevedo88.goldgem.test.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.mariazevedo88.goldgem.dao.ConnectionDAO;

@DisplayName("DatabaseConnectionTest")
public class DatabaseConnectionTest{
	
	private static Connection testConnection;
	private static ConnectionDAO genericJDBCDao;
	
	@Test
	@DisplayName("Connect to an database")
	public void connectDatabase(){
		genericJDBCDao = new ConnectionDAO("root", "");
		genericJDBCDao.setDbms("h2");
		genericJDBCDao.setDbName("library");
		
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

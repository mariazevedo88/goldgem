package io.github.mariazevedo88.goldgem.test.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import io.github.mariazevedo88.goldgem.dao.ConnectionDAO;

@DisplayName("DatabaseConnectionTest")
@TestMethodOrder(OrderAnnotation.class)
public class DatabaseConnectionTest{
	
	private static Connection testConnection;
	private static ConnectionDAO genericJDBCDao;

	@Test
	@DisplayName("Trying to connect to an database with the wrong port number")
	@Order(1)
	public void connectDatabaseWithWrongPortNumber(){
		ConnectionDAO genericJDBCDao = new ConnectionDAO("root", "");
		genericJDBCDao.setDbms("mysql");
		genericJDBCDao.setDbName("library");
		genericJDBCDao.setServerName("localhost");
		genericJDBCDao.setPortNumber("8080");
		
		Connection testConnection = genericJDBCDao.createConnection();
		genericJDBCDao.setConnection(testConnection);
		
		assertTrue(genericJDBCDao.getConnection() == null);
	}
	
	@Test
	@DisplayName("Connect to an database")
	@Order(1)
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

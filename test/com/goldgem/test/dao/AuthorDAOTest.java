package com.goldgem.test.dao;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.goldgem.dao.ConnectionDAO;
import com.goldgem.dao.GenericDAO;
import com.goldgem.test.AuthorTest;

public class AuthorDAOTest{

	@Test
	public void addAuthorUsingJDBC(){
		ConnectionDAO genericJDBCDao = DatabaseConnectionTest.getGenericJDBCDAO();
		if (genericJDBCDao == null){
			DatabaseConnectionTest dbConn = new DatabaseConnectionTest();
			dbConn.connectDatabase();
			genericJDBCDao = DatabaseConnectionTest.getGenericJDBCDAO();
		}
		
		try {
			Statement stmt = genericJDBCDao.getConnection().createStatement();
			String sql = "insert into author (id, first_name, last_name) values (3, 'Clifford', 'Stein')";
			int rs = stmt.executeUpdate(sql);
			if (rs == 1){
				System.out.println(rs + " row have been affected.");
			}else if (rs > 1){
				System.out.println(rs + " rows have been affected.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addAuthorUsingHibernate(){
		GenericDAO<AuthorTest> genericDAO = new GenericDAO<AuthorTest>(AuthorTest.class, "hibernate.cfg.xml");
		
		AuthorTest author = new AuthorTest();
		author.setFirstName("Joseph");
		author.setLastName("Hair Jr.");
		
		boolean rs = genericDAO.save(author);
		if (rs == true){
			System.out.println(AuthorTest.class.getName() + " added successfully.");
		}else{
			System.out.println("Error on adding a(n) " + AuthorTest.class.getName());
		}
	}
}

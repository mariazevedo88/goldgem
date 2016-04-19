package com.goldgem.test.dao;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.goldgem.dao.ConnectionDAO;
import com.goldgem.dao.GenericDAO;
import com.goldgem.test.Author;

public class AuthorDAO{

	@Test
	public void addAuthorUsingJDBC(){
		ConnectionDAO genericJDBCDao = DatabaseConnection.getGenericJDBCDAO();
		if (genericJDBCDao == null){
			DatabaseConnection dbConn = new DatabaseConnection();
			dbConn.connectDatabase();
			genericJDBCDao = DatabaseConnection.getGenericJDBCDAO();
		}
		
		try {
			Statement stmt = genericJDBCDao.getConnection().createStatement();
			String sql = "insert into author (id, first_name, last_name) values (2, 'Clifford', 'Stein')";
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
		GenericDAO<Author> genericDAO = new GenericDAO<Author>(Author.class, "hibernate.cfg.xml");
		
		Author author = new Author();
		author.setFirstName("Joseph");
		author.setLastName("Hair Jr.");
		
		boolean rs = genericDAO.save(author);
		if (rs == true){
			System.out.println(Author.class.getName() + " added successfully.");
		}else{
			System.out.println("Error on adding a(n) " + Author.class.getName());
		}
	}
}

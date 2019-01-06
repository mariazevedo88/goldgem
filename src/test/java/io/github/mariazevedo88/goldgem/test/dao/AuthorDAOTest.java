package io.github.mariazevedo88.goldgem.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.Test;

import io.github.mariazevedo88.goldgem.dao.ConnectionDAO;
import io.github.mariazevedo88.goldgem.dao.GenericDAO;
import io.github.mariazevedo88.goldgem.test.Author;

public class AuthorDAOTest{
	
	private static final Logger logger = Logger.getLogger(AuthorDAOTest.class);
	
	private int getSequenceNextVal(Connection conn) throws SQLException {
		
		String sequenceSql = "select * from hibernate_sequence";
		PreparedStatement ps = conn.prepareStatement(sequenceSql);
		ResultSet resultSet = ps.executeQuery();
		int nextID = 1;
		if(resultSet.next()) {
			nextID = resultSet.getInt(1);
		}
		
		return nextID;
	}

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
			
			//Get next available id
			int nextID = getSequenceNextVal(genericJDBCDao.getConnection());
			
			String sql = "insert into author (id, first_name, last_name) values (" + nextID + ", 'Clifford', 'Stein')";
			int rs = stmt.executeUpdate(sql);
			assertEquals(rs + " row have been affected.", 1, rs);
			
		} catch (SQLException e) {
			logger.error("Erro ao inserir novo autor.");
		}
	}
	
	@Test
	public void addAuthorUsingHibernate(){
		
		GenericDAO<Author> genericDAO = new GenericDAO<Author>(Author.class, "hibernate.cfg.xml");
		
		Author author = new Author();
		author.setFirstName("Joseph");
		author.setLastName("Hair Jr.");
		
		boolean rs = genericDAO.save(author);
		String msg = rs == true ? author.getFullName() + " added successfully." : "Already exist " + author.getFullName();
		assertTrue(msg, rs);
	}

}

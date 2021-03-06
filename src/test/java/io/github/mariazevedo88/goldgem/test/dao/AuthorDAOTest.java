package io.github.mariazevedo88.goldgem.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import io.github.mariazevedo88.goldgem.dao.ConnectionDAO;
import io.github.mariazevedo88.goldgem.dao.GenericDAO;
import io.github.mariazevedo88.goldgem.dto.GenericDTO;
import io.github.mariazevedo88.goldgem.test.Author;

@DisplayName("AuthorDAOTest")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class AuthorDAOTest{
	
	private static final Logger logger = Logger.getLogger(AuthorDAOTest.class);
	private GenericDAO<Author> genericDAO;
	private ConnectionDAO genericJDBCDao;
	
	@BeforeAll
	public void setUp() {
		genericDAO = new GenericDAO<Author>(Author.class, "hibernate.cfg.xml");
		
		genericJDBCDao = DatabaseConnectionTest.getGenericJDBCDAO();
		if (genericJDBCDao == null){
			DatabaseConnectionTest dbConn = new DatabaseConnectionTest();
			dbConn.connectDatabase();
			genericJDBCDao = DatabaseConnectionTest.getGenericJDBCDAO();
		}
	}

	@Test
	@DisplayName("Add an author using Hibernate")
	@Order(1)
	public void addAuthorUsingHibernate(){
		
		Author author = new Author();
		author.setFirstName("Joseph");
		author.setLastName("Hair Jr.");
		author.setCreatedAt(DateTime.now().toDate());
		
		boolean rs = genericDAO.save(author);
		String msg = rs == true ? author.getFullName() + " added successfully." : "Already exist " + author.getFullName();
		assertTrue(rs, msg);
	}
	
	@Test
	@DisplayName("Get an author using Hibernate")
	@Order(2)
	public void getAuthorUsingHibernate(){
		
		Author author = new Author();
		author.setId(1);
		
		GenericDTO authorToFound = genericDAO.getByID(author.getId());
		assertEquals(author.getId(), authorToFound.getId());
	}
	
	@Test
	@DisplayName("Delete an author using Hibernate")
	@Order(3)
	public void deleteAuthorUsingHibernate(){
		
		Author author = new Author();
		author.setId(1);
		
		GenericDTO authorToFound = genericDAO.getByID(author.getId());
		boolean isDeleted = genericDAO.delete(authorToFound);
		assertTrue(isDeleted);
	}
	
	@Test
	@DisplayName("Add an author using JDBC")
	@Order(4)
	public void addAuthorUsingJDBC() throws ParseException{
		
		try {
			Statement stmt = genericJDBCDao.getConnection().createStatement();
			
			//Get next available id
			int nextID = getSequenceNextVal(genericJDBCDao.getConnection());
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dateStr = formatter.parse(DateTime.now().toString());
			Date sqlDate = new Date(dateStr.getTime());
			
			String sql = "insert into author (id, first_name, last_name, created_at) values (" + nextID + ", 'Clifford', 'Stein', '" + sqlDate + "')";
			int rs = stmt.executeUpdate(sql);
			assertEquals(1, rs, rs + " row have been affected.");
			
			stmt.close();
			
		} catch (SQLException e) {
			logger.error("Error inserting new author." + e);
		}
	}
	
	private int getSequenceNextVal(Connection conn) throws SQLException {
		
		String sequenceSql = "select * from hibernate_sequence";
		PreparedStatement ps = conn.prepareStatement(sequenceSql);
		ResultSet resultSet = ps.executeQuery();
		int nextID = 1;
		if(resultSet.next()) {
			nextID = resultSet.getInt(1);
		}
		
		ps.close();
		
		return nextID;
	}
	
	@Test
	@DisplayName("Remove an author using JDBC")
	@Order(5)
	public void deleteAuthorUsingJDBC(){
		
		try {
			Statement stmt = genericJDBCDao.getConnection().createStatement();
			
			String sql = "delete from author where first_name like 'Clifford'";
			int rs = stmt.executeUpdate(sql);
			assertEquals(1, rs, rs + " row have been affected.");
			
			stmt.close();
			
		} catch (SQLException e) {
			logger.error("Error delete author." + e);
		}
	}
	
	@AfterAll
	public void tearDown() {
		try {
			Statement stmt = genericJDBCDao.getConnection().createStatement();
			
			String sql = "update hibernate_sequence SET next_val = 1";
			stmt.executeUpdate(sql);
			stmt.close();
			
		} catch (SQLException e) {
			logger.error("Error update hibernate_sequence." + e);
		}
	}

}

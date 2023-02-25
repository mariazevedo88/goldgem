package io.github.mariazevedo88.goldgem.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
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
	
	private static final Logger logger = LogManager.getLogger(AuthorDAOTest.class);
	private GenericDAO<Author> genericDAO;
	private ConnectionDAO genericJDBCDao;
	
	@BeforeAll
	public void setUp() {
		genericDAO = new GenericDAO<Author>(Author.class, "hibernate.cfg.xml");
		
		genericJDBCDao = new ConnectionDAO("root", "");
		genericJDBCDao.setDbms("h2");
		genericJDBCDao.setDbName("library");
		
		Connection conn = genericJDBCDao.createConnection();
		genericJDBCDao.setConnection(conn);
	}

	@Test
	@DisplayName("Add an author using Hibernate")
	@Order(1)
	public void addAuthorUsingHibernate(){
		
		Author author = new Author();
		author.setFirstName("Joseph");
		author.setLastName("Hair Jr.");
		author.setCreatedAt(Date.from(Instant.now()));
		
		boolean rs = genericDAO.save(author);
		String msg = rs == true ? author.getFullName() + " added successfully." : "Already exist " + author.getFullName();
		assertTrue(rs, msg);
	}
	
	@Test
	@DisplayName("Get an author using Hibernate")
	@Order(2)
	public void getAuthorUsingHibernate(){
		GenericDTO authorToFound = genericDAO.getByID(1);
		assertEquals(1, authorToFound.getId());
	}
	
	@Test
	@DisplayName("Delete an author using Hibernate")
	@Order(3)
	public void deleteAuthorUsingHibernate(){
		GenericDTO authorToFound = genericDAO.getByID(1);
		boolean isDeleted = genericDAO.delete(authorToFound);
		assertTrue(isDeleted);
	}
	
	@Test
	@DisplayName("Add an author using JDBC")
	@Order(4)
	public void addAuthorUsingJDBC() throws ParseException{
		
		try {
		    String sql = "insert into author (id, first_name, last_name, created_at) values (?,?,?,?)";
			PreparedStatement stmt = genericJDBCDao.getConnection().prepareStatement(sql);
			stmt.setInt(1, 1);
			stmt.setString(2, "Clifford");
			stmt.setString(3, "Stein");
			stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
			
			int rs = stmt.executeUpdate();
			assertEquals(1, rs, rs + " row have been affected.");
			
			stmt.close();
			
		} catch (SQLException e) {
			logger.error("Error inserting new author." + e);
		}
	}
	
	@Test
	@DisplayName("Remove an author using JDBC")
	@Order(5)
	public void deleteAuthorUsingJDBC(){
		
		try {
			PreparedStatement stmt = genericJDBCDao.getConnection().prepareStatement("delete from author where id=?");
			stmt.setInt(1, 1);
			int rs = stmt.executeUpdate();
			assertEquals(1, rs);
			
			stmt.close();
			
		} catch (SQLException e) {
			logger.error("Error delete author." + e);
		}
	}

}

package io.github.mariazevedo88.goldgem.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import org.junit.Before;
import org.junit.Test;

import io.github.mariazevedo88.goldgem.dao.ConnectionDAO;
import io.github.mariazevedo88.goldgem.dao.GenericDAO;
import io.github.mariazevedo88.goldgem.dto.GenericDTO;
import io.github.mariazevedo88.goldgem.test.Author;

public class AuthorDAOTest{
	
	private static final Logger logger = Logger.getLogger(AuthorDAOTest.class);
	private GenericDAO<Author> genericDAO;
	private ConnectionDAO genericJDBCDao;
	
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
	
	@Before
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
			assertEquals(rs + " row have been affected.", 1, rs);
			
		} catch (SQLException e) {
			logger.error("Error inserting new author." + e);
		}
	}
	
	@Test
	public void addAuthorUsingHibernate(){
		
		Author author = new Author();
		author.setFirstName("Joseph");
		author.setLastName("Hair Jr.");
		author.setCreatedAt(DateTime.now().toDate());
		
		boolean rs = genericDAO.save(author);
		String msg = rs == true ? author.getFullName() + " added successfully." : "Already exist " + author.getFullName();
		assertTrue(msg, rs);
	}
	
	@Test
	public void getAuthorUsingHibernate(){
		
		Author author = new Author();
		author.setId(1);
		
		GenericDTO authorToFound = genericDAO.getByID(author.getId());
		assertEquals(author.getId(), authorToFound.getId());
	}

}

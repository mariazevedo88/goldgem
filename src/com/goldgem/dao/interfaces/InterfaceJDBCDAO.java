/**
 * 
 */
package com.goldgem.dao.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.goldgem.dao.GenericJDBCDAO;

/**
 * Interface that implements DAO pattern using JDBC and SQL
 * @author Mariana Azevedo
 * @since 25/03/2016
 *
 */
public interface InterfaceJDBCDAO {
	
	public Connection createConnection() throws SQLException;

	public GenericJDBCDAO getByID(long id);
	
	public List<GenericJDBCDAO> getAll();
	
	public boolean save(GenericJDBCDAO entity);
	
	public boolean update(GenericJDBCDAO entity);
	
	public boolean delete(GenericJDBCDAO entity);
}

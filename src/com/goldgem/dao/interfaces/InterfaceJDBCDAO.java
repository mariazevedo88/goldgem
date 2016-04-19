/**
 * 
 */
package com.goldgem.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.goldgem.dto.GenericDTO;

/**
 * Interface that implements DAO pattern using JDBC and SQL
 * @author Mariana Azevedo
 * @since 25/03/2016
 *
 */
public interface InterfaceJDBCDAO {
	
	public GenericDTO getByID(String table, long id) throws SQLException;
	
	public List<GenericDTO> getAll(String table) throws SQLException;
}

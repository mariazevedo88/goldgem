package com.goldgem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.goldgem.dao.interfaces.InterfaceJDBCDAO;
import com.goldgem.dto.GenericDTO;

/**
 * Class<T> that implements an interface using the design pattern DAO (Data Access Object)
 * using JDBC and SQL.
 * @author Mariana Azevedo
 * @since 25/03/2016
 * @param <T>
 */
public class GenericJDBCDAO<T> implements InterfaceJDBCDAO {
	
	private ConnectionDAO connDao;
	private static final String SELECT_CLAUSE = "select * from ";
	private static final Logger logger = Logger.getLogger(GenericJDBCDAO.class);
	
	/**
	 * Constructor that receives as parameter a class and instantiates a JDBC session
	 * @author Mariana Azevedo
	 * @since 25/03/2016
	 * @param username
	 * @param password
	 */
	public GenericJDBCDAO(String username, String password) {
		super();
	    connDao = new ConnectionDAO(username, password);
	}
	
	public ConnectionDAO getConnectionDAO() {
		return connDao;
	}

	public void setConnectionDAO(ConnectionDAO connDao) {
		this.connDao = connDao;
	}

	@Override
	public List<GenericDTO> getAll(String table){
		
		String sql = SELECT_CLAUSE.concat(table);
		List<GenericDTO> rsList = new ArrayList<>();
		
		try(Statement stmt = getConnectionDAO().getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);){
			
			if(!rs.wasNull()){
				for (int i=0; i<rs.getMetaData().getColumnCount(); i++){
					rsList.add((GenericDTO) rs.getObject(i));
				}
			}
			
		}catch(SQLException e){
			logger.error(e);
		}
		
		return rsList;
	}

	@Override
	public GenericDTO getByID(String table, long id) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT_CLAUSE).append(table).append("where id=").append(id);
		
		GenericDTO genericDTO = null;
		
		try(Statement stmt = getConnectionDAO().getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());){
			
			if(!rs.wasNull()) genericDTO = (GenericDTO) rs.getObject(1);
			
		}catch(SQLException e){
			logger.error(e);
		}
		
		return genericDTO;
		
	}
}

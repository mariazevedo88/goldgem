package com.goldgem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public List<GenericDTO> getAll(String table) throws SQLException {
		
		Statement stmt = getConnectionDAO().getConnection().createStatement();
		String sql = "select * from " + table;
		ResultSet rs = stmt.executeQuery(sql);
		
		if( rs.wasNull()){
			return null;
		}else{
			List<GenericDTO> rsList = new ArrayList<GenericDTO>();
			for (int i=0; i<rs.getMetaData().getColumnCount(); i++){
				rsList.add((GenericDTO) rs.getObject(i));
			}
			return rsList;
		}
	}

	@Override
	public GenericDTO getByID(String table, long id) throws SQLException {
		
		Statement stmt = getConnectionDAO().getConnection().createStatement();
		String sql = "select * from " + table + "where id=" + id;
		ResultSet rs = stmt.executeQuery(sql);
		
		if( rs.wasNull()){
			return null;
		}else{
			return (GenericDTO) rs.getObject(0);
		}
	}
}

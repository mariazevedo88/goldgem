package io.github.mariazevedo88.goldgem.dao.interfaces;

import java.util.List;

import io.github.mariazevedo88.goldgem.dto.GenericDTO;

/**
 * Interface that implements DAO pattern using Hibernate
 * @author Mariana Azevedo
 * @since
 *
 */
public interface InterfaceDAO {

	public GenericDTO getByID(long id);
	
	public List<GenericDTO> getAll();
	
	public boolean save(GenericDTO entity);
	
	public boolean update(GenericDTO entity);
	
	public boolean delete(GenericDTO entity);
	
}

package io.github.mariazevedo88.goldgem.bo;

import java.util.List;

import io.github.mariazevedo88.goldgem.dto.GenericDTO;

/**
 * Class that implements an interface using the design pattern BO (Business Object).
 *  
 * @author Mariana de Azevedo Santos
 * @since 02/06/2013
 */

public interface GenericBO {

	/**
	 * Setter method for an entity
	 * @param entity
	 */
	public void setEntity(GenericDTO entity);
	
	/**
	 * Getter method for an entity 
	 * @return GenericDTO
	 */
	public GenericDTO getEntity();
	
	/**
	 * Method that return a generic DTO searched by its identification number (id)
	 * @param id
	 * @return GenericDTO
	 */
	public GenericDTO getByID(long id);
	
	/**
	 * Method that return a list of all entities on database
	 * @return GenericDTO
	 */
	public List<GenericDTO> getAll();
	
	/**
	 * Method that saves an entity on database
	 * @return boolean (true or false)
	 */
	public boolean save();
	
	/**
	 * Method that update an entity on database
	 * @return boolean boolean (true or false)
	 */
	public boolean update();
	
	/**
	 * Method that deletes an entity on database
	 * @return boolean (true or false)
	 */
	public boolean delete();
}

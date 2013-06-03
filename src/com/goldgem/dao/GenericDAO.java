package com.goldgem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import com.goldgem.dao.interfaces.InterfaceDAO;
import com.goldgem.dto.GenericDTO;

/**
 * Class<T> that implements an interface using the design pattern DAO (Data Access Object).
 * @author Mariana de Azevedo Santos
 *		   Contact: mariana@bsi.ufla.br
 * @param <T>
 */

public class GenericDAO<T> implements InterfaceDAO{

	private Session session;
	private Class<T> clazz;
	
	Configuration cfg = new Configuration();
	
	/**
	 * Constructor that receives as parameter a class and instantiates a hibernate session
	 * @param clazz
	 */
	public GenericDAO(Class<T> clazz){
		this.clazz = clazz;
		
        cfg.configure("hibernate.cfg.xml");
        ServiceRegistryBuilder serviceRegistryBuilder = 
        		new ServiceRegistryBuilder().applySettings(cfg.getProperties());
        SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
        session = sessionFactory.openSession();
	}
	
	/**
	 * Method that return a generic DTO searched by its identification number (id)
	 * @param id
	 * @return GenericDTO
	 */
	@Override
	public GenericDTO getByID(long id) {
		
		Transaction transaction = session.beginTransaction();
		
		GenericDTO genericEntity = (GenericDTO) this.session.load(clazz, id);
		
		transaction.commit();
		
		return genericEntity;
	}

	/**
	 * Method that return a list of all entities on database
	 * @return GenericDTO
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GenericDTO> getAll() {
		
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = this.session.createCriteria(clazz);

		transaction.commit();
		
		return criteria.list();
		
	}

	/**
	 * Method that saves an entity on database
	 * @return boolean (true or false)
	 */
	@Override
	public boolean save(GenericDTO entity) {
		
		try{
			
			Transaction transaction = session.beginTransaction();
			
			session.save(entity);
			
			transaction.commit();
			
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * Method that update an entity on database
	 * @return boolean boolean (true or false)
	 */
	@Override
	public boolean delete(GenericDTO entity) {
		
		try{
			Transaction transaction = session.beginTransaction();
			
			this.session.delete(entity);
			
			transaction.commit();
			
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Method that deletes an entity on database
	 * @return boolean (true or false)
	 */
	@Override
	public boolean update(GenericDTO entity) {
		
		try{
			Transaction transaction = session.beginTransaction();
			
			this.session.update(entity);
			
			transaction.commit();
		
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}

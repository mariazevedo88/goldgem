package io.github.mariazevedo88.goldgem.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import io.github.mariazevedo88.goldgem.dao.interfaces.InterfaceDAO;
import io.github.mariazevedo88.goldgem.dto.GenericDTO;

/**
 * Class<T> that implements an interface using the design pattern DAO (Data Access Object)
 * using Hibernate.
 * @author Mariana Azevedo
 * @since 
 * @param <T>
 */
public class GenericDAO<T> implements InterfaceDAO{

	private Class<T> clazz;
	private Session session;
	private Configuration cfg;
	private static final Logger logger = Logger.getLogger(GenericDAO.class);
	
	/**
	 * Constructor that receives as parameter a class and instantiates a hibernate session
	 * @author Mariana Azevedo
	 * @since 
	 * @param clazz
	 */
	public GenericDAO(Class<T> clazz, String hibernateConfPath){
		
		this.clazz = clazz;
		
		cfg = new Configuration();
        cfg.configure(hibernateConfPath);
        cfg.addAnnotatedClass(clazz);
		ServiceRegistry serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistryBuilder);
        session = sessionFactory.openSession();
	}
	
	public Configuration getHibernateConfig(){
		return cfg;
	}
	
	public Session getSession(){
		return session;
	}
	
	/**
	 * Method that return a generic DTO searched by its identification number (id)
	 * @author Mariana Azevedo
	 * @since
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
	 * @author Mariana Azevedo
	 * @since
	 * @param
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
	 * @author Mariana Azevedo
	 * @since 
	 * @param entity
	 * @return boolean (true or false)
	 */
	@Override
	public boolean save(GenericDTO entity) {
		
		try{
			Transaction transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
		}catch (Exception e){
			logger.error(e);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Method that update an entity on database
	 * @author Mariana Azevedo
	 * @since 
	 * @param entity
	 * @return boolean (true or false)
	 */
	@Override
	public boolean delete(GenericDTO entity) {
		
		try{
			Transaction transaction = session.beginTransaction();
			this.session.delete(entity);
			transaction.commit();
		}catch (Exception e){
			logger.error(e);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Method that deletes an entity on database
	 * @author Mariana Azevedo
	 * @since
	 * @param entity
	 * @return boolean (true or false)
	 */
	@Override
	public boolean update(GenericDTO entity) {
		
		try{
			Transaction transaction = session.beginTransaction();
			this.session.update(entity);
			transaction.commit();
		}catch (Exception e){
			logger.error(e);
			return false;
		}
		
		return true;
	}

}

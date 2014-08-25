package dao.jpa.core;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;

import model.contrato.Persistivel;
import model.impl.AbstractModel;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.exception.ConstraintViolationException;

import dao.contrato.IDao;

@SuppressWarnings("unchecked")
public abstract class AbstractDao<T extends Persistivel> implements IDao<T>{

	protected EntityManager entityManager;
	private Class<T> classeParametrizada;
	
	public AbstractDao(EntityManager entityManager){
		this.entityManager = entityManager;		
		
		//Usando Reflexão para obter a classe que foi parametrizada ao instanciar uma filha de AbstractDao
		//Isso é necessário porque eu não consigo fazer T.class
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();		 
		classeParametrizada = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
		
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void save(T obj) throws Exception{		
		try {
			obj.setDataCriacao(new Date());
			this.entityManager.persist(obj);
		}catch (PersistenceException e) {  
			//TODO Rever ...
			e.printStackTrace();
            Throwable t = e;  
            boolean cont = true;  
            while (t != null) {  
                if (t.getMessage().contains("Duplicate entry")) {  
                    cont = false;  
                    e.printStackTrace();
                    //throw new ValorDuplicadoException();
                }  
                t = t.getCause();  
            }  
            if (cont) {  
                throw new Exception(e.getMessage());  
            }  
        }catch (Exception e) {
        	e.printStackTrace();
		}
	
	}
	
	public T update(T obj) throws Exception{
		try {
			return this.entityManager.merge(obj);
		}catch (PersistenceException e) {  
            Throwable t = e;  
            boolean cont = true;  
            while (t != null) {  
                if (t.getMessage().contains("Duplicate entry")) {  
                    cont = false;  
                    //throw new ValorDuplicadoException();   
                }  
                t = t.getCause();  
            }  
            
            if (cont) {  
                throw new Exception(e.getMessage());  
            }  
            return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
				
	}	
	
    public void delete(T obj) throws ConstraintViolationException{
    	try {
    		this.entityManager.remove(obj);	
		}catch (PersistenceException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
    	    	
    }
    
    public T get(long id) {
    	return this.entityManager.find(classeParametrizada, id);
    }
	
    public List<T> getAll(){
    	CriteriaQuery<T> consulta = (CriteriaQuery<T>) entityManager.getCriteriaBuilder().createQuery();  
        consulta.select(consulta.from(classeParametrizada));  
        return entityManager.createQuery(consulta).getResultList(); 
    }
    
    //Chico 
    public Long count(){
    	Session session = (Session) entityManager.getDelegate();    	
    	Criteria criteria = session.createCriteria(classeParametrizada); 
    	criteria.setProjection(Projections.rowCount());
    	return ((Long)criteria.list().get(0)).longValue();    	
    }  
    
}
package dao.jpa.core;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DaoFactory {

	private EntityManager entityManager = null;

	public DaoFactory() {
		this.entityManager = PersistenceUtil.getEntityManager();
	}

	public void beginTransaction() {
		this.entityManager.getTransaction().begin();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void commitTransaction() {
		this.entityManager.getTransaction().commit();
	}

	public void rollbackTransaction() {
		this.entityManager.getTransaction().rollback();
	}

	public void close() {
		this.entityManager.close();
	}

}
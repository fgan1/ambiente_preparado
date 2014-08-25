package dao.jpa.core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

	private static EntityManagerFactory entityManagerfactory = null;

	private static final String CONF_ARQUIVO_NAME = "conf";
	private static final String PERSISTENCE_UNIT_NAME = "persistence_unit";

	// Esse bloco garante o funcionamento do Singleton
	static {
		String persistenceUnit = java.util.ResourceBundle.getBundle(
				CONF_ARQUIVO_NAME).getString(PERSISTENCE_UNIT_NAME);
		entityManagerfactory = Persistence
				.createEntityManagerFactory(persistenceUnit);
	}

	public static EntityManager getEntityManager() {
		return entityManagerfactory.createEntityManager();
	}
}

package be.panidel.dataLayer.helper;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.dao.MarshalHelper;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.helper.DAOConfig.EnvironmentType;
import be.panidel.dataLayer.helper.DAOConfig.PersistenceDbInit;
import be.panidel.dataLayer.helper.DAOConfig.PersistenceType;
import be.panidel.dataLayer.helper.DAOConfig.PersistenceUnit;

public class DataLayerHelper {

	private final static Logger LOG = LoggerFactory.getLogger(DataLayerHelper.class);

	private static PersistenceUnit CURRENT_PERSISTENCE_UNIT;
	private static Context envCtx;
	private static Server server;
	private static PersistenceType persistenceType;
	private static EntityManagerFactory EMF;

	static {
		try {

			try {

				Context initCtx = new InitialContext();
				envCtx = (Context) initCtx.lookup("java:comp/env");
				persistenceType = PersistenceType.DATA_SOURCE;
				LOG.info("Persistence type : " + persistenceType + "-" + envCtx);

			} catch (NameNotFoundException e) {

				persistenceType = PersistenceType.JDBC;
				File file = new File(DAOConfig.DB_CONN_DEFINITION);
				LOG.info("Persistence type : " + persistenceType);
				LOG.info("Db connection spec : " + file.getAbsolutePath());
				server = (Server) MarshalHelper.unmarchalXml(Server.class, file);
				LOG.info("" + server);

			}

		} catch (NamingException | JAXBException e) {
			LOG.error("", e);
		}
	}

	static DataSource getDatasource(String dsName) throws NamingException {

		DataSource ds = (DataSource) envCtx.lookup(dsName);

		return ds;

	}

	public static Resource getResource(String resourceName) throws JAXBException {

		for (Resource resource : server.getGlobalNamingResources().resources) {
			if (resource.getName().equals(resourceName)) {
				LOG.debug("Resource found : " + resource);
				return resource;
			}
		}
		LOG.error("Resource not found : " + resourceName);
		return null;
	}

	public static PersistenceUnit selectCurrentPersistenceUnit(PersistenceUnit persistenceUnit)
			throws DataLayerException {
		CURRENT_PERSISTENCE_UNIT = persistenceUnit;
		return CURRENT_PERSISTENCE_UNIT;
	}

	public static void closeEmf() {
		if (EMF == null) {
			LOG.warn("EMF is null");
		} else {
			DataLayerHelper.EMF.close();
		}
	}

	public static boolean checkLegalEnvironmentState() {
		return EnvironmentType.PROD.equals(CURRENT_PERSISTENCE_UNIT.getEnvironmentType())
				&& !PersistenceDbInit.VALIDATE.equals(CURRENT_PERSISTENCE_UNIT.getPersistenceDbInit());
	}

	public static EnvironmentType getEnvironmentType() {
		return CURRENT_PERSISTENCE_UNIT.getEnvironmentType();
	}

	public static EntityManager getNewEntityManager() throws DataLayerException {

		Properties props = DAOConfig.getProps(CURRENT_PERSISTENCE_UNIT, persistenceType);

		if (EMF == null) {

			String resourceName = null;
			switch (persistenceType) {
			case JDBC:
				resourceName = CURRENT_PERSISTENCE_UNIT.getPersistenceName();
				break;
			case DATA_SOURCE:
				resourceName = CURRENT_PERSISTENCE_UNIT.getPersistenceName() + "DS";
				break;

			default:
				throw new IllegalStateException("Illegal Persistence type : " + persistenceType);
			}

			LOG.info("Persistence : " + resourceName + "-" + persistenceType);

			EMF = Persistence.createEntityManagerFactory(resourceName, props);

			LOG.debug("EMF is created for persistence unit : " + CURRENT_PERSISTENCE_UNIT);

		}

		EntityManager em;

		try {

			em = EMF.createEntityManager(props);

		} catch (Exception e) {
			throw new DataLayerException(e);
		}
		return em;

	}

	public static void closeEntityManager(EntityManager em) {
		if (em != null) {
			em.close();
		} else {
			LOG.error("EntityManager is empty.");
		}
	}

	public static boolean createDbSchema() {

		if (CURRENT_PERSISTENCE_UNIT != null) {

			Properties prop = DAOConfig.getProps(CURRENT_PERSISTENCE_UNIT, persistenceType);

			if (PersistenceType.DATA_SOURCE.equals(persistenceType)) {
				throw new IllegalStateException("Illegal Persistence type : " + persistenceType);
			}

			String persistenceName = CURRENT_PERSISTENCE_UNIT.getPersistenceName();
			Persistence.generateSchema(persistenceName, prop);

			return true;
		}
		return false;

	}

	public static Connection getConn() throws JAXBException, SQLException {

		Resource resource = DataLayerHelper.getResource(CURRENT_PERSISTENCE_UNIT.getPersistenceName());

		Properties props = DAOConfig.getProps(CURRENT_PERSISTENCE_UNIT, persistenceType);

		return DriverManager.getConnection(resource.getUrl(), props);

	}

	public static String getDescription() {

		Properties props = DAOConfig.getProps(CURRENT_PERSISTENCE_UNIT, persistenceType);
		String message = "" + CURRENT_PERSISTENCE_UNIT.getPersistenceName();
		message += "\n";
		message += "" + CURRENT_PERSISTENCE_UNIT.getPersistenceDbInit();
		message += "\n";
		message += "" + props.getProperty("javax.persistence.jdbc.url");
		message += "\n";
		message += "" + props.getProperty("hibernate.hbm2ddl.auto");
		message += "\n";
		message += "" + props;

		return message;

	}

}

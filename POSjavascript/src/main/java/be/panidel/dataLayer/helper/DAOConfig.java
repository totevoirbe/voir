package be.panidel.dataLayer.helper;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOConfig {

	private final static Logger LOG = LoggerFactory.getLogger(DAOConfig.class);

	public static final PersistenceUnit DEFAULT_SERVER_PERSISTENCE_UNIT = PersistenceUnit.POS_RECETTE;

	public static final BigDecimal POS_MULTIPLIER = new BigDecimal(100.0);
	public static final Date FIRST_DATE = new GregorianCalendar(2000, 0, 0).getTime();
	public static final Date LAST_DATE = new GregorianCalendar(2050, 0, 0).getTime();

	public static final String DOC_BASE = "resultat/";
	public static final String RESOURCE_BASE = "src/main/resources/";
	public static final String DOC_DEST = "";

	public static final String POS10_PRODUCTS = "POSproducts.xlsx";
	public static final String POS10_PAYMENTMODE = "payementmode.xml";
	public static final String POS10_VATRATE = "vatrate.xml";
	public final static String POS10_CASHSALES_REPOSITORY = "/home/tote/Desktop/Pos10CaisseGroup";

	public final static String DB_CONN_DEFINITION = "/home/tote/Desktop/jee-workspace/Servers/Tomcat v9.0 Server at localhost-config/server.xml";

	public final static String MYSQL_PROCESS_LIST_QUERY = "select host as HOST_NAME,count(host) as HOST_COUNT from information_schema.processlist group by host";

	public static final File CATALOG_FILE = new File(DAOConfig.DOC_BASE + "products.xml");

	public enum PersistenceType {
		DATA_SOURCE, JDBC
	};

	public enum PersistenceDbInit {
		CREATE, UPDATE, VALIDATE, CREATE_DROP
	};

	public enum EnvironmentType {
		DEV, RECETTE, PROD
	};

	public static Properties getProps(PersistenceUnit persistenceUnit, PersistenceType persistenceType) {

		Properties props = new Properties();

		try {

			props.setProperty("javax.persistence.jdbc.serverTimezone", "UTC");
			props.setProperty("javax.persistence.jdbc.createDatabaseIfNotExist", "true");
			props.setProperty("javax.persistence.jdbc.autoReconnect", "true");
			props.setProperty("javax.persistence.jdbc.useSSL", "false");
			props.setProperty("javax.persistence.jdbc.create", "false");

			props.setProperty("serverTimezone", "UTC");
			props.setProperty("createDatabaseIfNotExist", "true");
			props.setProperty("autoReconnect", "true");
			props.setProperty("useSSL", "false");
			props.setProperty("create", "false");

			// "hibernate.hbm2ddl.auto"
			// create :- create the schema, the data previously present (if there) in the
			// schema is lost
			// update:- update the schema with the given values.
			// validate:- validate the schema. It makes no change in the DB.
			// create-drop:- create the schema with destroying the data previously
			// present(if there). It also drop the database schema when the SessionFactory
			// is closed.
			props.setProperty("hibernate.hbm2ddl.auto", "validate");
			props.setProperty("hibernate.show_sql", "false");
			props.setProperty("hibernate.connection.autocommit", "false");
			props.setProperty("hibernate.enable_lazy_load_no_trans", "true");
			// props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

			if (PersistenceType.JDBC.equals(persistenceType)) {

				Resource resource = DataLayerHelper.getResource("jdbc/" + persistenceUnit.getPersistenceName());

				String URL = resource.getUrl();
				if (EnvironmentType.DEV.equals(persistenceUnit.getEnvironmentType())) {
					if (PersistenceDbInit.CREATE.equals(persistenceUnit.getPersistenceDbInit())
							|| PersistenceDbInit.CREATE_DROP.equals(persistenceUnit.getPersistenceDbInit())) {
						URL += ";create=true";
					} else {
						URL += ";create=false";
					}
				}

				props.setProperty("javax.persistence.jdbc.name", resource.getName());
				props.setProperty("javax.persistence.jdbc.auth", resource.getAuth());
				props.setProperty("javax.persistence.jdbc.driverClassName", resource.getDriverClassName());
				props.setProperty("javax.persistence.jdbc.maxIdle", resource.getMaxIdle());
				props.setProperty("javax.persistence.jdbc.maxTotal", resource.getMaxTotal());
				props.setProperty("javax.persistence.jdbc.maxWaitMillis", resource.getMaxWaitMillis());
				props.setProperty("javax.persistence.jdbc.password", resource.getPassword());
				props.setProperty("javax.persistence.jdbc.type", resource.getType());
				props.setProperty("javax.persistence.jdbc.url", URL);
				props.setProperty("javax.persistence.jdbc.username", resource.getUsername());
				props.setProperty("javax.persistence.jdbc.user", resource.getUsername());

				props.setProperty("name", resource.getName());
				props.setProperty("auth", resource.getAuth());
				props.setProperty("driverClassName", resource.getDriverClassName());
				props.setProperty("maxIdle", resource.getMaxIdle());
				props.setProperty("maxTotal", resource.getMaxTotal());
				props.setProperty("maxWaitMillis", resource.getMaxWaitMillis());
				props.setProperty("password", resource.getPassword());
				props.setProperty("type", resource.getType());
				props.setProperty("url", URL);
				props.setProperty("username", resource.getUsername());
				props.setProperty("user", resource.getUsername());

			}

			switch (persistenceUnit.getPersistenceDbInit()) {
			case CREATE:
				props.setProperty("hibernate.hbm2ddl.auto", "create");
				props.setProperty("javax.persistence.jdbc.create", "true");
				props.setProperty("create", "true");
				break;
			case UPDATE:
				props.setProperty("hibernate.hbm2ddl.auto", "update");
				props.setProperty("javax.persistence.jdbc.create", "false");
				props.setProperty("create", "false");
				break;
			case VALIDATE:
				props.setProperty("hibernate.hbm2ddl.auto", "validate");
				props.setProperty("javax.persistence.jdbc.create", "false");
				props.setProperty("create", "false");
				break;
			case CREATE_DROP:
				props.setProperty("hibernate.hbm2ddl.auto", "create_drop");
				props.setProperty("javax.persistence.jdbc.create", "true");
				props.setProperty("create", "true");
				break;
			}

		} catch (Exception e) {
			LOG.error("", e);
		}

		return props;
	}

	public enum PersistenceUnit {

		POS_EMBEDDED("posEmbedded", PersistenceDbInit.VALIDATE, EnvironmentType.DEV),

		POS_EMBEDDED_CREATE("createPosEmbedded", PersistenceDbInit.CREATE, EnvironmentType.DEV),

		POS_RECETTE("posRecette", PersistenceDbInit.VALIDATE, EnvironmentType.RECETTE),

		POS_RECETTE_CREATE("createPosRecette", PersistenceDbInit.CREATE, EnvironmentType.RECETTE),

		POS_PROD("posProd", PersistenceDbInit.VALIDATE, EnvironmentType.PROD),

		POS_PROD_CREATE("createPosProd", PersistenceDbInit.CREATE, EnvironmentType.PROD);

		private String persistenceName;
		private PersistenceDbInit persistenceDbInit;
		private EnvironmentType environmentType;

		private PersistenceUnit(String persistenceName, PersistenceDbInit persistenceDbInit,
				EnvironmentType environmentType) {

			this.persistenceName = persistenceName;
			this.persistenceDbInit = persistenceDbInit;
			this.environmentType = environmentType;

		}

		public String getPersistenceName() {

			if (EnvironmentType.PROD.equals(environmentType) && !PersistenceDbInit.VALIDATE.equals(persistenceDbInit)) {
				LOG.warn("Creating of update db is dangerous");
			}

			return persistenceName;
		}

		public PersistenceDbInit getPersistenceDbInit() {
			return persistenceDbInit;
		}

		public EnvironmentType getEnvironmentType() {
			return environmentType;
		}

	};

}

package be.panidel.frontLayer.restService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.helper.DAOConfig;
import be.panidel.dataLayer.helper.DataLayerHelper;

public class PosServletContextListener implements ServletContextListener {

	private final static Logger LOG = LoggerFactory.getLogger(PosServletContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent e) {
		LOG.info("====================INIT coontext SERVLET");

		try {

			DataLayerHelper.selectCurrentPersistenceUnit(DAOConfig.DEFAULT_SERVER_PERSISTENCE_UNIT);

			if (DataLayerHelper.checkLegalEnvironmentState()) {
				throw new IllegalStateException("Creating or updating DB in PROD is not allowed");
			}

			long numberOfProducts = DataFacade.instance.countProducts();
			LOG.info("numberOfProducts : " + numberOfProducts);

		} catch (Throwable e1) {
			LOG.error("", e1);
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		LOG.debug("====================DESTROY coontext SERVLET");
	}
}
package be.panidel.exe;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import be.panidel.frontLayer.restService.CorsFilter;
import be.panidel.frontLayer.restService.PosServletContextListener;
import be.panidel.frontLayer.restService.RestPosService;

public class RestServer {

	public static void main(String[] args) throws Exception {

		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.setContextPath("/");

		servletContextHandler.addEventListener(new PosServletContextListener());

		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setDirectoriesListed(true);
		resource_handler.setWelcomeFiles(new String[] { "gui.html" });
		resource_handler.setResourceBase(".");

		FilterHolder filterHolder = servletContextHandler.addFilter(CorsFilter.class, "/*",
				EnumSet.of(DispatcherType.REQUEST));
		filterHolder.setInitParameter("allowedOrigins", "*");

		Server jettyServer = new Server(8080);

		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { resource_handler, servletContextHandler, new DefaultHandler() });

		jettyServer.setHandler(servletContextHandler);
		jettyServer.setHandler(servletContextHandler);

		ServletHolder jerseyServlet = servletContextHandler
				.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/POSjavascript/rest/*");
		jerseyServlet.setInitOrder(0);

		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
				RestPosService.class.getCanonicalName());

		try {
			jettyServer.start();
			jettyServer.join();
		} finally {
			jettyServer.destroy();
		}
	}
}
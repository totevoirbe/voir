package be.panidel.frontLayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Check extends HttpServlet {

	private final static Logger LOG = LoggerFactory.getLogger(Check.class);

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LOG.debug("Check is active");

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\" />");
		out.println("<title>Test</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>POS is active on " + SimpleDateFormat.getInstance().format(new Date()) + ".</p>");
		out.println("</body>");
		out.println("</html>");
	}
}
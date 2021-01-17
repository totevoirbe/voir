package be.panidel.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import be.panidel.common.POSConstants;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.OperationUnit;
import be.panidel.tools.DOMUtils;

public class SalesDAO {

	private static final Logger log = Logger.getLogger("SalesDAO");

	public final static String SALES_EMPLOYEE = "personnel";
	public final static String SALES_TIME = "date";
	public final static String SALES_COMPANY = "company";
	public final static String SALES_COMPUTERNAME = "computername";
	public final static String SALES_ATTRIBUTE_SOURCE = "source";

	public final static String DOCUMENT_CANCELLED = "cancelled";

	public final static String ROOT = "root";

	SalesDAO() {
		super();
	}

	public Document readSales(File[] files)
			throws ParserConfigurationException, SAXException, IOException {

		Document sales = DOMUtils.getDocumentBuilder().newDocument();
		Node salesRoot = sales.createElement(ROOT);
		sales.appendChild(salesRoot);

		for (int i = 0; i < files.length; i++) {

			File file = files[i];
			try {

				log.debug("Redind file : " + file.getName() + " ...");

				Document document = DOMUtils.getDocumentBuilder().parse(file);
				Node root = document.getDocumentElement();
				Node salesNode = sales.importNode(root, true);
				salesRoot.appendChild(salesNode);
				log.debug(file.getName() + " loaded");
			} catch (DOMException e) {
				log.error("readSales", e);
			} catch (SAXException e) {
				log.error("readSales", e);
			} catch (IOException e) {
				log.error("readSales", e);
			} catch (ParserConfigurationException e) {
				log.error("readSales", e);
			}
		}
		return sales;
	}

	public void writeOperationUnit(OperationUnit operationUnit, File repository)
			throws DAOException {

		StringBuffer xml = new StringBuffer();

		FacadeDAO.instance().getOperationUnitDAO()
				.appendXml(operationUnit, xml);

		String fileName = POSConstants.SDF_FOR_FILE_SALES.format(new Date())
				+ ".xml";

		File file = new File(repository, fileName);

		writeSalesToFile(xml.toString(), file);

	}

	public File writeSalesToFile(String content, File file) throws DAOException {

		log.debug("START writeFile -----------------------------------");

		content = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + content;
		Writer out = null;
		try {
			log.debug("Write sales : " + file.getAbsolutePath());
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF8"));
			out.write(content);
		} catch (IOException e) {
			throw new DAOException(file.getAbsolutePath(), e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				throw new DAOException(file.getAbsolutePath(), e);
			}
		}
		log.debug("END writeFile -----------------------------------");
		return file;
	}
}
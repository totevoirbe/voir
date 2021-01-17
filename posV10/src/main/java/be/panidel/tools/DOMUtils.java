package be.panidel.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import be.panidel.dao.DAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.exception.ParameterException;

/**
 * @author franzph
 * 
 */
public class DOMUtils {

	private static final Logger log = Logger.getLogger(DOMUtils.class);

	private static DocumentBuilder db;

	public static NodeList getDocumentsAsList(File file, String tagDocument)
			throws DAOException {
		NodeList nodelist = null;
		DocumentBuilder db = null;
		InputStream is = null;
		try {
			nodelist = null;
			if (file != null && file.isFile() && file.canRead()) {
				is = new FileInputStream(file);
				db = DOMUtils.getDocumentBuilder();
				Document document = db.parse(is);
				// String xpath = "//" + tagDocument;
				// nodelist = XPathAPI.selectNodeList(document, xpath);

				XPathFactory factory = XPathFactory.newInstance();
				XPath xpath = factory.newXPath();
				XPathExpression expr = xpath.compile("//" + tagDocument);
				nodelist = (NodeList) expr.evaluate(document,
						XPathConstants.NODESET);
			} else {
				if (file == null) {
					throw new DAOException("File is null");
				} else {
					throw new DAOException("File : " + file.getAbsolutePath()
							+ " Error : is file?[" + file.isFile()
							+ "]/can readf?[" + file.isFile());
				}
			}
		} catch (DAOException e) {
			exceptionSpc(file, is, e);
		} catch (SAXException e) {
			exceptionSpc(file, is, e);
		} catch (IOException e) {
			exceptionSpc(file, is, e);
		} catch (ParserConfigurationException e) {
			exceptionSpc(file, is, e);
		} catch (XPathExpressionException e) {
			exceptionSpc(file, is, e);
		}

		return nodelist;
	}

	private static void exceptionSpc(File file, InputStream is, Exception e)
			throws DAOException {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e1) {
				throw new DAOException((file != null ? file.getAbsolutePath()
						: "file is null"), e);
			}
		}
		throw new DAOException((file != null ? file.getAbsolutePath()
				: "file is null"), e);
	}

	public static void XMLFileWriter(Document document, String fileName)
			throws TransformerException {

		log.debug("Write file " + fileName);

		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer transformer = tfactory.newTransformer();

		Source input = new DOMSource(document);
		Result output = new StreamResult(new File(fileName));
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(input, output);
		log.debug("File written");

	}

	public static String XMLToStringWriter(Node node)
			throws TransformerException, ParserConfigurationException,
			SAXException, IOException {
		Document document = db.newDocument();
		Node cloneNode = node.cloneNode(true);
		Node documentNode = document.adoptNode(cloneNode);
		document.appendChild(documentNode);
		return XMLToStringWriter(document);
	}

	public static String XMLToStringWriter(NodeList nodeList)
			throws TransformerException, ParserConfigurationException,
			SAXException, IOException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < nodeList.getLength(); i++) {
			sb.append(XMLToStringWriter(nodeList.item(i)));
			sb.append("\r");
		}
		return sb.toString();
	}

	public static String XMLToStringWriter(Document document)
			throws TransformerException, UnsupportedEncodingException {

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		XMLWriter(document, os);
		return os.toString("UTF-8");
	}

	public static void XMLWriter(Document document, OutputStream os)
			throws TransformerException, UnsupportedEncodingException {

		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer transformer = tfactory.newTransformer();

		Source input = new DOMSource(document);
		Result output = new StreamResult(os);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(input, output);
	}

	public static void HTMLWriter(Document document, OutputStream os)
			throws TransformerException, UnsupportedEncodingException {

		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer transformer = tfactory.newTransformer();

		Source input = new DOMSource(document);
		Result output = new StreamResult(os);
		transformer.setOutputProperty(OutputKeys.METHOD, "html");
		transformer.transform(input, output);
	}

	public static Document xmlFileReader(String fileName)
			throws ParserConfigurationException, SAXException, IOException {

		File file = new File(fileName);
		Document document = getDocumentBuilder().parse(file);

		// InputStream is = DOMUtils.class.getResourceAsStream(fileName);
		// Document document = getDocumentBuilder().parse(is);

		return document;
	}

	public static DocumentBuilder getDocumentBuilder()
			throws ParserConfigurationException, SAXException, IOException {
		if (db == null) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
		}

		return db;
	}

	public static String[] listOfId(Element node, String nodeName) {

		try {
			log.debug("Main table : " + DOMUtils.XMLToStringWriter(node));
		} catch (Exception e) {
			log.error("Main table error", e);
		}

		NodeList elements = node.getElementsByTagName(nodeName);

		try {
			log.debug("Elements : " + DOMUtils.XMLToStringWriter(elements));
		} catch (Exception e) {
			log.error("Main table error", e);
		}

		int rows = elements.getLength();

		String[] docArray = new String[rows];
		for (int row = 0; row < rows; row++) {
			Element element = (Element) elements.item(row);
			docArray[row] = element.getAttribute("id");
		}
		return docArray;
	}

	public static Element clearTable(String nodeName, XMLSource xmlSource)
			throws ParserConfigurationException, SAXException, IOException {

		Element node = xmlSource.getData(nodeName);

		// clear node elements
		do {
			Node child = node.getFirstChild();
			node.removeChild(child);
		} while (node.hasChildNodes());

		return node;
	}

	public static List<String> getElementNames(Node firstElement) {
		NodeList elements = firstElement.getChildNodes();
		List<String> elementNames = new ArrayList<String>();
		for (int i = 0; i < elements.getLength(); i++) {
			if (elements.item(i) instanceof Element) {
				elementNames.add(elements.item(i).getNodeName());
			}
		}
		return elementNames;
	}

	public static Node addNodeWithValue(Element elementNode, String tagName,
			String value) {
		Document document = elementNode.getOwnerDocument();
		return elementNode.appendChild(newNode(document, tagName, value));
	}

	public static Element addEmptyNode(Element elementNode, String tagName) {
		Document document = elementNode.getOwnerDocument();
		return (Element) elementNode.appendChild(newNode(document, tagName,
				null));
	}

	public static void replaceElement(Element content, Element table) {
		NodeList nl = table.getChildNodes();
		String id = DOMUtils.getElementId(content);
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i) instanceof Element) {
				Element node = (Element) nl.item(i);
				if (id.equals(node.getAttribute(DAO.ID))) {
					table.replaceChild(content, node);
				}
			}
		}
	}

	public static List<Element> getAsList(Element element) {
		NodeList nl = element.getChildNodes();
		List<Element> retList = new ArrayList<Element>();
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i) instanceof Element) {
				Element node = (Element) nl.item(i);
				retList.add(node);
			}
		}
		return retList;
	}

	public static Element getElementById(String id, Element element) {
		if (id == null) {
			return null;
		}
		NodeList nl = element.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i) instanceof Element) {
				Element node = (Element) nl.item(i);
				if (id.equals(node.getAttribute(DAO.ID))) {
					return node;
				}
			}
		}
		return null;
	}

	public static Element getElementByCode(String code, Element element) {
		NodeList nl = element.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i) instanceof Element) {
				Element node = (Element) nl.item(i);
				NodeList codeNodes = node.getElementsByTagName(DAO.CODE);
				if (codeNodes != null && codeNodes.getLength() > 0) {
					Element codeElement = (Element) codeNodes.item(0);
					Text value = (Text) codeElement.getFirstChild();
					if (code.equals(value.getTextContent())) {
						return node;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Set text value of the element
	 * 
	 * @param element
	 *            base element
	 * @param value
	 *            value to set
	 */
	public static void setElementValue(Element element, String value) {
		if (element != null) {
			if (value == null) {
				value = new String();
			}
			element.setTextContent(value);
		} else {
			log.error("Element is null : " + value);
		}
	}

	public static String getElementText(Node element) {
		if (element != null) {
			Text content = (Text) element.getFirstChild();
			if (content != null) {
				return content.getNodeValue();
			}
		}
		return new String();
	}

	public static String getElementValueByTagName(String tagName,
			Element element) {
		return getElementText(getElementByTagName(tagName, element).item(0));
	}

	public static BigDecimal getElementBigDecimalByTagName(String tagName,
			Element element) throws ParameterException {
		BigDecimal value = Tools
				.toBigDecimalNotNullDivide(getElementValueByTagName(tagName,
						element));
		return value;
	}

	public static Boolean getElementBooleanByTagName(String tagName,
			Element element) throws ParameterException {
		Boolean value = Tools.toBoolean(getElementValueByTagName(tagName,
				element));
		return value;
	}

	public static NodeList getElementByTagName(String tagName, Element element) {
		return element.getElementsByTagName(tagName);
	}

	/**
	 * @param element
	 * @return
	 */
	public static String getElementId(Element element) {
		if (element != null) {
			return element.getAttribute(DAO.ID);
		}
		return null;
	}

	/**
	 * @param document
	 * @param tagName
	 * @param data
	 * @return
	 */
	private static Node newNode(Document document, String tagName, String data) {

		if (Tools.isNullOrEmpty(tagName)) {
			throw new IllegalArgumentException("Tag name is null or empty");
		}

		Node child = document.createElement(tagName);

		if (data == null) {
			data = new String();
		}
		Node textNode = document.createTextNode(data);
		child.appendChild(textNode);

		return child;
	}

	// public static Element newNode(Element element, String tagName){
	//
	// if (Tools.isNullOrEmpty(tagName)) {
	// throw new IllegalArgumentException("Tag name is null or empty");
	// }
	//
	// Document document = element.getOwnerDocument();
	// Element node = document.createElement(tagName);
	//
	// return node;
	// }
}

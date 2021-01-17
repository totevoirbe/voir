package be.panidel.tools;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLSource {

	private static final Logger log = Logger.getLogger(XMLSource.class);

	private Document xmlData;

	public XMLSource(String sourceName) throws ParserConfigurationException,
			SAXException, IOException {

		xmlData = DOMUtils.xmlFileReader(sourceName);

	}

	/**
	 * Write to file
	 * 
	 * @throws TransformerException
	 */
	public void save(String fileName) throws TransformerException {
		// URL url = DOMUtils.class.getResource(sourceName);
		// String fileName = url.getFile().substring(1);
		log.debug("Write on file : " + fileName);
		DOMUtils.XMLFileWriter(xmlData, fileName);
	}

	public Element getData(String name) {
		Element root = xmlData.getDocumentElement();
		NodeList nodeList = root.getElementsByTagName(name);
		return (Element) nodeList.item(0);
	}

	public void updateData(String name, Element element) {
		Element root = xmlData.getDocumentElement();
		NodeList nodeList = root.getElementsByTagName(name);
		Element domElement = (Element) nodeList.item(0);
		root.replaceChild(domElement, element);
	}

	public Document getXMLData() {
		return xmlData;
	}
}

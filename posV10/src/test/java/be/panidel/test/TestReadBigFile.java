package be.panidel.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

class ReadBigFileContentHandler implements ContentHandler {

//	private final String enrichement =
//		"<initial>PFR</initial>" +
//		"<name>Philippe Franzen</name>" + 
//		"<rue>rue pas loin</rue>" +
//		"<numero>numéro 14</numero>";
	private BufferedWriter out;
	private BufferedWriter bufferedWriters[];
	private Integer counter;
	private String tagName;
	private Integer channel;
	
	public ReadBigFileContentHandler(BufferedWriter out,
			BufferedWriter[] bufferedWriters, Integer counter) {
		super();
		this.out = out;
		this.bufferedWriters = bufferedWriters;
		this.counter = counter;
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// System.err.println("startPrefixMapping;"+prefix+";"+uri);
	}

	@Override
	public void startElement(String uri, String localName,
			String qName, Attributes atts) throws SAXException {
		// System.err.println("startElement;"+uri+";"+localName+";"+qName+";"+atts);
		tagName = qName;
	}

	@Override
	public void startDocument() throws SAXException {
		// System.err.println("startDocument");
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// System.err.println("skippedEntity;"+name);
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// System.err.println("setDocumentLocator;"+locator);
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// System.err.println("processingInstruction;"+target+";"+data);
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start,
			int length) throws SAXException {
		// System.err.println("ignorableWhitespace;"+ch+";"+start+";"+length);
	}

	@Override
	public void endPrefixMapping(String prefix)
			throws SAXException {
		// System.err.println("endPrefixMapping;"+prefix);
	}

	@Override
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		// System.err.println("endElement;"+uri+";"+localName+";"+qName);
		String text = "</" +tagName + ">";
		if (channel != null) {
			try {
				out.write(text);
				bufferedWriters[channel].write(text);
				counter++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		channel = null;
	}

	@Override
	public void endDocument() throws SAXException {
		// System.err.println("endDocument");
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
//		System.err.println("characters;"+new String(ch, start, length)+";"+start+";"+length);
//		System.err.println(new String(ch, start, length));

		if ("channel".equals(tagName)) {
			try {
				channel = new Integer(tagName);
			} catch (NumberFormatException e) {
			} 
		} else if (length>0 && channel!= null) {
			String text = "<" +tagName + ">" + new String(ch, start, length);
			try {
				out.write(text);
				bufferedWriters[channel].write(text);
				counter++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

public class TestReadBigFile {

	private static final String SCE_FILE_NAME = "C:/Users/franzph/Desktop/testBigFile/BigSceFileMsg.tmp";
	private static final String DEST_FILE_NAME = "C:/Users/franzph/Desktop/testBigFile/BigDestFileMsg";
	private static final String XML_SCE_FILE_NAME = "C:/Users/franzph/Desktop/testBigFile/BigSceFileMsg.xml";
	private static final String XML_DEST_FILE_NAME = "C:/Users/franzph/Desktop/testBigFile/BigDestFileMsg";

	private static final int ROWS = 10;
	private static final int CHANNELS = 10;

	public static void main(String[] args) {
		// createTempFile();
		// readTempFile();
		TestReadBigFile trb = new TestReadBigFile();
		trb.createXMLTempFile();
		trb.streamXMLFile();
	}

	/**
	 * Création d'un fichier de données
	 */
	public void createTempFile() {
		System.out.println("Create File " + new Date());
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		BufferedWriter out = null;
		try {
			File bigFile = new File(SCE_FILE_NAME);
			bigFile.createNewFile();
			// if(!bigFile.isFile()) {
			FileWriter fileWriter = new FileWriter(bigFile);
			out = new BufferedWriter(fileWriter);
			for (int i = 0; i < ROWS; i++) {
				String lineContent = df.format(new Date()) + ".xml,"
						+ Math.round(Math.random() * CHANNELS);
				for (int j = 0; j < 10; j++) {
					lineContent = lineContent + "This is option " + j + ",";
				}
				out.write(lineContent + "\n");
			}

			// }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
					System.out.println("File created " + new Date());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Création d'un fichier de données
	 */
	public void createXMLTempFile() {
		System.out.println("Create File " + new Date());
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		BufferedWriter out = null;
		try {
			File bigFile = new File(XML_SCE_FILE_NAME);
			bigFile.createNewFile();
			// if(!bigFile.isFile()) {
			FileWriter fileWriter = new FileWriter(bigFile);
			out = new BufferedWriter(fileWriter);
			out.write("<document>\n");
			for (int i = 0; i < ROWS; i++) {
				out.write("   <line>\n");
				String lineContent = "      <date>" + df.format(new Date())
						+ ".xml" + "</date>\n" + "      <channel>"
						+ Math.round(Math.random() * CHANNELS) + "</channel>\n";
				for (int j = 0; j < 10; j++) {
					lineContent = lineContent + "      <option>"
							+ "This is option " + j + "</option>\n";
				}
				out.write(lineContent + "\n");
				out.write("   </line>\n");
			}
			out.write("</document>\n");
			// }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				if (out != null) {
					try {
						out.close();
						System.out.println("File created " + new Date());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void streamXMLFile() {

		boolean byReader = true; /* true = byReader, false = by streamer */

		System.out.println("-> START process " + new Date());
		int rowsReaded = 0;
		BufferedReader in = null;
		BufferedWriter out = null;
		BufferedWriter bufferedTabWriters = null;
		BufferedWriter bufferedWriters[] = new BufferedWriter[CHANNELS + 1];
		try {
			System.out.println("Start streaming XML file");
			File sceBigFile = new File(XML_SCE_FILE_NAME);

			File destBigFile = new File(XML_DEST_FILE_NAME + ".xml");
			destBigFile.createNewFile();
			for (int i = 0; i <= CHANNELS; i++) {
				File tabFile = new File(XML_DEST_FILE_NAME + i + ".xml");
				tabFile.createNewFile();
				FileWriter fileWriter = new FileWriter(tabFile);
				 bufferedTabWriters = new BufferedWriter(
						fileWriter);
				bufferedWriters[i] = bufferedTabWriters;
			}
			FileWriter fileWriter = new FileWriter(destBigFile);
			out = new BufferedWriter(fileWriter);
			if (sceBigFile.isFile()) {

				Integer counter = new Integer(0);
				FileReader fileReader = new FileReader(sceBigFile);
				in = new BufferedReader(fileReader);
				XMLReader xmlReader = XMLReaderFactory.createXMLReader();
				System.out.println("SetHandler");

				xmlReader.setContentHandler(new ReadBigFileContentHandler(out, bufferedWriters, counter));

				if (byReader) {
					xmlReader.parse(new InputSource(fileReader));
				} else {
					xmlReader.parse(new InputSource(in));
				}

				System.out.println("End streaming XML file, lines : " + counter);
			}

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
					for (int i = 0; i <= CHANNELS; i++) {
						bufferedWriters[i].close();
					}
					out.close();
					System.out.println("-> END Process " + new Date()
							+ " rows : " + rowsReaded);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(bufferedTabWriters!=null){
				try {
					bufferedTabWriters.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
		}
	}

	/**
	 * 
	 */
	public void readTempFile() {
		System.out.println("-> START process " + new Date());
		int rowsReaded = 0;
		BufferedReader in = null;
		BufferedWriter out = null;
		BufferedWriter bufferedTabWriters = null;
		BufferedWriter bufferedWriters[] = new BufferedWriter[CHANNELS + 1];
		try {
			File sceBigFile = new File(SCE_FILE_NAME);
			File destBigFile = new File(DEST_FILE_NAME + ".tmp");
			destBigFile.createNewFile();
			for (int i = 0; i <= CHANNELS; i++) {
				File tabFile = new File(DEST_FILE_NAME + i + ".tmp");
				tabFile.createNewFile();
				FileWriter fileWriter = new FileWriter(tabFile);
				bufferedTabWriters = new BufferedWriter(
						fileWriter);
				bufferedWriters[i] = bufferedTabWriters;
			}
			FileWriter fileWriter = new FileWriter(destBigFile);
			out = new BufferedWriter(fileWriter);
			if (sceBigFile.isFile()) {
				FileReader fileReader = new FileReader(sceBigFile);
				in = new BufferedReader(fileReader);
				String test = null;
				do {
					test = in.readLine();
					out.write(test + "PFR" + "Philippe Franzen"
							+ "rue pas loin" + "numéro 14" + "\n");
					if (test != null) {
						String splitTest[] = test.split(",");
						if (splitTest.length > 2) {
							int index = new Integer(splitTest[1]);
							bufferedWriters[index].write(test + "PFR"
									+ "Philippe Franzen" + "rue pas loin"
									+ "numéro 14" + "\n");
						}
					}
					rowsReaded++;
				} while (test != null);
			} else {
				System.out.println("This is not a file " + new Date());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
					for (int i = 0; i <= CHANNELS; i++) {
						bufferedWriters[i].close();
					}
					out.close();
					System.out.println("-> END Process " + new Date()
							+ " rows : " + rowsReaded);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(bufferedTabWriters!=null){
				try {
					bufferedTabWriters.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}

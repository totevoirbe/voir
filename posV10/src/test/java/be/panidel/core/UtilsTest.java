/**
 * 
 */
package be.panidel.core;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author Philippe_2
 * 
 */
public class UtilsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void getListOfFilesTest() {
//		log.debug("START getListOfFilesTest");
//		File file = new FileHelper().getStorageByResource(STORAGE);
//		File[] allFiles = new FileHelper().getListOfFiles(file);
//		if(allFiles!=null) {
//			for (int i = 0; i < allFiles.length; i++) {
//				log.debug("File selected : " + allFiles[i]);
//			}
//		} else {
//			log.debug("allFiles is null");
//		}
//		log.debug("END getListOfFilesTest");
//	}
	
//	@Test
//	public void getListOfXMLFilesByExtensionTest() {
//		log.debug("START getListOfFilesByExtensionTest");
//		File file = new FileHelper().getStorageByResource(STORAGE);
//		File[] allFiles = new FileHelper().getListOfFiles(file,".xml");
//		for (int i = 0; i < allFiles.length; i++) {
//			log.debug("File selected : " + allFiles[i]);
//		}
//		log.debug("END getListOfFilesByExtensionTest");
//	}

//	@Test
//	public final void testReadSales() {
//		log.debug("START readSaleTest");
//		FileHelper utils = new FileHelper();
//		File[] files = utils.getListOfFiles(utils.getStorageByResource(STORAGE),"20090410", ".xml");
//		log.debug("Nbre de fichiers trouvés [" + (files==null?"Files is null":files.length) + "]");
//		if (log.isDebugEnabled()) {
//			for (int i = 0; i < files.length; i++) {
//				log.debug("File name [" + files[i].getAbsolutePath() + "]");
//			}
//		}
//		try {
//			utils.readSales(files);
//		} catch (ParserConfigurationException e) {
//			log.debug("", e);
//		} catch (SAXException e) {
//			log.debug("", e);
//		} catch (IOException e) {
//			log.debug("", e);
//		}
//		log.debug("END readSaleTest");
//	}

//	@Test
//	public final void testWriteFile() {
//
//		FileHelper utils = new FileHelper();
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//		Date date = new Date();
//		File [] files = utils.getListOfFiles(STORAGE, sdf.format(date),".xml");
//			try {
//				Document document = utils.readSales(files);
//				DOMUtils.XMLToStringWriter(document);
//			} catch (UnsupportedEncodingException e) {
//				log.debug("", e);
//			} catch (ParserConfigurationException e) {
//				log.debug("", e);
//			} catch (SAXException e) {
//				log.debug("", e);
//			} catch (IOException e) {
//				log.debug("", e);
//			} catch (TransformerException e) {
//				log.debug("", e);
//			}
//	}
}

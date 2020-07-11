package be.voir.dataLayer;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.voir.dataLayer.CodeTVADAO.CodeTVAEnum;
import be.voir.dataLayer.ProductCategoryTagDAO.ProductCategoryTagEnum;

public class ExcelHelperTest {

	private static final Logger LOG = LoggerFactory.getLogger(ExcelHelperTest.class);

	private static CodeTVADAO codeTVADAO;
	private static ProductCategoryTagDAO productCategoryTagDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		codeTVADAO = new CodeTVADAO();
		productCategoryTagDAO = new ProductCategoryTagDAO();
	}

	@Test
	public void testWriteAndReadFile() {

		String fileName = "CATALOG-testWriteAndReadFile.xlsx";
		String sheetName = "CATALOG";

		List<Product> products = new ArrayList<Product>();
		products.add(getProduct1());
		products.add(getProduct2());
		// INJECTION
		ExcelHelper.injectDAO(new CodeTVADAO(), new ProductCategoryTagDAO());

		try {
			ExcelHelper.writeFile(fileName, sheetName, products);
			List<Product> productsReaded = ExcelHelper.readFile(fileName, sheetName);
			LOG.info("" + productsReaded);
		} catch (Throwable e) {
			LOG.error("[PRODUCTS READED:" + products + "]", e);
			fail(e.getMessage());
		}

	}

	@Test
	public void testReadFullOrignalandTestReadWriteFile() {

		// INJECTION
		ExcelHelper.injectDAO(new CodeTVADAO(), new ProductCategoryTagDAO());

		String CATALOGOriginal = "/home/tote/git/repository2/voirBackend/src/main/resources/CATALOG-products.xlsx";
		String fileName = "CATALOG-testReadFullOrignalandTestReadWriteFile.xlsx";
		String sheetName = "CATALOG";

		List<Product> products = null;
		List<Product> productsReaded = null;

		try {
			// read catalog original
			products = ExcelHelper.readFile(CATALOGOriginal, sheetName);
			LOG.info("" + products);
			ExcelHelper.writeFile(fileName, sheetName, products);
			productsReaded = ExcelHelper.readFile(fileName, sheetName);
			LOG.info("" + productsReaded);
		} catch (Throwable e) {
			LOG.error("[PRODUCTS:" + products + "][READED:" + productsReaded + "]", e);
			fail(e.getMessage());
		}

	}

	private Product getProduct1() {

		ProductCategoryTag productCategoryTag1 = productCategoryTagDAO.get(ProductCategoryTagEnum.SANDWICH.name());
		ProductCategoryTag productCategoryTag2 = productCategoryTagDAO.get(ProductCategoryTagEnum.CHEVRE.name());

		String ticketLabel = "ticketLabel1";
		String code = "code1";
		String name = "name1";
		String htmlKeyLabel = "htmlKeyLabel1";
		ProductCategoryTag[] productCategoryTags = { productCategoryTag1, productCategoryTag2 };
		String image = "image1";
		CodeTVA vatRateOnPlace = codeTVADAO.get(CodeTVAEnum.VATONPLACENORMAL.name());
		CodeTVA vatRateTakeAway = codeTVADAO.get(CodeTVAEnum.VATTAKEAWAYNORMAL.name());
		BigDecimal mini = new BigDecimal(11.11);
		BigDecimal normal = new BigDecimal(21.12);
		BigDecimal geant = new BigDecimal(31.31);
		BigDecimal fitmini = new BigDecimal(41.41);
		BigDecimal fitnormal = new BigDecimal(51.51);
		BigDecimal fitgeant = new BigDecimal(61.61);
		String webDetail = "webDetail1";
		String afficheDetail = "afficheDetail1";
		Boolean canMerge = true;

		return new Product(code, name, productCategoryTags, vatRateOnPlace, vatRateTakeAway, mini, normal, geant,
				fitmini, fitnormal, fitgeant, image, htmlKeyLabel, ticketLabel, webDetail, afficheDetail, canMerge);

	}

	private Product getProduct2() {

		ProductCategoryTag productCategoryTag1 = productCategoryTagDAO.get(ProductCategoryTagEnum.ASSIETTE.name());
		ProductCategoryTag productCategoryTag2 = productCategoryTagDAO.get(ProductCategoryTagEnum.MER.name());

		String code = "code2";
		String ticketLabel = "ticketLabel2";
		String name = "name2";
		String htmlKeyLabel = "htmlKeyLabel2";
		ProductCategoryTag[] productCategoryTags = { productCategoryTag1, productCategoryTag2 };
		String image = "image2";
		CodeTVA vatRateOnPlace = codeTVADAO.get(CodeTVAEnum.VATONPLACEBOISSONS.name());
		CodeTVA vatRateTakeAway = codeTVADAO.get(CodeTVAEnum.VATTAKEAWAYLUX.name());
		BigDecimal mini = new BigDecimal(12.12);
		BigDecimal normal = new BigDecimal(22.22);
		BigDecimal geant = new BigDecimal(32.32);
		BigDecimal fitmini = new BigDecimal(42.42);
		BigDecimal fitnormal = new BigDecimal(52.52);
		BigDecimal fitgeant = new BigDecimal(62.62);
		String webDetail = "webDetail2";
		String afficheDetail = "afficheDetail2";
		Boolean canMerge = true;

		return new Product(code, name, productCategoryTags, vatRateOnPlace, vatRateTakeAway, mini, normal, geant,
				fitmini, fitnormal, fitgeant, image, htmlKeyLabel, ticketLabel, webDetail, afficheDetail, canMerge);

	}

}
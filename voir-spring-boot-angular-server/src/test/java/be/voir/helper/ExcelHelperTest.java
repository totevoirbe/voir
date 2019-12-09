package be.voir.helper;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.voir.VoirApplication;
import be.voir.referential.model.Product;
import be.voir.referential.model.ProductCategoryTag;
import be.voir.referential.model.VatRate;
import be.voir.referential.service.ProductCategoryTagService;
import be.voir.referential.service.ProductCategoryTagService.ProductCategoryTagEnum;
import be.voir.referential.service.ProductService;
import be.voir.referential.service.VatRateService;
import be.voir.referential.service.VatRateService.VatRateEnum;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { VoirApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExcelHelperTest {

	private static final Logger LOG = LoggerFactory.getLogger(ExcelHelperTest.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private VatRateService codeTVAService;

	@Autowired
	private ProductCategoryTagService productCategoryTagService;

	@Test
	public void contextLoads() {
		Assertions.assertThat(codeTVAService).isNotNull();
		Assertions.assertThat(productCategoryTagService).isNotNull();
	}

	@Test
	public void testWriteAndReadFile() {

		String fileName = "CATALOG-products.xlsx";
		String sheetName = "CATALOG";

		List<Product> products = new ArrayList<Product>();

		products.add(getProduct1(productCategoryTagService, codeTVAService));
		products.add(getProduct2(productCategoryTagService, codeTVAService));

		try {
			ExcelHelper.writeFile(fileName, sheetName, products);
			ExcelHelper.readProductFile(fileName, sheetName, productService, codeTVAService, productCategoryTagService);
			LOG.info("" + productService.getAll());
		} catch (Throwable e) {
			LOG.error("[PRODUCTS READED:" + products + "]", e);
			fail(e.getMessage());
		}

	}

	@Test
	public void testReadFullOrignalandTestReadWriteFile() {

		String CATALOGOriginal = "/home/tote/git/repository2/voirBackend/src/main/resources/POSproducts20180402.xlsx";
		String fileName = "CATALOG-testReadFullOrignalandTestReadWriteFile.xlsx";
		String sheetName = "CATALOG";

		List<Product> products = null;

		try {
			// read catalog original
			ExcelHelper.readProductFile(CATALOGOriginal, sheetName, productService, codeTVAService,
					productCategoryTagService);
			LOG.info("" + productService.getAll());
			products = new ArrayList<Product>();
			for (Product product : productService.getAll()) {
				products.add(product);
			}
			ExcelHelper.writeFile(fileName, sheetName, products);
			ExcelHelper.readProductFile(fileName, sheetName, productService, codeTVAService, productCategoryTagService);
			LOG.info("" + productService.getAll());
		} catch (Throwable e) {
			LOG.error("[PRODUCTS:" + products + "]", e);
			fail(e.getMessage());
		}

	}

	private static Product getProduct1(ProductCategoryTagService productCategoryTagService,
			VatRateService codeTVAService) {

		ProductCategoryTag productCategoryTag1 = productCategoryTagService
				.getByCode(ProductCategoryTagEnum.SANDWICH.name());
		ProductCategoryTag productCategoryTag2 = productCategoryTagService
				.getByCode(ProductCategoryTagEnum.CHEVRE.name());

		Long id = 2L;
		String code = "code1";
		String ticketLabel = "ticketLabel1";
		String name = "name1";
		String htmlKeyLabel = "htmlKeyLabel1";
		List<ProductCategoryTag> productCategoryTags = new ArrayList<>(
				Arrays.asList(productCategoryTag1, productCategoryTag2));
		String image = "image1";
		VatRate vatRateOnPlace = codeTVAService.getByCode(VatRateEnum.VATONPLACENORMAL.name());
		VatRate vatRateTakeAway = codeTVAService.getByCode(VatRateEnum.VATTAKEAWAYNORMAL.name());
		BigDecimal mini = new BigDecimal(11.11);
		BigDecimal normal = new BigDecimal(21.12);
		BigDecimal geant = new BigDecimal(31.31);
		BigDecimal fitmini = new BigDecimal(41.41);
		BigDecimal fitnormal = new BigDecimal(51.51);
		BigDecimal fitgeant = new BigDecimal(61.61);
		String webDetail = "webDetail1";
		String afficheDetail = "afficheDetail1";
		Boolean canMerge = true;

		return new Product(id, code, name, productCategoryTags, vatRateOnPlace, vatRateTakeAway, mini, normal, geant,
				fitmini, fitnormal, fitgeant, image, htmlKeyLabel, ticketLabel, webDetail, afficheDetail, canMerge);

	}

	private static Product getProduct2(ProductCategoryTagService productCategoryTagService,
			VatRateService codeTVAService) {

		ProductCategoryTag productCategoryTag1 = productCategoryTagService
				.getByCode(ProductCategoryTagEnum.ASSIETTE.name());
		ProductCategoryTag productCategoryTag2 = productCategoryTagService.getByCode(ProductCategoryTagEnum.MER.name());

		Long id = 2L;
		String code = "code2";
		String ticketLabel = "ticketLabel2";
		String name = "name2";
		String htmlKeyLabel = "htmlKeyLabel2";
		List<ProductCategoryTag> productCategoryTags = new ArrayList<>(
				Arrays.asList(productCategoryTag1, productCategoryTag2));
		String image = "image2";
		VatRate vatRateOnPlace = codeTVAService.getByCode(VatRateEnum.VATONPLACEBOISSONS.name());
		VatRate vatRateTakeAway = codeTVAService.getByCode(VatRateEnum.VATTAKEAWAYLUX.name());
		BigDecimal mini = new BigDecimal(12.12);
		BigDecimal normal = new BigDecimal(22.22);
		BigDecimal geant = new BigDecimal(32.32);
		BigDecimal fitmini = new BigDecimal(42.42);
		BigDecimal fitnormal = new BigDecimal(52.52);
		BigDecimal fitgeant = new BigDecimal(62.62);
		String webDetail = "webDetail2";
		String afficheDetail = "afficheDetail2";
		Boolean canMerge = true;

		return new Product(id, code, name, productCategoryTags, vatRateOnPlace, vatRateTakeAway, mini, normal, geant,
				fitmini, fitnormal, fitgeant, image, htmlKeyLabel, ticketLabel, webDetail, afficheDetail, canMerge);

	}

}

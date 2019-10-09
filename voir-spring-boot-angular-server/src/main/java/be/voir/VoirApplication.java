package be.voir;

import java.math.BigDecimal;

import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

import be.voir.helper.ExcelHelper;
import be.voir.referential.model.ProductCategoryTag;
import be.voir.referential.model.VatRate;
import be.voir.referential.service.ProductCategoryTagService;
import be.voir.referential.service.ProductCategoryTagService.ProductCategoryTagEnum;
import be.voir.referential.service.ProductService;
import be.voir.referential.service.VatRateService;
import be.voir.referential.service.VatRateService.VatRateEnum;

@SpringBootApplication
public class VoirApplication {

	private Logger LOG = LoggerFactory.getLogger(VoirApplication.class);

	private static final String version = "v0.01";

	String fileName = "CATALOG-testReadFullOrignalandTestReadWriteFile.xlsx";
	String sheetName = "CATALOG";

	public static void main(String[] args) {
		SpringApplication.run(VoirApplication.class, args);
	}

	@Value("${spring.application.name:defautValue}")
	private String name;

	@RequestMapping(value = "/")
	public String name() {
		return name + version;
	}

	@Bean
	CommandLineRunner initProductCategoryTag(ProductCategoryTagService productCategoryTagService) {
		return args -> {
			for (ProductCategoryTagEnum productCategoryTagEnum : ProductCategoryTagEnum.values()) {
				String code = productCategoryTagEnum.name();
				String label = productCategoryTagEnum.getLabel();
				ProductCategoryTag productCategoryTag = new ProductCategoryTag(code, label);
				productCategoryTagService.save(productCategoryTag);
			}
			LOG.info("" + productCategoryTagService.getAll());
		};
	}

	@Bean
	CommandLineRunner initCodeTVA(VatRateService codeTVAService) {
		return args -> {
			for (VatRateEnum codeTVAEnum : VatRateEnum.values()) {
				String code = codeTVAEnum.name();
				String label = codeTVAEnum.getLabel();
				BigDecimal rate = codeTVAEnum.getRate();
				VatRate codeTVA = new VatRate(code, label, rate);
				codeTVAService.save(codeTVA);
			}
			LOG.info("" + codeTVAService.getAll());
		};
	}

	@Bean
	CommandLineRunner initProduct(ProductService productService, ProductCategoryTagService productCategoryTagService,
			VatRateService codeTVAService) {
		return args -> {
			ExcelHelper.readFile(fileName, sheetName, productService, codeTVAService, productCategoryTagService);
			LOG.info("Number of products : " + IterableUtils.size(productService.getAll()));

		};
	}
}

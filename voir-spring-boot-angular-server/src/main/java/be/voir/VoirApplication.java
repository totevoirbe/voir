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
import be.voir.referential.model.CodeTVA;
import be.voir.referential.model.ProductCategoryTag;
import be.voir.referential.service.CodeTVAService;
import be.voir.referential.service.CodeTVAService.CodeTVAEnum;
import be.voir.referential.service.ProductCategoryTagService;
import be.voir.referential.service.ProductCategoryTagService.ProductCategoryTagEnum;
import be.voir.referential.service.ProductService;

@SpringBootApplication
public class VoirApplication {

	private Logger LOG = LoggerFactory.getLogger(VoirApplication.class);

	String fileName = "CATALOG-testReadFullOrignalandTestReadWriteFile.xlsx";
	String sheetName = "CATALOG";

	public static void main(String[] args) {
		SpringApplication.run(VoirApplication.class, args);
	}

	@Value("${spring.application.name:defautValue}")
	private String name;

	@RequestMapping(value = "/")
	public String name() {
		return name;
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
	CommandLineRunner initCodeTVA(CodeTVAService codeTVAService) {
		return args -> {
			for (CodeTVAEnum codeTVAEnum : CodeTVAEnum.values()) {
				String code = codeTVAEnum.name();
				String label = codeTVAEnum.getLabel();
				BigDecimal rate = codeTVAEnum.getRate();
				CodeTVA codeTVA = new CodeTVA(code, label, rate);
				codeTVAService.save(codeTVA);
			}
			LOG.info("" + codeTVAService.getAll());
		};
	}

	@Bean
	CommandLineRunner initProduct(ProductService productService, ProductCategoryTagService productCategoryTagService,
			CodeTVAService codeTVAService) {
		return args -> {
			ExcelHelper.readFile(fileName, sheetName, productService, codeTVAService, productCategoryTagService);
			LOG.info("Number of products : " + IterableUtils.size(productService.getAll()));

		};
	}
}

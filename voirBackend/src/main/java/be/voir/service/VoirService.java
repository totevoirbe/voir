package be.voir.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.voir.dataLayer.CodeTVA;
import be.voir.dataLayer.CodeTVADAO;
import be.voir.dataLayer.ExcelHelper;
import be.voir.dataLayer.Product;
import be.voir.dataLayer.ProductCategoryTagDAO;

@Path("/VoirService")
// Local URL - http://localhost:8080/voirBackend/rest/VoirService/ProductsCatalog
public class VoirService {

	private static final Logger LOG = LoggerFactory.getLogger(VoirService.class);

	@GET
	@Path("/ProductsCatalog")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductsCatalog() {

		LOG.info("");

		String fileName = "/home/tote/git/repository2/voirBackend/CATALOG-testReadFullOrignalandTestReadWriteFile.xlsx";
		String sheetName = "CATALOG";
		ExcelHelper.injectDAO(new CodeTVADAO(), new ProductCategoryTagDAO());

		try {
			List<Product> products = ExcelHelper.readFile(fileName, sheetName);
			return products;
		} catch (IOException e) {
			LOG.error(fileName + "/" + sheetName, e);
			return null;
		}
	}

	@GET
	@Path("/CodeTVACollection")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<CodeTVA> getCodeTVAList() {

		LOG.info("");

		CodeTVADAO codeTVADAO = new CodeTVADAO();
		Collection<CodeTVA> products = codeTVADAO.getCodeTVAs();
		return products;

	}

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response VoirServiceTest() {
		return Response.status(200).entity("TestSuccessfull").build();
	}

}
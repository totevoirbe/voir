package be.panidel.frontLayer.restService;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.businessLayer.BusinessFacade;
import be.panidel.businessLayer.CashSaleComputation;
import be.panidel.dataLayer.model.CashSaleModel;
import be.panidel.dataLayer.model.PaymentMethodModel;
import be.panidel.dataLayer.model.ProductModel;
import be.panidel.frontLayer.model.CashSaleJsonModel;
import be.panidel.frontLayer.restService.RestPosServiceMessage.ResponseResult;

@Path("/pos")
public class RestPosService {

	private final static Logger LOG = LoggerFactory.getLogger(RestPosService.class);

	@GET
	@Path("/check")
	@Produces(MediaType.TEXT_HTML)
	public String checkIsActive() {

		LOG.debug("Check REST is active");

		StringBuffer response = new StringBuffer();

		response.append("<!DOCTYPE html>");
		response.append("<html>");
		response.append("<head>");
		response.append("<meta charset=\"utf-8\" />");
		response.append("<title>Test</title>");
		response.append("</head>");
		response.append("<body>");
		response.append(
				"<p>POS rest service is active on " + SimpleDateFormat.getInstance().format(new Date()) + ".</p>");
		response.append("</body>");
		response.append("</html>");

		return response.toString();
	}

	@GET
	@Path("/products")
	@Produces(MediaType.APPLICATION_XML)
	public Collection<ProductModel> getProductsXML() {

		LOG.debug("get Products XML");

		return BusinessFacade.getProducts();
	}

	@GET
	@Path("/products")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ProductModel> getProductsJSON() {

		LOG.debug("get Products JSON");

		return BusinessFacade.getProducts();
	}

	@GET
	@Path("/products")
	@Produces(MediaType.TEXT_XML)
	public Collection<ProductModel> getProductsTEXT() {

		LOG.debug("get Products TEXT");

		return BusinessFacade.getProducts();
	}

	@POST
	@Path("/products")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ProductModel> getProductsByPost() {

		LOG.debug("post Products JSON");

		return BusinessFacade.getProducts();
	}

	@POST
	@Path("/paymentMethods")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<PaymentMethodModel> getPaymentMethodsByPost() {

		LOG.debug("post paymentMethods JSON");

		return BusinessFacade.getPaymentMethods();

	}

	@GET
	@Path("/paymentMethods")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<PaymentMethodModel> getPaymentMethodsJSON() {

		LOG.debug("get Products JSON");

		return BusinessFacade.getPaymentMethods();
	}

	@POST
	@Path("/getCashSale")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestPosServiceResponse getCashSale(CashSaleJsonModel cashSaleAsJson) {

		try {

			LOG.info("Receive message : " + cashSaleAsJson);

			CashSaleModel saleModelRetValue = BusinessFacade.getCashSale(cashSaleAsJson);

			String message = "the message is success : " + saleModelRetValue;
			ResponseResult responseResult = ResponseResult.SUCCESS;
			Date date = new Date();
			RestPosServiceResponse serviceResponse = new RestPosServiceResponse(message, cashSaleAsJson.getId(),
					responseResult, date);
			LOG.info("return message : " + serviceResponse);

			return serviceResponse;

		} catch (Throwable e) {

			LOG.error("" + cashSaleAsJson, e);

			String message = "the message is failed";
			ResponseResult responseResult = ResponseResult.FAILED;
			Date date = new Date();

			RestPosServiceResponse serviceResponse = new RestPosServiceResponse(message, null, responseResult, date);

			return serviceResponse;
		}
	}

	@POST
	@Path("/writeCashSale")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestPosServiceResponse writeCashSale(CashSaleJsonModel cashSaleJson) {

		try {

			LOG.info("Receive message : " + cashSaleJson);

			CashSaleComputation cashSaleComputation = new CashSaleComputation();
			cashSaleComputation.writeCashSale(cashSaleJson, null);

			String message = "the message is success";
			ResponseResult responseResult = ResponseResult.SUCCESS;
			Date date = new Date();
			RestPosServiceResponse serviceResponse = new RestPosServiceResponse(message, cashSaleJson.getId(),
					responseResult, date);
			LOG.info("return message : " + serviceResponse);

			return serviceResponse;

		} catch (Throwable e) {

			LOG.error("" + cashSaleJson, e);

			String message = "the message is failed";
			ResponseResult responseResult = ResponseResult.FAILED;
			Date date = new Date();

			RestPosServiceResponse serviceResponse = new RestPosServiceResponse(message, null, responseResult, date);

			return serviceResponse;
		}
	}
}
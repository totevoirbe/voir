package be.panidel.tarif.xlsWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Collection;

import javax.xml.bind.JAXBException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.dao.MarshalHelper;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.helper.DAOConfig;
import be.panidel.dataLayer.model.ProductModel;
import be.panidel.tarif.exception.IllegalTypeException;
import be.panidel.tarif.model.PageAfficheDef;
import be.panidel.tarif.model.RootAfficheDef;

public class XlsAfficheBodyWriter {

	private final static Logger LOG = LoggerFactory.getLogger(XlsAfficheBodyWriter.class);

	public static void writeAfficheModel(Collection<ProductModel> productModels)
			throws JAXBException, InvalidFormatException, IOException, IllegalTypeException, DataLayerException {

		// generate data for affiche as excel file
		File xmlAfficheFileSce = new File(DAOConfig.DOC_DEST + "affichesDefinition.xml");
		String xlsAfficheBodyName = DAOConfig.DOC_BASE + "AfficheBody.xlsx";
		RootAfficheDef rootAfficheDef = (RootAfficheDef) MarshalHelper.unmarchalXml(RootAfficheDef.class,
				xmlAfficheFileSce);
		OutputStream xlsOutputStream = new FileOutputStream(new File(xlsAfficheBodyName));
		XlsAfficheBodyWriter.writeXlsxfile(xlsOutputStream, rootAfficheDef, productModels);
		LOG.debug("" + rootAfficheDef);

	}

	/**
	 * generate data for affiche as excel file
	 * 
	 * @param outputStream
	 * @param rootAfficheDef
	 * @param productModels
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws IllegalTypeException
	 * @throws DataLayerException
	 */
	private static void writeXlsxfile(OutputStream outputStream, RootAfficheDef rootAfficheDef,
			Collection<ProductModel> productModels)
			throws InvalidFormatException, IOException, IllegalTypeException, DataLayerException {

		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();

		Sheet sheet = xssfWorkbook.createSheet("products");
		int rowIndex = 0;

		for (PageAfficheDef pageAfficheDef : rootAfficheDef.getPage()) {

//			Sheet sheet = xssfWorkbook.createSheet(pageAfficheDef.getName());
//			int rowIndex = 0;

			XlsAfficheBodyWriterHelper.setColumnsWith(sheet);

			Row rowHeader = XlsAfficheBodyWriterHelper.createRow(sheet, rowIndex++,
					XlsAfficheBodyWriterHelper.HEADER_ROW_HEIGHT_IN_POINTS);
			XlsAfficheBodyWriterHelper.setHeader(xssfWorkbook, rowHeader);

			for (String productCode : pageAfficheDef.getTr()) {

				if (productCode.trim().isEmpty()) {

					// add empty row
					Row row = XlsAfficheBodyWriterHelper.createRow(sheet, rowIndex++,
							XlsAfficheBodyWriterHelper.EMPTY_ROW_HEIGHT_IN_POINTS);
					XlsAfficheBodyWriterHelper.setEmptyRow(xssfWorkbook, sheet, row);

					continue;

				} else if (productCode.startsWith("[")) {

					// add title row
					Row rowTitle = XlsAfficheBodyWriterHelper.createRow(sheet, rowIndex++,
							XlsAfficheBodyWriterHelper.TITLE_ROW_HEIGHT_IN_POINTS);
					XlsAfficheBodyWriterHelper.setTitle(xssfWorkbook, sheet, rowTitle, productCode);
					continue;

				}

				ProductModel productModel = DataFacade.instance.getProductByCode(productCode);

				if (productModel == null) {

					LOG.error(productCode + " is null");

				} else {

					// add product row
					Row rowProduct = XlsAfficheBodyWriterHelper.createRow(sheet, rowIndex++,
							XlsAfficheBodyWriterHelper.PRODUCT_ROW_HEIGHT_IN_POINTS);

					XlsAfficheBodyWriterHelper.setMarge(xssfWorkbook, rowProduct);

					XlsAfficheBodyWriterHelper.setLogo(xssfWorkbook, sheet, rowProduct, productModel.getImage());

					XlsAfficheBodyWriterHelper.setLabelCell(xssfWorkbook, rowProduct, productModel.getName());

					if (productModel.getGeant() == null || productModel.getGeant().compareTo(BigDecimal.ZERO) <= 0) {
						XlsAfficheBodyWriterHelper.setPrice1Cell(xssfWorkbook, rowProduct, null);

						try {
							XlsAfficheBodyWriterHelper.setPrice2Cell(xssfWorkbook, rowProduct,
									productModel.getNormal());
						} catch (Exception e) {
							LOG.error("", e);
						}

						sheet.addMergedRegion(new CellRangeAddress(rowProduct.getRowNum(), rowProduct.getRowNum(),
								XlsAfficheBodyWriterHelper.LABEL_COL_INDEX,
								XlsAfficheBodyWriterHelper.PRICE_1_COL_INDEX));
					} else {
						XlsAfficheBodyWriterHelper.setPrice1Cell(xssfWorkbook, rowProduct, productModel.getNormal());
						XlsAfficheBodyWriterHelper.setPrice2Cell(xssfWorkbook, rowProduct, productModel.getGeant());
					}

					if (productModel.getAfficheDetail() != null && productModel.getAfficheDetail().trim().length() > 0
							&& (!"NULL".equals(productModel.getAfficheDetail().trim().toUpperCase()))) {

						// add detail row
						Row rowDescription = XlsAfficheBodyWriterHelper.createRow(sheet, rowIndex++,
								XlsAfficheBodyWriterHelper.DESCRIPTION_ROW_HEIGHT_IN_POINTS);

						XlsAfficheBodyWriterHelper.setMarge(xssfWorkbook, rowDescription);

						XlsAfficheBodyWriterHelper.setLogo(xssfWorkbook, sheet, rowDescription, null);

						XlsAfficheBodyWriterHelper.setDescriptionCell(xssfWorkbook, sheet, rowDescription,
								productModel.getAfficheDetail());
						rowDescription.setHeightInPoints(0);

					}
				}
			}
		}

		xssfWorkbook.write(outputStream);

	}

}

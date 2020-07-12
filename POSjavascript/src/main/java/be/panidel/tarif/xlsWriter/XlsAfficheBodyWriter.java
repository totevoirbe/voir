package be.panidel.tarif.xlsWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.xml.bind.JAXBException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
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
import be.panidel.tarif.xlsWriter.XslAfficheBodyA3.ColDef;

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

//		Sheet sheet = xssfWorkbook.createSheet("products");
//		int rowIndex = 0;

		for (PageAfficheDef pageAfficheDef : rootAfficheDef.getPage()) {

			Sheet sheet = xssfWorkbook.createSheet(pageAfficheDef.getName());
			int rowIndex = 0;

			sheet.setColumnWidth(ColDef.MARGE.getColIndex(), ColDef.MARGE.getColumnWidth());
			sheet.setColumnWidth(ColDef.LOGO.getColIndex(), ColDef.LOGO.getColumnWidth());
			sheet.setColumnWidth(ColDef.LABEL.getColIndex(), ColDef.LABEL.getColumnWidth());
			sheet.setColumnWidth(ColDef.PRICE_1.getColIndex(), ColDef.PRICE_1.getColumnWidth());
			sheet.setColumnWidth(ColDef.PRICE_2.getColIndex(), ColDef.PRICE_2.getColumnWidth());

			XlsAfficheBodyWriterHelper.createHeaderRow(xssfWorkbook, sheet, rowIndex++);

			for (String productCode : pageAfficheDef.getTr()) {

				if (productCode.trim().isEmpty()) {

					// add empty row
					XlsAfficheBodyWriterHelper.createEmptyRow(xssfWorkbook, sheet, rowIndex++);

					continue;

				} else if (productCode.startsWith("[")) {

					// add title row
					XlsAfficheBodyWriterHelper.createTitleRow(xssfWorkbook, sheet, rowIndex++, productCode);
					continue;

				}

				ProductModel productModel = DataFacade.instance.getProductByCode(productCode);

				if (productModel == null) {

					LOG.error(productCode + " is null");

				} else {

					// add product row
					XlsAfficheBodyWriterHelper.createProductRow(xssfWorkbook, sheet, rowIndex++, productModel);

					if (productModel.getAfficheDetail() != null && productModel.getAfficheDetail().trim().length() > 0
							&& (!"NULL".equals(productModel.getAfficheDetail().trim().toUpperCase()))) {

						// add detail row
						XlsAfficheBodyWriterHelper.createDescriptionRow(xssfWorkbook, sheet, rowIndex++,
								productModel.getAfficheDetail());

					}
				}
			}
		}

		xssfWorkbook.write(outputStream);

	}

}

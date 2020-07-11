package be.panidel.pos10.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import be.panidel.dataLayer.helper.DAOConfig;
import be.panidel.dataLayer.helper.XlsProductDataReaderHelper;
import be.panidel.pos10.model.RefProductPos10;
import be.panidel.tarif.exception.IllegalTypeException;

public class XlsProductReader {

	public static List<RefProductPos10> parseXlsxfileForOldRefs()
			throws InvalidFormatException, IOException, IllegalTypeException {

		List<RefProductPos10> refProductPos10s = new ArrayList<>();

		InputStream inputStream = XlsProductReader.class.getClassLoader().getResourceAsStream(DAOConfig.POS10_PRODUCTS);

		Workbook workbook = WorkbookFactory.create(inputStream);

		Sheet sheet = workbook.getSheetAt(1);

		String[] headers = new String[100];

		for (Iterator<Row> rowIterator = sheet.rowIterator(); rowIterator.hasNext();) {

			Map<String, Object> rowCells = new HashMap<>();

			Row row = rowIterator.next();

			for (Iterator<Cell> cellIterator = row.cellIterator(); cellIterator.hasNext();) {
				Cell cell = cellIterator.next();
				if (row.getRowNum() == sheet.getFirstRowNum()) {
					headers[cell.getColumnIndex()] = "" + XlsProductDataReaderHelper.parseCell(cell);
				} else {
					Object cellObject = XlsProductDataReaderHelper.parseCell(cell);
					if (cellObject != null) {
						rowCells.put(headers[cell.getColumnIndex()], cellObject);
					}
				}
			}
			if (row.getRowNum() != sheet.getFirstRowNum()) {

				if (rowCells.get("id") != null) {
					String id = "" + ((Double) rowCells.get("id")).intValue();
					String code = "" + rowCells.get("code");
					String group = "" + rowCells.get("group");
					RefProductPos10 refProductPos10 = new RefProductPos10(id, code, group);
					refProductPos10s.add(refProductPos10);
				}
			}
		}
		return refProductPos10s;
	}

}

package testJaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadCaisses {

	public static Map<Date, DayResult> readFile(String fileName, String SHEET_NAME) throws IOException {

		Map<Date, DayResult> daysResult = new HashMap<Date, DayResult>();
		FileInputStream excelInputStream = new FileInputStream(new File(fileName));
		Workbook workbook = new XSSFWorkbook(excelInputStream);
		Sheet sheet = workbook.getSheet(SHEET_NAME);
		Iterator<Row> rowItr = sheet.iterator();

		if (rowItr.hasNext()) {
			rowItr.next();
		}

		while (rowItr.hasNext()) {
			Row row = rowItr.next();

			Cell cell = row.getCell(0);
			Date date = cell.getDateCellValue();
			DayResult dayResult = new DayResult();
			daysResult.put(date, dayResult);

			cell = row.getCell(1);
			double caisseAsDouble = cell.getNumericCellValue();
			BigDecimal caisse = new BigDecimal(caisseAsDouble);
			dayResult.setCaisse(caisse);

			cell = row.getCell(2);
			double carteAsDouble = cell.getNumericCellValue();
			BigDecimal carte = new BigDecimal(carteAsDouble);
			dayResult.setCarte(carte);

		}
		workbook.close();
		excelInputStream.close();
		return daysResult;
	}

}
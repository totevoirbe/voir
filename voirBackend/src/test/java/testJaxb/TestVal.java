package testJaxb;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class TestVal {

	private static final BigDecimal MULTIPLICAND = new BigDecimal(0.6);
	private static final BigDecimal THRESHOLD = new BigDecimal(20); // minimum disponible
	private static final BigDecimal MAX_VALUE = new BigDecimal(10); // ticket maximum
	private static final int MAX_ITERATION = 500;
	private static Map<Date, DayResult> daysResult;

	public static void main(String[] args) {

		try {

			daysResult = ReadCaisses.readFile("/home/tote/git/repository2/voirBackend/caisses.xlsx", "caisses");

			File posIn = new File("/home/tote/Desktop/POSIN");
			File posOut = new File("/home/tote/Desktop/POSOUT");

			Map<String, MonthAmount> monthAmountsBefore = new HashMap<String, MonthAmount>();
			Map<String, MonthAmount> monthAmountsAfter = new HashMap<String, MonthAmount>();
			Set<String> allPayTypesBefore = new HashSet<String>();
			Set<String> allPayTypesAfter = new HashSet<String>();

			for (File file : posIn.listFiles()) {

				DocumentList documentList = unmarshall(file);

				add(monthAmountsBefore, allPayTypesBefore, documentList);

				Date dateOfDay = new SimpleDateFormat("yyyyMMdd").parse(documentList.getDate().toString());

				String dayDescription = documentList.getDate().longValue() + "/caisse:"
						+ daysResult.get(dateOfDay).getCaisse().longValue() + "/carte:"
						+ daysResult.get(dateOfDay).getCarte().longValue() + "/vente:"
						+ amount(documentList).longValue() + "/nbre:" + documentList.getDocument().size();

				if (daysResult.get(dateOfDay).getCaisse().compareTo(daysResult.get(dateOfDay).getCarte()) >= 0) {
					System.out.println(dayDescription);
				} else {
					System.err.println(dayDescription);
				}

				BigDecimal reducedValue = reduce(documentList);

				String afterReduced = "Reduced value:" + reducedValue + "/reste:" + amount(documentList).longValue()
						+ "/nbre:" + documentList.getDocument().size();
				if (amount(documentList).compareTo(daysResult.get(dateOfDay).getCarte()) >= 0) {
					System.out.println(afterReduced);
				} else {
					System.err.println(afterReduced);
				}

				add(monthAmountsAfter, allPayTypesAfter, documentList);

				marshal(posOut, "" + documentList.getDate() + "DAGOSALLE.xml", documentList);

			}

			BigDecimal totalBefore = BigDecimal.ZERO;
			BigDecimal totalAfter = BigDecimal.ZERO;

			for (String month : monthAmountsBefore.keySet()) {
				MonthAmount monthAmount = monthAmountsBefore.get(month);
				totalBefore = totalBefore.add(monthAmount.getTotalDayAmount());
				System.out.println("Before : " + month + "/" + monthAmount.getTotalDayItemsAmount() + "/"
						+ monthAmount.getTotalDayAmount());
			}

			System.out.println("Before : " + totalBefore + "/" + allPayTypesBefore);

			for (String month : monthAmountsAfter.keySet()) {
				MonthAmount monthAmount = monthAmountsAfter.get(month);
				totalAfter = totalAfter.add(monthAmount.getTotalDayAmount());
				System.out.println("After : " + month + "/" + monthAmount.getTotalDayItemsAmount() + "/"
						+ monthAmount.getTotalDayAmount());
			}

			System.out.println("After : " + totalAfter + "/" + allPayTypesAfter);

		} catch (JAXBException | IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void add(Map<String, MonthAmount> monthAmounts, Set<String> allPayTypes, DocumentList documentList) {

		String mounth = documentList.getDate().toString().substring(0, 6);

		MonthAmount monthAmountBefore = monthAmounts.get(mounth);

		if (monthAmountBefore == null) {
			monthAmountBefore = new MonthAmount();
			monthAmounts.put(mounth, monthAmountBefore);
		}

		monthAmountBefore.addTotalDayItemsAmount(itemsAmount(documentList));
		monthAmountBefore.addTotalDayAmount(amount(documentList));
		allPayTypes.addAll(payTypes(documentList));

	}

	public static BigDecimal reduce(DocumentList documentList) throws ParseException {

		Date dateOfDay = new SimpleDateFormat("yyyyMMdd").parse(documentList.getDate().toString());

		BigDecimal reducedValue = BigDecimal.ZERO;

		BigDecimal allowed = amount(documentList).subtract(daysResult.get(dateOfDay).getCarte());

		if (allowed.compareTo(THRESHOLD) > 0) {

			BigDecimal reduceExpected = allowed.multiply(MULTIPLICAND);

			Set<Document> reducedDocument = new HashSet<Document>();

			int iteration = 0;

			do {

				int index = (int) (Math.random() * documentList.getDocument().size());
				Document document = documentList.getDocument().get(index);

				if (amount(document).compareTo(MAX_VALUE) < 0) {
					if (!reducedDocument.contains(document)) {
						reducedDocument.add(document);
						reducedValue = reducedValue.add(amount(document));
					}
				}

			} while (iteration++ < MAX_ITERATION && reducedValue.compareTo(reduceExpected) < 0
					&& reducedValue.compareTo(allowed) < 0);

			for (Document doc : reducedDocument) {
				documentList.getDocument().remove(doc);
			}
		}
		return reducedValue;

	}

	public static DocumentList unmarshall(File file) throws JAXBException, IOException {

		JAXBContext context = JAXBContext.newInstance(DocumentList.class);
		return (DocumentList) context.createUnmarshaller().unmarshal(new FileReader(file));

	}

	public static void marshal(File dirOut, String fileName, DocumentList documentList)
			throws JAXBException, IOException {

		File file = new File(dirOut, fileName);
		JAXBContext context = JAXBContext.newInstance(DocumentList.class);
		Marshaller mar = context.createMarshaller();
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		mar.marshal(documentList, file);

	}

	public static BigDecimal itemsAmount(DocumentList documentList) {

		BigDecimal total = BigDecimal.ZERO;

		if (documentList.getDocument() != null) {
			for (Document document : documentList.getDocument()) {
				BigDecimal value = itemsAmont(document);
				total = total.add(value);
			}
		}

		return total;

	}

	public static BigDecimal amount(DocumentList documentList) {

		BigDecimal total = BigDecimal.ZERO;

		if (documentList.getDocument() != null) {
			for (Document document : documentList.getDocument()) {
				BigDecimal value = amount(document);
				total = total.add(value);
			}
		}

		return total;

	}

	public static BigDecimal itemsAmont(Document document) {

		BigDecimal total = BigDecimal.ZERO;

		if (document.getItem() != null) {
			for (Item item : document.getItem()) {
				BigDecimal value = itemValue(item);
				total = total.add(value);
			}
		}

		return total;

	}

	public static BigDecimal amount(Document document) {

		BigDecimal total = BigDecimal.ZERO;

		boolean countableAmount = isCountableAmount(document);

		if (document.getPayement() != null && countableAmount) {
			for (Payement payement : document.getPayement()) {
				BigDecimal value = payValue(payement);
				total = total.add(value);
			}
		}

		return total;

	}

	public static BigDecimal itemValue(Item item) {

		if (item.getQuantity() == null || item.getUnitPrice() == null) {
			return BigDecimal.ZERO;
		}

		BigDecimal q = new BigDecimal(item.getQuantity()).divide(new BigDecimal(100));
		BigDecimal p = item.getUnitPrice().divide(new BigDecimal(100));

		return p.multiply(q);

	}

	public static BigDecimal payValue(Payement payement) {

		if (payement.getQuantity() == null || payement.getValue() == null) {
			return BigDecimal.ZERO;
		}

		BigDecimal q = new BigDecimal(payement.getQuantity()).divide(new BigDecimal(100));
		BigDecimal v = payement.getValue().divide(new BigDecimal(100));

		return v.multiply(q);

	}

	public static Set<String> payTypes(DocumentList documentList) {

		Set<String> payTypes = new HashSet<String>();

		if (documentList.getDocument() != null) {
			for (Document document : documentList.getDocument()) {
				payTypes.addAll(payTypes(document));
			}
		}

		return payTypes;

	}

	public static Set<String> payTypes(Document document) {

		Set<String> payTypes = new HashSet<String>();

		if (document.getPayement() != null) {
			for (Payement pay : document.getPayement()) {
				payTypes.add("" + pay.getMode() + "/" + pay.getDescription());
			}
		}

		return payTypes;

	}

	public static boolean isCountableAmount(Document document) {

		if (document.getPayement() != null) {
			for (Payement pay : document.getPayement()) {
				if (pay.getMode() != 60) {
					return false;
				}
			}
		}

		return true;

	}

}

class MonthAmount {

	BigDecimal totalDayItemsAmount = BigDecimal.ZERO;
	BigDecimal totalDayAmount = BigDecimal.ZERO;

	public void addTotalDayItemsAmount(BigDecimal totalDayItemsAmount) {
		this.totalDayItemsAmount = this.totalDayItemsAmount.add(totalDayItemsAmount);
	}

	public void addTotalDayAmount(BigDecimal totalDayAmount) {
		this.totalDayAmount = this.totalDayAmount.add(totalDayAmount);
	}

	public BigDecimal getTotalDayItemsAmount() {
		return totalDayItemsAmount;
	}

	public BigDecimal getTotalDayAmount() {
		return totalDayAmount;
	}
}

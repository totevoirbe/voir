package be.panidel.pos.gui.reports;

import java.math.BigDecimal;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;
import be.panidel.management.Product;
import be.panidel.management.impl.SubProduct;

public class ReportPanelBySupplies extends ReportCashBook {

	private static final long serialVersionUID = 1L;

	// private static final Logger log = Logger
	// .getLogger("SummaryPanelBySupplies");

	public ReportPanelBySupplies(Panel cp) {
		super(cp);
	}

	// protected Map<String, Item> getList(OperationUnitList operationUnitList)
	// {
	// try {
	// return ComputeHelper.getBySupplies(operationUnitList);
	// } catch (DAOException e) {
	// log.error(operationUnitList, e);
	// }
	// return null;
	// }

	protected BigDecimal getTotal(BigDecimal total, Item item)
			throws DAOException {
		Product product = (Product) item;
		for (SubProduct subProduct : product.getSubProductList()) {
			BigDecimal subProductCost = subProduct.getQty().multiply(
					subProduct.getRawProduct().getPrixAchat());
			total = total.add(item.getQuantity().multiply(subProductCost));
		}
		return total;
	}

	// protected BigDecimal getPourcent(Item item) {
	// BigDecimal pourcent = item.getQuantity().multiply(item.getAchatHtva())
	// .multiply(new BigDecimal(100));
	// return pourcent;
	// }
	//
	// protected String getColumnValue(Item item) {
	// return item.getAchatHtva().toPlainString();
	// }
}
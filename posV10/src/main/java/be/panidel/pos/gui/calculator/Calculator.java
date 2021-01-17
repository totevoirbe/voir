package be.panidel.pos.gui.calculator;

import java.awt.*;

import javax.swing.*;

import be.panidel.management.Item;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.error.Message;
import be.panidel.pos.model.CashRegisterModel;
import be.panidel.tools.Tools;

import java.math.BigDecimal;
import java.text.*;
import org.apache.log4j.Logger;

/**
 * FormattedTextFieldDemo.java requires no other files.
 * 
 * It implements a mortgage calculator that uses four JFormattedTextFields.
 */
public class Calculator extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6458032380984476569L;

	private static final Logger log = Logger.getLogger("Calculator");

	private AbstractCashRegister cashRegister;

	// Labels to identify the fields
	private JLabel totalLabel;
	private JLabel paidLabel;
	private JLabel toBePaidLabel;

	// Fields for data entry
	private JFormattedTextField totalField;
	private JFormattedTextField paidField;
	private JFormattedTextField toBePaidField;

	public Calculator(AbstractCashRegister cashRegister) {

		super(new BorderLayout());

		this.cashRegister = cashRegister;

		// Create the labels.
		totalLabel = new JLabel("Total");
		totalLabel.setFont(Tools.getDefaultFont(20));
		paidLabel = new JLabel("Reçu");
		paidLabel.setFont(Tools.getDefaultFont(20));
		toBePaidLabel = new JLabel("");
		toBePaidLabel.setFont(Tools.getDefaultFont(20));

		// Create the text fields and set them up.
		totalField = createFormattedTextField(cashRegister);
		paidField = createFormattedTextField(cashRegister);
		toBePaidField = createFormattedTextField(cashRegister);

		// Tell accessibility tools about label/textfield pairs.
		totalLabel.setLabelFor(totalField);
		paidLabel.setLabelFor(paidField);
		toBePaidLabel.setLabelFor(toBePaidField);

		// Lay out the labels in a panel.
		JPanel labelPane = new JPanel(new GridLayout(0, 1));
		labelPane.add(totalLabel);
		labelPane.add(paidLabel);
		labelPane.add(toBePaidLabel);

		// Layout the text fields in a panel.
		JPanel fieldPane = new JPanel(new GridLayout(0, 1));
		fieldPane.add(totalField);
		fieldPane.add(paidField);
		fieldPane.add(toBePaidField);

		// Put the panels in this panel, labels on left,
		// text fields on right.
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(labelPane, BorderLayout.CENTER);
		add(fieldPane, BorderLayout.LINE_END);
	}

	public boolean refresh() {
		log.debug("START refreh");
		CashRegisterModel cashregisterModel = cashRegister
				.getCashregisterModel();
		boolean canBeClosed = false;
		BigDecimal qty = new BigDecimal(0);
		BigDecimal total = new BigDecimal(0);
		BigDecimal paid = new BigDecimal(0);
		BigDecimal restToBePaid = new BigDecimal(0);
		try {
			if (cashregisterModel.getTicketTable().hasOperations()) {
				log.debug("Ticket table has operations");
				canBeClosed = true;
			}
			if (cashregisterModel.getPaidTable().hasOperations()) {
				log.debug("Pay table has operations");
				canBeClosed = true;
			}
			// Create the text fields and set them up.
			total = cashregisterModel.getTicketTable().getTableTotal();
			paid = cashregisterModel.getPaidTable().getTableTotal();
			restToBePaid = total.subtract(paid);
			if (cashregisterModel.getTicketTable() != null) {
				for (int i = 0; i < cashregisterModel.getTicketTable().size(); i++) {
					Item item = cashregisterModel.getTicketTable().get(i);
					qty = qty.add(item.getQuantity());
				}
			}
			if (log.isDebugEnabled()) {
				log.debug("Total=[" + total + "] Paid=[" + paid + "]"
						+ "rest=[" + restToBePaid + "]");
				log.debug("DOUBLE Total=[" + total + "] Paid=[" + paid + "]"
						+ "rest=[" + restToBePaid + "]");
			}
			totalLabel.setText("Total (" + qty.toPlainString() + ")");
			totalField.setValue(total);
			paidField.setValue(paid);
			if (restToBePaid.compareTo(BigDecimal.ZERO) == 0) {
				log.debug("rest to be paid is null");
				toBePaidField.setText("");
				toBePaidField.setForeground(new Color(0, 230, 0));
				toBePaidLabel.setText("");
				toBePaidLabel.setForeground(new Color(0, 230, 0));
			} else if (restToBePaid.compareTo(BigDecimal.ZERO) < 0) {
				log.debug("rest to be paid is null");
				toBePaidField.setValue(restToBePaid.negate());
				toBePaidField.setForeground(new Color(0, 230, 0));
				toBePaidLabel.setText("Rendre");
				toBePaidLabel.setForeground(new Color(0, 230, 0));
				canBeClosed = false;
			} else if (restToBePaid.compareTo(BigDecimal.ZERO) > 0) {
				log.debug("rest to be paid is null");
				toBePaidField.setValue(restToBePaid);
				toBePaidField.setForeground(Color.RED);
				toBePaidLabel.setText("A payer");
				toBePaidLabel.setForeground(Color.RED);
				canBeClosed = false;
			}
		} catch (NumberFormatException e) {
			log.error("Erreur format total[" + total + "] paid[" + paid
					+ "] rest[" + restToBePaid + "]", e);
			cashregisterModel.appendMessage("Number format error",
					Message.ERROR);
		}
		log.debug("END refreh + [" + canBeClosed + "]");
		return canBeClosed;
	}

	private JFormattedTextField createFormattedTextField(
			AbstractCashRegister cashregister) {

		JFormattedTextField formattedTextField = new JFormattedTextField(
				NumberFormat.getCurrencyInstance());
		formattedTextField.setFont(Tools.getDefaultFont(20));
		formattedTextField.setColumns(6);
		formattedTextField.setEditable(false);
		formattedTextField.setHorizontalAlignment(JTextField.RIGHT);
		return formattedTextField;
	}
}
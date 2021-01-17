package be.panidel.pos.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import be.panidel.management.Company;
import be.panidel.management.Item;
import be.panidel.management.Person;
import be.panidel.pos.exception.ParameterException;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.CashRegister;
import be.panidel.pos.gui.error.Message;
import be.panidel.pos.model.table.ItemsTable;
import be.panidel.pos.model.table.PayTable;
import be.panidel.pos.model.table.SplitUpTable;
import be.panidel.tools.Tools;

public class CashRegisterModel {

	private static final Logger log = Logger.getLogger("CashRegisterModel");

	private CashRegisterTable ticketTable;
	private CashRegisterTable paidTable;
	private CashRegisterTable splitTable;
	private StringBuffer input = new StringBuffer();
	private List<Message> errorMessages;
	private Date startOfSale;
	private Person personnel;
	private Company company;
	private Item lastItemEdited;
	private BigDecimal multiplier = BigDecimal.ONE;
	private int selectedKeyboard = CashRegister.KBDEFAULT;
	private AbstractCashRegister splitPanel;
	private boolean takeOnPlace;

	public CashRegisterModel(Person person) {
		ticketTable = new ItemsTable();
		paidTable = new PayTable();
		splitTable = new SplitUpTable();
		this.personnel = person;
	}

	public void clearAll() {
		ticketTable = new ItemsTable();
		paidTable = new PayTable();
		setCompany(null);
		setStartOfSale(null);
		multiplier = BigDecimal.ONE;
		takeOnPlace = false;
	}

	public BigDecimal getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(BigDecimal multiplier) {
		this.multiplier = multiplier;
	}

	public void resetMultiplier() {
		this.multiplier = BigDecimal.ONE;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Date getStartOfSale() {
		return startOfSale;
	}

	public void setStartOfSale(Date startOfSale) {
		this.startOfSale = startOfSale;
	}

	public boolean isSaleOpenned() {
		return startOfSale != null;
	}

	public Person getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Person personnel) {
		this.personnel = personnel;
	}

	public Item getLastItemEdited() {
		return lastItemEdited;
	}

	public void setLastItemEdited(Item lastItemEdited) {
		this.lastItemEdited = lastItemEdited;
	}

	public List<Message> getErrorMessages() {
		return (errorMessages == null ? new ArrayList<Message>()
				: errorMessages);
	}

	public Integer hasError() {
		Integer retValue = null;
		if (errorMessages == null) {
			errorMessages = new ArrayList<Message>();
		} else {
			for (int i = 0; i < errorMessages.size(); i++) {
				int level = errorMessages.get(i).getLevel();
				if (Message.ERROR == level) {
					return Message.ERROR;
				} else if (Message.WARNING == level) {
					retValue = Message.WARNING;
				} else if (retValue == null && Message.INFORMATION == level) {
					retValue = Message.INFORMATION;
				}
			}
		}
		return retValue;
	}

	public void appendMessage(String message, int errorType) {
		if (errorMessages == null) {
			errorMessages = new ArrayList<Message>();
		}
		errorMessages.add(new Message(errorType, message));
	}

	public void clearMessages() {
		errorMessages = null;
	}

	public String getMessage() {
		if (errorMessages == null) {
			return new String();
		}
		StringBuffer message = new StringBuffer();
		for (int i = 0; i < errorMessages.size(); i++) {
			if (i > 0) {
				message.append("\n\r");
			}
			message.append(errorMessages.get(i).getText());
		}
		return message.toString();
	}

	public CashRegisterTable getTicketTable() {
		return ticketTable;
	}

	public CashRegisterTable getPaidTable() {
		return paidTable;
	}

	public CashRegisterTable getSplitTable() {
		return splitTable;
	}

	public boolean isPaid() {
		return ticketTable.getTableTotal().compareTo(paidTable.getTableTotal()) == 0;
	}

	public boolean hasOperations() {
		return ticketTable.hasOperations() || paidTable.hasOperations();
	}

	public void mergeDigit(String text) {
		if (input == null) {
			input = new StringBuffer();
		}
		input.append(text);
	}

	public void clearInput() {
		input = new StringBuffer();
	}

	public Integer getInputAsInteger() {
		log.debug("Input As String [" + getInputAsString() + "]");
		Integer result;
		try {
			result = Tools.toIntegerCurrencyFormated(input.toString());
		} catch (ParameterException e) {
			log.error("Bad input As String [" + getInputAsString() + "]");
			return null;
		}
		log.debug("Convertion as integer [" + getInputAsString() + "]");
		return result;
	}

	public BigDecimal getInputAsBigDecimal() {
		log.debug("Input As String [" + getInputAsString() + "]");
		BigDecimal result;
		try {
			result = Tools.toBigDecimal(input.toString());
		} catch (ParameterException e) {
			log.error("Bad input As String [" + getInputAsString() + "]");
			return null;
		}
		log.debug("Convertion as integer [" + getInputAsString() + "]");
		return result;
	}

	public String getInputAsString() {
		if (input != null) {
			return input.toString();
		}
		return null;
	}

	public void setInput(String value) {
		if (value == null) {
			resetInput();
		} else {
			input = new StringBuffer(value);
		}
	}

	public void resetInput() {
		input = new StringBuffer();
	}

	public int getSelectedKeyboard() {
		return selectedKeyboard;
	}

	public void setSelectedKeyboard(int selectedKeyboard) {
		this.selectedKeyboard = selectedKeyboard;
	}

	public AbstractCashRegister getSplitPanel() {
		return splitPanel;
	}

	public void setSplitPanel(AbstractCashRegister splitPanel) {
		this.splitPanel = splitPanel;
	}

	public boolean isTakeOnPlace() {
		return takeOnPlace;
	}

	public void setTakeOnPlace(boolean takeOnPlace) {
		this.takeOnPlace = takeOnPlace;
	}
}
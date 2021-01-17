package be.panidel.pos.controler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import be.panidel.common.Identification;
import be.panidel.common.POSConstants;
import be.panidel.common.POSParameters;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;
import be.panidel.management.OperationUnit;
import be.panidel.management.Payement;
import be.panidel.management.Person;
import be.panidel.management.Product;
import be.panidel.management.impl.OperationUnitImpl;
import be.panidel.management.impl.PayementModeImpl;
import be.panidel.management.impl.PayementItemImpl;
import be.panidel.management.impl.ProductItemImpl;
import be.panidel.pos.exception.ParameterException;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.CashRegister;
import be.panidel.pos.gui.SplitPanel;
import be.panidel.pos.gui.error.Message;
import be.panidel.pos.model.CashRegisterModel;
import be.panidel.pos.model.CashRegisterTable;
import be.panidel.pos.model.PersonStatus;
import be.panidel.tools.PublishNewSale;

public class CashRegisterController {

	private static final Logger log = Logger
			.getLogger("CashRegisterController");

	private AbstractCashRegister cashRegister;

	public CashRegisterController(AbstractCashRegister cashRegister)
			throws DAOException {
		this.cashRegister = cashRegister;
	}

	public void close() {

		boolean allAreClosed = true;
		Map<Person, PersonStatus> statusList = cashRegister.getStatus();
		for (PersonStatus status : statusList.values()) {
			List<CashRegisterModel> modelList = status.getCashregisterModels();
			for (CashRegisterModel cashRegisterModel : modelList) {
				if (cashRegisterModel.isSaleOpenned()) {
					allAreClosed = false;
				}
			}
		}
		if (allAreClosed) {
			System.exit(0);
		} else {
			cashRegister.getCashregisterModel().appendMessage(
					"Cloturer toutes les ventes", Message.ERROR);
			cashRegister.getInfoPanel().refreshDisplay();
		}

	}

	public void setTakeOnPlace(boolean takeOnPlace) {
		cashRegister.getCashregisterModel().setTakeOnPlace(takeOnPlace);
		if (cashRegister.getKeyboardCommandPanelButton() != null) {
			cashRegister.getKeyboardCommandPanelButton().getOnPlaceButton()
					.setSelected(takeOnPlace);
		}
	}

	public void setKeyboard(int keyboardID) {
		cashRegister.getCashregisterModel().setSelectedKeyboard(keyboardID);
		if (cashRegister.getKeyboardCommandPanelButton() != null) {
			cashRegister.getKeyboardCommandPanelButton().selectButton(
					keyboardID);
		}
		cashRegister.getConfigurablePanelButton().refreshButtons(keyboardID,
				false);
		cashRegister.updateUI();
	}

	private void clearSale() {
		log.debug("START clearSale------------------------------");
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();
		setKeyboard(CashRegister.KBDEFAULT);
		cashRegister.getKeyboardCommandPanelButton().getOnPlaceButton()
				.setSelected(false);
		closeCurrentStatus();
		cashRegisterModel.clearAll();

		cashRegister.getInfoPanel().refreshDisplay();
		((CashRegister) cashRegister).getCmdPanelEmployees().updateUI();
		cashRegister.updateUI();

		log.debug("END clearSale------------------------------");
	}

	public void openSale() {
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();
		if (cashRegisterModel.getStartOfSale() == null) {
			cashRegisterModel.setStartOfSale(new Date());
			try {
				cashRegisterModel.setCompany(FacadeDAO.instance()
						.getCompanyDAO().getById("1"));
			} catch (DAOException e) {
				log.error("Erreur reading company 1", e);
				cashRegisterModel
						.appendMessage(
								"Erreur recherche compagnie 1. Appelez le service technique",
								Message.ERROR);
			}
		}
	}

	public void endSale() {
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();
		cashRegisterModel.clearMessages();
		if (cashRegisterModel.getPersonnel() == null) {
			cashRegisterModel
					.appendMessage("Veuillez vous identifier pour continuer.",
							Message.WARNING);
		}
		if (!cashRegisterModel.hasOperations()) {
			cashRegisterModel.appendMessage(
					"Il n'y a pas d'opérations en cours.", Message.WARNING);
		}
		if (!cashRegisterModel.isPaid()) {
			cashRegisterModel.appendMessage("Il reste un solde à payer.",
					Message.ERROR);
			cashRegister.getInfoPanel().refreshMessageField();
		}
		if (cashRegisterModel.hasError() == null) {
			try {
				FacadeDAO
						.instance()
						.getSalesDAO()
						.writeOperationUnit(
								getAsOperationUnit(false),
								POSParameters.instance()
										.getCashregisterStorageCaissesSales());
				new PublishNewSale().start();
			} catch (DAOException e) {
				try {
					log.error(getAsOperationUnit(false).toString(), e);
				} catch (ParameterException e1) {
					log.error("Erreur grave", e1);
				}
				cashRegisterModel.appendMessage("Erreur service id:endsale",
						Message.ERROR);
			} catch (ParameterException e) {
				try {
					log.error(getAsOperationUnit(false).toString(), e);
				} catch (ParameterException e1) {
					log.error("Erreur grave", e1);
				}
				cashRegisterModel.appendMessage("Erreur système à signaler",
						Message.ERROR);
			}
			cashRegisterModel.appendMessage("La vente est enregistrée.",
					Message.INFORMATION);
			cashRegister.setLastSaleNow();
			clearSale();
		}
	}

	public void cancelSale() {
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();
		cashRegisterModel.clearMessages();
		if (cashRegisterModel.getPersonnel() == null) {
			cashRegisterModel
					.appendMessage("Veuillez vous identifier pour continuer.",
							Message.WARNING);
		}
		if (!cashRegisterModel.hasOperations()) {
			cashRegisterModel.appendMessage(
					"Il n'y a pas d'opérations en cours.", Message.WARNING);
		}
		if (cashRegisterModel.hasError() == null) {
			try {
				FacadeDAO
						.instance()
						.getSalesDAO()
						.writeOperationUnit(
								getAsOperationUnit(true),
								POSParameters.instance()
										.getCashregisterStorageCaissesSales());
				new PublishNewSale().start();
			} catch (DAOException e) {
				try {
					log.error(getAsOperationUnit(false), e);
				} catch (ParameterException e1) {
					log.error("Erreur grave", e1);
				}
				cashRegisterModel.appendMessage("Erreur service id:cancelsale",
						Message.ERROR);
			} catch (ParameterException e) {
				log.error("erreur grave.", e);
				cashRegisterModel.appendMessage("Erreur système à signaler",
						Message.ERROR);
			}
			cashRegisterModel.appendMessage("La vente est annulée.",
					Message.INFORMATION);
			clearSale();
		}
	}

	public Boolean isSalesOK(AbstractCashRegister cashregister) {
		CashRegisterModel cashRegisterModel = cashregister
				.getCashregisterModel();
		cashRegisterModel.clearMessages();
		if (cashRegisterModel.getPersonnel() == null) {
			cashRegisterModel
					.appendMessage("Veuillez vous identifier pour continuer.",
							Message.WARNING);
		} else if (cashRegisterModel.getStartOfSale() == null) {
			cashregister.getCashRegisterController().openSale();
		}
		return cashregister.getCashregisterModel().hasError() == null;
	}

	public void appendProductToTicketTable(Identification content) {
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();
		String inputAsString = null;
		try {
			CashRegisterTable table = cashRegisterModel.getTicketTable();
			cashRegisterModel.clearMessages();

			Product product = (Product) content;

			inputAsString = cashRegisterModel.getInputAsString();
			BigDecimal amount = cashRegisterModel.getInputAsBigDecimal();
			BigDecimal multiplier = cashRegisterModel.getMultiplier();
			log.debug("amount [" + amount + "]");
			if (multiplier == null) {
				cashRegisterModel.appendMessage("Multiplier is empty",
						Message.ERROR);
				return;
			} else if (amount == null) {
				amount = product.getPrix();
				log.debug("amount = prix [" + amount + "]");
			}
			if (amount == null || new BigDecimal(0).equals(amount)) {
				cashRegisterModel.appendMessage(
						"Montant null, encoder un montant", Message.ERROR);
				return;
			}
			if (amount.compareTo(POSParameters.instance()
					.getItemSaleAmountMax()) > 0) {
				cashRegisterModel.appendMessage(
						"Montant supérieur à "
								+ POSParameters.instance()
										.getItemSaleAmountMax() + "€",
						Message.ERROR);
				return;
			}
			BigDecimal total = multiplier.multiply(amount);
			if (total.compareTo(POSParameters.instance().getItemSaleTotalMax()) > 0) {
				cashRegisterModel.appendMessage("Qte * montant supérieur à  "
						+ POSParameters.instance().getItemSaleTotalMax() + "€",
						Message.ERROR);
				return;
			}
			Item item = new ProductItemImpl(product, product.getDescription(),
					multiplier, amount, product.getTvaTakeAway(),
					product.getTvaTakeOnPlace());
			cashRegisterModel.setLastItemEdited(table.append(item));
			cashRegisterModel.resetMultiplier();
		} catch (Throwable e) {
			log.error("APTTT - Erreur encodage : [" + inputAsString + "]", e);
			cashRegisterModel.appendMessage("APTTT - Erreur encodage : ["
					+ inputAsString + "]", Message.ERROR);
		}
		cashRegisterModel.clearInput();

		cashRegister.getInfoPanel().refreshDisplay();
	}

	public void appendPaymentToPaidTable(Identification content) {
		log.debug("START appendPaymentToPaidTable -------------------------------------");
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();
		try {
			cashRegisterModel.clearMessages();
			CashRegisterTable ticketTable = cashRegisterModel.getTicketTable();
			CashRegisterTable paidTable = cashRegisterModel.getPaidTable();

			Payement payement = (Payement) content;

			BigDecimal amount = cashRegisterModel.getInputAsBigDecimal();
			BigDecimal multiplier = cashRegisterModel.getMultiplier();
			BigDecimal total = ticketTable.getTableTotal();
			BigDecimal paid = paidTable.getTableTotal();

			if (multiplier == null) {
				cashRegisterModel.appendMessage("Multiplier is empty",
						Message.ERROR);
				return;
			} else if (!((PayementModeImpl) payement).isNeedSomeValue()
					&& amount == null) {
				amount = total.subtract(paid);
				multiplier = BigDecimal.ONE;
				log.debug("amount = reste [" + amount + "]");
			}
			if (amount == null || new Integer(0).equals(amount)) {
				cashRegisterModel.appendMessage(
						"Payement null, encoder un montant", Message.ERROR);
				return;
			}

			BigDecimal totalOfOperation = amount.multiply(multiplier);
			BigDecimal paidForId = paidTable.getTableTotal(payement.getId())
					.add(totalOfOperation);
			BigDecimal allPaid = paid.add(totalOfOperation);

			if (payement.isBeAlone() && paidTable.hasOther(payement.getId())) {
				cashRegisterModel.appendMessage(payement.getName()
						+ " doit être unique", Message.ERROR);
				return;
			} else if (payement.getMaxQuantity().compareTo(BigDecimal.ZERO) > 0
					&& paidTable.getQuantity(payement.getId()).compareTo(
							payement.getMaxQuantity()) > 0) {
				cashRegisterModel.appendMessage(
						payement.getName()
								+ " trop d'unités : "
								+ paidTable.getQuantity(payement.getId())
										.toPlainString(), Message.ERROR);
				return;
			} else if (payement.getMaxTotalAmount().compareTo(BigDecimal.ZERO) > 0
					&& paidForId.compareTo(payement.getMaxTotalAmount()) > 0) {
				cashRegisterModel.appendMessage(payement.getName()
						+ " montant trop élévé : " + paidForId.toPlainString(),
						Message.ERROR);
				return;
			} else if (totalOfOperation.compareTo(POSParameters.instance()
					.getItemPayMax()) > 0) {
				cashRegisterModel.appendMessage("Trop payé supérieur à "
						+ POSParameters.instance().getItemPayMax() + "€",
						Message.ERROR);
				return;
			} else if (allPaid.compareTo(POSParameters.instance()
					.getItemPayTotalMax()) > 0) {
				cashRegisterModel.appendMessage("Payements supérieurs à "
						+ POSParameters.instance().getItemPayTotalMax() + "€",
						Message.ERROR);
				return;
			}
			Item item = new PayementItemImpl(payement, multiplier,
					BigDecimal.ONE, BigDecimal.ONE, amount,
					payement.getDescription());
			cashRegisterModel.setLastItemEdited(paidTable.append(item));
			cashRegisterModel.resetMultiplier();
		} catch (Throwable e) {
			String inputAsString = cashRegisterModel.getInputAsString();
			log.error("APTPT - Erreur encodage : [" + inputAsString + "]", e);
			cashRegisterModel.appendMessage("APTPT - Erreur encodage : ["
					+ inputAsString + "]", Message.ERROR);
		}
		cashRegisterModel.clearInput();
		cashRegister.getInfoPanel().refreshDisplay();
		log.debug("END appendPaymentToPaidTable -------------------------------------");
	}

	public void setMultiplyer() {
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();
		cashRegisterModel.clearMessages();

		String inputAsString = cashRegisterModel.getInputAsString();
		try {
			BigDecimal amount = cashRegisterModel.getInputAsBigDecimal();
			log.debug("amount [" + amount + "]");
			if (amount != null) {
				cashRegisterModel.setMultiplier(amount);
				log.debug("amount = multiplier [" + amount + "]");
			}
		} catch (Throwable e) {
			log.debug("SM - Erreur encodage : [" + inputAsString + "]");
			cashRegisterModel.appendMessage("SM - Erreur encodage : ["
					+ inputAsString + "]", Message.ERROR);
		}

		cashRegisterModel.clearInput();
		cashRegister.getInfoPanel().refreshInputField();
	}

	public void equalOperation() {
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();
		cashRegisterModel.clearMessages();
		Item selectedItem = cashRegisterModel.getLastItemEdited();
		try {
			Item[] uiSelectedItems = cashRegister.getInfoPanel()
					.getSelectedItems();
			if (uiSelectedItems.length == 1) {
				selectedItem = cashRegister.getInfoPanel().getSelectedItems()[0];
			}
			BigDecimal amount = cashRegisterModel.getInputAsBigDecimal();
			log.debug("amount [" + amount + "]");
			if (amount != null) {
				selectedItem.setUnitPrice(amount);
			}
			BigDecimal multiplier = cashRegisterModel.getMultiplier();
			log.debug("multiplier [" + multiplier + "]");
			if (multiplier != null) {
				selectedItem.setQuantity(multiplier);
			}
		} catch (Throwable e) {
			String inputAsString = cashRegisterModel.getInputAsString();
			log.error("EO - Erreur encodage : [" + inputAsString + "]", e);
			cashRegisterModel.appendMessage("EO - Erreur encodage : ["
					+ inputAsString + "]", Message.ERROR);
		}
		cashRegisterModel.setLastItemEdited(selectedItem);
		cashRegisterModel.clearInput();
		cashRegisterModel.resetMultiplier();
		cashRegister.getInfoPanel().refreshDisplay();
	}

	public void clearInput() {
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();
		cashRegisterModel.clearInput();
		cashRegisterModel.resetMultiplier();
		cashRegister.getInfoPanel().refreshInputField();
	}

	public void backInputOperation() {
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();
		cashRegisterModel.clearMessages();
		String inputAsString = cashRegisterModel.getInputAsString();
		if (inputAsString != null && inputAsString.length() > 0) {
			cashRegisterModel.setInput(inputAsString.substring(0,
					inputAsString.length() - 1));
			cashRegister.getInfoPanel().refreshDisplay();
		}
	}

	public void invertInputValue() {
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();
		cashRegisterModel.setMultiplier(cashRegisterModel.getMultiplier()
				.negate());
		cashRegister.getInfoPanel().refreshDisplay();
	}

	private OperationUnit getAsOperationUnit(boolean cancelled)
			throws ParameterException {
		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();

		OperationUnit operationUnit = new OperationUnitImpl(
				POSConstants.SDF_FOR_FILE_SALES.format(cashRegisterModel
						.getStartOfSale()), cashRegisterModel.getPersonnel(),
				cashRegisterModel.getStartOfSale(),
				cashRegisterModel.getCompany(), CashRegister.COMPUTERNAME,
				cashRegisterModel.isTakeOnPlace(),
				cashRegisterModel.getTicketTable(),
				cashRegisterModel.getPaidTable(), cancelled);

		return operationUnit;
	}

	public void selectPerson(Person person) {
		log.debug("START selectPerson ------------------------------------------");

		if (person != cashRegister.getCashregisterModel().getPersonnel()) {
			log.debug("change person");
			PersonStatus personStatus = cashRegister.getStatus().get(person);
			if (personStatus == null) {
				log.debug("person status is null, create person status");
				CashRegisterModel cashRegisterModel = new CashRegisterModel(
						person);
				personStatus = new PersonStatus(cashRegisterModel);
				cashRegister.getStatus().put(person, personStatus);
				cashRegisterModel.setPersonnel(person);
				cashRegister.setCashregisterModel(cashRegisterModel);
			} else {
				log.debug("select current status for this person");
				cashRegister.setCashregisterModel(personStatus
						.getCurrentCashregisterModel());
			}
		} else {
			log.debug("same person");
		}
		setKeyboard(cashRegister.getCashregisterModel().getSelectedKeyboard());
		setTakeOnPlace(cashRegister.getCashregisterModel().isTakeOnPlace());
		cashRegister.getInfoPanel().refreshDisplay();
		log.debug("STOP selectPerson ------------------------------------------");
	}

	private void closeCurrentStatus() {

		CashRegisterModel cashRegisterModel = cashRegister
				.getCashregisterModel();

		PersonStatus personStatus = cashRegister.getStatus().get(
				cashRegisterModel.getPersonnel());
		cashRegisterModel = personStatus.closeCurrentCashregisterModel();
	}

	public void openSplitUpPanel() {
		CashRegisterModel CRM = cashRegister.getCashregisterModel();
		AbstractCashRegister splitPanel = CRM.getSplitPanel();
		if (splitPanel == null) {
			splitPanel = SplitPanel.createAndShowGUI(cashRegister);
		}
		splitPanel.setVisible(true);
	}

}
package be.panidel.pos.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

import javax.swing.JPanel;

import be.panidel.common.POSException.POSException;
import be.panidel.dao.FacadeDAO;
import be.panidel.management.Person;
import be.panidel.pos.controler.CashRegisterController;
import be.panidel.pos.gui.commandPanel.CmdPanelKeyboard;
import be.panidel.pos.gui.configurablePanel.ConfigurablePanelButton;
import be.panidel.pos.gui.toolsPanel.InfoPanel;
import be.panidel.pos.model.CashRegisterModel;
import be.panidel.pos.model.PersonStatus;

public abstract class AbstractCashRegister extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6857292574077833386L;

	private static final Logger log = Logger.getLogger("AbstractCashRegister");

	public final static int KBDEFAULT = 1;
	public final static int[] KBALL = { 1, 2, 3, 4, 5, 6 };

	protected CashRegisterModel cashregisterModel;
	protected CashRegisterController cashRegisterController;
	protected CmdPanelKeyboard keyboardCommandPanelButton;
	protected FacadeDAO facadeDAO = FacadeDAO.instance();
	protected InfoPanel infoPanel;
	protected ConfigurablePanelButton configurablePanelButton;
	protected final Map<Person, PersonStatus> status = new HashMap<Person, PersonStatus>();
	protected AbstractCashRegister parentCashRegister;
	protected int selectedKeyboard = KBDEFAULT;
	protected Date lastSale = new Date(0);

	protected AbstractCashRegister(AbstractCashRegister parentCashRegister) {
		super();
		log.info("init");
		this.parentCashRegister = parentCashRegister;
		try {
			initComponents();
		} catch (POSException e) {
			log.error("Init error", e);
		}
	}

	abstract void initComponents() throws POSException;

	@Override
	public void setVisible(boolean flag) {
		if (flag) {
			infoPanel.refreshDisplay();
		}
		super.setVisible(flag);
	}

	public CashRegisterModel getCashregisterModel() {
		return cashregisterModel;
	}

	public CashRegisterController getCashRegisterController() {
		if (cashRegisterController == null) {
			if (parentCashRegister != null) {
				return parentCashRegister.getCashRegisterController();
			} else {
				log.error("cashRegister is null");
				return null;
			}
		} else {
			return cashRegisterController;
		}
	}

	public void setCashregisterModel(CashRegisterModel cashregisterModel) {
		this.cashregisterModel = cashregisterModel;
	}

	public ConfigurablePanelButton getConfigurablePanelButton() {
		if (configurablePanelButton == null) {
			if (parentCashRegister != null) {
				return parentCashRegister.getConfigurablePanelButton();
			} else {
				log.error("cashRegister is null");
				return null;
			}
		} else {
			return configurablePanelButton;
		}
	}

	public Map<Person, PersonStatus> getStatus() {
		return status;
	}

	public InfoPanel getInfoPanel() {
		return infoPanel;
	}

	public FacadeDAO getFacadeDAO() {
		return facadeDAO;
	}

	public CmdPanelKeyboard getKeyboardCommandPanelButton() {
		if (keyboardCommandPanelButton == null) {
			if (parentCashRegister != null) {
				return parentCashRegister.getKeyboardCommandPanelButton();
			} else {
				log.debug("cashRegister is null");
				return null;
			}
		} else {
			return keyboardCommandPanelButton;
		}
	}

	public int getSelectedKeyboard() {
		return selectedKeyboard;
	}

	public void setSelectedKeyboard(int selectedKeyboard) {
		this.selectedKeyboard = selectedKeyboard;
	}

	public AbstractCashRegister getParentCashRegister() {
		return parentCashRegister;
	}

	public WindowsClose getWindowsClose(AbstractCashRegister cashregister) {
		return new WindowsClose(cashregister);
	}

	public void setLastSaleNow() {
		this.lastSale = new Date();
	}

	public long getLastSale() {
		return lastSale.getTime();
	}
}

class WindowsClose extends WindowAdapter {

	private AbstractCashRegister cashregister;

	public WindowsClose(AbstractCashRegister cashregister) {
		this.cashregister = cashregister;
	}

	// anonymous WindowAdapter class
	public void windowClosing(WindowEvent w) {
		cashregister.getCashRegisterController().close();
	}
}
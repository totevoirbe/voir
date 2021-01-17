package be.panidel.pos.gui.toolsPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import org.apache.log4j.Logger;

import be.panidel.common.Identification;
import be.panidel.common.POSConstants;
import be.panidel.common.impl.GroupList;
import be.panidel.common.impl.IdentificationList;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Group;
import be.panidel.management.Payement;
import be.panidel.management.Product;
import be.panidel.pos.bean.KeyBean;
import be.panidel.pos.gui.SelectButtonPanel;
import be.panidel.pos.gui.toolsPanel.buttons.ButtonDetailPanelClearButton;
import be.panidel.pos.gui.toolsPanel.buttons.ButtonDetailPanelQuit;
import be.panidel.pos.gui.toolsPanel.buttons.ButtonDetailPanelSetButton;
import be.panidel.pos.gui.toolsPanel.buttons.ButtonDetailPanelSubmit;
import be.panidel.pos.gui.toolsPanel.buttons.ButtonPickColorPanel;

class selectIdentification implements ListDataListener {

	IdentificationList listToclear;

	public selectIdentification(IdentificationList listToclear) {
		super();
		this.listToclear = listToclear;
	}

	@Override
	public void contentsChanged(ListDataEvent e) {
		IdentificationList idList = (IdentificationList) e.getSource();
		if (idList.getSelectedItem() != null) {
			if (listToclear.getSelectedItem() != null) {
				listToclear.setSelectedItem(null);
			}
		}
	}

	@Override
	public void intervalAdded(ListDataEvent e) {
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
	}

}

public class ButtonDetailPanel extends JLayeredPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2527004357008614382L;

	private static final Logger log = Logger.getLogger("ButtonConfigPanel");

	private JComboBox productList;
	private JComboBox paymentList;

	private JTextField keyOperationTypeField = new JTextField(15);
	private JTextField fieldCode = new JTextField(15);
	private JTextField fieldName = new JTextField(15);
	private JTextField fielDescription = new JTextField(15);
	private JTextField fieldHtmlKeyLabel = new JTextField(15);
	private JTextField fieldPrice = new JTextField(15);
	private JComboBox groupList;
	private JButton pickColorButton;

	private Identification identification;
	private KeyBean keybean;
	private SelectButtonPanel selectButtonPanel;

	public ButtonDetailPanel(SelectButtonPanel selectButtonPanel) {

		this.selectButtonPanel = selectButtonPanel;

		setBorder(BorderFactory.createTitledBorder("ButtonArticle"));
		LayoutManager lm = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(lm);

		JPanel messageJPanel = new JPanel();
		messageJPanel.setLayout(new BoxLayout(messageJPanel,
				BoxLayout.PAGE_AXIS));

		IdentificationList idList = new IdentificationList(FacadeDAO.instance()
				.getProductsDAO(), POSConstants.ADD_PRODUCT);
		IdentificationList payList = new IdentificationList(FacadeDAO
				.instance().getPayementModesDAO(), POSConstants.ADD_PAYE);

		idList.addListDataListener(new selectIdentification(payList));
		productList = new JComboBox(idList);
		productList.setPreferredSize(new Dimension(60, 60));

		messageJPanel.add(new JLabel("Sélection produit"));
		messageJPanel.add(productList);

		payList.addListDataListener(new selectIdentification(idList));
		paymentList = new JComboBox(payList);
		paymentList.setPreferredSize(new Dimension(60, 60));

		messageJPanel.add(new JLabel("Sélection payement"));
		messageJPanel.add(paymentList);

		messageJPanel.add(new ButtonDetailPanelSetButton(selectButtonPanel));

		keyOperationTypeField.setEnabled(false);
		messageJPanel.add(new JLabel("Type d'opération de la touche"));
		messageJPanel.add(keyOperationTypeField);
		messageJPanel.add(new JLabel(
				"Libellé produit affiché (; pour aller à la ligne)"));
		messageJPanel.add(fieldHtmlKeyLabel);
		messageJPanel.add(new JLabel("Code du produit"));
		messageJPanel.add(fieldCode);
		messageJPanel.add(new JLabel("Nom du produit"));
		messageJPanel.add(fieldName);
		messageJPanel.add(new JLabel("Description du produit"));
		messageJPanel.add(fielDescription);
		messageJPanel.add(new JLabel("Prix du produit"));
		messageJPanel.add(fieldPrice);

		pickColorButton = new ButtonPickColorPanel();
		GroupList gList = new GroupList(pickColorButton);
		groupList = new JComboBox(gList);
		groupList.setPreferredSize(new Dimension(60, 60));
		messageJPanel.add(new JLabel("Sélection groupe"));
		messageJPanel.add(groupList);
		messageJPanel.add(pickColorButton);

		messageJPanel.add(new ButtonDetailPanelSubmit(selectButtonPanel));
		messageJPanel.add(new ButtonDetailPanelClearButton(selectButtonPanel));
		messageJPanel.add(new ButtonDetailPanelQuit(selectButtonPanel));

		add(messageJPanel);
	}

	/**
	 * Update data fields with identification data for selected key
	 * 
	 * @param keybean
	 *            selected key
	 */
	public void refresh(KeyBean keybean) {

		// if (content==null) {
		// log.error("content is null");
		// return;
		// }

		this.keybean = keybean;
		try {
			if (POSConstants.ADD_PRODUCT.equals(keybean.getOperationType())) {
				Product product = FacadeDAO.instance().getProductsDAO()
						.getById(keybean.getOperationCode());
				keyOperationTypeField.setText(keybean.getOperationType());
				fieldHtmlKeyLabel.setText(product.getHtmlKeyLabel());
				fieldCode.setText(product.getCode());
				fieldName.setText(product.getName());
				fielDescription.setText(product.getDescription());
				fieldPrice.setEnabled(true);
				fieldPrice.setText(product.getPrix().toPlainString());
				identification = product;
				groupList.setEnabled(true);
				Group group = product.getGroupAsGroup();
				if (group != null) {
					groupList.setSelectedItem(group.getDescription());
					pickColorButton.setEnabled(true);
					pickColorButton.setBackground(product.getGroupAsGroup()
							.getTouchColorAsColor());
				} else {
					groupList.setSelectedItem("");
					pickColorButton.setEnabled(true);
					pickColorButton.setBackground(Color.WHITE);
				}
			} else if (POSConstants.ADD_PAYE.equals(keybean.getOperationType())) {
				Payement payement = FacadeDAO.instance().getPayementModesDAO()
						.getById(keybean.getOperationCode());
				keyOperationTypeField.setText(keybean.getOperationType());
				fieldHtmlKeyLabel.setText(payement.getHtmlKeyLabel());
				fieldCode.setText(payement.getCode());
				fieldName.setText(payement.getName());
				fielDescription.setText(payement.getDescription());
				fieldPrice.setEnabled(false);
				fieldPrice.setText("Pas applicable");
				identification = payement;
				groupList.setEnabled(false);
				pickColorButton.setEnabled(false);
			} else {
				keyOperationTypeField.setText("-");
				fieldHtmlKeyLabel.setText("-");
				fieldCode.setText("-");
				fieldName.setText("-");
				fielDescription.setText("-");
				fieldPrice.setText("-");
				groupList.setEnabled(false);
				pickColorButton.setEnabled(false);
			}
		} catch (DAOException e) {
			log.error("Key Id = " + keybean.getId(), e);

		}

	}

	public void writeMessage(String message) {
		this.keybean = null;
		keyOperationTypeField.setText(new String());
		fieldHtmlKeyLabel.setText(new String());
		fieldCode.setText(message);
		fieldName.setText(new String());
		fielDescription.setText(new String());
		fieldPrice.setText(new String());
	}

	/**
	 * Update identification object. Replace fields content with user data
	 * encoded.
	 */
	public void setNewValues() {
		log.debug("Set new button value");
		if (identification == null) {
			log.debug("identification is null");
			return;
		}
		if (keybean == null) {
			log.debug("keybean is null");
			return;
		}
		if (keyOperationTypeField == null) {
			log.debug("keyOperationTypeField is null");
			return;
		}
		keybean.setOperationType(keyOperationTypeField.getText());
		identification.setHtmlKeyLabel(fieldHtmlKeyLabel.getText());
		identification.setCode(fieldCode.getText());
		identification.setName(fieldName.getText());
		identification.setDescription(fielDescription.getText());
		identification.setHtmlKeyLabel(fieldHtmlKeyLabel.getText());
		if (identification instanceof Product) {
			Product product = (Product) identification;
			product.setPrix(new BigDecimal(fieldPrice.getText()));
			product.setGroup(((GroupList) groupList.getModel())
					.getSelectedGroup());
			Group group = product.getGroupAsGroup();
			if (group != null) {
				group.setTouchColor(pickColorButton.getBackground());
			}
		}
	}

	public Identification getIdentification() {
		return identification;
	}

	public KeyBean getKeybean() {
		return keybean;
	}

	public SelectButtonPanel getParent() {
		return selectButtonPanel;
	}

	public JComboBox getProductList() {
		return productList;
	}

	public JComboBox getPaymentList() {
		return paymentList;
	}
}
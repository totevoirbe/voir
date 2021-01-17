package be.panidel.pos.gui.toolsPanel;

import java.awt.event.ActionEvent;

import be.panidel.pos.gui.SelectButtonPanel;
import be.panidel.pos.gui.configurablePanel.ConfigurableButton;

/**
 * @author franzph
 *
 */
public class ButtonConfigPanelAction extends AbstractButtonAction {

	private SelectButtonPanel selectButtonPanel;
	private ConfigurableButton associatedButton;
	
	public ButtonConfigPanelAction(SelectButtonPanel selectButtonPanel) {
		super();
		this.selectButtonPanel = selectButtonPanel;
	}

	/* (non-Javadoc)
	 * @see be.panidel.pos.gui.common.ButtonAction#theAction(java.awt.event.ActionEvent)
	 */
	public void theAction(ActionEvent evt) {
		selectButtonPanel.getButtonDetailPanel().refresh(keybean);
		selectButtonPanel.getConfigurablePanelButton().setSelectedButton(associatedButton, new Integer(keybean.getId()));
	}

	public void setAssociatedButton(ConfigurableButton associatedButton) {
		this.associatedButton = associatedButton;
	}
}

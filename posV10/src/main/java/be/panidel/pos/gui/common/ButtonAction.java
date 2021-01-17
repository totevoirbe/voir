package be.panidel.pos.gui.common;

import java.awt.event.ActionEvent;

import be.panidel.common.Identification;
import be.panidel.pos.bean.KeyBean;

public interface ButtonAction {
	
	void theAction(ActionEvent evt);
	void setContent(Identification content);
	void setKeybean(KeyBean keybean);

}

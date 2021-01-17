package be.panidel.pos.gui.toolsPanel;

import be.panidel.common.CoupleMessages;
import be.panidel.common.Identification;
import be.panidel.pos.bean.KeyBean;
import be.panidel.pos.gui.common.ButtonAction;

public abstract class AbstractButtonAction implements ButtonAction {

	protected Identification content;
	protected KeyBean keybean;

	@Override
	public void setContent(Identification content) {
		this.content = content;
	}

	@Override
	public void setKeybean(KeyBean keybean) {
		this.keybean = keybean;
	}

	public String toString() {

		CoupleMessages cm = new CoupleMessages();

		cm.put("content", content);
		cm.put("keybean", keybean);

		return cm.toString();
	}
}

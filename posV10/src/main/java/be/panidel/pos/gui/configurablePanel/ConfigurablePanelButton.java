package be.panidel.pos.gui.configurablePanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import be.panidel.common.POSConstants;

public abstract class ConfigurablePanelButton extends JLayeredPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4804604933309047146L;
	private static final Logger log = Logger.getLogger("ProductPanelButton");
	protected Map<Integer, KbArray> kbArrays = new HashMap<Integer, KbArray>();
	protected int[] defaultsKeyboards;

	public final static int HKEYBOARDSIZE = 8;
	public final static int VKEYBOARDSIZE = 12;

	public ConfigurablePanelButton(int[] defaultsKeyboards) {

		super();

		this.defaultsKeyboards = defaultsKeyboards;
	}

	public void refreshButtons(int keyboardID, boolean refreshCash) {
		log.debug("START refreshButtons - " + keyboardID);
		this.removeAll();

		KbArray kbArray = null;
		if (refreshCash) {
			kbArray = initButtonMap(keyboardID);
			kbArrays.put(keyboardID, kbArray);
		} else {
			kbArray = kbArrays.get(keyboardID);
		}
		if (kbArray == null) {
			log.error("Heybord is null [" + keyboardID + "]");
		}

		new Timer(60000, new TimerListener(this)).start();

		log.debug("Set gridlayout : " + kbArray);
		// Add several labels to the layered pane.
		GridLayout gl = new GridLayout(VKEYBOARDSIZE, HKEYBOARDSIZE);
		for (int vIndex = 0; vIndex < VKEYBOARDSIZE; vIndex++) {
			for (int hIndex = 0; hIndex < HKEYBOARDSIZE; hIndex++) {
				JButton button = kbArray.get(hIndex, vIndex);
				add(button);
				log.debug(button);
			}
		}
		setLayout(gl);
		log.debug("END refreshButtons - " + keyboardID);
	}

	public abstract KbArray initButtonMap(int keyboardID);

}

class TimerListener implements ActionListener {

	private ConfigurablePanelButton pb;

	TimerListener(ConfigurablePanelButton pb) {
		this.pb = pb;
	}

	public void actionPerformed(ActionEvent evt) {
		TitledBorder tb = BorderFactory
				.createTitledBorder(POSConstants.SDF_SHORTDATE_SHORTTIME
						.format(new Date()));
		pb.setBorder(tb);
	}
}

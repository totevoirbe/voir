package be.panidel.pos.gui.toolsPanel;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

public class PickColorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -530791697109702740L;

	private static final Logger log = Logger.getLogger("PickColorPanel");

	private JFrame frame;
	private JDialog jDialog;

	private PickColorPanel() {
		super();
		initComponents();
		//super.setVisible(true);
		setVisible(true);
	}

	private void initComponents() {
		
		log.debug("START initComponents");

		LayoutManager lm = new BoxLayout(this, BoxLayout.LINE_AXIS);
		setLayout(lm);

		ActionListener okListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("OK test");
			}
		};
		ActionListener cancelListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("CANCEL test");
			}
		};


		jDialog = JColorChooser.createDialog(new JColorChooser(),
				"Coleur du groupe", true, new JColorChooser(),  okListener, cancelListener);
		
		
		add(jDialog);

		log.debug("STOP initComponents");
	}
	

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static PickColorPanel doCreateAndShowGUI() {

		// Create and set up the window.
		JFrame frame = new JFrame("Summary2");
		// frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// Create and set up the content pane.
		PickColorPanel contentPane = new PickColorPanel();
		contentPane.setOpaque(true); // content panes must be opaque
		contentPane.setFrame(frame);
		frame.setContentPane(contentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);

		return contentPane;
	}
	
	private static PickColorPanel instance;
	private static Object lock = new Object();

	public static PickColorPanel createAndShowGUI(Color initColor) {
		if (instance == null) {
			synchronized (lock) {
				instance = doCreateAndShowGUI();
			}
		}
		JColorChooser.showDialog(instance, "Coleur du groupe", initColor);

		instance.getFrame().setVisible(true);
		return instance;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JDialog getjColorChooser() {
		return jDialog;
	}
}
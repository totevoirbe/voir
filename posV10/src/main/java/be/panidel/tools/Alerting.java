package be.panidel.tools;

import javax.swing.JFrame;
import javax.swing.JTextPane;

public class Alerting {

	static boolean goOn = false;

	// public static AbstractButton goOnButton() {
	// return new AbstractButton("Continuer") {
	//
	// /**
	// *
	// */
	// private static final long serialVersionUID = 1L;
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// Alerting.goOn = true;
	// }
	// };
	// }

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static JFrame createAndShowGUI() {
		// Create and set up the window.

		JFrame frame = new JFrame();

		frame.setResizable(false);

		JTextPane message = new JTextPane();
		message.setText("ATTENTION NOUVEAU MODELE DE DONNEES");

		frame.add(message);

		// Display the window.
		frame.pack();
		// frame.setExtendedState(frame.getExtendedState() |
		// JFrame.MAXIMIZED_BOTH);

		frame.setVisible(true);

		return frame;
	}

	public static void init() {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
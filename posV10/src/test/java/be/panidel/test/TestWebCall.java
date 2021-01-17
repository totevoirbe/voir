package be.panidel.test;

import javax.media.*;

import javax.swing.*;

import java.awt.*;

import java.net.*;

import java.awt.event.*;

public class TestWebCall extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Player _player;

	TestWebCall() {

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				_player.stop();

				_player.deallocate();

				_player.close();

				System.exit(0);

			}

		});

		// setExtent( 0, 0, 320, 260 );

		JPanel panel = (JPanel) getContentPane();

		panel.setLayout(new BorderLayout());

		String mediaFile = "http://voir.dyndns-home.com:82/index1.htm";

		try {

			MediaLocator mlr = new MediaLocator(mediaFile);

			// URL url = new URL("ff");

			URLConnection urlc = mlr.getURL().openConnection();

			urlc.addRequestProperty("Authorization",
					"Basic%20cGhpbGZyYW46cGY4MjEw");

			_player = Manager.createRealizedPlayer(mlr);

			if (_player.getVisualComponent() != null)

				panel.add("Center", _player.getVisualComponent());

			if (_player.getControlPanelComponent() != null)

				panel.add("South", _player.getControlPanelComponent());

		}

		catch (Exception e) {

			System.err.println("Got exception " + e);

		}

	}

	public static void main(String[] args) {

//		TestWebCall jmfTest = new TestWebCall();

//		jmfTest.show();

	}

}

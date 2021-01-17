package be.panidel.test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

public class TestMediaPlayer extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7331538584345062098L;
	private static final Logger log = Logger.getLogger("TestMediaPlayer");

	public static void main(String[] args) {

		try {
			// URL url = new
			// URL("http://philfran:pf8210@localhost:85/videostream.cgi");

			URL url = new URL(
					"http://philfran:pf8210@voir.dyndns-home.com:82/videostream.cgi");

			JPanel panel = new TestMediaPlayer(url);

			// panel.show();
			panel.setVisible(true);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public TestMediaPlayer(URL mediaURL) {
		setLayout(new BorderLayout()); // use a BorderLayout

		// Use lightweight components for Swing compatibility
		Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, true);

		try {
			// URLConnection urlc = mediaURL.openConnection();
			//
			// urlc.addRequestProperty("Authorization",
			// "Basic cGhpbGZyYW46cGY4MjEw");
			//
			// urlc.connect();

			Player mediaPlayer = Manager.createRealizedPlayer(mediaURL);

			// get the components for the video and the playback controls
			Component video = mediaPlayer.getVisualComponent();
			Component controls = mediaPlayer.getControlPanelComponent();

			if (video != null)
				add(video, BorderLayout.CENTER); // add video component

			if (controls != null)
				add(controls, BorderLayout.SOUTH); // add controls

			mediaPlayer.start(); // start playing the media clip
		} // end try
		catch (NoPlayerException e) {
			log.error("", e);
		} // end catch
		catch (IOException e) {
			log.error("", e);
		} // end catch
		catch (CannotRealizeException e) {
			log.error("", e);
		}

	} // end MediaPanel constructor
} // end class MediaPanel
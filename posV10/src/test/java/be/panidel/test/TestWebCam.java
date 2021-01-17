package be.panidel.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class TestWebCam {

	private static final Logger log = Logger.getLogger("TestWebCam");

	public static void main(String[] args) {

		try {
			// URL url = new
			// URL("http://voir.dyndns-home.com:82/videostream.cgi");
			URL url = new URL("http://localhost:85/videostream.cgi");

			URLConnection urlc = url.openConnection();

			urlc.addRequestProperty("Authorization",
					"Basic cGhpbGZyYW46cGY4MjEw");

			urlc.connect();
			System.out.println(urlc.getContentType());
			System.out.println(urlc.getContentLength());
			System.out.println(urlc.getDoInput());
			System.out.println(urlc.getDoOutput());
			InputStream is = urlc.getInputStream();
			int length = 0;
			byte[] buf = new byte[256];
			do {
				length = is.read(buf);
				System.out.println(new String(buf));
			} while (length > 0);
		} catch (MalformedURLException e) {
			log.error("", e);
		} catch (IOException e) {
			log.error("", e);
		}

	}

}

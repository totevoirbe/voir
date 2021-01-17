package be.panidel.zip;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipTest {

	public static void main(String[] args) {
		ZipOutputStream zos = null;
		try {
			// Encode a String into bytes
			String inputString = "blahblahblah\u20AC\u20AC";
			byte[] input = inputString.getBytes("UTF-8");

			// Compress the bytes
			byte[] output = new byte[100];
			Deflater compresser = new Deflater();
			compresser.setInput(input);
			compresser.finish();
			int compressedDataLength = compresser.deflate(output);

			FileOutputStream fos = new FileOutputStream(
					"C:/Users/franzph/Desktop/aeff.zip");
			zos = new ZipOutputStream(fos);
			ZipEntry ze = new ZipEntry("entryTest.txt");
			zos.putNextEntry(ze);
			zos.write(output);
			zos.flush();

			// Decompress the bytes
			Inflater decompresser = new Inflater();
			decompresser.setInput(output, 0, compressedDataLength);
			byte[] result = new byte[100];
			int resultLength = decompresser.inflate(result);
			decompresser.end();

			// Decode the bytes into a String
			String outputString = new String(result, 0, resultLength, "UTF-8");

			System.out.println(outputString);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (DataFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				zos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

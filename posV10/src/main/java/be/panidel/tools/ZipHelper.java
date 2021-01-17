package be.panidel.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

public class ZipHelper {

	private static final Logger log = Logger.getLogger("ZipHelper");

	public static void writeZipContentAsFile(ByteArrayOutputStream baos,
			String outputZipFile) throws IOException {
		FileOutputStream fos = null;
		try {
			log.debug("WriteZipContentAsFile to " + outputZipFile);
			fos = new FileOutputStream(outputZipFile);
			fos.write(baos.toByteArray());
			log.debug("WriteZipContentAsFile done");
		} finally {
			fos.close();
		}
	}

	/**
	 * Zip it
	 * 
	 * @param zipFile
	 *            output ZIP file location
	 * @throws IOException
	 */
	public static ByteArrayOutputStream zipFiles(File[] fileList)
			throws IOException {

		log.debug("zipFiles to stream");

		byte[] buffer = new byte[1024];

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(bos);
		for (File file : fileList) {
			log.debug("Add file : " + file.getAbsoluteFile());
			ZipEntry ze = new ZipEntry(file.getName());
			zos.putNextEntry(ze);
			FileInputStream in = new FileInputStream(file.getAbsoluteFile());
			int len;
			while ((len = in.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}
			in.close();
		}
		zos.closeEntry();
		zos.close();
		log.debug("return zipFiles as stream");
		return bos;
	}

	/**
	 * Unzip it
	 * 
	 * @param zipFile
	 *            input zip file
	 * @param output
	 *            zip file output folder
	 * @throws IOException
	 */
	public static void unZipFiles(String zipFile, File fileOutputFolder)
			throws IOException {

		log.debug("unzip multiple zip File to output folder");

		unZipFiles(new FileInputStream(zipFile), fileOutputFolder);
	}

	public static void unZipFiles(InputStream fis, File fileOutputFolder)
			throws IOException {

		log.debug("unzip multiple zip content as stream to output folder");

		byte[] buffer = new byte[1024];
		// get the zip file content
		ZipInputStream zis = new ZipInputStream(fis);
		// get the zipped file list entry
		ZipEntry ze = zis.getNextEntry();
		while (ze != null) {
			String fileName = ze.getName();
			log.debug("unzip file : " + fileName);
			File newFile = new File(fileOutputFolder, fileName);
			// create all non exists folders
			// else you will hit FileNotFoundException for compressed folder
			new File(newFile.getParent()).mkdirs();
			FileOutputStream fos = new FileOutputStream(newFile);
			int len;
			while ((len = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			ze = zis.getNextEntry();
		}

		zis.closeEntry();
		zis.close();
		log.debug("unZipFiles done");
	}

}
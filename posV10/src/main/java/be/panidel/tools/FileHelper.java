package be.panidel.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;

import be.panidel.common.POSConstants;
import be.panidel.dao.exeption.DAOException;

public class FileHelper {

	private static final Logger log = Logger.getLogger("FileHelper");

	public static File copyFile(String filename, File repertorySce,
			File repertoryDest) throws DAOException {

		File sceFile = null;
		File destFile = null;

		sceFile = new File(repertorySce, filename);
		destFile = new File(repertoryDest, filename);

		copyFile(sceFile, destFile);

		return destFile;
	}

	public static File copyFileToDir(File sceFile, 
			File repertoryDest) throws DAOException {

		File destFile = null;
		destFile = new File(repertoryDest, sceFile.getName());

		copyFile(sceFile, destFile);

		return destFile;
	}


	public static File copyFile(File sceFile, File destFile)
			throws DAOException {

		FileReader frSce = null;
		BufferedReader bFrSce = null;
		FileWriter fwDest = null;
		BufferedWriter bFwDest = null;

		try {

			frSce = new FileReader(sceFile);
			bFrSce = new BufferedReader(frSce);

			fwDest = new FileWriter(destFile);
			bFwDest = new BufferedWriter(fwDest);

			String oneLine = bFrSce.readLine();

			while (oneLine != null) {
				bFwDest.write(oneLine);
				oneLine = bFrSce.readLine();
			}

		} catch (FileNotFoundException e) {
			throw new DAOException("Copy file : " + sceFile.getAbsolutePath()
					+ " to : " + destFile.getAbsolutePath(), e);
		} catch (IOException e) {
			throw new DAOException("Copy file : " + sceFile.getAbsolutePath()
					+ " to : " + destFile.getAbsolutePath(), e);
		} finally {
			if (bFrSce != null) {
				try {
					bFrSce.close();
				} catch (IOException e) {
					throw new DAOException("Copy file : "
							+ sceFile.getAbsolutePath() + " to : "
							+ destFile.getAbsolutePath(), e);
				}
			}
			if (bFwDest != null) {
				try {
					bFwDest.close();
				} catch (IOException e) {
					throw new DAOException("Copy file : "
							+ sceFile.getAbsolutePath() + " to : "
							+ destFile.getAbsolutePath(), e);
				}
			}
		}

		return destFile;
	}

	public static File moveFile(String filename, File repertorySce,
			File repertoryDest) throws DAOException {

		// File (or directory) to be moved
		File file = new File(repertorySce, filename);

		return moveFile(file, repertoryDest);

	}

	public static File moveFile(File file, File repertoryDest)
			throws DAOException {

		File destFile = new File(repertoryDest, file.getName());

		if (!file.isFile()) {
			return null;
		}

		if (destFile.isFile()) {
			File copyDestFile = new File(destFile.getAbsolutePath() + "_"
					+ POSConstants.SDF_FOR_FILE_SALES.format(new Date()));
			if (!file.renameTo(copyDestFile)) {
				throw new DAOException("Error moving file : ["
						+ file.getAbsolutePath() + "] to [" + copyDestFile
						+ "]");
			} else {
				log.debug("File move with susccess : ["
						+ file.getAbsolutePath() + "] to [" + copyDestFile
						+ "]");
			}
		} else {
			if (!file.renameTo(destFile)) {
				throw new DAOException("Error moving file : ["
						+ file.getAbsolutePath() + "] to [" + destFile + "]");
			}
		}

		return destFile;

	}

	public static File getOrCreateStorage(String storagePath) {
		File storage = new File(storagePath);
		if (storage == null || !storage.isDirectory()) {
			storage.mkdirs();
		}
		return storage;
	}
}
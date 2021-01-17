package be.panidel.tools;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

import org.apache.log4j.Logger;

import be.panidel.common.CoupleMessages;
import be.panidel.common.POSParameters;
import be.panidel.dao.PosAccessBeanDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.bean.PosAccessBean;
import be.panidel.pos.exception.ParameterException;

public class PublishNewSale extends Thread {

	private static final Logger log = Logger.getLogger("PublishNewSale");

	public PublishNewSale() {
		super();
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
	}

	@Override
	public void run() {
		try {
			log.info("Publish Sales");
			for (PosAccessBean posAccess : PosAccessBeanDAO.instance()) {
				try {
					CoupleMessages cm = new CoupleMessages();
					cm.put("posAccess.getDir()", posAccess.getDir());
					cm.put("posAccess.getLastAccess()",
							posAccess.getLastAccess());
					log.info(cm.toString());
					SalesFilesGroupingTool.doIt(POSParameters.instance()
							.getCashregisterStorageCaissesSales(),
							POSParameters.instance()
									.getCashregisterStorageCaissesGroupbyday(),
							POSParameters.instance()
									.getCashregisterStorageCaissesArchives(),
							POSParameters.instance()
									.getCashregisterStorageCaissesRejected());
					long lastFileModified = -1;
					lastFileModified = copySales(posAccess,
							POSParameters.instance()
									.getCashregisterStorageCaissesArchives(),
							lastFileModified);
					if (lastFileModified > 0) {
						posAccess.setLastAccess(new Date(lastFileModified));
					}
				} catch (DAOException e) {
					log.error("", e);
				}
			}
		} catch (ParameterException e) {
			log.error("", e);
		} catch (DAOException e) {
			log.error("", e);
		}
	}

	private long copySales(PosAccessBean posAccess, File sourceDir,
			long lastFileModified) throws DAOException {

		FileFilter ff = new FileXMLFilterByDate(posAccess.getLastAccess());
		File[] fileList = sourceDir.listFiles(ff);
		log.info("Files quantity to sync : " + fileList.length);
		File dirDestination = posAccess.getDir();

		for (int i = 0; i < fileList.length; i++) {
			File sceFile = fileList[i];
			FileHelper.copyFileToDir(sceFile, dirDestination);
			if (sceFile.lastModified() > lastFileModified) {
				lastFileModified = sceFile.lastModified();
			}
		}
		return lastFileModified;
	}
}

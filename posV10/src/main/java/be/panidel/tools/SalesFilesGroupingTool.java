package be.panidel.tools;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import be.panidel.common.POSParameters;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.OperationUnitDAO;
import be.panidel.dao.OperationUnitList;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.exception.ParameterException;
import be.panidel.pos.gui.CashRegister;
import be.panidel.tools.salesFileFilter.SalesFileFilterImpl;

public class SalesFilesGroupingTool {

	private static final Logger log = Logger.getLogger("SalesFiles");

	public static final int FILES_CAN_NOT_ARCHIVED = 0;
	public static final int FILES_HAVE_TO_BE_ARCHIVED = 1;

	public static Object lockForScanOfNetwork = new Object();

	public static synchronized void doIt(File storage, File storageGroupBy,
			File storageArchive, File rejectedFolder) throws DAOException {

		log.info("Start-storage[" + storage + "]-storageGroupBy["
				+ storageGroupBy + "]-storageArchive[" + storageArchive
				+ "]-rejectedFolder[" + rejectedFolder + "]");
		try {
			if (POSParameters.instance().getSyncReceiveEnabled()) {
				log.info("Process eMails");
				MailHelper.instance().processMail(
						POSParameters.instance().getSyncMsgSubjectPrefix(),
						POSParameters.instance()
								.getResultsStorageCaissesSales(),
						TimerProp.instance().getLastAccess(
								TimerProp.LAST_PROCESS_SYNC_EMAIL),
						POSParameters.instance().getSyncPopHost(),
						POSParameters.instance().getSyncEMailUser(),
						POSParameters.instance().getSyncEMailPwd());
			}
		} catch (MessagingException e1) {
			log.error("", e1);
		} catch (IOException e1) {
			log.error("", e1);
		} catch (ParameterException e1) {
			log.error("", e1);
		}

		log.debug("Init Grouping files");

		Map<String, FileIdentifierList> allFiles = new HashMap<String, FileIdentifierList>();

		SalesFileFilterImpl fileFilter = new SalesFileFilterImpl();
		File[] validFileList = storage.listFiles(fileFilter);
		if (validFileList != null) {
			for (int i = 0; i < validFileList.length; i++) {
				FileIdentifier fileIdentifier = new FileIdentifier(
						validFileList[i]);
				log.debug(fileIdentifier.getFileName());
				FileIdentifierList filesOfDay = allFiles.get(fileIdentifier
						.getDateOfFileAsString());
				if (filesOfDay == null) {
					log.debug(fileIdentifier.getDateOfFileAsString()
							+ "/file of day is null");
					// get sales computed and lock them
					filesOfDay = new FileIdentifierList(storageGroupBy,
							fileIdentifier.getDateOfFileAsDate(),
							fileIdentifier.getDateOfFileAsDate());
					filesOfDay.setAllUnMovable();
					// add sales to referencial
					allFiles.put(fileIdentifier.getDateOfFileAsString(),
							filesOfDay);
				}
				log.debug(fileIdentifier.getDateOfFileAsString() + "/"
						+ filesOfDay.size());
				filesOfDay.add(fileIdentifier);
			}

			// Grouping sales
			log.info("Grouping files");

			OperationUnitDAO operationUnitDAO = FacadeDAO.instance()
					.getOperationUnitDAO();

			for (String dayAsString : allFiles.keySet()) {

				FileIdentifierList filesOfDay = allFiles.get(dayAsString);

				OperationUnitList operationUnitList = operationUnitDAO
						.getOperationUnit(filesOfDay);

				if (operationUnitList.size() > 0) {

					String fileName = dayAsString + CashRegister.COMPUTERNAME
							+ ".xml";
					File file = new File(storageGroupBy, fileName);
					try {
						operationUnitDAO.writeOperationUnitList(
								operationUnitList, dayAsString,
								OperationUnitDAO.TYPE_OF_UNIT_DAY, file);
					} catch (DAOException e) {
						log.error(dayAsString, e);
					}
				}
			}

			// archiving files
			log.info("Archiving files");

			for (FileIdentifierList fileContainer : allFiles.values()) {
				for (FileIdentifier fileIdentifier : fileContainer) {
					try {
						if (fileIdentifier.isRejected()) {
							FileHelper.moveFile(fileIdentifier.getFile(),
									rejectedFolder);
							log.error("Rejected : "
									+ fileIdentifier.getFile()
											.getAbsolutePath());
						} else if (!fileIdentifier.isUnMovable()) {
							FileHelper.moveFile(fileIdentifier.getFile(),
									storageArchive);

						}
					} catch (DAOException e) {
						log.error("Error on Reject : "
								+ fileIdentifier.getFile().getAbsolutePath());
					}
				}
			}
		}
		log.info("done");
	}
}
package be.panidel.dao;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import be.panidel.common.POSParameters;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.bean.PosAccessBean;
import be.panidel.pos.exception.ParameterException;
import be.panidel.pos.gui.CashRegister;

public class PosAccessBeanDAO extends ArrayList<PosAccessBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static PosAccessBeanDAO instance;
	private static Object lock = new Object();

	private static final Logger log = Logger.getLogger("PosAccessBeanDAO");

	public static PosAccessBeanDAO instance() throws DAOException {
		if (instance == null) {
			synchronized (lock) {
				try {
					log.debug("instanciate");
					instance = new PosAccessBeanDAO();
					for (String posName : POSParameters.instance().getPosList()) {
						if (!posName.equals(CashRegister.COMPUTERNAME)) {
							setStorage(posName, "Caisses");
						}
					}
					log.debug("done");
				} catch (ParameterException e) {
					throw new DAOException(e);
				}
			}
		}
		return instance;
	}

	private static void setStorage(String posName, String subDir) {
		String dir = "\\\\" + posName + "\\" + subDir;
		File posStorage = new File(dir);
		if (posStorage != null && posStorage.isDirectory()) {
			PosAccessBean posAccess = new PosAccessBean(posStorage);
			instance.add(posAccess);
		}
	}
}
package be.panidel.tools;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.Test;

import be.panidel.common.POSParameters;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.exception.ParameterException;

public class TestFileHelper {

	private static final Logger log = Logger.getLogger("TestFileHelper");

	@Test
	public void testCopyFileByStringFromSceDirToDestDir() {

		File sceDir = new File("C:/testzip");
		File destDir = new File("//DAGORUE/CaisseArchive");

		// for (File sceFile : destDir.listFiles()) {
		// sceFile.delete();
		// }
		//
		// assertEquals(0, destDir.listFiles().length);

		try {
			for (File sceFile : sceDir.listFiles()) {
				log.info("Sce[" + sceFile.getAbsolutePath() + "]-Dest["
						+ destDir.getAbsolutePath() + "]");
				FileHelper.copyFileToDir(sceFile, destDir);

			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
		assertEquals(sceDir.listFiles().length, destDir.listFiles().length);
	}

	// @Test
	public void testCopyFileToDestDir() {
		fail("Not yet implemented");
	}

	// @Test
	public void testMoveFileStringFileFile() {
		try {
			FileHelper.moveFile("20090911105416264.xml", POSParameters
					.instance().getCashregisterStorageCaissesRejected(),
					FileHelper.getOrCreateStorage("C:/CaisseRejected/AEFF"));
		} catch (DAOException e) {
			e.printStackTrace();
			fail();
		} catch (ParameterException e) {
			e.printStackTrace();
			fail();
		}
		fail("Not yet implemented");
	}

	// @Test
	public void testMoveFileFileFile() {
		fail("Not yet implemented");
	}

	// @Test
	public void testExtractTimeFromSalesFileName() {
		fail("Not yet implemented");
	}

	// @Test
	public void testExtractAndValidateFileName() {
		fail("Not yet implemented");
	}

	// @Test
	public void testGetOperationsFiles() {
		fail("Not yet implemented");
	}

	// @Test
	public void testReadSales() {
		fail("Not yet implemented");
	}

	// @Test
	public void testGetOrCreateStorage() {
		fail("Not yet implemented");
	}

	// @Test
	public void testWriteSalesToFile() {
		fail("Not yet implemented");
	}

}

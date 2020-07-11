package be.panidel.dataLayer.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysHelper {

	private final static Logger LOG = LoggerFactory.getLogger(SysHelper.class);

	public static long maxMemoryRatio = 0;

	public static void main(String[] args) {
		SysHelper.checkMemoryLevelAndWarn(1L, 1L, "SELF_TEST");
		SysHelper.displayMemoryUtilization("MAIN TEST");
	}

	public static void displayMemoryUtilization(String message) {
		Runtime runtime = Runtime.getRuntime();

		long memoryRatio = (runtime.totalMemory() - runtime.freeMemory()) * 100 / runtime.totalMemory();

		if (memoryRatio > maxMemoryRatio) {
			maxMemoryRatio = memoryRatio;
		}

		LOG.info(displayMemoryUtilization(memoryRatio, runtime, message));

	}

	public static boolean checkMemoryLevelAndWarn(long warnLevel, long errorLevel, String message) {

		Runtime runtime = Runtime.getRuntime();
		long memoryRatio = (runtime.totalMemory() - runtime.freeMemory()) * 100 / runtime.totalMemory();

		if (memoryRatio > maxMemoryRatio) {
			maxMemoryRatio = memoryRatio;
		}

		if (memoryRatio > warnLevel) {
			if (memoryRatio > errorLevel) {
				LOG.warn(displayMemoryUtilization(memoryRatio, runtime, message));
			} else {
				LOG.error(displayMemoryUtilization(memoryRatio, runtime, message));
				if (memoryRatio > 98) {
					throw new IllegalStateException(displayMemoryUtilization(memoryRatio, runtime, message));
				}
			}
		}

		return memoryRatio > warnLevel;

	}

	public static String displayMemoryUtilization(long avalaibilityRatio, Runtime runtime, String message) {
		int mb = 1024 * 1024;
		return "[" + message + "]" + "Heap utilization " + avalaibilityRatio + "% / max use " + maxMemoryRatio
				+ "% - Used[" + (runtime.totalMemory() - runtime.freeMemory()) / mb + "], Free["
				+ runtime.freeMemory() / mb + "], Total[" + runtime.totalMemory() / mb + "], Max["
				+ runtime.maxMemory() / mb + "]";

	}

}

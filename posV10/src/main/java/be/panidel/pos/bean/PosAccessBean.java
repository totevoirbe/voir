package be.panidel.pos.bean;

import java.io.File;
import java.util.Date;

import be.panidel.tools.TimerProp;

public class PosAccessBean {

	private File dir;
	private Date lastAccess;

	public PosAccessBean(File dir) {
		this.dir = dir;
	}

	public File getDir() {
		return dir;
	}

	public Date getLastAccess() {
		if (lastAccess == null) {
			lastAccess = TimerProp.instance().getLastAccess(
					getSyncVariableName());
		}
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		TimerProp.instance().setLastAccess(getSyncVariableName(), lastAccess);
		this.lastAccess = lastAccess;
	}

	private String getSyncVariableName() {
		return "POSACCESS" + dir.getAbsolutePath();
	}
}

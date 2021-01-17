package be.panidel.management.impl;

import be.panidel.common.impl.IdentificationImpl;
import be.panidel.dao.DAO;
import be.panidel.management.Group;

public class NothingImpl extends IdentificationImpl {

	@Override
	public DAO getDAOInstance() {
		return null;
	}

	@Override
	public String getValueLabel() {
		return null;
	}

	public Group getGroupAsGroup() {
		return null;
	}

	public String getGroup() {
		return null;
	}

	public void setGroup(Group group) {}

	public void setGroup(String id){}

}

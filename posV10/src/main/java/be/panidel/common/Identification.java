package be.panidel.common;

import be.panidel.dao.DAO;

public interface Identification extends Comparable<Identification>{

	String getCode();

	void setCode(String code);

	String getDescription();

	void setDescription(String description);

	String getId();

	void setId(String id);

	String getName();

	void setName(String name);
	
	String getHtmlKeyLabel();

	void setHtmlKeyLabel(String htmlKeyLabel);

	String getValueLabel();
	
	DAO getDAOInstance();
}
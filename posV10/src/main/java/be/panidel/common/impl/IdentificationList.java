package be.panidel.common.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import org.apache.log4j.Logger;

import be.panidel.common.Identification;
import be.panidel.dao.DAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.tools.Tools;

public class IdentificationList extends AbstractListModel implements
		ComboBoxModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger("InitListModel");
	private static final String START_ID_IDENTIFIER = "(id=";
	private static final String END_ID_IDENTIFIER = ")";

	private List<Identification> identifications;
	private Identification selectedObject;
	private String type;

	public IdentificationList(DAO dao, String type) {
		super();
		this.type = type;
		try {
			identifications = dao.getList();
			Collections.sort(identifications);
		} catch (DAOException e) {
			log.error("Oups", e);
		}
	}

	public Identification getSelectedIdentification() {
		return selectedObject;
	}

	@Override
	public Object getSelectedItem() {
		if (selectedObject != null)
			return selectedObject.getDescription();
		else
			return null;
	}

	@Override
	public void setSelectedItem(Object description) {
		Identification theSelectedObject = null;
		if (description != null) {
			String searchById = null;
			String descriptionAsString = (String) description;
			int indexOfIdentificationStart = descriptionAsString
					.indexOf(START_ID_IDENTIFIER);
			if (indexOfIdentificationStart >= 0
					&& descriptionAsString.length() > indexOfIdentificationStart
							+ END_ID_IDENTIFIER.length()
							+ START_ID_IDENTIFIER.length() + 1) {
				int indexOfIdentificationEnd = descriptionAsString
						.lastIndexOf(END_ID_IDENTIFIER);
				searchById = descriptionAsString.substring(
						indexOfIdentificationStart
								+ START_ID_IDENTIFIER.length(),
						indexOfIdentificationEnd - 1);
			}
			for (int i = 0; i < identifications.size()
					&& theSelectedObject == null; i++) {
				Identification identTest = identifications.get(i);
				if (searchById != null && searchById.equals(identTest.getId())) {
					theSelectedObject = identTest;
				} else if (descriptionAsString.equals(identTest
						.getDescription())) {
					theSelectedObject = identTest;
				}
			}
		}
		selectedObject = theSelectedObject;
		fireContentsChanged(this, -1, -1);
	}

	@Override
	public Object getElementAt(int index) {
		if (index >= 0 && index < identifications.size()) {
			String description = identifications.get(index).getDescription();
			// treat doublon
			if ((index > 0 && description.equals(identifications.get(index - 1)
					.getDescription()))
					|| (index < identifications.size() - 2 && description
							.equals(identifications.get(index + 1)
									.getDescription()))) {
				description = Tools.addIdToText(description, identifications
						.get(index).getId());
			}
			return description;
		} else {
			return null;
		}
	}

	@Override
	public int getSize() {
		return identifications.size();
	}

	public String getType() {
		return type;
	}
}

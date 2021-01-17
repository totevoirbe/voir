package be.panidel.common.impl;

import java.awt.Color;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;

import org.apache.log4j.Logger;

import be.panidel.common.Identification;
import be.panidel.dao.DAO;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Group;
import be.panidel.tools.Tools;

public class GroupList extends AbstractListModel implements ComboBoxModel,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger("InitListModel");
	private static final String START_ID_IDENTIFIER = "(id=";
	private static final String END_ID_IDENTIFIER = ")";

	private List<Identification> groups;
	private Group selectedObject;
	private JButton pickColorButton;

	public GroupList(JButton pickColorButton) {
		super();
		if (pickColorButton == null) {
			log.error("PickColorButton has to be declared");
		}
		this.pickColorButton = pickColorButton;
		try {
			DAO dao = FacadeDAO.instance().getGroupsDAO();
			groups = dao.getList();
			Collections.sort(groups);
		} catch (DAOException e) {
			log.error("Oups", e);
		}
	}

	public Group getSelectedGroup() {
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
		Group theSelectedObject = null;
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
			for (int i = 0; i < groups.size() && theSelectedObject == null; i++) {
				Group identTest = (Group) groups.get(i);
				if (searchById != null && searchById.equals(identTest.getId())) {
					theSelectedObject = identTest;
				} else if (descriptionAsString.equals(identTest
						.getDescription())) {
					theSelectedObject = identTest;
				}
			}
		}
		selectedObject = theSelectedObject;
		if (selectedObject != null) {
			pickColorButton
					.setBackground(selectedObject.getTouchColorAsColor());
		} else {
			pickColorButton.setBackground(Color.WHITE);
		}
		fireContentsChanged(this, -1, -1);
	}

	@Override
	public Object getElementAt(int index) {
		if (index >= 0 && index < groups.size()) {
			String description = groups.get(index).getDescription();
			// treat doublon
			if ((index > 0 && description.equals(groups.get(index - 1)
					.getDescription()))
					|| (index < groups.size() - 2 && description.equals(groups
							.get(index + 1).getDescription()))) {
				description = Tools.addIdToText(description, groups.get(index)
						.getId());
			}
			return description;
		} else {
			return null;
		}
	}

	@Override
	public int getSize() {
		return groups.size();
	}
}

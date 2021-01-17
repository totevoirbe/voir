package be.panidel.management.impl;

import java.awt.Color;

import org.apache.log4j.Logger;

import be.panidel.common.impl.IdentificationImpl;
import be.panidel.dao.DAO;
import be.panidel.dao.FacadeDAO;
import be.panidel.management.Group;

public class GroupImpl extends IdentificationImpl implements Group {

	private static final Logger log = Logger.getLogger("GroupImpl");

	private Color touchColor;

	public GroupImpl() {
		super();
		this.touchColor=Color.WHITE;
	}

	public GroupImpl(String id, String code, String name, String description, String htmlKeyLabel, Color touchColor) {
		super(id, code, name, description, htmlKeyLabel);
		setTouchColor(touchColor);
	}

	public GroupImpl(String id, String code, String name, String description, String htmlKeyLabel, String touchColor) {
		super(id, code, name, description, htmlKeyLabel);
		setTouchColor(touchColor);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append("touchcolor["+touchColor.getRGB()+"]");
		return sb.toString();
	}

	@Override
	public DAO getDAOInstance() {
		return FacadeDAO.instance().getGroupsDAO();
	}

	public Color getTouchColorAsColor() {
		return touchColor;
	}
	public String getTouchColor() {
		if (getTouchColorAsColor()==null) {
			return null;
		}
		return Integer.toString(getTouchColorAsColor().getRGB());
	}

	@Override
	public String getValueLabel() {
		return null;
	}

	public void setTouchColor(Color touchColor) {
		this.touchColor = touchColor;
	}
	public void setTouchColor(String touchColor) {
		try {
			this.touchColor = new Color(Integer.parseInt(touchColor));
		} catch (NumberFormatException e) {
			this.touchColor = Color.WHITE;
			log.error("Erreur couleur : " + touchColor,e);
		}
	}

}
package be.panidel.management;

import java.awt.Color;

import be.panidel.common.Identification;

public interface Group extends Identification {

	Color getTouchColorAsColor();
	String getTouchColor();
	void setTouchColor(Color TouchColor);
	void setTouchColor(String touchColor);

}
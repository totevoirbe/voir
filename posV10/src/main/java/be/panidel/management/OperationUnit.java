package be.panidel.management;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import be.panidel.pos.exception.ParameterException;

public interface OperationUnit {

	void setFileName(String filename);

	String getFileName();

	Person getEmployee();

	Date getBeginTime();

	Date getEndTime();

	Company getCompany();

	String getComputerName();

	List<Item> getItemList();

	List<Item> getPayList();

	boolean isCancelled();

	Map<BigDecimal, BigDecimal> getTvaList() throws ParameterException;

	BigDecimal getTotalTVAC();

	BigDecimal getCaTVAC() throws ParameterException;

	BigDecimal getItemQuantity();

	void setBeginTime(Date date);

	public Boolean getTakeOnPlace();

	public void setTakeOnPlace(Boolean takeOnPlace);

}
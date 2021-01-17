package be.panidel.pos.bean;

import be.panidel.common.CoupleMessages;
import be.panidel.common.Identification;
import be.panidel.dao.DAO;
import be.panidel.dao.FacadeDAO;
import be.panidel.tools.Tools;

public class KeyBean implements Identification{
	
	private String id;
	private String keyCode;
	private String operationType;
	private String operationCode;
	private String htmlKeyLabel;
	private String keyboardCol;
	private String keyboardRow;

	
	public String toString(){
		
		CoupleMessages cm = new CoupleMessages();
		
		cm.put("id", id);
		cm.put("keyCode", keyCode);
		cm.put("operationType", operationType);
		cm.put("operationCode", operationCode);
		cm.put("htmlKeyLabel", htmlKeyLabel);
		cm.put("keyboardCol", keyboardCol);
		cm.put("keyboardRow", keyboardRow);

		return cm.toString();
	}
	
	public KeyBean() {
		super();
	}

	public KeyBean(String id) {
		this(id, new String(), new String(), new String(), new String(),
				new String(), new String());
	}

	public KeyBean(String id, String keyCode, String operationType,
			String operationCode, String keyboardCol, String keyboardRow, String htmlKeyLabel) {
		super();
		this.id = id;
		this.keyCode = keyCode;
		this.operationType = operationType;
		this.operationCode = operationCode;
		this.keyboardCol = keyboardCol;
		this.keyboardRow = keyboardRow;
		this.htmlKeyLabel = htmlKeyLabel;
	}
	
	public void removeAssociation() {
		keyCode = new String();
		operationType = new String();
		operationCode = new String();
		keyboardCol = new String();
		keyboardRow = new String();
		htmlKeyLabel = new String();
	}

	@Override
	public int compareTo(Identification id) {
		return Tools.Compare(this.getDescription(), id.getDescription());
	}

	public String getId() {
		return id;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public String getOperationType() {
		return operationType;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public String getHtmlKeyLabel() {
		return htmlKeyLabel;
	}

	public String getKeyboardCol() {
		return keyboardCol;
	}

	public String getKeyboardRow() {
		return keyboardRow;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public void setHtmlKeyLabel(String htmlKeyLabel) {
		this.htmlKeyLabel = htmlKeyLabel;
	}

	public void setKeyboardCol(String keyboardCol) {
		this.keyboardCol = keyboardCol;
	}

	public void setKeyboardRow(String keyboardRow) {
		this.keyboardRow = keyboardRow;
	}

	@Override
	public String getCode() {
		return null;
	}

	@Override
	public DAO getDAOInstance() {
		return FacadeDAO.instance().getKeyboardLayoutDAO();
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getValueLabel() {
		return null;
	}

	@Override
	public void setCode(String code) {

	}

	@Override
	public void setDescription(String description) { }

	@Override
	public void setName(String name) {	}
}

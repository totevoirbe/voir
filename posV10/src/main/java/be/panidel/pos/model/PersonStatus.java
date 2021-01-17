package be.panidel.pos.model;

import java.util.ArrayList;
import java.util.List;

import be.panidel.management.Person;

public class PersonStatus {

	private List<CashRegisterModel> cashregisterModels = new ArrayList<CashRegisterModel>();

	private int currentIndex = 0;

	public PersonStatus(CashRegisterModel cashregisterModel) {
		cashregisterModels.add(cashregisterModel);
	}

	public CashRegisterModel getCurrentCashregisterModel() {
		return cashregisterModels.get(currentIndex);
	}
	
	public CashRegisterModel getNextCashregisterModel() {
		if(currentIndex >= cashregisterModels.size() -1) {
			currentIndex = 0;
		} else {
			currentIndex += 1;
		}
		return getCurrentCashregisterModel();
	}
	
	public CashRegisterModel getNewCashregisterModel(Person person) {
		CashRegisterModel cashregisterModel = new CashRegisterModel(person);
		cashregisterModels.add(cashregisterModel);
		currentIndex = cashregisterModels.size()-1;
		return getCurrentCashregisterModel();
	}
	
	public CashRegisterModel closeCurrentCashregisterModel() {
		if (cashregisterModels.size() > 1) {
			cashregisterModels.remove(currentIndex);
			if(currentIndex >= cashregisterModels.size() -1) {
				currentIndex = 0;
			}
		} 
		return getCurrentCashregisterModel();
	}

	public List<CashRegisterModel> getCashregisterModels() {
		return cashregisterModels;
	}
}
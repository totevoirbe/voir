var calculator;
var cashSaleEngine;
var commandCashSale;
var currentSession;
var sessions;
var guiDao;
var localStorageQueue;

function CommandCashSale() {

	this.init = function() {
		currentSession.cashSale = guiDao.getNewCashSale();
	}

	this.displayResume = function() {
		guiView.displayResume(currentSession.cashSale);
	}

	this.displayAdmin = function() {
		guiView.showAdminWindow();
	}

	this.selection = function(item) {

		if (!item.deleted) {
			currentSession.cashSale.lastItem = item;
			if (!guiView.multipleSelection) {
				currentSession.cashSale.itemProducts.forEach(function(item) {
					item.itemSelected = undefined;
				});
				currentSession.cashSale.itemPayments.forEach(function(item) {
					item.itemSelected = undefined;
				});
			} else {
				if (item.itemSelected) {
					item.itemSelected = undefined;
				} else {
					item.itemSelected = true;
				}
			}
			ticket.display();
		}

	}

	this.selectSession = function(sessionId) {

		for (var integer = 0; integer < sessions.length; integer++) {
			var session = sessions[integer];
			if (session.sessionId == sessionId) {
				currentSession = session;
				guiView.setMainKbd();
				ticket.display();
				return;
			}
		}

		currentSession = new Session();
		sessions.push(currentSession);

		currentSession.guiSession = new GuiSession();
		currentSession.guiSession.calculatorCurrentDigitChain = "";
		currentSession.sessionId = sessionId;
		currentSession.cashSale = guiDao.getNewCashSale();

		guiView.setMainKbd();
		ticket.display();

	}

	this.isOpen = function() {

		if (currentSession.cashSale) {
			return currentSession.cashSale.cashSaleStatus == "OPEN";
		}
		return false;

	}

	this.openCashSale = function(cashSale) {

		cashSale.cashSaleStatus = "OPEN";
		cashSale.identifier = "";
		cashSale.source = "";
		cashSale.consumptionPlace = "TAKE_AWAY";
		cashSale.openDate = new Date();
		cashSale.itemProducts = new Array();
		cashSale.itemPayments = new Array();

	}

	this.endCashSale = function() {

		if (commandCashSale.isOpen()
				&& currentSession.cashSale.cashSaleRemainToPay == 0) {
			currentSession.cashSale.cashSaleStatus = "DONE";
			this.writeCashSale(currentSession.cashSale);
			cashSaleEngine.reset();
			calculator.reset();
			guiView.clear();
			ticket.display();
		}
	}

	this.cancelCashSale = function() {

		if (commandCashSale.isOpen()) {
			var ok = confirm("Confirmer annulation!");
			if (ok == true) {
				currentSession.cashSale.cashSaleStatus = "CANCEL";
				this.writeCashSale(currentSession.cashSale);
			}
		}
		ticket.display();
	}

	this.addProduct = function(productCode) {

		if (!this.isOpen()) {
			guiView.clear();
			currentSession.cashSale = guiDao.getNewCashSale();
			this.openCashSale(currentSession.cashSale);
		}

		var product = guiDao.getProductByCode(productCode);
		var quantity = 1;
		var unitPrice = product
				.getPrice(currentSession.guiSession.currentKbdName);
		var deleted = false;

		if (currentSession.priceoperatorFlag) {
			unitPrice = currentSession.priceoperatorFlag;
		} else {
			quantity = cashSaleEngine.getProductQuantity();
		}
		currentSession.priceoperatorFlag = undefined;

		var itemProduct = guiDao.getNewItemProduct(product, quantity,
				unitPrice, deleted, currentSession.guiSession.currentKbdName);

		currentSession.cashSale.itemProducts.push(itemProduct);
		currentSession.cashSale.lastItem = itemProduct;
		commandCashSale.selection(itemProduct);

		this.computeCashSale(currentSession.cashSale);
		cashSaleEngine.reset();
		calculator.reset();
		ticket.display();

	}

	this.addPayment = function(paymentMethodCode, value) {

		if (!this.isOpen()) {
			return;
		}

		
		
		
		var paymentMethod = guiDao.getPaymentMethodByCode(paymentMethodCode);
		var refCashSale = currentSession.cashSale;
		if (guiView.multipleSelection) {
			refCashSale = commandCashSale.computeSelectedCashSale(currentSession.cashSale);
		} else {
			this.computeCashSale(refCashSale);
		}

		if (value) {
			var quantity = 1;
			var CSQUantity = cashSaleEngine.getPaymentValue();
			if (!isNaN(CSQUantity)) {
				quantity = CSQUantity;
			}
			this.addItemPayment(paymentMethod, quantity, value);
		} else {
			if (paymentMethodCode == "GRATUIT") {
				if (currentSession.cashSale.lastItem.product) {
					this.addItemPayment(paymentMethod, 1,
							currentSession.cashSale.lastItem.unitPriceTakeaway);
				} else if (currentSession.cashSale.cashSaleRemainToPay) {
					this.addItemPayment(paymentMethod, 1,
							currentSession.cashSale.cashSaleRemainToPay);
				} else {
					showModalInform("Il n'y a pas de reste à payer");
				}
			} else if (paymentMethodCode == "FIDELITE") {
				if (currentSession.cashSale.lastItem.product) {
					this.addItemPayment(paymentMethod, 1,
							currentSession.cashSale.lastItem.unitPriceTakeaway);
				} else {
					showModalInform("Il n'y a pas de produit sélectionné");
				}
			} else if (paymentMethodCode == "JETE") {
				var action = function() {
					commandCashSale.addGlobalPayment(paymentMethod);
				};
				showModalConfirm(
						"Jeté porte sur tout le ticket, les autres payements seront effacés.",
						action);
			} else {
				if (currentSession.cashSale.cashSaleRemainToPay) {
					this.addItemPayment(paymentMethod, 1,
							currentSession.cashSale.cashSaleRemainToPay);
				}
			}
		}
	}

	this.addGlobalPayment = function(paymentMethod) {
		currentSession.cashSale.itemPayments = new Array();
		this.computeCashSale(currentSession.cashSale);
		if (currentSession.cashSale.cashSaleRemainToPay) {
			this.addItemPayment(paymentMethod, 1,
					currentSession.cashSale.cashSaleRemainToPay);
		}
	}

	this.addItemPayment = function(paymentMethod, quantity, unitValue) {

		var itemPayment = guiDao.getNewItemPayment(paymentMethod, quantity,
				unitValue);

		currentSession.cashSale.itemPayments.push(itemPayment);
		currentSession.cashSale.lastItem = itemPayment;
		commandCashSale.selection(itemPayment);

		cashSaleEngine.reset();
		calculator.reset();
		commandCashSale.computeCashSale(currentSession.cashSale);
		ticket.display();

	}

	this.deleteItem = function(item) {

		this.cashSaleDeleteItem(currentSession.cashSale, item);
		this.computeCashSale(currentSession.cashSale);
		cashSaleEngine.reset();
		calculator.reset();
		ticket.display();

	}

	this.updateItem = function(item, qty, value, itemType) {

		if (item.deleted) {
			this.cashSaleUndeleteItem(currentSession.cashSale, item);
		}
		if (itemType == "PRODUCT") {
			this.cashSaleUpdateProductItem(currentSession.cashSale, item, qty,
					value);
		} else {
			this.cashSaleUpdatePaymentItem(currentSession.cashSale, item, qty,
					value);
		}

		this.computeCashSale(currentSession.cashSale);
		cashSaleEngine.reset();
		calculator.reset();
		ticket.display();

	}

	this.computeSelectedCashSale = function(cashSale) {

		var selectedCashSale = guiDao.getNewCashSale();
		this.openCashSale(selectedCashSale);

		if (cashSale.itemProducts) {
			cashSale.itemProducts.forEach(function(itemProduct) {
				if (!itemProduct.deleted && itemProduct.itemSelected) {
					selectedCashSale.itemProducts.push(itemProduct);
				}
			});
		}

		if (cashSale.itemPayments) {
			cashSale.itemPayments.forEach(function(itemPayment) {
				if (!itemPayment.deleted && itemPayment.itemSelected) {
					selectedCashSale.itemPayments.push(itemPayment);
				}
			});
		}

		this.computeCashSale(selectedCashSale);
		return selectedCashSale;

	}

	this.computeCashSale = function(cashSale) {

		cashSale.cashSaleTotal = 0;
		cashSale.cashSalePaymentTotal = 0;
		cashSale.cashSaleNbArticles = 0;
		cashSale.cashSaleRemainToPay = 0;
		cashSale.paymentSubTotal = new Array();

		if (cashSale.itemProducts) {
			cashSale.itemProducts.forEach(function(itemProduct) {
				if (!itemProduct.deleted) {
					var unitPrice = itemProduct.unitPriceOnplace;
					if (cashSale.consumptionPlace !== "ON_PLACE") {
						unitPrice = itemProduct.unitPriceTakeaway;
					}
					cashSale.cashSaleTotal += itemProduct.quantity * unitPrice;
					cashSale.cashSaleNbArticles += itemProduct.quantity;
				}
			});
		}

		if (cashSale.itemPayments) {
			cashSale.itemPayments.forEach(function(itemPayment) {
				if (!itemPayment.deleted) {
					cashSale.cashSalePaymentTotal += itemPayment.quantity
							* itemPayment.unitPrice;
				}
			});
		}

		cashSale.cashSaleTotal = roundValue(cashSale.cashSaleTotal);
		cashSale.cashSalePaymentTotal = roundValue(cashSale.cashSalePaymentTotal);
		cashSale.cashSaleNbArticles = roundValue(cashSale.cashSaleNbArticles);
		cashSale.cashSaleRemainToPay = roundValue(cashSale.cashSaleTotal
				- cashSale.cashSalePaymentTotal);

	}

	this.writeCashSale = function(cashSale) {
		cashSale.endDate = new Date();
		guiDao.submitCashSale(cashSale);
	}

	this.cashSaleDeleteItem = function(cashSale, item) {
		if (!item) {
			console.err("no item to delete");
			return;
		}

		item.deleted = true;
	}

	this.cashSaleUndeleteItem = function(cashSale, item) {
		if (!item) {
			console.err("no item to delete");
			return;
		}

		item.deleted = false;
	}

	this.cashSaleUpdateProductItem = function(cashSale, item, qty, value) {
		if (!item) {
			console.err("no item to delete");
			return;
		}

		item.quantity = qty;
		item.setUnitPrice(value);

	}

	this.cashSaleUpdatePaymentItem = function(cashSale, item, qty, value) {

		if (!item) {
			console.err("no item to delete");
			return;
		}

		item.quantity = qty;
		item.setUnitPrice(value);
	}

	this.toggleConsumptionPlace = function() {

		if (this.isOpen()) {
			if (currentSession.cashSale.consumptionPlace == "ON_PLACE") {
				currentSession.cashSale.consumptionPlace = "TAKE_AWAY";
			} else {
				currentSession.cashSale.consumptionPlace = "ON_PLACE";
			}
		} else {
			guiView.clear();
			currentSession.cashSale = guiDao.getNewCashSale();
			this.openCashSale(currentSession.cashSale);
		}
		this.computeCashSale(currentSession.cashSale);
		ticket.display();

	}

}

// cashSale engine
function CashSaleEngine() {

	this.getProductQuantity = function() {
		var quantity = 1;
		if (currentSession.guiSession.calculatorCurrentDigitChain !== "") {
			var inputStringWithoutLeadingOperators = calculator
					.removeLeadingOperators(currentSession.guiSession.calculatorCurrentDigitChain);
			var result = calculator
					.evalChain(inputStringWithoutLeadingOperators);
			if (result) {
				quantity = result;
			}
		}
		return quantity;
	}

	this.getPaymentValue = function() {
		var inputStringWithoutLeadingOperators = calculator
				.removeLeadingOperators(currentSession.guiSession.calculatorCurrentDigitChain);
		var result = calculator.evalChain(inputStringWithoutLeadingOperators);
		return result;
	}

	this.priceOperator = function() {
		currentSession.qtyoperatorFlag = undefined;
		var inputStringWithoutLeadingOperators = calculator
				.removeLeadingOperators(currentSession.guiSession.calculatorCurrentDigitChain);

		var price = calculator.evalChain(inputStringWithoutLeadingOperators);
		if (price) {
			if (currentSession.cashSale.lastItem) {
				currentSession.cashSale.lastItem.setUnitPrice(price);
				currentSession.priceoperatorFlag = undefined;
				currentSession.guiSession.calculatorCurrentDigitChain = "";
				commandCashSale.computeCashSale(currentSession.cashSale);
				ticket.display();
			} else {
				currentSession.guiSession.calculatorCurrentDigitChain = "";
				currentSession.priceoperatorFlag = price;
			}
		}
	}

	this.operatorDigit = function(digit) {
		if (digit == "*") {
			currentSession.priceoperatorFlag = undefined;
			if (currentSession.guiSession.calculatorCurrentDigitChain !== "") {

				var inputStringWithoutLeadingOperators = calculator
						.removeLeadingOperators(currentSession.guiSession.calculatorCurrentDigitChain);

				var qty = calculator
						.evalChain(inputStringWithoutLeadingOperators);
				if (qty) {
					currentSession.qtyoperatorFlag = qty;
				}
			} else {
				currentSession.qtyoperatorFlag = -1;
			}
		}
	}

	this.reset = function() {
		currentSession.guiSession.calculatorCurrentDigitChain = "";
		currentSession.priceoperatorFlag = undefined;
		currentSession.qtyoperatorFlag = undefined;
	}

}

// Calculator
function Calculator() {

	var operators = "+-*/%";

	this.deleteDigit = function() {
		this.reset();
		ticket.display();
	}

	this.concatenateDigit = function(digit) {
		if (digit == 0
				&& (currentSession.guiSession.calculatorCurrentDigitChain === "" || currentSession.guiSession.calculatorCurrentDigitChain === '0')) {
			currentSession.guiSession.calculatorCurrentDigitChain = '0';
		} else {
			currentSession.guiSession.calculatorCurrentDigitChain += digit;
		}
		displayTotal
				.displayCalcResult(currentSession.guiSession.calculatorCurrentDigitChain);
	}

	this.operatorDigit = function(digit) {
		if (currentSession.guiSession.calculatorCurrentDigitChain !== "") {
			var lastChar = currentSession.guiSession.calculatorCurrentDigitChain
					.substr(currentSession.guiSession.calculatorCurrentDigitChain.length - 1);
			if (operators.indexOf(lastChar) >= 0) {
				currentSession.guiSession.calculatorCurrentDigitChain = currentSession.guiSession.calculatorCurrentDigitChain
						.substr(
								0,
								currentSession.guiSession.calculatorCurrentDigitChain.length - 1)
						+ digit
			} else {
				currentSession.guiSession.calculatorCurrentDigitChain += digit;
			}
			displayTotal
					.displayCalcResult(currentSession.guiSession.calculatorCurrentDigitChain);
		}
		cashSaleEngine.operatorDigit(digit);
	}

	this.periodDigit = function() {
		if (currentSession.guiSession.calculatorCurrentDigitChain === "") {
			currentSession.guiSession.calculatorCurrentDigitChain = "0.";
		} else {
			currentSession.guiSession.calculatorCurrentDigitChain += ".";
		}
		displayTotal
				.displayCalcResult(currentSession.guiSession.calculatorCurrentDigitChain);
	}

	this.equalDigit = function() {
		var inputStringWithoutLeadingOperators = this
				.removeLeadingOperators(currentSession.guiSession.calculatorCurrentDigitChain);
		var total = this.evalChain(inputStringWithoutLeadingOperators);
		displayTotal.displayCalcResult(total);
		this.reset();
		currentSession.guiSession.calculatorCurrentDigitChain = "" + total;
	}

	this.evalChain = function(inputStringWithoutLeadingOperators) {

		var chainEval = eval(inputStringWithoutLeadingOperators);
		if (!isNaN(chainEval)) {
			return Math.round(chainEval * 100) / 100;
		}

	}

	this.removeLeadingOperators = function(inputString) {

		var lastCharIndex = 1;
		while (inputString.length > 0 && lastCharIndex >= 0) {
			lastChar = inputString.substr(inputString.length - 1);
			lastCharIndex = operators.indexOf(lastChar);
			if (lastCharIndex >= 0) {
				inputString = inputString.substr(0, inputString.length - 1);
			}
		}
		return inputString;

	}

	this.reset = function() {
		currentSession.guiSession.calculatorCurrentDigitChain = "";
	}
}
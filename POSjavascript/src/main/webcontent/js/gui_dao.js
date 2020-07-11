var SALE_TYPE = 8;
var ITEM_TYPE = 9;
var PRODUCT_TYPE = 7;

var RETRY_DELAY = 60 * 100 // 1 minute

var ID_CAISSE = "9";

var prefix_TIME = 0;
var LAST_UUID_TIME = "";

var SERVER_BASE = 'http://37.59.98.85:8080/POSjavascript/';
var SERVER_BASE = 'http://localhost:8080/POSjavascript/';

var WRITE_CASHSALE_URL = SERVER_BASE + 'rest/pos/writeCashSale';
var READ_PRODUCTS_URL = SERVER_BASE + 'rest/pos/products';
var READ_PAYMENTMETHODS_URL = SERVER_BASE + 'rest/pos/paymentMethods';

function getUUID() {

	var idDate = new Date();

	var UUIDTime = idDate.getFullYear() + (twoDigits(idDate.getMonth() + 1))
			+ (twoDigits(idDate.getDate())) + (twoDigits(idDate.getHours()))
			+ (twoDigits(idDate.getMinutes()))
			+ (twoDigits(idDate.getSeconds())) + ID_CAISSE;

	if (LAST_UUID_TIME == UUIDTime) {
		LAST_UUID_TIME = UUIDTime;
		prefix_TIME = prefix_TIME + 1;
		UUIDTime = prefix_TIME + UUIDTime;
	} else {
		LAST_UUID_TIME = UUIDTime;
		prefix_TIME = 0;
	}

	return UUIDTime;
}

function twoDigits(val) {
	if (val < 10) {
		return "0" + val
	} else {
		return "" + val
	}
}

function GuiDao() {

	this.products;
	this.productTypes;
	this.paymentMethods;

	this.kbdList;

	this.kbdSelRowsList;
	this.kbdSelKeys;
	this.kbds;
	this.kbdSelRows;

	this.init = function() {
		guiDao.productTypes = new Array();
		guiDao.paymentMethods = new Array();
		guiDao.kbdList = JSON.parse(kbdDefJson).kbds;
		guiDao.kbdSelKeys = new Array();
		guiDao.kbds = new Kbds();
		guiDao.kbdSelRows = new KbdSelRows();
		var productTypeArray = JSON.parse(productTypesJson);
		for (var index = 0; index < productTypeArray.productTypes.length; index++) {
			var productFromJson = productTypeArray.productTypes[index];
			var id = productFromJson.id;
			var code = productFromJson.code;
			var name = productFromJson.name;
			var backgroundColor = productFromJson.backgroundColor;
			var textColor = productFromJson.textColor;
			var productType = new ProductTypeModel(id, code, name,
					backgroundColor, textColor);
			guiDao.productTypes.push(productType);
		}
		guiDao.readProducts(guiDao.parseJsonProducts,
				guiDao.parseJsonProductsDefaultTable, productsJson);
		guiDao.readPaymentMethods(guiDao.parseJsonPaymentMethods,
				guiDao.parseJsonPaymentMethodsDefaultTable, paymentMethodsJson);
		for (var kbdIndex = 0; kbdIndex < guiDao.kbdList.length; kbdIndex++) {
			var kbdObj = guiDao.kbdList[kbdIndex];
			var kbd = new Kbd(kbdObj.name, kbdObj.label);
			guiDao.kbds.kbds.push(kbd);
			for (var kbdRowIndex = 0; kbdRowIndex < kbdObj.kbdRows.length; kbdRowIndex++) {
				var kbdRowObj = kbdObj.kbdRows[kbdRowIndex];
				var kbdRow = new KbdRow();
				kbd.kbdRows.push(kbdRow);
				for (var kbdFctIndex = 0; kbdFctIndex < kbdRowObj.kbdFcts.length; kbdFctIndex++) {
					var kbdFctObj = kbdRowObj.kbdFcts[kbdFctIndex];
					var kbdFct = new KbdFct(kbdFctObj.id);
					kbdRow.kbdFcts.push(kbdFct);
				}
			}
		}
	}

	this.parseJsonProductsDefaultTable = function(productsJsonString) {

		console.log("Parse Default Product list : " + productsJsonString);
		var productArray = JSON.parse(productsJsonString);
		guiDao.parseJsonProducts(productArray.products);
		console.log("Default Product list : " + guiDao.products);

	}

	this.parseJsonProducts = function(productArray) {

		if (!productArray) {
			console.log("Readed Product list is empty.");
			return null;
		}

		console.log("Parse Product list : " + productArray);

		guiDao.products = new Array();

		for (var index = 0; index < productArray.length; index++) {

			var productFromJson = productArray[index];

			var id = productFromJson.id;
			var label = productFromJson.name;
			var ticketLabel = productFromJson.name;
			var code = productFromJson.code;
			var name = productFromJson.name;
			var htmlKeyLabel = productFromJson.htmlKeyLabel;
			var type = productFromJson.type;
			var image = productFromJson.image;
			var vatRateOnPlace = productFromJson.codeTva;
			var vatRateTakeAway = productFromJson.codeTva;
			var mini = productFromJson.mini;
			var normal = productFromJson.normal;
			var geant = productFromJson.geant;
			var fitmini = productFromJson.fitmini;
			var fitnormal = productFromJson.fitnormal;
			var fitgeant = productFromJson.fitgeant;
			var webDetail = productFromJson.name;
			var afficheDetail = productFromJson.name;
			var canMerge = productFromJson.canMerge;

			var product = new ProductModel(id, label, ticketLabel, code, name,
					htmlKeyLabel, type, image, vatRateOnPlace, vatRateTakeAway,
					mini, normal, geant, fitmini, fitnormal, fitgeant,
					webDetail, afficheDetail, canMerge);

			guiDao.products.push(product);

			console.log("Product : " + product);

		}
	}

	this.parseJsonPaymentMethodsDefaultTable = function(
			paymentMethodsJsonString) {

		console.log("Parse Default Payment Method list : "
				+ paymentMethodsJsonString);
		var paymentMethodsArray = JSON.parse(paymentMethodsJsonString);
		guiDao.parseJsonPaymentMethods(paymentMethodsArray.paymentMethods);
		console.log("Default Payment Method list : " + guiDao.paymentMethods);

	}

	this.parseJsonPaymentMethods = function(paymentMethodsArray) {

		if (!paymentMethodsArray) {
			console.log("Readed payment Method list is empty.");
			return null;
		}

		console.log("Parse payment Method list : " + paymentMethodsArray);

		guiDao.paymentMethods = new Array();

		for (var index = 0; index < paymentMethodsArray.length; index++) {

			var paymentFromJson = paymentMethodsArray[index];

			var id = paymentFromJson.id;
			var code = paymentFromJson.code;
			var label = paymentFromJson.label;
			var ticketLabel = paymentFromJson.ticketLabel;
			var htmlKeyLabel = paymentFromJson.label;
			var needSomeValue = paymentFromJson.needSomeValue;
			var maxQuantity = paymentFromJson.maxQuantity;
			var maxTotalAmount = paymentFromJson.maxTotalAmount;
			var beAlone = paymentFromJson.beAlone;
			var canMerge = paymentFromJson.canMerge;
			var paymentMethodComputation = paymentFromJson.paymentMethodComputation;

			var paymentMethod = new PaymentMethodModel(id, code, label,
					htmlKeyLabel, ticketLabel, needSomeValue, maxQuantity,
					maxTotalAmount, beAlone, canMerge, paymentMethodComputation);

			guiDao.paymentMethods.push(paymentMethod);

			console.log("paymentMethod : " + paymentMethod);

		}
	}

	this.getNewItemProduct = function(product, quantity, unitPrice, deleted,
			priceCategory) {

		var id = getUUID();

		var unitPriceTakeaway = unitPrice;
		var unitPriceOnplace = Math.round((unitPriceTakeaway * 1.1) * 10) / 10;

		var itemProduct = new ItemProductModel(id, product, quantity, deleted,
				priceCategory, unitPrice);
		return itemProduct

	}

	this.getNewItemPayment = function(paymentMethod, quantity, unitPrice,
			priceCategory) {

		var id = getUUID();

		var itemPayment = new ItemPaymentModel(id, paymentMethod, quantity,
				unitPrice, priceCategory)
		return itemPayment

	}

	this.getNewCashSale = function() {

		var cashSale = new CashSaleModel();
		cashSale.id = getUUID();

		return cashSale

	}

	this.getProductByIndex = function(index) {
		return products[index];
	}

	this.getProductById = function(id) {

		for (var index = 0; index < this.products.length; index++) {
			var product = this.products[index];
			if (product.id === id) {
				return product;
			}
		}
	}

	this.getProductByCode = function(code) {

		for (var index = 0; index < this.products.length; index++) {
			var product = this.products[index];
			if (product.code === code) {
				return product;
			}
		}
	}

	this.getProductTypeByName = function(name) {

		for (var index = 0; index < this.productTypes.length; index++) {
			var productType = this.productTypes[index];
			if (productType.name === name) {
				return productType;
			}
		}
	}

	this.getPaymentMethodById = function(id) {

		for (var index = 0; index < this.paymentMethods.length; index++) {
			var paymentMethod = this.paymentMethods[index];
			if (paymentMethod.id === id) {
				return paymentMethod;
			}
		}

	}

	this.getPaymentMethodByCode = function(code) {

		for (var index = 0; index < this.paymentMethods.length; index++) {
			var paymentMethod = this.paymentMethods[index];
			if (paymentMethod.code === code) {
				return paymentMethod;
			}
		}

	}

	this.getKbdNumberOfRows = function(kbdIndex) {
		return this.kbds.kbds[kbdIndex].kbdRows.length();
	}

	this.getRowNumberOfFcts = function(kbdIndex, kbdRowIndex) {
		return this.kbds.kbds[kbdIndex].kbdRows[kbdRowIndex].kbdFcts.length();
	}

	this.get = function(kbdIndex, kbdRowIndex, kbdFctIndex) {
		return this.kbds.kbds[kbdIndex].kbdRows[kbdRowIndex].kbdFcts[kbdFctIndex].keyId;
	}

	this.getKbd = function(name) {
		var kbds = guiDao.kbds.kbds;

		for (var integer = 0; integer < kbds.length; integer++) {
			var kbd = kbds[integer];
			if (kbd.name == name) {
				return kbd
			}
		}
	}

	this.submitCashSale = function(cashSale) {

		console.log("[commandeSale.submitSale] " + cashSale);

		var id = cashSale.id;
		var cashSaleStatus = cashSale.cashSaleStatus;
		var openDate = cashSale.openDate;
		var endDate = cashSale.endDate;
		var identifier = cashSale.identifier;
		var source = cashSale.source;
		var consumptionPlace = cashSale.consumptionPlace;
		var cashSaleTotal = cashSale.cashSaleTotal;
		var cashSaleExcludVAT = cashSale.cashSaleExcludVAT;
		var cashSaleDeducedExcludVAT = cashSale.cashSaleDeducedExcludVAT;
		var cashSaleFree = cashSale.cashSaleFree;
		var cashSaleLost = cashSale.cashSaleLost;
		var cashSaleTrash = cashSale.cashSaleTrash;
		var cashSalePaymentTotal = cashSale.cashSalePaymentTotal;
		var cashSaleNbArticles = cashSale.cashSaleNbArticles;
		var itemProductJsons;
		var itemPaymentJsons;

		if (cashSale.itemProducts) {

			itemProductJsons = new Array();

			for (var index = 0; index < cashSale.itemProducts.length; index++) {

				var itemProduct = cashSale.itemProducts[index];

				var productId = itemProduct.product.id;
				var quantity = itemProduct.quantity;
				var unitPrice = itemProduct.unitPriceOnplace;
				if (cashSale.consumptionPlace == "TAKE_AWAY") {
					unitPrice = itemProduct.unitPriceTakeaway;
				}
				var deleted = itemProduct.deleted;
				var priceCategory = itemProduct.priceCategory;

				var itemProductJson = new ItemProductJsonModel(productId,
						quantity, unitPrice, deleted, priceCategory);

				itemProductJsons.push(itemProductJson);

			}
		}

		if (cashSale.itemPayments) {

			itemPaymentJsons = new Array();

			for (var index = 0; index < cashSale.itemPayments.length; index++) {

				var itemPayment = cashSale.itemPayments[index];

				var paymentMethodId = itemPayment.paymentMethod.id;
				var quantity = itemPayment.quantity;
				var unitPrice = itemPayment.unitPrice;
				var deleted = itemPayment.deleted;

				var itemPaymentJson = new ItemPaymentJsonModel(paymentMethodId,
						quantity, unitPrice, deleted)

				itemPaymentJsons.push(itemPaymentJson);

			}
		}

		var cashSaleMessageJson = new CashSaleModel(id, cashSaleStatus,
				openDate, endDate, identifier, source, consumptionPlace,
				cashSaleTotal, cashSaleExcludVAT, cashSaleDeducedExcludVAT,
				cashSaleFree, cashSaleLost, cashSaleTrash,
				cashSalePaymentTotal, cashSaleNbArticles, itemProductJsons,
				itemPaymentJsons);

		localStorageQueue.pushLocalStorage(cashSaleMessageJson);

	}

	this.writeCashSale = function(jsonMessage, postAction, posIndex, postError) {

		console.log("[guiDao.writeCashSale]json message : " + jsonMessage);

		$
				.ajax({
					type : 'POST',
					url : WRITE_CASHSALE_URL,
					contentType : "application/json",
					data : jsonMessage,
					timeout : 10000,
					postActionValue : postAction,
					postErrorValue : postError,
					posIndexValue : posIndex,
					crossDomain : true,
					success : function(data) {

						var returnedMessage = "[guiDao.writeCashSale]message["
								+ data.message + "], responseResult["
								+ data.responseResult + "], date[" + data.date
								+ "]";

						console.log(returnedMessage);

						if ("SUCCESS" == data.responseResult
								&& this.postActionValue) {
							this.postActionValue(this.posIndexValue);
						}

					},
					error : function(data) {
						var message = '[guiDao.writeCashSale]La requête n\'a pas abouti : '
								+ data.statusText;
						console.error(message);
						if (this.postErrorValue) {
							this.postErrorValue(message);
						}
					}
				});

	};

	this.readProducts = function(postAction, postError, productsJsonString) {

		console.log("Read produts.");

		$
				.ajax({
					type : 'POST',
					url : READ_PRODUCTS_URL,
					timeout : 10000,
					postActionValue : postAction,
					postErrorValue : postError,
					productsJsonValue : productsJsonString,
					crossDomain : true,
					async : false,
					success : function(data) {

						var returnedMessage = "[guiDao.writeCashSale] " + data;

						if (!returnedMessage) {
							console.log("readProducts - no returned message");
						} else {
							console
									.log("readProducts - returned message length "
											+ returnedMessage.length);
						}

						this.postActionValue(data);

					},
					error : function(data) {

						var message = '[guiDao.readProducts]La requête n\'a pas abouti : '
								+ data.statusText
								+ ' / load default product table';
						console.error(message);
						this.postErrorValue(this.productsJsonValue);

					}
				});

	};

	this.readPaymentMethods = function(postAction, postError,
			paymentMethodsJsonString) {

		console.log("Read payment methods.");

		$
				.ajax({
					type : 'POST',
					url : READ_PAYMENTMETHODS_URL,
					timeout : 10000,
					postActionValue : postAction,
					postErrorValue : postError,
					productsJsonValue : paymentMethodsJsonString,
					crossDomain : true,
					async : false,
					success : function(data) {

						var returnedMessage = "[guiDao.writeCashSale] " + data;

						if (!returnedMessage) {
							console
									.log("readPaymentMethods - no returned message");
						} else {
							console
									.log("readPaymentMethods - returned message length "
											+ returnedMessage.length);
						}

						this.postActionValue(data);

					},
					error : function(data) {

						var message = '[guiDao.readPaymentMethods]La requête n\'a pas abouti : '
								+ data.statusText
								+ ' / load default payment methods table';
						console.error(message);
						this.postErrorValue(this.productsJsonValue);

					}
				});

	};

	this.errorHandler = function(e) {
		var msg = '';

		switch (e.code) {
		case FileError.QUOTA_EXCEEDED_ERR:
			msg = 'QUOTA_EXCEEDED_ERR';
			break;
		case FileError.NOT_FOUND_ERR:
			msg = 'NOT_FOUND_ERR';
			break;
		case FileError.SECURITY_ERR:
			msg = 'SECURITY_ERR';
			break;
		case FileError.INVALID_MODIFICATION_ERR:
			msg = 'INVALID_MODIFICATION_ERR';
			break;
		case FileError.INVALID_STATE_ERR:
			msg = 'INVALID_STATE_ERR';
			break;
		default:
			msg = 'Unknown Error';
			break;
		}
		;

		console.log('Error: ' + msg);
	}
}

function LocalStorageQueue() {

	this.maxSize = localStorage.getItem('maxSize');

	if (!this.maxSize || this.maxSize == 'NaN') {

		this.maxSize = 5;
		localStorage.setItem('maxSize', this.maxSize);

	}

	this.pushLocalStorage = function(action, postError) {

		var posIndex = -1;

		for (var i = 0; i < localStorageQueue.maxSize; i++) {
			var storedAction = localStorage.getItem('action' + i);
			if (storedAction == null) {
				posIndex = i;
				break;
			}
		}

		if (posIndex == -1) {
			posIndex = localStorageQueue.maxSize;
			localStorageQueue.maxSize += 5;
			localStorage.setItem('maxSize', localStorageQueue.maxSize);
		}

		var jsonMessage = JSON.stringify(action);

		localStorage.setItem('action' + posIndex, jsonMessage);

		this.purgeLocalStorage(localStorageQueue.removeLocalStorage, postError);

		return posIndex;

	}

	this.purgeLocalStorage = function(postAction, postError) {

		for (var index = localStorageQueue.maxSize - 1; index >= 0; index--) {
			var action = localStorage.getItem('action' + index);
			var sentTime = localStorage.getItem('sent' + index);
			var actualTime = Date.now();
			if (action != null
					&& (sentTime == null || sentTime < actualTime - RETRY_DELAY)) {
				var jsonMessage = localStorage.getItem('action' + index);
				localStorage.setItem('sent' + index, Date.now());
				guiDao.writeCashSale(jsonMessage, postAction, index, postError);
			}

		}

	}

	this.removeLocalStorage = function(posIndex) {

		localStorage.removeItem('action' + posIndex);
		localStorage.removeItem('sent' + posIndex);

		var storedAction = null;
		for (var index = localStorageQueue.maxSize - 1; index >= 5
				&& storedAction == null; index--) {
			storedAction = localStorage.getItem('action' + index);
			if (storedAction == null) {
				localStorage.removeItem('sent' + index);
				localStorageQueue.maxSize = index + 1;
				localStorage.setItem('maxSize', localStorageQueue.maxSize);
			}
		}

	}

	this.clearLocalStorage = function() {
		localStorage.clear();
		localStorageQueue.maxSize = 5;
		localStorage.setItem('maxSize', localStorageQueue.maxSize);
	}

	this.getAll = function() {

		var actionList = new Array();

		for (var i = 0; i < localStorageQueue.maxSize; i++) {
			var action = localStorage.getItem('action' + i) + " / sent : "
					+ localStorage.getItem('sent' + i);
			actionList.push(action);
		}

		return actionList;
	}

	this.listLocalStorageQueue = function() {

		var actionList = this.getAll();
		console.log("html.listLocalStorageQueue]action list size "
				+ actionList.length);

		for (var i = 0; i < actionList.length; i++) {
			var action = actionList[i];
			console.log("local storage " + i + " : " + action);
		}
	}

}
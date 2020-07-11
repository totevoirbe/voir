var DEFAULT_PRICE_CATEGORY = "SDWNORMAL";

function ProductTypeModel(id, code, name, backgroundColor, textColor) {
	this.id = id;
	this.code = code;
	this.name = name;
	this.backgroundColor = backgroundColor;
	this.textColor = textColor;
}

function ProductModel(id, label, ticketLabel, code, name, htmlKeyLabel, type,
		image, vatRateOnPlace, vatRateTakeAway, mini, normal, geant, fitmini,
		fitnormal, fitgeant, webDetail, afficheDetail, canMerge) {

	this.id = id;
	this.label = label;
	this.ticketLabel = ticketLabel;
	this.code = code;
	this.name = name;
	this.htmlKeyLabel = htmlKeyLabel;
	this.type = type;
	this.image = image;
	this.vatRateOnPlace = vatRateOnPlace;
	this.vatRateTakeAway = vatRateTakeAway;
	this.mini = mini;
	this.normal = normal;
	this.geant = geant;
	this.fitmini = fitmini;
	this.fitnormal = fitnormal;
	this.fitgeant = fitgeant;
	this.webDetail = webDetail;
	this.afficheDetail = afficheDetail;
	this.canMerge = canMerge;

	this.toString = function() {

		return "id[" + this.id + "],label[" + this.label + "], ticketLabel["
				+ this.ticketLabel + "], code[" + this.code + "], name["
				+ this.name + "], htmlKeyLabel[" + this.htmlKeyLabel + "]";

	}

	this.getTicketLabel = function(productType) {

		if (productType == "SDWMINI") {
			return label + " (mini)";
		} else if (productType == "SDWGEANT") {
			return label + " (géant)";
		} else if (productType == "FITMINI") {
			return label + " (fit.mini)";
		} else if (productType == "FITNORMAL") {
			return label + " (fitness)";
		} else if (productType == "FITGEANT") {
			return label + " (fit.géant)";
		}

		return label;
	}

	this.getPrice = function(productType) {

		var price = normal;

		if (productType == "SDWMINI") {
			price = mini;
		} else if (productType == "SDWNORMAL") {
			price = normal;
		} else if (productType == "SDWGEANT") {
			price = geant;
		} else if (productType == "FITMINI") {
			price = fitmini;
		} else if (productType == "FITNORMAL") {
			price = fitnormal;
		} else if (productType == "FITGEANT") {
			price = fitgeant;
		}

		if (price === undefined)
			price = normal;

		return price;
	}

}

function PaymentMethodModel(id, code, label, htmlKeyLabel, ticketLabel,
		needSomeValue, maxQuantity, maxTotalAmount, beAlone, canMerge,
		paymentMethodComputation) {

	this.id = id;
	this.code = code;
	this.label = label;
	this.htmlKeyLabel = htmlKeyLabel;
	this.ticketLabel = ticketLabel;
	this.needSomeValue = needSomeValue;
	this.maxQuantity = maxQuantity;
	this.maxTotalAmount = maxTotalAmount;
	this.beAlone = beAlone;
	this.canMerge = canMerge;
	this.paymentMethodComputation = paymentMethodComputation;

	this.toString = function() {

		return "id[" + this.id + "], code[" + this.code + "],label["
				+ this.label + "], htmlKeyLabel[" + this.htmlKeyLabel
				+ "], ticketLabel[" + this.ticketLabel + "], needSomeValue["
				+ this.needSomeValue + "], needSomeValue[" + this.needSomeValue
				+ "], maxQuantity[" + this.maxQuantity + "], maxTotalAmount["
				+ this.maxTotalAmount + "], beAlone[" + this.beAlone
				+ "], canMerge[" + this.canMerge
				+ "], paymentMethodComputation["
				+ this.paymentMethodComputation + "]";

	}

}

function ItemProductModel(id, product, quantity, deleted, priceCategory,
		unitPrice) {

	this.setUnitPrice = function(unitPrice) {
		this.unitPriceTakeaway = unitPrice;
		this.unitPriceOnplace = Math.round((unitPrice * 1.1) * 10) / 10;
	}
	this.id = id;
	this.product = product;
	this.quantity = quantity;
	this.deleted = deleted;
	this.setUnitPrice(unitPrice);
	this.itemSelected;
	if (priceCategory) {
		this.priceCategory = priceCategory;
	} else {
		this.priceCategory = DEFAULT_PRICE_CATEGORY;
	}
	this.reconcilId // link with pay

	this.toString = function() {

		return "id[" + this.id + "],product[" + this.product + "], quantity["
				+ this.quantity + "], unitPrice[" + this.unitPrice
				+ "], deleted[" + this.deleted + "], priceCategory["
				+ this.priceCategory + "]";

	}

}

function ItemProductJsonModel(productId, quantity, unitPrice, deleted,
		priceCategory) {

	this.productId = productId;
	this.quantity = quantity;
	this.unitPrice = unitPrice;
	this.deleted = deleted;
	this.priceCategory = priceCategory;

	this.toString = function() {

		return "productId[" + this.productId + "], quantity[" + this.quantity
				+ "], unitPrice[" + this.unitPrice + "], deleted["
				+ this.deleted + "], priceCategory[" + this.priceCategory + "]";

	}

}

function ItemPaymentModel(id, paymentMethod, quantity, unitPrice, deleted) {

	this.id = id;
	this.paymentMethod = paymentMethod;
	this.quantity = quantity;
	this.unitPrice = unitPrice;
	this.deleted = deleted;
	this.itemSelected;
	this.reconcilId // link with pay

	this.setUnitPrice = function(unitPrice) {

		this.unitPrice = unitPrice;

	}

	this.toString = function() {

		return "id[" + this.id + "],paymentMethod[" + this.paymentMethod
				+ "], quantity[" + this.quantity + "], unitPrice["
				+ this.unitPrice + "], deleted[" + this.deleted + "]";

	}

}

function ItemPaymentJsonModel(paymentMethod, quantity, unitPrice, deleted) {

	this.paymentMethod = paymentMethod;
	this.quantity = quantity;
	this.unitPrice = unitPrice;
	this.deleted = deleted;

	this.toString = function() {

		return "paymentMethod[" + this.paymentMethod + "], quantity["
				+ this.quantity + "], unitPrice[" + this.unitPrice
				+ "], deleted[" + this.deleted + "]";

	}

}

function CashSaleModel(id, cashSaleStatus, openDate, endDate, identifier,
		source, consumptionPlace, cashSaleTotal, cashSaleExcludVAT,
		cashSaleDeducedExcludVAT, cashSaleFree, cashSaleLost, cashSaleTrash,
		cashSalePaymentTotal, cashSaleNbArticles, itemProducts, itemPayments) {

	this.id = id;
	this.cashSaleStatus = cashSaleStatus; // {undefined}, OPEN, DONE, CANCEL
	this.openDate = openDate;
	this.endDate = endDate;
	this.identifier = identifier;
	this.source = source;
	this.consumptionPlace = consumptionPlace; // ON_PLACE, TAKE_AWAY

	this.cashSaleTotal = cashSaleTotal;
	this.cashSaleExcludVAT = cashSaleExcludVAT;
	this.cashSaleDeducedExcludVAT = cashSaleDeducedExcludVAT;
	this.cashSaleFree = cashSaleFree;
	this.cashSaleLost = cashSaleLost;
	this.cashSaleTrash = cashSaleTrash;
	this.cashSalePaymentTotal = cashSalePaymentTotal;
	this.cashSaleNbArticles = cashSaleNbArticles;

	this.itemProducts = itemProducts;
	this.itemPayments = itemPayments;

	this.nextReconcilId = 1 // group pay and product - 0 is reserved for main

}

function FrontJsonCheckModel(cashSaleJsons, cashSaleValuatorOPEN,
		cashSaleValuatorCANCEL, cashSaleValuatorDONE) {

	this.cashSaleJsons = cashSaleJsons;

	this.cashSaleValuatorOPEN = cashSaleValuatorOPEN;
	this.cashSaleValuatorCANCEL = cashSaleValuatorCANCEL;
	this.cashSaleValuatorDONE = cashSaleValuatorDONE;

}

function CashSaleValuatorModel(count, cashSaleTotal, paymentTotal, nbArticles,
		remainValue) {

	this.count = count;
	this.cashSaleTotal = cashSaleTotal;
	this.paymentTotal = paymentTotal;
	this.nbArticles = nbArticles;
	this.remainValue = remainValue;

}

function Session() {

	this.sessionId;
	this.cashSale;
	this.guiSession;
	this.priceoperatorFlag;
	this.qtyoperatorFlag;

}

function GuiSession() {

	this.currentKbdName;
	this.calculatorCurrentDigitChain;
	this.currentKbdButton;

}

function Kbds() {

	this.kbds = new Array();

}

function Kbd(name, label) {

	this.kbdRows = new Array();
	this.name = name;
	this.label = label;

}

function KbdRow() {

	this.kbdFcts = new Array();

}

function KbdFct(keyId) {

	this.keyId = keyId;

}

function KbdSelRows() {

	this.kbdSelRows = new Array();

}

function KbdSelRow() {

	this.kbdSelKeys = new Array();

}

function KbdSelKey(keyId, label, title, img) {

	this.keyId = keyId;
	this.label = label;
	this.title = title;
	this.img = img;

}
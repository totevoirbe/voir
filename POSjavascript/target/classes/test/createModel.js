function getRandomProduct() {

	var index = Math.round(Math.random() * guiDao.products.length);
	var product = guiDao.products[index];
	return product;

}

function getRandomPaymentMethod() {

	var index = Math.round(Math.random() * guiDao.paymentMethods.length);
	if (index >= guiDao.paymentMethods.length) {
		index = guiDao.paymentMethods.length - 1;
	}
	var paymentMethod = guiDao.paymentMethods[index];
	return paymentMethod;

}

function createItemProduct() {

	var id = getUUID();

	var product = getRandomProduct();

	var quantity = 6;
	var unitPrice = product.normal;
	var deleted = false;
	var priceCategory = "SDWNORMAL";
	var unitPriceTakeaway = unitPrice;

	var itemProduct = new ItemProductModel(id, product, quantity, deleted,
			priceCategory, unitPrice);

	return itemProduct;

}

function createItemPayment() {

	var id = getUUID();

	var paymentMethod = getRandomPaymentMethod();
	var quantity = 6;
	var unitPrice = 2.3;
	var deleted = false;

	var itemPayment = new ItemPaymentModel(id, paymentMethod, quantity,
			unitPrice, deleted);

	return itemPayment;

}

function createCashSale() {

	var id = getUUID();
	var cashSaleStatus = 'DONE';// {undefined}, OPEN, DONE, CANCEL}
	var openDate = new Date();
	var endDate = new Date();
	var identifier = id;
	var source = 'test';
	var consumptionPlace = 'ON_PLACE';
	var cashSaleTotal = 0.1;
	var cashSaleExcludVAT = 0.2;
	var cashSaleDeducedExcludVAT = 0.3;
	var cashSaleFree = 0.4;
	var cashSaleLost = 0.5;
	var cashSaleTrash = 0.6;
	var cashSalePaymentTotal = 0.7;
	var cashSaleNbArticles = 0.8;

	var itemPayments = new Array(createItemPayment());
	var itemProducts = new Array(createItemProduct());

	var cashSale = new CashSaleModel(id, cashSaleStatus, openDate, endDate,
			identifier, source, consumptionPlace, cashSaleTotal,
			cashSaleExcludVAT, cashSaleDeducedExcludVAT, cashSaleFree,
			cashSaleLost, cashSaleTrash, cashSalePaymentTotal,
			cashSaleNbArticles, itemProducts, itemPayments);

	return cashSale;

}

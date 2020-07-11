SQL SELECT
	s.cashSaleStatus sCashSaleStatus,
	s.cashSaleTotal sCashSaleTotal,
	s.saleExcludVAT sSaleExcludVAT,
	Year(s.endDate) as sYear,
	Ceil(Month(s.endDate)/3)as sQuarter,
	Month(s.endDate) as sMonth,
	Week(s.endDate) as sWeek,
	weekDay(s.endDate) as sWeekday,
	DATE_FORMAT(s.endDate, '%Y-%m-%d') sDateOnly,
	s.identifier sIdentifier,
	s.nbArticles sNbArticles,
	s.openDate sOpenDate,
	s.paySubTotal sPaySubTotal,
	s.paymentTotal sPaymentTotal,
	s.remainValue sRemainValue,
	s.source sSource,
	s.takeOnPlace sTakeOnPlace,
	iprod.deleted iprodDeleted,
	iprod.priceCategory iprodPriceCategory,
	iprod.quantity iprodQuantity,
	iprod.unitPrice iprodUnitPrice,
	iprod.quantity * iprod.unitPrice iprodTotal,
	prod.afficheDetail prodAfficheDetail,
	prod.canMerge prodCanMerge,
	prod.code prodCode,
	prod.vatRateOnPlace_vat_id vatRateOnplace,
	prod.vatRateTakeAway_vat_id vatRateAmay,
	prod.fitgeant prodFitgeant,
	prod.fitmini prodFitmini,
	prod.fitnormal prodFitnormal,
	prod.geant prodGeant,
	prod.htmlKeyLabel prodHtmlKeyLabel,
	prod.image prodImage,
	prod.label prodLabel,
	prod.mini prodMini,
	prod.name prodName,
	prod.normal prodNormal,
	prod.ticketLabel prodTicketLabel,
	prod.type prodType,
	prod.webDetail prodWebDetail,
	ipay.deleted,
	ipay.quantity,
	ipay.unitPrice,
	pay.canMerge,
	pay.htmlKeyLabel,
	pay.label,
	pay.ticketLabel
FROM 
	cashsale as s,
	cashsale_itemproduct,
	itemproduct as iprod,
	product as prod,
	cashsale_itempayment,
	itempayment as ipay,
	paymentmethod as pay
WHERE
	s.cashSaleStatus = 2 AND iprod.deleted IS NULL
    AND s.cashsale_ID = cashsale_itemproduct.CashSaleModel_cashsale_ID
	AND cashsale_itemproduct.itemProducts_itemproduct_id = iprod.itemproduct_id
	AND iprod.product_product_id = prod.product_id
	AND s.cashsale_ID = cashsale_itempayment.CashSaleModel_cashsale_ID
	AND cashsale_itempayment.itemPayments_itempayment_id = ipay.itempayment_id
	AND ipay.paymentMethod_paymentmethod_id = pay.paymentmethod_id;

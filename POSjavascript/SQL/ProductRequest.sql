SELECT
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
	prod.webDetail prodWebDetail
FROM 
	cashsale as s,
	cashsale_itemproduct,
	itemproduct as iprod,
	product as prod
WHERE
	s.cashSaleStatus = 2 AND iprod.deleted IS NULL
    AND s.cashsale_ID = cashsale_itemproduct.CashSaleModel_cashsale_ID
	AND cashsale_itemproduct.itemProducts_itemproduct_id = iprod.itemproduct_id
	AND iprod.product_product_id = prod.product_id;
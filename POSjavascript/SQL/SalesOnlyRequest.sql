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
	s.takeOnPlace sTakeOnPlace
FROM 
	cashsale as s
WHERE
	s.cashSaleStatus = 2;

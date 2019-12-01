export enum PriceCategory {
	SdwMini = 'SDWMINI',
	SdwNormal = 'SDWNORMAL',
	SdwGeant = 'SDWGEANT',
	SdwFitMini = 'FITMINI',
	SdwFitNormal = 'FITNORMAL',
	SdwFitGeant = 'FITGEANT'
}

export enum PeriodType {
	year = 'YEAR',
	month = 'MONTH',
	day = 'DAY',
	hour = 'HOUR',
	cashSaleUnit = 'CASH_SALE_UNIT'
}

export enum CashSaleStatus {

	open = 'OPEN',
	cancel = 'CANCEL',
	done = 'DONE'

}

export enum PaymentMethod {
	free = 'FREE',
	add = 'ADD',
	lost = 'LOST',
	trash = 'TRASH'
}
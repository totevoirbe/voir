import { CashSaleStatus } from "./enumValues";

export class CashSale {

	constructor(
		public id,
		public cashSaleStatus: CashSaleStatus,
		public openDate: Date,
		public endDate: Date,
		public identifier,
		public source,
		public consumptionPlace,
		public cashSaleTotal: number,
		public cashSaleExcludVAT: number,
		public cashSaleDeducedExcludVAT: number,
		public cashSaleFree: number,
		public cashSaleLost: number,
		public cashSaleTrash: number,
		public cashSalePaymentTotal: number,
		public cashSaleNbArticles: number,
		public itemProducts,
		public itemPayments) {

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

	}
}
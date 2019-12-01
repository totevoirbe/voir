import { PaymentMethod } from './enumValues';

export class itemPayment {
	constructor(
		public id: number,
		public paymentMethod: PaymentMethod,
		public quantity,
		public unitPrice,
		public deleted) {

		this.id = id;
		this.paymentMethod = paymentMethod;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.deleted = deleted;

	}
}
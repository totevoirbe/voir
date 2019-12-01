import { Product } from './product';
import { PriceCategory } from './enumValues';

export class ItemProduct {

	constructor(
		public id: number,
		public product: Product,
		public quantity: number,
		public deleted: boolean,
		public priceCategory: PriceCategory,
		public unitPrice: number) {

		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.deleted = deleted;
		this.unitPrice = unitPrice;
		this.priceCategory = priceCategory;
	}
}
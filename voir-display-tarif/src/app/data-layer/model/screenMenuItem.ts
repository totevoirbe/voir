import { Product } from './product';

export class ScreenMenuItem {

	constructor(
		public id: number,
		public page: string,
		public product: Product,
		public title: string
	) {
		this.id = id;
		this.page = page;
		this.product = product;
		this.title = title;
	}
}

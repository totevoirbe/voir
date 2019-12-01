import { ProductCategoryTag } from './product-category-tag';
import { VatRate } from './vat-rate';

export class Product {

	constructor(
		public id: number,
		public label: string,
		public ticketLabel: string,
		public code: string,
		public name: string,
		public htmlKeyLabel: string,
		public productCategoryTags: ProductCategoryTag[],
		public image: string,
		public vatRateOnPlace: VatRate,
		public vatRateTakeAway: VatRate,
		public mini: number,
		public normal: number,
		public geant: number,
		public fitmini: number,
		public fitnormal: number,
		public fitgeant: number,
		public webDetail: string,
		public afficheDetail: string,
		public canMerge: boolean,
	) {
		this.id = id;
		this.label = label;
		this.ticketLabel = ticketLabel;
		this.code = code;
		this.name = name;
		this.htmlKeyLabel = htmlKeyLabel;
		this.productCategoryTags = productCategoryTags;
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
	}
}

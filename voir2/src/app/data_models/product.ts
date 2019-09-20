import { PriceCategory } from './price-category';

export interface ProductCategoryTag {
	tag: string;
}

export class Product {

	public productCategoryTags: ProductCategoryTag[] = [{ tag: 'assiettes' }, { tag: 'autre' }, { tag: 'baguette' }, { tag: 'boisson' }, { tag: 'chèvre' }, { tag: 'classiques' }, { tag: 'dessert' }, { tag: 'mer' }, { tag: 'panini' }, { tag: 'poulet' }, { tag: 'salade' }, { tag: 'tartiner' }, { tag: 'salaison' }, { tag: 'sandwich' }, { tag: 'sud' }, { tag: 'suggestion' }, { tag: 'viennoiserie' }];

	constructor(
		public id: number,
		public label: string,
		public ticketLabel: string,
		public code: string,
		public name: string,
		public htmlKeyLabel: string,
		public categoryTags: ProductCategoryTag[],
		public image: string,
		public vatRateOnPlace: number,
		public vatRateTakeAway: number,
		public mini: number,
		public normal: number,
		public geant: number,
		public fitmini: number,
		public fitnormal: number,
		public fitgeant: number,
		public webDetail: string,
		public afficheDetail: string,
		public canMerge: boolean,
		public hiddenIndex: string
	) {
		this.id = id;
		this.label = label;
		this.ticketLabel = ticketLabel;
		this.code = code;
		this.name = name;
		this.htmlKeyLabel = htmlKeyLabel;
		this.categoryTags = categoryTags;
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

	getTicketLabel(pricecategory: PriceCategory): string {

		if (PriceCategory.SdwMini === pricecategory) {
			return this.label + ' (mini)';
		} else if (PriceCategory.SdwNormal === pricecategory) {
			return this.label;
		} else if (PriceCategory.SdwGeant === pricecategory) {
			return this.label + ' (géant)';
		} else if (PriceCategory.SdwFitMini === pricecategory) {
			return this.label + ' (fit.mini)';
		} else if (PriceCategory.SdwFitNormal === pricecategory) {
			return this.label + ' (fitness)';
		} else if (PriceCategory.SdwFitGeant === pricecategory) {
			return this.label + ' (fit.géant)';
		}

		console.error('Not a category');

	}

	getPrice(pricecategory: PriceCategory): number {

		let price: number;

		if (PriceCategory.SdwMini === pricecategory) {
			price = this.mini;
		} else if (PriceCategory.SdwNormal === pricecategory) {
			price = this.normal;
		} else if (PriceCategory.SdwGeant === pricecategory) {
			price = this.geant;
		} else if (PriceCategory.SdwFitMini === pricecategory) {
			price = this.fitmini;
		} else if (PriceCategory.SdwFitNormal === pricecategory) {
			price = this.fitnormal;
		} else if (PriceCategory.SdwFitGeant === pricecategory) {
			price = this.fitgeant;
		}

		if (price === undefined) {
			price = this.normal;
		}

		return price;
	}

}

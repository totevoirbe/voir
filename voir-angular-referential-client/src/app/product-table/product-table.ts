import { Product } from 'src/product-service/model/product';
import { ProductCategoryTag } from 'src/product-service/model/product-category-tag';
import { VatRate } from 'src/product-service/model/vat-rate';

export class ProductTable extends Product {

    public productCategoryTagsAsString: string;
    public vatRateOnPlaceAsString: string;
    public vatRateTakeAwayAsString: string;

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
        super(
            id,
            label,
            ticketLabel,
            code,
            name,
            htmlKeyLabel,
            productCategoryTags,
            image,
            vatRateOnPlace,
            vatRateTakeAway,
            mini,
            normal,
            geant,
            fitmini,
            fitnormal,
            fitgeant,
            webDetail,
            afficheDetail,
            canMerge);
    }
}

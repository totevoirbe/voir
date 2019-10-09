export class VatRate {
    constructor(
        public code: string,
        public label: string,
        public rate: number
    ) {
        this.code = code;
        this.label = label;
        this.rate = rate;
    }
}

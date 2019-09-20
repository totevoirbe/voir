export class ProductType {
	constructor(
		public id: string,
		public code: string,
		public name: string,
		public backgroundColor: string,
		public textColor: string
	) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.backgroundColor = backgroundColor;
		this.textColor = textColor;
	}
}

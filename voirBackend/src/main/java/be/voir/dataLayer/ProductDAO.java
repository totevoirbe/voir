package be.voir.dataLayer;

public class ProductDAO {

	public enum ProductXlsField {

		CODE("code"), NAME("name"), HTMLKEYLABEL("htmlKeyLabel"), PRODUCTVATEGORYTAGS("productCategoryTags"),
		IMAGE("image"), VATRATETAKEAWAY("vatRateTakeAway"), VATRATEONPLACE("vatRateOnPlace"), MINI("mini"),
		NORMAL("normal"), GEANT("geant"), FITMINI("fitmini"), FITNORMAL("fitnormal"), FITGEANT("fitgeant"),
		TICKETLABEL("ticketLabel"), WEBDETAIL("webDetail"), AFFICHEDETAIL("afficheDetail"), CANMERGE("canMerge");

		public String colHeader;

		ProductXlsField(String colHeader) {
			this.colHeader = colHeader;
		}

		public String getColHeader() {
			return colHeader;
		}

	}

}

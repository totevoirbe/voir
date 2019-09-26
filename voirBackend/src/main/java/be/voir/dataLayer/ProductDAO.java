package be.voir.dataLayer;

public class ProductDAO {

	public enum ProductXlsField {

		CODE("code"), NAME("name"), PRODUCTVATEGORYTAGS("productCategoryTags"), VATRATEONPLACE("vatRateOnPlace"),
		VATRATETAKEAWAY("vatRateTakeAway"), MINI("mini"), NORMAL("normal"), GEANT("geant"), FITMINI("fitmini"),
		FITNORMAL("fitnormal"), FITGEANT("fitgeant"), IMAGE("image"), HTMLKEYLABEL("htmlKeyLabel"),
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

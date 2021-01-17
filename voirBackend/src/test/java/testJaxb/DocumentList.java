package testJaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "documentlist")
public class DocumentList {

	private Long date;
	private List<Document> document;

	@XmlAttribute
	public void setDate(Long date) {
		this.date = date;
	}

	public List<Document> getDocument() {
		return document;
	}

	public void setDocument(List<Document> document) {
		this.document = document;
	}

	public Long getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "DocumentList [date=" + date + ", document=" + document + "]";
	}
}
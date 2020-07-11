package be.panidel.pos10.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "documentlist")
public class DocumentList {

	@XmlAttribute
	private String date;

	private Collection<Document> document;

	@Override
	public String toString() {
		return "DocumentList [date=" + date + ", documents=" + document + "]";
	}

	public String getDate() {
		return date;
	}

	public Collection<Document> getDocument() {
		return document;
	}

	public void setDocument(Collection<Document> document) {
		this.document = document;
	}

}

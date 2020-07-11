package be.panidel.dataLayer.helper;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class GlobalNamingResources {

	List<Resource> resources;

	@XmlElement(name = "Resource")
	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "GlobalNamingResources [resources=" + resources + "]";
	}

}

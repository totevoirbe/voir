package be.panidel.dataLayer.helper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Server")
public class Server {

	GlobalNamingResources globalNamingResources;

	@XmlElement(name = "GlobalNamingResources")
	public GlobalNamingResources getGlobalNamingResources() {
		return globalNamingResources;
	}

	public void setGlobalNamingResources(GlobalNamingResources globalNamingResources) {
		this.globalNamingResources = globalNamingResources;
	}

	@Override
	public String toString() {
		return "Server [globalNamingResources=" + globalNamingResources + "]";
	}

}

package be.panidel.frontLayer.restService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/simpleCheck")
public class RestPosSimpleCheck {

	private final static Logger LOG = LoggerFactory.getLogger(RestPosSimpleCheck.class);

	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public Message getXML() {

		LOG.debug("Check simple rest service XML");

		Message message = new Message();
		message.setSummary("Simple rest check");
		message.setDescription("POS Service");
		return message;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Message getJSON() {

		LOG.debug("Check simple rest service JSON");

		Message message = new Message();
		message.setSummary("Simple rest check");
		message.setDescription("POS Service");
		return message;
	}

	@GET
	@Produces({ MediaType.TEXT_XML })
	public Message getHTML() {

		LOG.debug("Check simple rest service TEXT");

		Message message = new Message();
		message.setSummary("Simple rest check");
		message.setDescription("POS Service");
		return message;
	}

}

@XmlRootElement
class Message {
	private String summary;
	private String description;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
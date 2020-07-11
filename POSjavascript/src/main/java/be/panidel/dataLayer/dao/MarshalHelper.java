package be.panidel.dataLayer.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarshalHelper {

	private final static Logger LOG = LoggerFactory.getLogger(MarshalHelper.class);

	public static void marchalToXml(Object obj, File file) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

		jaxbMarshaller.marshal(obj, file);

	}

	public static void marchalToXml(Object obj, OutputStream outputStream) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

		jaxbMarshaller.marshal(obj, outputStream);

	}

	public static Object unmarchalXml(Class<?> clazz, InputStream inputStream) throws JAXBException {

		Object obj = null;

		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		obj = jaxbUnmarshaller.unmarshal(inputStream);

		return obj;

	}

	public static Object unmarchalXml(Class<?> clazz, File file) throws JAXBException {

		Object obj = null;

		JAXBContext jaxbContext = null;
		Unmarshaller jaxbUnmarshaller = null;
		InputStream inputStream = null;

		try {

			jaxbContext = JAXBContext.newInstance(clazz);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			if (!file.exists()) {
				inputStream = MarshalHelper.class.getClassLoader().getResourceAsStream(file.getName());
				obj = jaxbUnmarshaller.unmarshal(inputStream);
			} else {
				obj = jaxbUnmarshaller.unmarshal(file);
			}

		} finally {

			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					LOG.error(file.getName());
				}
			}

		}

		return obj;

	}

}

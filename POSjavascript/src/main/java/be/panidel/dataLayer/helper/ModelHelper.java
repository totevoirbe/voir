package be.panidel.dataLayer.helper;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.model.PaymentMethodModel;
import be.panidel.dataLayer.model.ProductModel;
import be.panidel.dataLayer.model.VatRateModel;

public class ModelHelper {

	private final static Logger LOG = LoggerFactory.getLogger(ModelHelper.class);

	enum CheckNullCases {
		RETURN_FALSE, RETURN_TRUE, CHECK
	};

	public static boolean checkEquals(ProductModel value, ProductModel valueEval) {

		switch (checkNullCases(value, valueEval)) {
		case RETURN_FALSE:
			LOG.debug("check null false");
			return false;
		case RETURN_TRUE:
			return true;
		case CHECK:
			break;
		}

		if (!value.equals(valueEval)) {
			LOG.debug(value + "<>" + valueEval);
			return false;
		}
		return true;

	}

	public static boolean checkEquals(PaymentMethodModel value, PaymentMethodModel valueEval) {

		switch (checkNullCases(value, valueEval)) {
		case RETURN_FALSE:
			LOG.debug("check null false");
			return false;
		case RETURN_TRUE:
			return true;
		case CHECK:
			break;
		}

		if (!value.equals(valueEval)) {
			LOG.debug(value + "<>" + valueEval);
			return false;
		}
		return true;

	}

	public static boolean checkEquals(VatRateModel value, VatRateModel valueEval) {

		switch (checkNullCases(value, valueEval)) {
		case RETURN_FALSE:
			LOG.debug("check null false");
			return false;
		case RETURN_TRUE:
			return true;
		case CHECK:
			break;
		}

		if (!value.equals(valueEval)) {
			LOG.debug(value + "<>" + valueEval);
			return false;
		}
		return true;

	}

	public static boolean checkEquals(Enum<?> value, Enum<?> valueEval) {

		switch (checkNullCases(value, valueEval)) {
		case RETURN_FALSE:
			LOG.debug("check null false");
			return false;
		case RETURN_TRUE:
			return true;
		case CHECK:
			break;
		}

		if (!value.equals(valueEval)) {
			LOG.debug(value.name() + "<>" + valueEval.name());
			return false;
		}
		return true;

	}

	public static boolean checkEquals(String value, String valueEval) {

		switch (checkNullCases(value, valueEval)) {
		case RETURN_FALSE:
			LOG.debug("check null false");
			return false;
		case RETURN_TRUE:
			return true;
		case CHECK:
			break;
		}

		if (!value.equals(valueEval)) {
			LOG.debug(value + "<>" + valueEval);
			return false;
		}
		return true;
	}

	public static boolean checkEquals(Long value, Long valueEval) {

		switch (checkNullCases(value, valueEval)) {
		case RETURN_FALSE:
			LOG.debug("check null false");
			return false;
		case RETURN_TRUE:
			return true;
		case CHECK:
			break;
		}

		if (value.compareTo(valueEval) != 0) {
			LOG.debug(value.doubleValue() + "<>" + valueEval.doubleValue());
			return false;
		}

		return true;
	}

	public static boolean checkEquals(BigDecimal value, BigDecimal valueEval) {

		switch (checkNullCases(value, valueEval)) {
		case RETURN_FALSE:
			if (value == null && valueEval != null && !ModelHelper.checkEquals(BigDecimal.ZERO, valueEval)) {
				return true;
			}
			if (value != null && valueEval == null && !BigDecimal.ZERO.equals(value)) {
				return true;
			}
			LOG.debug("check null false");
			return false;
		case RETURN_TRUE:
			return true;
		case CHECK:
			break;
		}

		if (value.compareTo(valueEval.subtract(new BigDecimal(0.10))) < 0
				|| value.compareTo(valueEval.add(new BigDecimal(0.10))) > 0) {
			LOG.debug(value.doubleValue() + "<>" + valueEval.doubleValue());
			return false;
		}

		return true;
	}

	public static boolean checkEquals(Boolean value, Boolean valueEval) {

		switch (checkNullCases(value, valueEval)) {
		case RETURN_FALSE:
			LOG.debug("check null false");
			return false;
		case RETURN_TRUE:
			return true;
		case CHECK:
			break;
		}

		if (!value.equals(valueEval)) {
			LOG.debug(value.booleanValue() + "<>" + valueEval.booleanValue());
			return false;
		}

		return true;
	}

	public static boolean checkEquals(Date value, Date valueEval) {

		switch (checkNullCases(value, valueEval)) {
		case RETURN_FALSE:
			LOG.debug("check null false");
			return false;
		case RETURN_TRUE:
			return true;
		case CHECK:
			break;
		}

		long valueInSec = value.getTime() / 1000;
		long valueEvalInSec = valueEval.getTime() / 1000;

		if (valueInSec < (valueEvalInSec - 1) || valueInSec > (valueEvalInSec + 1)) {
			LOG.debug(valueInSec + "<>" + valueEvalInSec);
			return false;
		}

		return true;

	}

	public static boolean checkEquals(Collection<?> collection, Collection<?> collectionEval) {

		switch (checkNullCases(collection, collectionEval)) {
		case RETURN_FALSE:
			LOG.debug("check null false");
			return false;
		case RETURN_TRUE:
			return true;
		case CHECK:
			break;
		}

		if (collection.size() != collectionEval.size()) {
			LOG.debug(collection.size() + "<>" + collectionEval.size());
			return false;
		}

		for (Iterator<?> itemsIterator = collection.iterator(); itemsIterator.hasNext();) {
			Object valueObject = itemsIterator.next();
			if (valueObject != null && !collectionEval.contains(valueObject)) {
				LOG.debug("Not Null and not found in collection : " + valueObject);
				return false;
			}
		}

		for (Iterator<?> itemsIterator = collectionEval.iterator(); itemsIterator.hasNext();) {
			Object valueObject = itemsIterator.next();
			if (valueObject != null && !collection.contains(valueObject)) {
				LOG.debug("Not Nulll and not found in collection eval : " + valueObject);
				return false;
			}
		}

		return true;
	}

	private static CheckNullCases checkNullCases(Object value, Object valueEval) {

		if (value == null && valueEval == null) {
			return CheckNullCases.RETURN_TRUE;
		}
		if (value == null && valueEval != null) {
			return CheckNullCases.RETURN_FALSE;
		}
		if (value != null && valueEval == null) {
			return CheckNullCases.RETURN_FALSE;
		}
		return CheckNullCases.CHECK;

	}

}
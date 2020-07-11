package be.panidel.dataLayer.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.helper.DAOConfig;
import be.panidel.dataLayer.model.DataModelInterface;
import be.panidel.dataLayer.model.VatRateModel;

public class VatRateDao extends GenericDaoImpl<VatRateModel> {

	private final static Logger LOG = LoggerFactory.getLogger(VatRateDao.class);

	@Override
	public VatRateModel write(VatRateModel vatRate, EntityManager em) {

		VatRateModel persistedVatRate = find(vatRate.getId(), em);

		if (persistedVatRate == null) {

			super.persist(vatRate, em);

			persistedVatRate = vatRate;

			LOG.debug("Vat rate entity created : " + persistedVatRate);

		} else {

			populate(persistedVatRate, vatRate);

			LOG.debug("Vat rate entity updated : " + persistedVatRate);

		}

		return persistedVatRate;

	}

	@Override
	public List<VatRateModel> findAll(EntityManager em) {

		Date fromDate = DAOConfig.FIRST_DATE;
		Date toDate = DAOConfig.LAST_DATE;
		List<VatRateModel> vats = em.createNamedQuery(DataModelInterface.POS_VAT_ALL, VatRateModel.class)
				.setParameter("fromDate", fromDate, TemporalType.DATE).setParameter("toDate", toDate, TemporalType.DATE)
				.getResultList();

		return vats;
	}

	@Override
	public void populate(VatRateModel persistedVatRate, VatRateModel vatRate) {

		persistedVatRate.setId(vatRate.getId());
		persistedVatRate.setCode(vatRate.getCode());
		persistedVatRate.setFromDate(vatRate.getFromDate());
		persistedVatRate.setToDate(vatRate.getToDate());
		persistedVatRate.setRate(vatRate.getRate());

	}

}

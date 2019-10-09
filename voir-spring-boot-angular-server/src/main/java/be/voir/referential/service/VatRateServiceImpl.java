package be.voir.referential.service;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.voir.exception.ResourceNotFoundException;
import be.voir.referential.dao.VatRateRepository;
import be.voir.referential.model.VatRate;

@Service
@Transactional
public class VatRateServiceImpl implements VatRateService {

	private VatRateRepository codeTVARepository;

	public VatRateServiceImpl(VatRateRepository codeTVARepository) {
		this.codeTVARepository = codeTVARepository;
	}

	@Override
	public Iterable<VatRate> getAll() {
		return codeTVARepository.findAll();
	}

	@Override
	public VatRate get(long id) {
		return codeTVARepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Code TVA not found"));
	}

	@Override
	public VatRate save(VatRate product) {
		return codeTVARepository.save(product);
	}

	@Override
	public VatRate getByCode(@NotNull(message = "CODE code TVA ne peut pas être null.") String code) {

		Iterable<VatRate> codeTVAs = getAll();

		for (VatRate codeTVA : codeTVAs) {
			if (codeTVA.getCode().equalsIgnoreCase(code)) {
				return codeTVA;
			}
		}
		return null;
	}
}
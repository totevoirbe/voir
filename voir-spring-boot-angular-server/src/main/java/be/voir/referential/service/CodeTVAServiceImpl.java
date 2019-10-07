package be.voir.referential.service;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.voir.exception.ResourceNotFoundException;
import be.voir.referential.dao.CodeTVARepository;
import be.voir.referential.model.CodeTVA;

@Service
@Transactional
public class CodeTVAServiceImpl implements CodeTVAService {

	private CodeTVARepository codeTVARepository;

	public CodeTVAServiceImpl(CodeTVARepository codeTVARepository) {
		this.codeTVARepository = codeTVARepository;
	}

	@Override
	public Iterable<CodeTVA> getAll() {
		return codeTVARepository.findAll();
	}

	@Override
	public CodeTVA get(long id) {
		return codeTVARepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Code TVA not found"));
	}

	@Override
	public CodeTVA save(CodeTVA product) {
		return codeTVARepository.save(product);
	}

	@Override
	public CodeTVA getByCode(@NotNull(message = "CODE code TVA ne peut pas être null.") String code) {

		Iterable<CodeTVA> codeTVAs = getAll();

		for (CodeTVA codeTVA : codeTVAs) {
			if (codeTVA.getCode().equalsIgnoreCase(code)) {
				return codeTVA;
			}
		}
		return null;
	}
}
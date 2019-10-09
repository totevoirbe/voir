package be.voir.referential.controller;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.voir.referential.model.VatRate;
import be.voir.referential.service.VatRateService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vat-rate")
public class VatRateController {

	private static final Logger LOG = LoggerFactory.getLogger(VatRateController.class);

	private VatRateService vatRateService;

	public VatRateController(VatRateService vatRateService) {
		this.vatRateService = vatRateService;
	}

	@GetMapping(value = { "", "/" })
	public @NotNull Iterable<VatRate> getVatRates() {
		Iterable<VatRate> VatRates = vatRateService.getAll();
		LOG.info("" + VatRates);
		return VatRates;
	}
}

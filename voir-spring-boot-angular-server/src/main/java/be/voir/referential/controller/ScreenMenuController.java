package be.voir.referential.controller;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.voir.referential.model.ScreenMenu;
import be.voir.referential.service.ScreenMenuService;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201" })
@RestController
@RequestMapping("/api/screenMenu")
public class ScreenMenuController {

	private static final Logger LOG = LoggerFactory.getLogger(ScreenMenuController.class);

	private ScreenMenuService screenMenuService;

	public ScreenMenuController(ScreenMenuService screenMenuService) {
		this.screenMenuService = screenMenuService;
	}

	@GetMapping(value = { "", "/" })
	public @NotNull Iterable<ScreenMenu> getScreenMenu() {
		Iterable<ScreenMenu> screenMenuList = screenMenuService.getAll();
		;
		LOG.info("" + screenMenuList);
		return screenMenuList;
	}

}

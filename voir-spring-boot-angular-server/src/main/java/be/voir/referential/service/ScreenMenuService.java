package be.voir.referential.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import be.voir.referential.model.ScreenMenu;

@Validated
public interface ScreenMenuService {

	@NotNull
	Iterable<ScreenMenu> getAll();

	ScreenMenu save(ScreenMenu screenMenu);

	ScreenMenu get(@Min(value = 1L, message = "ID screen menu invalide.") long id);

}

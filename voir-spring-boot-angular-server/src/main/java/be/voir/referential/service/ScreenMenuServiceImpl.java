package be.voir.referential.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.voir.exception.ResourceNotFoundException;
import be.voir.referential.dao.ScreenMenuRepository;
import be.voir.referential.model.ScreenMenu;

@Service
@Transactional
public class ScreenMenuServiceImpl implements ScreenMenuService {

	private ScreenMenuRepository screenMenuRepository;

	public ScreenMenuServiceImpl(ScreenMenuRepository screenMenuRepository) {
		this.screenMenuRepository = screenMenuRepository;
	}

	@Override
	public Iterable<ScreenMenu> getAll() {
		return screenMenuRepository.findAll();
	}

	@Override
	public ScreenMenu save(ScreenMenu screenMenu) {
		ScreenMenu screenMenuSaved = null;
		if (screenMenu != null && screenMenu.getId() != null) {
			ScreenMenu screenMenuToDelete = get(screenMenu.getId());
			if (screenMenuToDelete != null) {
				screenMenuRepository.delete(screenMenuToDelete);
			}
		}
		screenMenuSaved = screenMenuRepository.save(screenMenu);
		return screenMenuSaved;
	}

	@Override
	public ScreenMenu get(long id) {
		return screenMenuRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Screen Menu not found"));
	}

}

package br.com.nunes.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nunes.restaurant.dao.MenuImageDao;
import br.com.nunes.restaurant.model.MenuImage;
import br.com.nunes.restaurant.model.MenuImageCompl;

@Service
public class MenuImageService {
	
	@Autowired
	private MenuImageDao dao;
	
	public MenuImage get(MenuImageCompl menuImageCompl) {
		return dao.findOne(menuImageCompl);
	}
	
	public List<MenuImage> getAll() {
		return dao.findAll();
	}
	
	public void save(MenuImage menuImage) {
		dao.save(menuImage);
	}
	
	public void save(List<MenuImage> menuImages) {
		dao.save(menuImages);
	}
	
	public void delete(MenuImageCompl menuImageCompl) {
		dao.delete(menuImageCompl);
	}

}

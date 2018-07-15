package br.com.nunes.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nunes.restaurant.dao.MenuProductsDao;
import br.com.nunes.restaurant.model.Menu;
import br.com.nunes.restaurant.model.MenuProducts;
import br.com.nunes.restaurant.model.MenuProductsCompl;

@Service
public class MenuProductsService {

	@Autowired
	private MenuProductsDao dao;
	
	public MenuProducts save(MenuProducts menuProducts) {
		return dao.save(menuProducts);
	}
	
	public void saveList(List<MenuProducts> menuProducts) {
		dao.save(menuProducts);
	}
	
	public MenuProducts get(MenuProductsCompl menuProductsCompl) {
		return dao.findOne(menuProductsCompl);
	}
	
	public List<MenuProducts> getByMenu(Menu menu) {
		return dao.findByMenu(menu);
	}
	
	public List<MenuProducts> getAll() {
		return dao.findAll();		
	}
	
	public void delete(MenuProductsCompl menuProductsCompl) {
		dao.delete(menuProductsCompl);
	}
	
	public void deleteByMenu(Long id) {
		dao.deleteByMenu(id);
	}
	
}

package br.com.nunes.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nunes.restaurant.dao.MenuDao;
import br.com.nunes.restaurant.model.Menu;

@Service
public class MenuService {

	@Autowired
	private MenuDao dao;
	
	public Menu save(Menu menu) {
		return dao.save(menu);
	}
	
	public void saveList(List<Menu> menu) {
		dao.save(menu);
	}
	
	public Menu get(Long id) {
		return dao.findOne(id);
	}
	
	public List<Menu> getAll() {
		return dao.findAll();		
	}
	
	public void delete(Long id) {
		dao.delete(id);
	}
	
}

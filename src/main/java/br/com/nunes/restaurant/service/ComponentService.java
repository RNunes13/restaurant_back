package br.com.nunes.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nunes.restaurant.dao.ComponentDao;
import br.com.nunes.restaurant.model.Component;

@Service
public class ComponentService {

	@Autowired
	private ComponentDao dao;

	public Component save(Component component) {
		return dao.save(component);
	}

	public void deleteById(Long id) {
		dao.delete(id);
	}

	public Component getById(Long id) {
		return dao.findOne(id);
	}

	public List<Component> getAll() {
		return dao.findAll();
	}

	public void saveList(List<Component> users) {
		dao.save(users);
	}

}

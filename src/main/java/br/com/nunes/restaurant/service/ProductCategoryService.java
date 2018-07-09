package br.com.nunes.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nunes.restaurant.dao.CategoryTypeDao;
import br.com.nunes.restaurant.model.CategoryType;

@Service
public class CategoryTypeService {
	
	@Autowired
	private CategoryTypeDao dao;
	
	public CategoryType save(CategoryType categoryType) {
		return dao.save(categoryType);
	}
	
	public void saveList(List<CategoryType> categoryTypes) {
		dao.save(categoryTypes);
	}
	
	public CategoryType get(Long id) {
		return dao.findOne(id);
	}
	
	public List<CategoryType> getAll() {
		return dao.findAll();
	}
	
	public void delete(Long id) {
		dao.delete(id);
	}

}

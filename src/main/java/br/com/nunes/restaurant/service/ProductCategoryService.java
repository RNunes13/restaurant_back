package br.com.nunes.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nunes.restaurant.dao.ProductCategoryDao;
import br.com.nunes.restaurant.model.ProductCategory;

@Service
public class ProductCategoryService {
	
	@Autowired
	private ProductCategoryDao dao;
	
	public ProductCategory save(ProductCategory productCategory) {
		return dao.save(productCategory);
	}
	
	public void saveList(List<ProductCategory> productCategories) {
		dao.save(productCategories);
	}
	
	public ProductCategory get(Long id) {
		return dao.findOne(id);
	}
	
	public List<ProductCategory> getAll() {
		return dao.findAll();
	}
	
	public void delete(Long id) {
		dao.delete(id);
	}

}

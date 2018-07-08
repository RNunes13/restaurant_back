package br.com.nunes.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nunes.restaurant.dao.ProductTypeDao;
import br.com.nunes.restaurant.model.ProductType;

@Service
public class ProductTypeService {
	
	@Autowired
	private ProductTypeDao dao;
	
	public ProductType save(ProductType productType) {
		return dao.save(productType);
	}
	
	public void saveList(List<ProductType> productTypes) {
		dao.save(productTypes);
	}
	
	public ProductType get(Long id) {
		return dao.findOne(id);
	}
	
	public List<ProductType> getAll() {
		return dao.findAll();
	}
	
	public void delete(Long id) {
		dao.delete(id);
	}

}

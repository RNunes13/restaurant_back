package br.com.nunes.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nunes.restaurant.dao.ProductDao;
import br.com.nunes.restaurant.model.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao dao;
	
	public Product save(Product product) {
		return dao.save(product);
	}
	
	public void saveList(List<Product> product) {
		dao.save(product);
	}
	
	public Product get(Long id) {
		return dao.findOne(id);
	}
	
	public List<Product> getAll() {
		return dao.findAll();		
	}
	
	public void delete(Long id) {
		dao.delete(id);
	}

}

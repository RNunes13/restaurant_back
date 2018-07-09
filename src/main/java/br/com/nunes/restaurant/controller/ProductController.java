package br.com.nunes.restaurant.controller;

import static br.com.nunes.restaurant.util.ResponseUtils.invalid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.nunes.restaurant.model.Product;
import br.com.nunes.restaurant.service.ProductService;
import br.com.nunes.restaurant.util.CustomResponse;
import br.com.nunes.restaurant.util.ICustomResponse;
import br.com.nunes.restaurant.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(value="product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@ApiOperation(value="Query a product by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Product get(@PathVariable Long id){
		return service.get(id);
	}
	
	@ApiOperation(value="Query all products")
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public List<Product> getAll(){
		return service.getAll();
	}
	
	@ApiOperation(value="Register a product")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> post(@Validated @RequestBody Product product, BindingResult result){
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		Product p = service.save(product);
		return ResponseUtils.ok(p);
	}
	
	@ApiOperation(value="Register a list of products")
	@RequestMapping(value="/registerList", method=RequestMethod.POST)
	public ResponseEntity<? extends ICustomResponse> postList(@Validated @RequestBody List<Product> products, BindingResult result){
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		service.saveList(products);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Update a product")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> put(@Validated @RequestBody Product product, BindingResult result){
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		Product isExist = service.get(product.getId());
		
		if (isExist != null) {
						
			service.save(product);
			Product p = service.get(product.getId());			
			return ResponseUtils.ok(p);

		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No product found with ID " + product.getId(), false), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation(value="Delete a product by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<CustomResponse> delete(@PathVariable Long id){
		Product product = service.get(id);
		
		if (product != null) {
			service.delete(id);
			return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully deleted", true), HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No product found with ID " + id, false), HttpStatus.NOT_FOUND);
		}
			
	}

}

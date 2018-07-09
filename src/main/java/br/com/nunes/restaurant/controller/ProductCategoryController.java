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

import br.com.nunes.restaurant.model.ProductCategory;
import br.com.nunes.restaurant.service.ProductCategoryService;
import br.com.nunes.restaurant.util.CustomResponse;
import br.com.nunes.restaurant.util.ICustomResponse;
import br.com.nunes.restaurant.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(value="/productCategory")
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryService service;
	
	@ApiOperation(value="Query a product category by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ProductCategory get(@PathVariable Long id){
		return service.get(id);
	}
	
	@ApiOperation(value="Query all product categories")
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public List<ProductCategory> getAll(){
		return service.getAll();
	}
	
	@ApiOperation(value="Register a product category")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> post(@Validated @RequestBody ProductCategory productCategory, BindingResult result){
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		ProductCategory c = service.save(productCategory);
		return ResponseUtils.ok(c);
	}
	
	@ApiOperation(value="Register a list of product categories")
	@RequestMapping(value="/registerList", method=RequestMethod.POST)
	public ResponseEntity<? extends ICustomResponse> postList(@Validated @RequestBody List<ProductCategory> productCategories, BindingResult result){
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		service.saveList(productCategories);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Update a product category")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> put(@Validated @RequestBody ProductCategory productCategory, BindingResult result){
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		ProductCategory isExist = service.get(productCategory.getId());
		
		if (isExist != null) {
						
			service.save(productCategory);
			ProductCategory p = service.get(productCategory.getId());			
			return ResponseUtils.ok(p);

		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No product category found with ID " + productCategory.getId(), false), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation(value="Delete a product category by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<CustomResponse> delete(@PathVariable Long id){
		ProductCategory productCategory = service.get(id);
		
		if (productCategory!= null) {
			service.delete(id);
			return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully deleted", true), HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No product category found with ID " + id, false), HttpStatus.NOT_FOUND);
		}
			
	}

}

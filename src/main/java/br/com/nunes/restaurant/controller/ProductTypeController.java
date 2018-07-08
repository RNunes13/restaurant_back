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

import br.com.nunes.restaurant.model.ProductType;
import br.com.nunes.restaurant.service.ProductTypeService;
import br.com.nunes.restaurant.util.CustomResponse;
import br.com.nunes.restaurant.util.ICustomResponse;
import br.com.nunes.restaurant.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(value="/productType")
public class ProductTypeController {
	
	@Autowired
	private ProductTypeService service;
	
	@ApiOperation(value="Query a product type by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ProductType get(@PathVariable Long id){
		return service.get(id);
	}
	
	@ApiOperation(value="Query all product types")
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public List<ProductType> getAll(){
		return service.getAll();
	}
	
	@ApiOperation(value="Register a product type")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> post(@Validated @RequestBody ProductType productType, BindingResult result){
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		ProductType p = service.save(productType);
		return ResponseUtils.ok(p);
	}
	
	@ApiOperation(value="Register a list of product types")
	@RequestMapping(value="/registerList", method=RequestMethod.POST)
	public ResponseEntity<? extends ICustomResponse> postList(@Validated @RequestBody List<ProductType> productTypes, BindingResult result){
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		service.saveList(productTypes);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Update a product type")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> put(@Validated @RequestBody ProductType productType, BindingResult result){
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		ProductType isExist = service.get(productType.getId());
		
		if (isExist != null) {
						
			service.save(productType);
			ProductType p = service.get(productType.getId());			
			return ResponseUtils.ok(p);

		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No product type found with ID " + productType.getId(), false), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation(value="Delete a product type by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<CustomResponse> delete(@PathVariable Long id){
		ProductType productType = service.get(id);
		
		if (productType != null) {
			service.delete(id);
			return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully deleted", true), HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No product type found with ID " + id, false), HttpStatus.NOT_FOUND);
		}
			
	}

}

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

import br.com.nunes.restaurant.model.CategoryType;
import br.com.nunes.restaurant.service.CategoryTypeService;
import br.com.nunes.restaurant.util.CustomResponse;
import br.com.nunes.restaurant.util.ICustomResponse;
import br.com.nunes.restaurant.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(value="/categoryType")
public class CategoryTypeController {
	
	@Autowired
	private CategoryTypeService service;
	
	@ApiOperation(value="Query a category type by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public CategoryType get(@PathVariable Long id){
		return service.get(id);
	}
	
	@ApiOperation(value="Query all category types")
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public List<CategoryType> getAll(){
		return service.getAll();
	}
	
	@ApiOperation(value="Register a category type")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> post(@Validated @RequestBody CategoryType categoryType, BindingResult result){
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		CategoryType c = service.save(categoryType);
		return ResponseUtils.ok(c);
	}
	
	@ApiOperation(value="Register a list of category types")
	@RequestMapping(value="/registerList", method=RequestMethod.POST)
	public ResponseEntity<? extends ICustomResponse> postList(@Validated @RequestBody List<CategoryType> categoryTypes, BindingResult result){
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		service.saveList(categoryTypes);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Update a category type")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> put(@Validated @RequestBody CategoryType categoryType, BindingResult result){
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		CategoryType isExist = service.get(categoryType.getId());
		
		if (isExist != null) {
						
			service.save(categoryType);
			CategoryType p = service.get(categoryType.getId());			
			return ResponseUtils.ok(p);

		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No category type found with ID " + categoryType.getId(), false), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation(value="Delete a category type by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<CustomResponse> delete(@PathVariable Long id){
		CategoryType categoryType = service.get(id);
		
		if (categoryType != null) {
			service.delete(id);
			return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully deleted", true), HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No category type found with ID " + id, false), HttpStatus.NOT_FOUND);
		}
			
	}

}

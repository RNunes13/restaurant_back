package br.com.nunes.restaurant.controller;

import static br.com.nunes.restaurant.util.ResponseUtils.invalid;

import java.util.List;

//import org.apache.commons.codec.digest.DigestUtils;
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

import br.com.nunes.restaurant.model.Component;
import br.com.nunes.restaurant.service.ComponentService;
import br.com.nunes.restaurant.util.CustomResponse;
import br.com.nunes.restaurant.util.ICustomResponse;
import br.com.nunes.restaurant.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(value="/component")
public class ComponentController {
	
	@Autowired
	ComponentService service;
	
	@ApiOperation(value="Query a component by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Component getComponent(@PathVariable Long id){
		return service.getById(id);
	}
	
	@ApiOperation(value="Query all components")
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public List<Component> getAllComponents(){
		return service.getAll();
	}
	
	@ApiOperation(value="Register a component")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> postComponent(@Validated @RequestBody Component component, BindingResult result){		
	
		if (result.hasErrors()){		
			return ResponseUtils.invalid(result.getAllErrors());
		}
		
		Component c = service.save(component);
		return ResponseUtils.ok(c);
	}
	
	@ApiOperation(value="Register a list of components")
	@RequestMapping(value="/registerList",method=RequestMethod.POST)
	public ResponseEntity<? extends ICustomResponse> postListComponent(@Validated @RequestBody List<Component> components, BindingResult result){		
	
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		service.saveList(components);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Update a component")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> putComponent(@Validated @RequestBody Component component, BindingResult result){
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		Component isExist = service.getById(component.getId());
		
		if (isExist != null) {		
			service.save(component);
			return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully updated", true), HttpStatus.OK);			
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No component found with ID " + component.getId(), false), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation(value="Delete a component by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<CustomResponse> deleteComponent(@PathVariable Long id){
		Component component = service.getById(id);
		
		if (component != null) {
			service.deleteById(id);
			return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully deleted", true), HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No component found with ID " + id, false), HttpStatus.NOT_FOUND);
		}
			
	}

}

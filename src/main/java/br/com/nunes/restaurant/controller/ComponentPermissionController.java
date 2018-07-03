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
import br.com.nunes.restaurant.model.ComponentPermission;
import br.com.nunes.restaurant.model.ComponentPermissionCompl;
import br.com.nunes.restaurant.model.User;
import br.com.nunes.restaurant.service.ComponentPermissionService;
import br.com.nunes.restaurant.service.ComponentService;
import br.com.nunes.restaurant.service.UserService;
import br.com.nunes.restaurant.util.CustomResponse;
import br.com.nunes.restaurant.util.ICustomResponse;
import br.com.nunes.restaurant.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(value="/componentPermission")
public class ComponentPermissionController {
	
	@Autowired
	ComponentPermissionService service;
	@Autowired
	UserService userService;
	@Autowired
	ComponentService componentService;
	
	@ApiOperation(value="Query a component permission by User ID and Component ID")
	@RequestMapping(value="/{id_user}/{id_component}", method=RequestMethod.GET)
	public ComponentPermission getComponentPermission(@PathVariable Long id_user, @PathVariable Long id_component){
		
		User user = userService.getById(id_user);		
		if (user == null) {return null;}
		
		Component component = componentService.getById(id_component);		
		if (component == null) {return null;}		
		
		ComponentPermissionCompl identity = new ComponentPermissionCompl(user, component);
		return service.getByIdentity(identity);
	}
	
	@ApiOperation(value="Query a component permission by User ID ")
	@RequestMapping(value="/{id_user}", method=RequestMethod.GET)
	public List<ComponentPermission> getComponentPermissionByUser(@PathVariable Long id_user){
		
		User user = userService.getById(id_user);		
		if (user == null) {return null;}
		
		ComponentPermissionCompl identity = new ComponentPermissionCompl(user, null);
		return service.getByUserId(identity);
	}
	
	@ApiOperation(value="Query all component permissions")
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public List<ComponentPermission> getAllComponentPermissions(){
		return service.getAll();
	}
	
	@ApiOperation(value="Register a component")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<? extends ICustomResponse> postComponentPermission(@Validated @RequestBody ComponentPermission permission, BindingResult result){		
	
		if (result.hasErrors()){
			return ResponseUtils.invalid(result.getAllErrors());
		}
		
		User user = userService.getById(permission.getComponentPermissionCompl().getUser().getId());
		if (user == null) {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No component permission found with User ID " +
					permission.getComponentPermissionCompl().getUser().getId(), false), HttpStatus.NOT_FOUND);
		}
		
		Component component = componentService.getById(permission.getComponentPermissionCompl().getComponent().getId());
		if (component == null) {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No component permission found with Component ID " +
					permission.getComponentPermissionCompl().getComponent().getId(), false), HttpStatus.NOT_FOUND);
		}
		
		service.save(permission);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
	}
	
	@ApiOperation(value="Register a list of component permission")
	@RequestMapping(value="/registerList",method=RequestMethod.POST)
	public ResponseEntity<? extends ICustomResponse> postListComponentPermission(@Validated @RequestBody List<ComponentPermission> permissions, BindingResult result){		
	
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		service.saveList(permissions);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Update a component permission")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> putComponentPermission(@Validated @RequestBody ComponentPermission permission, BindingResult result){
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		ComponentPermission isExist = service.getByIdentity(permission.getComponentPermissionCompl());
		
		if (isExist != null) {		
			service.save(permission);
			return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully updated", true), HttpStatus.OK);			
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No component permission found", false), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation(value="Delete a component permission by User ID and Component ID")
	@RequestMapping(value="/{id_user}/{id_component}", method=RequestMethod.DELETE)
	public ResponseEntity<CustomResponse> deleteComponentPermission(@PathVariable Long id_user, @PathVariable Long id_component){
		
		User user = userService.getById(id_user);
		if (user == null) {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No component permission found with User ID " + id_user, false), HttpStatus.NOT_FOUND);
		}
		
		Component component = componentService.getById(id_component);
		if (component == null) {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No component permission found with Component ID " + id_component, false), HttpStatus.NOT_FOUND);
		}
		
		ComponentPermissionCompl identity = new ComponentPermissionCompl(user, component);

		ComponentPermission permission = service.getByIdentity(identity);
		
		if (permission != null) {
			service.delete(identity);
			return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully deleted", true), HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No component permission found", false), HttpStatus.NOT_FOUND);
		}
			
	}

}

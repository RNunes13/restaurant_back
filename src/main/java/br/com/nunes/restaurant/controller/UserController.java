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

import br.com.nunes.restaurant.model.User;
import br.com.nunes.restaurant.service.UserService;
import br.com.nunes.restaurant.util.CustomResponse;
import br.com.nunes.restaurant.util.ICustomResponse;
import br.com.nunes.restaurant.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@ApiOperation(value="Query an user by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable Long id){
		return service.getById(id);
	}
	
	@ApiOperation(value="Query an user by login")
	@RequestMapping(value="/login/{username}/{password}", method=RequestMethod.GET)
	public User getUserByLogin(@PathVariable String username, @PathVariable String password){
		return service.getByLogin(username, password);
	}
	
	@ApiOperation(value="Query all users")
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public List<User> getAllUsers(){
		return service.getAll();
	}
	
	@ApiOperation(value="Register an user")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> postUser(@Validated @RequestBody User user, BindingResult result){		
	
		if (result.hasErrors()){		
			return ResponseUtils.invalid(result.getAllErrors());
		}
		
		//GENERATE/SET PASSWORD (HASH) FOR USER
		//user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
		
		User u = service.save(user);
		return ResponseUtils.ok(u);
	}
	
	@ApiOperation(value="Register a list of users")
	@RequestMapping(value="/registerList",method=RequestMethod.POST)
	public ResponseEntity<? extends ICustomResponse> postListUser(@Validated @RequestBody List<User> users, BindingResult result){		
	
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}	
		
		//GENERATE/SET PASSWORD (HASH) FOR EACH USER - W/ LAMBDA		
		//users.forEach((user)->user.setPassword(DigestUtils.sha256Hex(user.getPassword())));
		
		service.saveList(users);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Update an user")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> putUser(@Validated @RequestBody User user, BindingResult result){
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		User isExist = service.getById(user.getId());
		
		if (isExist != null) {
			
			/*String userPass = isExist.getPassword();
			
			if (!userPass.equals(DigestUtils.sha256Hex(user.getPassword()))) {
				user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
			}*/			
			service.save(user);
			return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully updated", true), HttpStatus.OK);			
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No user found with ID " + user.getId(), false), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation(value="Delete an user by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<CustomResponse> usuarioDelete(@PathVariable Long id){
		User user = service.getById(id);
		
		if (user != null) {
			service.deleteById(id);
			return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully deleted", true), HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No user found with ID " + id, false), HttpStatus.NOT_FOUND);
		}
			
	}

}

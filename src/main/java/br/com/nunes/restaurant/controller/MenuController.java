package br.com.nunes.restaurant.controller;

import static br.com.nunes.restaurant.util.ResponseUtils.invalid;

import java.math.BigDecimal;
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

import br.com.nunes.restaurant.model.Menu;
import br.com.nunes.restaurant.model.MenuPriceHistory;
import br.com.nunes.restaurant.service.MenuPriceHistoryService;
import br.com.nunes.restaurant.service.MenuService;
import br.com.nunes.restaurant.util.CustomResponse;
import br.com.nunes.restaurant.util.ICustomResponse;
import br.com.nunes.restaurant.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(value="/menu")
public class MenuController {
	
	@Autowired
	private MenuService service;
	@Autowired
	private MenuPriceHistoryService historyService;
	
	@ApiOperation(value="Query a menu by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Menu get(@PathVariable Long id){
		return service.get(id);
	}
	
	@ApiOperation(value="Query all menus")
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public List<Menu> getAll(){
		return service.getAll();
	}
	
	@ApiOperation(value="Register a menu")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> post(@Validated @RequestBody Menu menu, BindingResult result){
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		Menu m = service.save(menu);
		return ResponseUtils.ok(m);
	}
	
	@ApiOperation(value="Register a list of menus")
	@RequestMapping(value="/registerList", method=RequestMethod.POST)
	public ResponseEntity<? extends ICustomResponse> postList(@Validated @RequestBody List<Menu> menus, BindingResult result){
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		service.saveList(menus);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Update a menu")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> put(@Validated @RequestBody Menu menu, BindingResult result){
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		Menu isExist = service.get(menu.getId());
		
		if (isExist != null) {
			
			BigDecimal oldPrice = isExist.getPrice();
			BigDecimal newPrice = menu.getPrice();
			
			newPrice = newPrice.setScale(3);
			
			if (!oldPrice.equals(newPrice)) {
				
				MenuPriceHistory menuPriceHistory = new MenuPriceHistory(menu, oldPrice, newPrice);				
				historyService.save(menuPriceHistory);
				
			}
			
			service.save(menu);
			Menu m = service.get(menu.getId());			
			return ResponseUtils.ok(m);

		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No menu found with ID " + menu.getId(), false), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation(value="Delete a menu by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<CustomResponse> delete(@PathVariable Long id){
		Menu menu = service.get(id);
		
		if (menu!= null) {
			service.delete(id);
			return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully deleted", true), HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No menu found with ID " + id, false), HttpStatus.NOT_FOUND);
		}
			
	}

}

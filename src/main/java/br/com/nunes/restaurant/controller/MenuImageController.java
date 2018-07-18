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

import br.com.nunes.restaurant.model.Menu;
import br.com.nunes.restaurant.model.MenuImage;
import br.com.nunes.restaurant.model.MenuImageCompl;
import br.com.nunes.restaurant.service.MenuImageService;
import br.com.nunes.restaurant.service.MenuService;
import br.com.nunes.restaurant.util.CustomResponse;
import br.com.nunes.restaurant.util.ResponseUtils;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/menuImage")
public class MenuImageController {
	
	@Autowired
	private MenuImageService service;
	@Autowired
	private MenuService menuService;
	
	@ApiOperation(value = "Consult image of the menu")
	@RequestMapping(value = "/{id_menu}", method = RequestMethod.GET)
	public MenuImage get(@PathVariable Long id_menu) {
		
		Menu menu = menuService.get(id_menu);
		if (menu == null) { return null; }
		
		MenuImageCompl menuImageCompl = new MenuImageCompl(menu);
		
		return service.get(menuImageCompl);
		
	}
	
	@ApiOperation(value = "Consult all images")
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<MenuImage> getAll() {
		return service.getAll();
	}
	
	@ApiOperation(value = "Register an image in the menu")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> post(@Validated @RequestBody MenuImage menuImage, BindingResult result) {
		
		if (result.hasErrors()) {
			return invalid(result.getAllErrors());
		}
		
		Menu isExist = menuService.get(menuImage.getMenuImageCompl().getMenu().getId());
		
		if (isExist != null) {
			
			service.save(menuImage);
			return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No menu found", false), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation(value = "Register a list of the images")
	@RequestMapping(value = "/registerList", method = RequestMethod.POST)
	public ResponseEntity<?> postList(@Validated @RequestBody List<MenuImage> menuImage, BindingResult result) {
		
		if (result.hasErrors()) {
			return invalid(result.getAllErrors());
		}
		
		service.save(menuImage);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Update image of the menu")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> put(@Validated @RequestBody MenuImage menuImage, BindingResult result) {
		
		if (result.hasErrors()) {
			return invalid(result.getAllErrors());
		}
		
		MenuImage isExist = service.get(menuImage.getMenuImageCompl());
		
		if (isExist != null) {
			
			service.save(menuImage);
			MenuImage i = service.get(menuImage.getMenuImageCompl());
			return ResponseUtils.ok(i);
			
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No image found in the menu", false), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation(value = "Delete image of the menu")
	@RequestMapping(value = "/{id_menu}", method = RequestMethod.DELETE)
	public ResponseEntity<CustomResponse> delete(@PathVariable Long id_menu) {
		
		Menu menu = menuService.get(id_menu);
		
		if (menu == null) {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No menu found with ID " + id_menu, false), HttpStatus.NOT_FOUND);
		}
		
		MenuImageCompl menuImageCompl = new MenuImageCompl(menu);
		
		MenuImage isExist = service.get(menuImageCompl);
		
		if (isExist != null) {
			
			service.delete(menuImageCompl);
			return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully deleted", true), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No image found in the menu", false), HttpStatus.NOT_FOUND);
		}		
		
	}

}

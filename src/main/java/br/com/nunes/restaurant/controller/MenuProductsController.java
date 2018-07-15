package br.com.nunes.restaurant.controller;

import static br.com.nunes.restaurant.util.ResponseUtils.invalid;

import java.util.ArrayList;
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
import br.com.nunes.restaurant.model.MenuProducts;
import br.com.nunes.restaurant.model.MenuProductsAux;
import br.com.nunes.restaurant.model.MenuProductsAuxProducts;
import br.com.nunes.restaurant.model.MenuProductsCompl;
import br.com.nunes.restaurant.model.Product;
import br.com.nunes.restaurant.service.MenuProductsService;
import br.com.nunes.restaurant.service.MenuService;
import br.com.nunes.restaurant.service.ProductService;
import br.com.nunes.restaurant.util.CustomResponse;
import br.com.nunes.restaurant.util.ICustomResponse;
import br.com.nunes.restaurant.util.ResponseUtils;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/menuProducts")
public class MenuProductsController {

	@Autowired
	private MenuProductsService service;
	@Autowired
	private MenuService menuService;
	@Autowired
	private ProductService productService;
	
	@ApiOperation(value = "Consult a product of the menu")
	@RequestMapping(value = "/{id_menu}/{id_product}", method=RequestMethod.GET)
	public MenuProducts get(@PathVariable Long id_menu, @PathVariable Long id_product){
		
		Menu menu = menuService.get(id_menu);
		if (menu == null) { return null; }
		
		Product product = productService.get(id_product);
		if (product == null) { return null; }
		
		MenuProductsCompl menuProductsCompl = new MenuProductsCompl(menu, product); 
		
		return service.get(menuProductsCompl);
	}
	
	@ApiOperation(value = "Consult the products of the menu by menu ID")
	@RequestMapping(value = "/{id_menu}", method=RequestMethod.GET)
	public List<MenuProducts> getByMenu(@PathVariable Long id_menu){
		
		Menu menu = menuService.get(id_menu);		
		if (menu == null) { return null; }
		
		return service.getByMenu(menu);
	}
	
	@ApiOperation(value = "Consult all products")
	@RequestMapping(value = "/getAll", method=RequestMethod.GET)
	public List<MenuProducts> getAll(){
		return service.getAll();
	}
	
	@ApiOperation(value = "Register a product in the menu")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> post(@Validated @RequestBody MenuProducts menuProducts, BindingResult result) {
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		service.save(menuProducts);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Register a list of the products")
	@RequestMapping(value = "/registerList", method = RequestMethod.POST)
	public ResponseEntity<? extends ICustomResponse> postList(@Validated @RequestBody List<MenuProducts> menuProducts, BindingResult result) {
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		service.saveList(menuProducts);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Register a list of the products in a menu")
	@RequestMapping(value = "/registerListProductsInOneMenu", method = RequestMethod.POST)
	public ResponseEntity<? extends ICustomResponse> postListInOneMenu(@Validated @RequestBody MenuProductsAux menuProductsAux, BindingResult result) {
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		Menu menu = menuService.get(menuProductsAux.getId_menu());
		
		if (menu == null) {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No menu found with ID " + menuProductsAux.getId_menu(), false), HttpStatus.NOT_FOUND);
		}
		
		List<MenuProductsAuxProducts> products = menuProductsAux.getProducts();
		List<Long> productsNotFound = new ArrayList<Long>(); 
		
		products.forEach(product -> {
			
			Product p = productService.get(product.getId_product());
			
			if (p == null) {
				productsNotFound.add(product.getId_product());
				return;
			}
			
			MenuProductsCompl menuProductsCompl = new MenuProductsCompl(menu, p);
			MenuProducts menuProducts = new MenuProducts();
			menuProducts.setMenuProductsCompl(menuProductsCompl);

			service.save(menuProducts);
			
		});

		if (productsNotFound.size() == 0) {	
			
			return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
			
		} else if (productsNotFound.size() < products.size()) {
			
			String notFound = String.join(", ", productsNotFound.toString());			
			return new ResponseEntity<CustomResponse>(new CustomResponse(
					"Some of the products weren't found. The unregistered products are: " + notFound, true), HttpStatus.OK);
			
		} else {
			
			return new ResponseEntity<CustomResponse>(new CustomResponse("No products found", false), HttpStatus.NOT_FOUND);
			
		}
		
	}
	
	@ApiOperation(value = "Updated a product in the menu")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> put(@Validated @RequestBody MenuProducts menuProducts, BindingResult result) {
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		MenuProducts isExist = service.get(menuProducts.getMenuProductsCompl());
		
		if (isExist != null) {
			
			service.save(menuProducts);
			MenuProducts p = service.get(menuProducts.getMenuProductsCompl());
			return ResponseUtils.ok(p);
			
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No products found in the menu", false), HttpStatus.NOT_FOUND);			
		}

	}
	
	@ApiOperation(value="Delete a product in the menu")
	@RequestMapping(value="/{id_menu}/{id_product}", method=RequestMethod.DELETE)
	public ResponseEntity<CustomResponse> delete(@PathVariable Long id_menu, @PathVariable Long id_product){
		
		Menu menu = menuService.get(id_menu);
		
		if (menu == null) {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No menu found with ID " + id_menu, false), HttpStatus.NOT_FOUND);
		}
		
		Product product = productService.get(id_product);
		
		if (product == null) {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No product found with ID " + id_product, false), HttpStatus.NOT_FOUND);
		}
		
		MenuProductsCompl menuProductsCompl = new MenuProductsCompl(menu, product);
		
		service.delete(menuProductsCompl);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully deleted", true), HttpStatus.OK);

	}
	
	@ApiOperation(value="Delete the products from a menu")
	@RequestMapping(value="/{id_menu}", method=RequestMethod.DELETE)
	public ResponseEntity<CustomResponse> deleteFromMenu(@PathVariable Long id_menu){
		
		Menu menu = menuService.get(id_menu);
		
		if (menu == null) {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No menu found with ID " + id_menu, false), HttpStatus.NOT_FOUND);
		}
		
		service.deleteByMenu(id_menu);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully deleted", true), HttpStatus.OK);

	}
	
}

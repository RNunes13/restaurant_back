package br.com.nunes.restaurant.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.nunes.restaurant.model.MenuPriceHistory;
import br.com.nunes.restaurant.service.MenuPriceHistoryService;
import br.com.nunes.restaurant.util.CustomResponse;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "menuPriceHistory")
public class MenuPriceHistoryController {
	
	@Autowired
	private MenuPriceHistoryService service;
	
	@ApiOperation(value = "Consult menu price history by ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public MenuPriceHistory get(@PathVariable Long id) {
		return service.get(id);
	}
	
	@ApiOperation(value = "Consult all pricing history")
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<MenuPriceHistory> getAll() {
		return service.getAll();
	}
	
	@ApiOperation(value = "Consult price history by date period - Pattern yyyy-MM-dd HH:mm:ss")
	@RequestMapping(value = "/getPriceHistoryForPeriod/{initialDate}/{finalDate}", method = RequestMethod.GET)
	public List<MenuPriceHistory> getForPeriod(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Calendar initialDate,
											   @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Calendar finalDate) {
		return service.getForPeriod(initialDate, finalDate);
	}
	
	@ApiOperation(value = "Consult price history of a menu by date period - Pattern yyyy-MM-dd HH:mm:ss")
	@RequestMapping(value = "/getPriceHistoryForPeriod/{id_menu}/{initialDate}/{finalDate}", method = RequestMethod.GET)
	public List<MenuPriceHistory> getForPeriod(@PathVariable Long id_menu,
											   @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Calendar initialDate,
											   @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Calendar finalDate) {
		return service.getPriceHistoryOneMenuByDate(id_menu, initialDate, finalDate);
	}
	
	@ApiOperation(value = "Consult the latest price change of menu")
	@RequestMapping(value = "/getLatestPriceChange/{id_menu}", method = RequestMethod.GET)
	public MenuPriceHistory getLatestPriceChange(@PathVariable Long id_menu) {
		return service.getLatestPriceChange(id_menu);
	}
	
	/*@ApiOperation(value = "Register a menu price history")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> post(@Validated @RequestBody MenuPriceHistory menuPriceHistory, BindingResult result) {
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		MenuPriceHistory m = service.save(menuPriceHistory);
		return ResponseUtils.ok(m);
	}*/
	
	/*@ApiOperation(value = "Register a list of menu price history")
	@RequestMapping(value = "/registerList", method = RequestMethod.POST)
	public ResponseEntity<? extends ICustomResponse> postList(@Validated @RequestBody List<MenuPriceHistory> menuPriceHistory, BindingResult result) {
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		service.saveList(menuPriceHistory);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully registered", true), HttpStatus.OK);
	}*/
	
	/*@ApiOperation(value = "Updated a menu price history")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> put(@Validated @RequestBody MenuPriceHistory menuPriceHistory, BindingResult result) {
		
		if (result.hasErrors()){		
			return invalid(result.getAllErrors());
		}
		
		MenuPriceHistory isExist = service.get(menuPriceHistory.getId());
		
		if (isExist != null) {
			
			service.save(menuPriceHistory);
			MenuPriceHistory p = service.get(menuPriceHistory.getId());
			return ResponseUtils.ok(p);
			
		} else {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No history found with ID" + menuPriceHistory.getId(), false), HttpStatus.NOT_FOUND);			
		}
	}*/
	
	@ApiOperation(value="Delete a menu price history by ID")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<CustomResponse> delete(@PathVariable Long id){
		
		MenuPriceHistory menuPriceHistory = service.get(id);
		
		if (menuPriceHistory == null) {
			return new ResponseEntity<CustomResponse>(new CustomResponse("No history found with ID " + id, false), HttpStatus.NOT_FOUND);
		}
		
		service.delete(id);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully deleted", true), HttpStatus.OK);

	}
	
	@ApiOperation(value="Delete price history from a menu")
	@RequestMapping(value="/deletePriceHistoryFromOneMenu/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<CustomResponse> deletePriceHistoryFromOneMenu(@PathVariable Long id){
		
		service.deletePriceHistoryFromOneMenu(id);
		return new ResponseEntity<CustomResponse>(new CustomResponse("Successfully deleted", true), HttpStatus.OK);

	}
	
}

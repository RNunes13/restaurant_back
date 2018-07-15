package br.com.nunes.restaurant.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nunes.restaurant.dao.MenuPriceHistoryDao;
import br.com.nunes.restaurant.model.MenuPriceHistory;

@Service
public class MenuPriceHistoryService {

	@Autowired
	private MenuPriceHistoryDao dao;
	
	public MenuPriceHistory save(MenuPriceHistory menuPriceHistory) {
		return dao.save(menuPriceHistory);
	}
	
	public void saveList(List<MenuPriceHistory> menuPriceHistory) {
		dao.save(menuPriceHistory);
	}
	
	public MenuPriceHistory get(Long id) {
		return dao.findOne(id);
	}
	
	public List<MenuPriceHistory> getAll() {
		return dao.findAll();		
	}
	
	public List<MenuPriceHistory> getForPeriod(Calendar initialDate, Calendar finalDate) {
		return dao.findForPeriod(initialDate, finalDate);
	}
	
	public List<MenuPriceHistory> getPriceHistoryOneMenuByDate(Long id, Calendar initialDate, Calendar finalDate) {
		return dao.findPriceHistoryOneMenuByDate(id, initialDate, finalDate);
	}
	
	public MenuPriceHistory getLatestPriceChange(Long id) {
		return dao.findLatestPriceChange(id);
	}
	
	public void delete(Long id) {
		dao.delete(id);
	}
	
	public void deletePriceHistoryFromOneMenu(Long id) {
		dao.deletePriceHistoryFromOneMenu(id);
	}
	
}

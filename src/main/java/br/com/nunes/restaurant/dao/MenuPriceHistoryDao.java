package br.com.nunes.restaurant.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.nunes.restaurant.model.MenuPriceHistory;

@Repository
public interface MenuPriceHistoryDao extends JpaRepository<MenuPriceHistory, Long> {
	
	@Query("from MenuPriceHistory m where m.changedAt between :initialDate and :finalDate")
	public List<MenuPriceHistory> findForPeriod(@Param("initialDate") Calendar initialDate, @Param("finalDate") Calendar finalDate);
	
	@Query(value = "SELECT * FROM menu_price_history WHERE menu = :id AND changedAt BETWEEN :initialDate and :finalDate", nativeQuery = true)
	public List<MenuPriceHistory> findPriceHistoryOneMenuByDate(@Param("id") Long id, 
																@Param("initialDate") Calendar initialDate,
																@Param("finalDate") Calendar finalDate);

	@Query(value = "SELECT * FROM menu_price_history WHERE menu = :id ORDER BY changedAt DESC LIMIT 1", nativeQuery = true)
	public MenuPriceHistory findLatestPriceChange (@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM menu_price_history WHERE menu = :id", nativeQuery = true)
	public void deletePriceHistoryFromOneMenu (@Param("id") Long id);

}

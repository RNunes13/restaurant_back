package br.com.nunes.restaurant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.nunes.restaurant.model.Menu;
import br.com.nunes.restaurant.model.MenuProducts;
import br.com.nunes.restaurant.model.MenuProductsCompl;

@Repository
public interface MenuProductsDao extends JpaRepository<MenuProducts, MenuProductsCompl> {
	
	@Query("from MenuProducts m where m.menuProductsCompl.menu = :menu")
	public List<MenuProducts> findByMenu(@Param("menu") Menu menu);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM menu_products WHERE id_menu = :id", nativeQuery = true)
	public void deleteByMenu(@Param("id") Long id);

}

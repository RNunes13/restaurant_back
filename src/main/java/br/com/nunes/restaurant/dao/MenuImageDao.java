package br.com.nunes.restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nunes.restaurant.model.MenuImage;
import br.com.nunes.restaurant.model.MenuImageCompl;

@Repository
public interface MenuImageDao extends JpaRepository<MenuImage, MenuImageCompl> {

}

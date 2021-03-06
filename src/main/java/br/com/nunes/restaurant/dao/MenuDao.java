package br.com.nunes.restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nunes.restaurant.model.Menu;

@Repository
public interface MenuDao extends JpaRepository<Menu, Long> {

}

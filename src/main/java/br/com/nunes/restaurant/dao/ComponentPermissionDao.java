package br.com.nunes.restaurant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.nunes.restaurant.model.ComponentPermission;
import br.com.nunes.restaurant.model.ComponentPermissionCompl;
import br.com.nunes.restaurant.model.User;

@Repository
public interface ComponentPermissionDao extends JpaRepository<ComponentPermission, ComponentPermissionCompl> {
	
	@Query("from ComponentPermission p where p.componentPermissionCompl.user = :user")
	public List<ComponentPermission> findByUser(@Param("user") User user);
	
}

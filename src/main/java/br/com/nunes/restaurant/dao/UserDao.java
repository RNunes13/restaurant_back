package br.com.nunes.restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.nunes.restaurant.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

	@Query("from User u where u.username = :username and u.password = :password")
	public User findByLogin(@Param("username") String username, @Param("password") String password);
	
	@Query("from User u where u.username = :username")
	public User findByUsername(@Param("username") String username);	
	
	@Query("from User u where u.email = :email")
	public User findByEmail(@Param("email") String email);	

}

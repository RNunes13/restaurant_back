package br.com.nunes.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nunes.restaurant.dao.UserDao;
import br.com.nunes.restaurant.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao dao;

	public User save(User user) {
		return dao.save(user);
	}

	public void deleteById(Long id) {
		dao.delete(id);
	}

	public User getById(Long id) {
		return dao.findOne(id);
	}

	public List<User> getAll() {
		return dao.findAll();
	}

	public User getByLogin(String username, String password) {
		return dao.findByLogin(username, password);
	}
	
	public User getByUsername(String username) {
		return dao.findByUsername(username);
	}
	
	public User getByEmail(String email) {
		return dao.findByEmail(email);
	}

	public void saveList(List<User> users) {
		dao.save(users);
	}

}

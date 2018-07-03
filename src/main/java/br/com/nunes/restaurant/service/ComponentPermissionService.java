package br.com.nunes.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nunes.restaurant.dao.ComponentPermissionDao;
import br.com.nunes.restaurant.model.ComponentPermission;
import br.com.nunes.restaurant.model.ComponentPermissionCompl;

@Service
public class ComponentPermissionService {

	@Autowired
	private ComponentPermissionDao dao;

	public ComponentPermission save(ComponentPermission permission) {
		return dao.save(permission);
	}

	public void delete(ComponentPermissionCompl identity) {
		dao.delete(identity);
	}

	public ComponentPermission getByIdentity(ComponentPermissionCompl identity) {
		return dao.findOne(identity);
	}
	
	public List<ComponentPermission> getByUserId(ComponentPermissionCompl identity) {
		return dao.findByUser(identity.getUser());
	}

	public List<ComponentPermission> getAll() {
		return dao.findAll();
	}

	public void saveList(List<ComponentPermission> permissions) {
		dao.save(permissions);
	}

}

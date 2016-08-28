package ua.mysite.service;

import java.util.List;

import ua.mysite.entity.Role;

public interface RoleService {

	void save(String role);
	
	Role findById(int id);
	
	void deleteById(int id);
	
	List<Role> findAll();
}

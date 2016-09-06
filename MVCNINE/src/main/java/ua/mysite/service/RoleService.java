package ua.mysite.service;

import java.util.List;

import ua.mysite.entity.Role;

public interface RoleService {

	void save(Role role);
	
	Role findById(int id);
	
	Role findByRole(String role);
	
	void deleteById(int id);
	
	List<Role> findAll();
}

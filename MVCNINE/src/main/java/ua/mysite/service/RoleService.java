package ua.mysite.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.mysite.entity.Role;

public interface RoleService {

	void save(Role role);
	
	Role findById(int id);
	
	Role findByRole(String role);
	
	void deleteById(int id);
	
	List<Role> findAll();
	
	Page<Role> findAll(Pageable pageable);
}

package ua.mysite.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.mysite.entity.Role;
import ua.mysite.repository.RoleRepository;
import ua.mysite.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public void save(Role role) {
		roleRepository.save(role);
	}

	public Role findById(int id) {
		return roleRepository.findById(id);
	}

	public void deleteById(int id) {
		roleRepository.deleteById(id);

	}

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public Role findByRole(String role) {
		return roleRepository.findByRole(role);
	}

	public Page<Role> findAll(Pageable pageable) {
		return roleRepository.findAll(pageable);
	}

}

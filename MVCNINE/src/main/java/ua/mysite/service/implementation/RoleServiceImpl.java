package ua.mysite.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.mysite.entity.Role;
import ua.mysite.repository.RoleRepository;
import ua.mysite.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public void save(String role) {
		if (roleRepository.findByRole(role) == null) {
			Role role2 = new Role();
			role2.setRole(role);
			roleRepository.save(role2);
		} else
			System.out.println("Exist");
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

}

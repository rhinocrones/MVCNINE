package ua.mysite.service.implementation;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.form.UsrFilter;
import ua.mysite.entity.Role;
import ua.mysite.entity.Usr;
import ua.mysite.repository.RoleRepository;
import ua.mysite.repository.UsrRepository;
import ua.mysite.service.UsrService;
import ua.mysite.service.implementation.specification.UsrFilterAdapter;

@Service("userDetailsService")
public class UsrServiceImpl implements UsrService,  UserDetailsService {

	@Autowired
	private UsrRepository usrRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public void save(Usr user) {
		Role role = new Role();
		role.setRole("admin");
		user.setRole(role);
		user.setPassword(encoder.encode(user.getPassword()));
		usrRepository.save(user);
	}

	public Usr findById(int id) {
		return usrRepository.findById(id);
	}

	public void deleteById(int id) {
		usrRepository.deleteById(id);
	}

	public List<Usr> findAll() {
		return usrRepository.findAll();
	}

	public List<Usr> usrs() {
		return usrRepository.usrs();
	}

	public Usr findByUsername(String username) {
		return usrRepository.findByUsername(username);
	}

	public Usr findByEmail(String email) {
		return usrRepository.findByEmail(email);
	}

	public Usr findByPassword(String password) {
		return usrRepository.findByPassword(password);
	}

	public Page<Usr> findAll(Pageable pageable) {
		return usrRepository.findAll(pageable);
	}

	@Override
	public Page<Usr> findAll(Pageable pageable, UsrFilter form) {
		return usrRepository.findAll(new UsrFilterAdapter(form), pageable);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return usrRepository.findByUsername(username);
	}

	@PostConstruct
	public void saveAdmin(){
		Role role = new Role();
		role.setRole("admin");
/*		role.setId(1);*/
		roleRepository.save(role);
		Usr user = new Usr();
		user.setRole(role);
		user.setPassword(encoder.encode("admin"));
		user.setUsername("admin");
		user.setId(1);
		usrRepository.save(user);
	}
	

}

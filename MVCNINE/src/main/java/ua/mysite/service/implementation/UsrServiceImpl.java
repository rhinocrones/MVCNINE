package ua.mysite.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.mysite.entity.Usr;
import ua.mysite.repository.RoleRepository;
import ua.mysite.repository.UsrRepository;
import ua.mysite.service.UsrService;

@Service
public class UsrServiceImpl implements UsrService {

	@Autowired
	private UsrRepository usrRepository;

	@Autowired
	private RoleRepository roleRepository;

	public void save(Usr usr) {
		usrRepository.save(usr);
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

}

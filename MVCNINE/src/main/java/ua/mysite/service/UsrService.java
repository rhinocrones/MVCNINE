package ua.mysite.service;

import java.util.List;

import ua.mysite.entity.Usr;

public interface UsrService {

	void save(Usr usr);
	
	Usr findById(int id);
	
	Usr findByUsername(String username);
	
	Usr findByEmail(String email);
	
	Usr findByPassword (String password);
	
	void deleteById(int id);
	
	List<Usr> findAll();
	
	List<Usr> usrs();
}

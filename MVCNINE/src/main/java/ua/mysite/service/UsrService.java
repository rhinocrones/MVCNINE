package ua.mysite.service;

import java.util.List;

import ua.mysite.entity.Usr;

public interface UsrService {

	void save(Usr usr);
	
	Usr findById(int id);
	
	void deleteById(int id);
	
	List<Usr> findAll();
	
	List<Usr> usrs();
}

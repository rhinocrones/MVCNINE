package ua.mysite.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.form.UsrFilter;
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
	
	Page<Usr> findAll(Pageable pageable);
	
	Page<Usr> findAll(Pageable pageable, UsrFilter form);
}

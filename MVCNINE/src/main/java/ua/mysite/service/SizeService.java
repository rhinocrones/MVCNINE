package ua.mysite.service;

import java.util.List;

import ua.mysite.entity.Size;

public interface SizeService {

	void save(Size size);
	
	Size findById(int id);
	
	void deleteById(int id);
	
	List<Size> findAll();
}

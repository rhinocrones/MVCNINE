package ua.mysite.service;

import java.util.List;

import ua.mysite.entity.Brand;

public interface BrandService {

	void save(Brand brand);
	
	Brand findById(int id);
	
	void deleteById(int id);
	
	List<Brand> findAll();
	
}

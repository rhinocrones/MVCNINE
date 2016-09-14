package ua.mysite.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.form.BrandFilter;
import ua.mysite.entity.Brand;

public interface BrandService {

	void save(Brand brand);
	
	Brand findByBrand(String brand);
	
	Brand findById(int id);
	
	void deleteById(int id);
	
	List<Brand> findAll();
	
	Page<Brand> findAll(Pageable pageable);
	
	Page<Brand> findAll(Pageable pageable, BrandFilter form);
	
}

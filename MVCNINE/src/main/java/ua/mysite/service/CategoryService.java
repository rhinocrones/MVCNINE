package ua.mysite.service;

import java.util.List;

import ua.mysite.entity.Category;

public interface CategoryService {

	void save(Category category);
	
	Category findById(int id);
	
	void deleteById(int id);
	
	List<Category> findAll();
}

package ua.mysite.service;

import java.util.List;

import ua.mysite.entity.Product;

public interface ProductService {

	void save(String name, double price, String category, String brand,
			int size);
	
	Product findById(int id);
	
	void deleteById(int id);
	
	List<Product> findAll();
	
	List<Product> products();
}

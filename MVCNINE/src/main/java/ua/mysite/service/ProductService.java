package ua.mysite.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.form.ProductForm;
import ua.mysite.entity.Product;

public interface ProductService {

	void save(ProductForm form);
	
	ProductForm findForForm(int id);
	
	Product findById(int id);
	
	Product findByName(String name);
	
	void deleteById(int id);
	
	List<Product> findAll();
	
	List<Product> products();
	
	Page<Product> findAll(Pageable pageable);
}

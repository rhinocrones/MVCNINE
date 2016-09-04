package ua.mysite.service;

import java.util.List;

import ua.form.ProductForm;
import ua.mysite.entity.Product;

public interface ProductService {

	void save(ProductForm form);
	
	ProductForm findForForm(int id);
	
	Product findById(int id);
	
	void deleteById(int id);
	
	List<Product> findAll();
	
	List<Product> products();
}

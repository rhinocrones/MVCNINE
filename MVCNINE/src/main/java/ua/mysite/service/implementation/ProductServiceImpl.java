package ua.mysite.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.form.ProductForm;
import ua.mysite.entity.Product;
import ua.mysite.repository.BrandRepository;
import ua.mysite.repository.CategoryRepository;
import ua.mysite.repository.ProductRepository;
import ua.mysite.repository.SizeRepository;
import ua.mysite.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private SizeRepository sizeRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public void save(ProductForm form) {
		Product product = new Product();
		product.setId(form.getId());
		product.setName(form.getName());
		product.setPrice(Double.parseDouble(form.getPrice()));
		product.setCategory(form.getCategory());
		product.setBrand(form.getBrand());
		product.setSize(form.getSize());
		productRepository.save(product);
	}

	public void deleteById(int id) {
		productRepository.deleteById(id);

	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public List<Product> products() {
		return productRepository.products();
	}

	public ProductForm findForForm(int id) {
		Product product = productRepository.findOneCategoryBrandSizeInited(id);
		ProductForm form = new ProductForm();
		form.setId(product.getId());
		form.setName(product.getName());
		form.setPrice(String.valueOf(product.getPrice()));
		form.setCategory(product.getCategory());
		form.setBrand(product.getBrand());
		form.setSize(product.getSize());
		return form;
	}

	public Product findById(int id) {
		return productRepository.findById(id);
	}

}

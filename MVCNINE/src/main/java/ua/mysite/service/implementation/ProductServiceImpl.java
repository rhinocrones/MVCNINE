package ua.mysite.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.form.ProductForm;
import ua.form.ProductFormFilter;
import ua.mysite.entity.Product;
import ua.mysite.repository.BrandRepository;
import ua.mysite.repository.CategoryRepository;
import ua.mysite.repository.ProductRepository;
import ua.mysite.repository.SizeRepository;
import ua.mysite.service.ProductService;
import ua.mysite.service.implementation.specification.ProductFormFilterAdapter;

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

	public Product findByName(String name) {
		return productRepository.findByName(name);
	}

	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public Page<Product> findAll(Pageable pageable, ProductFormFilter form) {
		return productRepository.findAll(new ProductFormFilterAdapter(form), pageable);
	}

}

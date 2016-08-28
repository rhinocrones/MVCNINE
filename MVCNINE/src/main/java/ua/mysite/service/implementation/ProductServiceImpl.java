package ua.mysite.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.mysite.entity.Brand;
import ua.mysite.entity.Category;
import ua.mysite.entity.Product;
import ua.mysite.entity.Size;
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

	public void save(String name, double price, String category, String brand,
			int size) {
		Product product = new Product();

		if (name.isEmpty()) {
			product.setName("");
		} else {
			product.setName(name);
		}

		product.setPrice(price);

		Category category2 = categoryRepository.findByCategory(category);
		product.setCategory(category2);

		Brand brand2 = brandRepository.findByBrand(brand);
		product.setBrand(brand2);

		Size size2 = sizeRepository.findBySize(size);
		product.setSize(size2);
		productRepository.save(product);
	}

	public Product findById(int id) {
		return productRepository.findById(id);
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

}

package ua.mysite.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.form.CategoryFilter;
import ua.mysite.entity.Category;
import ua.mysite.repository.CategoryRepository;
import ua.mysite.service.CategoryService;
import ua.mysite.service.implementation.specification.CategoryFilterAdapter;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public void save(Category category) {
		categoryRepository.save(category);

	}

	public Category findById(int id) {
		return categoryRepository.findById(id);
	}

	public void deleteById(int id) {
		categoryRepository.deleteById(id);
	}

	public List<Category> findAll() {

		return categoryRepository.findAll();
	}

	public Category findByCategory(String category) {
		return categoryRepository.findByCategory(category);
	}

	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	public Page<Category> findAll(Pageable pageable, CategoryFilter form) {
		return categoryRepository.findAll(new CategoryFilterAdapter(form), pageable);
	}

}

package ua.mysite.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mysite.entity.Category;
import ua.mysite.repository.CategoryRepository;
import ua.mysite.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public void save(String category) {
		if (categoryRepository.findByCategory(category) == null) {
			Category category2 = new Category();
			category2.setCategory(category);
			categoryRepository.save(category2);
		} else
			System.out.println("Exist");

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

}

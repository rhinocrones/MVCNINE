package ua.mysite.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.mysite.entity.Category;
import ua.mysite.service.CategoryService;

public class CategoryValidator implements Validator{
	
	private final CategoryService categoryService;
	
	public CategoryValidator(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public boolean supports(Class<?> clazz) {
		return Category.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Category category = (Category) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "", "Category name can't be empty");
		if(categoryService.findByCategory(category.getCategory())!=null){
			errors.rejectValue("category", "", "Category with this name already exists");
		}
		
	}

}

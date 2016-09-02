package ua.mysite.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.mysite.entity.Category;
import ua.mysite.service.CategoryService;

public class CategoryEditor extends PropertyEditorSupport{

	private final CategoryService categoryService;

	public CategoryEditor(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Category category = categoryService.findById(Integer.valueOf(text));
		setValue(category);
	}
}

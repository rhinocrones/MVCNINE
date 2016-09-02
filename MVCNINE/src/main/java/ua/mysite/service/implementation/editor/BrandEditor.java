package ua.mysite.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.mysite.entity.Brand;
import ua.mysite.service.BrandService;

public class BrandEditor extends PropertyEditorSupport{

	private final BrandService brandService;

	public BrandEditor(BrandService brandService) {
		this.brandService = brandService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Brand brand = brandService.findById(Integer.valueOf(text));
		setValue(brand);
	}
}

package ua.mysite.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.mysite.entity.Brand;
import ua.mysite.service.BrandService;

public class BrandValidator implements Validator {

	private final BrandService brandService;

	public BrandValidator(BrandService brandService) {
		this.brandService = brandService;
	}

	public boolean supports(Class<?> clazz) {
		return Brand.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Brand brand = (Brand) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brand", "",
				"Brand name can't be empty");
		if (brandService.findByBrand(brand.getBrand()) != null) {
			errors.rejectValue("brand", "",
					"Brand with this name already exists");
		}

	}

}

package ua.mysite.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.ProductForm;
import ua.mysite.service.ProductService;

public class ProductFormValidator implements Validator{
	
	private final ProductService productService;
	
	public ProductFormValidator(ProductService productService){
		this.productService=productService;
	}
	

	public boolean supports(Class<?> clazz) {
		return ProductForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ProductForm form = (ProductForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Product name can't be empty");
		if(form.getId()==0)if(productService.findByName(form.getName())!=null){
			errors.rejectValue("name", "", "Recipe already exists");
		}
		Pattern p = Pattern.compile("^[0-9]{1,5}\\.[0-9]{2,2}$");
		Matcher m = p.matcher(form.getPrice());
		if(!m.matches()){
			errors.rejectValue("price", "", "Price format is min 0.00 - max 000.00");
		}
	}

}

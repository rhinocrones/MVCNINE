package ua.mysite.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.SizeForm;
import ua.mysite.service.SizeService;

public class SizeFormValidator implements Validator {

	private final SizeService sizeService;

	public SizeFormValidator(SizeService sizeService) {
		this.sizeService = sizeService;
	}

	public boolean supports(Class<?> clazz) {
		return SizeForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		SizeForm form = (SizeForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "size", "", "Size can't be empty");
		try {
			if(form.getId()==0)if(sizeService.findBySize(Integer.valueOf(form.getSize()))!=null){
				errors.rejectValue("size", "", "Size already exists");
			}
			
		} catch (NumberFormatException e) {
			errors.rejectValue("size", "", "Enter number");
		}


	}

}

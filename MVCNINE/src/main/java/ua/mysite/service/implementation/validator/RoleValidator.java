package ua.mysite.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.mysite.entity.Role;
import ua.mysite.service.RoleService;

public class RoleValidator implements Validator {

	private final RoleService roleService;

	public RoleValidator(RoleService roleService) {
		this.roleService = roleService;
	}

	public boolean supports(Class<?> clazz) {
		return Role.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Role role = (Role) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "",
				"Role can't be empty");
		if(role.getId()==0)if (roleService.findByRole(role.getRole()) != null) {
			errors.rejectValue("role", "",
					"Role with this name already exists");
		}
	}

}

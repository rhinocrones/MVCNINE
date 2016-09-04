package ua.mysite.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.mysite.entity.Role;
import ua.mysite.service.RoleService;

public class RoleEditor extends PropertyEditorSupport {

	private final RoleService roleService;

	public RoleEditor(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Role role = roleService.findById(Integer.valueOf(text));
		setValue(role);
	}
}

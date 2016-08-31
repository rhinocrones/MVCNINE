package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.mysite.entity.Role;
import ua.mysite.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	@ModelAttribute("role")
	public Role getRole() {
		return new Role();
	}

	@RequestMapping("/adminPanel/role")
	public String showRole(Model model) {
		model.addAttribute("roles", roleService.findAll());
		return "role";
	}

	@RequestMapping(value = "/adminPanel/role", method = RequestMethod.POST)
	public String save(@ModelAttribute("role") Role role) {
		roleService.save(role);
		return "redirect:/adminPanel/role";
	}

	@RequestMapping(value = "/adminPanel/role/delete/{id}")
	public String delete(@PathVariable int id) {
		roleService.deleteById(id);
		return "redirect:/adminPanel/role";
	}
	
	@RequestMapping("/adminPanel/role/update/{id}")
	public String updateRole(@PathVariable int id, Model model) {
		model.addAttribute("role", roleService.findById(id));
		model.addAttribute("roles", roleService.findAll());
		return "role";
	}
}

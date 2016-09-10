package ua.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.mysite.entity.Role;
import ua.mysite.service.RoleService;
import ua.mysite.service.implementation.validator.RoleValidator;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	@ModelAttribute("role")
	public Role getRole() {
		return new Role();
	}
	
	@InitBinder("role")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new RoleValidator(roleService));
	}

	@RequestMapping("/adminPanel/role")
	public String showBrand(Model model, @PageableDefault(5) Pageable pageable) {
		model.addAttribute("page", roleService.findAll(pageable));
		return "role";
	}

	@RequestMapping(value = "/adminPanel/role", method = RequestMethod.POST)
	public String save(@ModelAttribute("role") @Valid Role role,
			BindingResult br, Model model, @PageableDefault(5) Pageable pageable) {
		if (br.hasErrors()) {
			model.addAttribute("page", roleService.findAll(pageable));
			return "role";
		}
		roleService.save(role);
		return "redirect:/adminPanel/role";
	}

	@RequestMapping(value = "/adminPanel/role/delete/{id}")
	public String delete(
			@PathVariable int id,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort) {
		roleService.deleteById(id);
		return "redirect:/adminPanel/role?page=" + page + "&size=" + size
				+ "&sort=" + sort;
	}
	
	@RequestMapping(value="/adminPanel/role/update/{id}")
	public String updateCategory(@PathVariable int id, Model model,
			@PageableDefault(5) Pageable pageable) {
		model.addAttribute("role", roleService.findById(id));
		model.addAttribute("page", roleService.findAll(pageable));
		return "role";
	}
}

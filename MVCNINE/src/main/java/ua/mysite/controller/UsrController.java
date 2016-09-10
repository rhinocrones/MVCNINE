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
import ua.mysite.entity.Usr;
import ua.mysite.service.RoleService;
import ua.mysite.service.UsrService;
import ua.mysite.service.implementation.editor.RoleEditor;
import ua.mysite.service.implementation.validator.UsrValidator;

@Controller
public class UsrController {

	@Autowired
	private UsrService usrService;

	@Autowired
	private RoleService roleService;

	@InitBinder("usr")
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Role.class, new RoleEditor(roleService));
		binder.setValidator(new UsrValidator(usrService));
	}

	@ModelAttribute("usr")
	public Usr getUsr() {
		return new Usr();
	}

	@RequestMapping("/adminPanel/usr")
	public String showUsr(Model model, @PageableDefault(5) Pageable pageable) {
		model.addAttribute("page", usrService.findAll(pageable));
		model.addAttribute("roles", roleService.findAll());
		return "usr";
	}

	@RequestMapping(value = "/adminPanel/usr/delete/{id}")
	public String delete(
			@PathVariable int id,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort) {
		usrService.deleteById(id);
		return "redirect:/adminPanel/usr?page=" + page + "&size=" + size
				+ "&sort=" + sort;
	}

	@RequestMapping(value = "/adminPanel/usr/update/{id}")
	public String update(@PathVariable int id, Model model,
			@PageableDefault(5) Pageable pageable) {
		model.addAttribute("usr", usrService.findById(id));
		model.addAttribute("page", usrService.findAll(pageable));
		model.addAttribute("roles", roleService.findAll());
		return "usr";
	}

	@RequestMapping(value="/adminPanel/usr", method=RequestMethod.POST)
	public String save(@ModelAttribute("usr") @Valid Usr usr,
			BindingResult br, Model model, @PageableDefault(5) Pageable pageable){
		if(br.hasErrors()){
			model.addAttribute("page", usrService.findAll(pageable));
			model.addAttribute("roles", roleService.findAll());
			return "usr";
		}
		usrService.save(usr);
		return "redirect:/adminPanel/usr";
	}
}

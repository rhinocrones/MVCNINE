package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.mysite.entity.Role;
import ua.mysite.entity.Usr;
import ua.mysite.service.RoleService;
import ua.mysite.service.UsrService;
import ua.mysite.service.implementation.editor.RoleEditor;

@Controller
public class UsrController {

	@Autowired
	private UsrService usrService;

	@Autowired
	private RoleService roleService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Role.class, new RoleEditor(roleService));
	}

	@ModelAttribute("usr")
	public Usr getUsr() {
		return new Usr();
	}

	@RequestMapping("/adminPanel/usr")
	public String showUsr(Model model) {
		model.addAttribute("usrs", usrService.usrs());
		model.addAttribute("roles", roleService.findAll());
		return "usr";
	}

	@RequestMapping(value = "/adminPanel/usr/delete/{id}")
	public String delete(@PathVariable int id) {
		usrService.deleteById(id);
		return "redirect:/adminPanel/usr";
	}

	@RequestMapping(value = "/adminPanel/usr/update/{id}")
	public String update(Model model, @PathVariable int id) {
		model.addAttribute("usr", usrService.findById(id));
		model.addAttribute("usrs", usrService.usrs());
		model.addAttribute("roles", roleService.findAll());
		return "usr";
	}

	@RequestMapping(value = "/adminPanel/usr", method = RequestMethod.POST)
	public String save(@ModelAttribute("usr") Usr usr) {
		usrService.save(usr);
		return "redirect:/adminPanel/usr";
	}
}

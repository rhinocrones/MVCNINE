package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.mysite.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value= "/adminPanel/role", method=RequestMethod.POST)
	public String save(@RequestParam String role){
		roleService.save(role);
		return "redirect:/adminPanel/role";
	}
	
	@RequestMapping(value= "/adminPanel/role/delete/{id}")
	public String delete(@PathVariable int id){
		roleService.deleteById(id);
		return "redirect:/adminPanel/role";
	}
}

package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.mysite.service.UsrService;

@Controller
public class UsrController {

	@Autowired
	private UsrService usrService;

	@RequestMapping(value = "/adminPanel/usr/delete/{id}")
	public String delete(@PathVariable int id) {
		usrService.deleteById(id);
		return "redirect:/adminPanel/usr";
	}

	@RequestMapping(value = "/adminPanel/usr", method = RequestMethod.POST)
	public String save(@RequestParam String username,
			@RequestParam String email, @RequestParam String password,
			@RequestParam String role) {
		usrService.save(username, email, password, role);
		return "redirect:/adminPanel/usr";
	}
}

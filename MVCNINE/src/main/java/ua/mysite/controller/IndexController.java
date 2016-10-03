package ua.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	@RequestMapping("/adminPanel")
	public String showAdmin(){
		return "adminPanel";
	}
	
	@RequestMapping("/userPanel")
	public String showUser(){
		return "userPanel";
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
}

package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.mysite.entity.Usr;
import ua.mysite.service.UsrService;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private UsrService usrService;

	@ModelAttribute("usr")
	public Usr getUser() {
		String id = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		if (!"anonymousUser".equals(id)) {
			return usrService.findById(Integer.valueOf(id));
		}
		return null;
	}
}

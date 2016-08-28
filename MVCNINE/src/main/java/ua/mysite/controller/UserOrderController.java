package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.mysite.service.UserOrderService;

@Controller
public class UserOrderController {

	@Autowired
	private UserOrderService userOrderService;

	@RequestMapping(value = "/adminPanel/userOrder/delete/{id}")
	public String delete(@PathVariable int id) {
		userOrderService.deleteById(id);
		return "redirect:/adminPanel/userOrder";
	}

	@RequestMapping(value= "/adminPanel/userOrder", method=RequestMethod.POST)
	public String save(@RequestParam String username, @RequestParam String name){
		userOrderService.save(username, name);
		return "redirect:/adminPanel/userOrder";
	}
}

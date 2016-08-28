package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.mysite.service.ProductService;

@Controller
public class UserPanelController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/userPanel/userProduct")
	public String showProduct(Model model){
		model.addAttribute("products", productService.products());
		return "userProduct";
	}	
}

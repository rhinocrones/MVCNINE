package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.mysite.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/adminPanel/product/delete/{id}")
	public String delete(@PathVariable int id) {
		productService.deleteById(id);
		return "redirect:/adminPanel/product";
	}

	@RequestMapping(value = "/adminPanel/product", method = RequestMethod.POST)
	public String save(@RequestParam String name, @RequestParam double price,
			@RequestParam String category, @RequestParam String brand,
			@RequestParam int size) {
		productService.save(name, price, category, brand, size);
		return "redirect:/adminPanel/product";
	}
}

package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.mysite.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value= "/adminPanel/category", method=RequestMethod.POST)
	public String save(@RequestParam String category){
		categoryService.save(category);
		return "redirect:/adminPanel/category";
	}
	
	@RequestMapping(value= "/adminPanel/category/delete/{id}")
	public String delete(@PathVariable int id){
		categoryService.deleteById(id);
		return "redirect:/adminPanel/category";
	}
}

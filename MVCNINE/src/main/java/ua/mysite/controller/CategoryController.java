package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.mysite.entity.Category;
import ua.mysite.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@ModelAttribute("category")
	public Category getCategory(){
		return new Category();
	}
	
	@RequestMapping("/adminPanel/category")
	public String showCategory(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		return "category";
	}
	
	@RequestMapping(value= "/adminPanel/category", method=RequestMethod.POST)
	public String save(@ModelAttribute("category") Category category){
		categoryService.save(category);
		return "redirect:/adminPanel/category";
	}
	
	@RequestMapping(value= "/adminPanel/category/delete/{id}")
	public String delete(@PathVariable int id){
		categoryService.deleteById(id);
		return "redirect:/adminPanel/category";
	}
	
	@RequestMapping("/adminPanel/category/update/{id}")
	public String updateCategory(@PathVariable int id, Model model){
		model.addAttribute("category", categoryService.findById(id));
		model.addAttribute("categories", categoryService.findAll());
		return "category";
	}
}

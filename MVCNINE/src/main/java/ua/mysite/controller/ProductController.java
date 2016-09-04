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

import ua.form.ProductForm;
import ua.mysite.entity.Brand;
import ua.mysite.entity.Category;
import ua.mysite.entity.Size;
import ua.mysite.service.BrandService;
import ua.mysite.service.CategoryService;
import ua.mysite.service.ProductService;
import ua.mysite.service.SizeService;
import ua.mysite.service.implementation.editor.BrandEditor;
import ua.mysite.service.implementation.editor.CategoryEditor;
import ua.mysite.service.implementation.editor.SizeEditor;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired 
	private CategoryService categoryService;
	
	@Autowired 
	private BrandService brandService;
	
	@Autowired 
	private SizeService sizeService;
		
	@InitBinder
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
	   binder.registerCustomEditor(Brand.class, new BrandEditor(brandService));
	   binder.registerCustomEditor(Size.class, new SizeEditor(sizeService));
	}
	
	@ModelAttribute("form")
	public ProductForm getForm(){
		return new ProductForm();
	}
	
	@RequestMapping("/adminPanel/product")
	public String showProducts(Model model){
		model.addAttribute("products", productService.products());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("sizes", sizeService.findAll());
		return "product";
	}

	@RequestMapping(value="/adminPanel/product", method=RequestMethod.POST)
	public String save(@ModelAttribute("form") ProductForm form){
		productService.save(form);
		return "redirect:/adminPanel/product";
	}
	
	@RequestMapping(value="/adminPanel/product/update/{id}")
	public String update(Model model, @PathVariable int id){
		model.addAttribute("form", productService.findForForm(id));
		model.addAttribute("products", productService.products());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("sizes", sizeService.findAll());
		return "product";
	}
	
	@RequestMapping(value = "/adminPanel/product/delete/{id}")
	public String delete(@PathVariable int id) {
		productService.deleteById(id);
		return "redirect:/adminPanel/product";
	}
}

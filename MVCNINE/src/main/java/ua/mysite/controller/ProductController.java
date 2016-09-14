package ua.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.form.ProductForm;
import ua.form.ProductFormFilter;
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
import ua.mysite.service.implementation.validator.ProductFormValidator;

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
	
	@ModelAttribute("filter")
	public ProductFormFilter getFilter() {
		return new ProductFormFilter();
	}
		
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
	   binder.registerCustomEditor(Brand.class, new BrandEditor(brandService));
	   binder.registerCustomEditor(Size.class, new SizeEditor(sizeService));
	   binder.setValidator(new ProductFormValidator(productService));
	}
	
	@ModelAttribute("form")
	public ProductForm getForm(){
		return new ProductForm();
	}
	
	@RequestMapping("/adminPanel/product")
	public String showProducts(Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") ProductFormFilter form){
		model.addAttribute("page", productService.findAll(pageable, form));
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("sizes", sizeService.findAll());
		return "product";
	}

	@RequestMapping(value="/adminPanel/product", method=RequestMethod.POST)
	public String save(@ModelAttribute("form") @Valid ProductForm form, BindingResult br, 
			Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") ProductFormFilter form1){
		if(br.hasErrors()){
			model.addAttribute("page", productService.findAll(pageable, form1));
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("brands", brandService.findAll());
			model.addAttribute("sizes", sizeService.findAll());
			return "product";
		}
		productService.save(form);
		return "redirect:/adminPanel/product"+getParams(pageable, form1);
	}
	
	@RequestMapping(value="/adminPanel/product/update/{id}")
	public String update(@PathVariable int id, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") ProductFormFilter form){
		model.addAttribute("form", productService.findForForm(id));
		model.addAttribute("page", productService.findAll(pageable, form));
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("sizes", sizeService.findAll());
		return "product";
	}
	
	@RequestMapping(value = "/adminPanel/product/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") ProductFormFilter form) {
		productService.deleteById(id);
		return "redirect:/adminPanel/product"+getParams(pageable, form);
	}
	
	private String getParams(Pageable pageable, ProductFormFilter form) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber() + 1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if (pageable.getSort() != null) {
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order) -> {
				buffer.append(order.getProperty());
				if (order.getDirection() != Direction.ASC)
					buffer.append(",desc");
			});
		}
		buffer.append("&search=");
		buffer.append(form.getSearch());
		return buffer.toString();
	}
}

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

import ua.form.CategoryFilter;
import ua.mysite.entity.Category;
import ua.mysite.service.CategoryService;
import ua.mysite.service.implementation.validator.CategoryValidator;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}

	@ModelAttribute("filter")
	public CategoryFilter getFilter() {
		return new CategoryFilter();
	}

	@InitBinder("category")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new CategoryValidator(categoryService));
	}

	@RequestMapping("/adminPanel/category")
	public String showCategory(Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") CategoryFilter form) {
		model.addAttribute("page", categoryService.findAll(pageable, form));
		return "category";
	}

	@RequestMapping(value = "/adminPanel/category", method = RequestMethod.POST)
	public String save(@ModelAttribute("category") @Valid Category category,
			BindingResult br, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") CategoryFilter form) {
		if (br.hasErrors()) {
			model.addAttribute("page", categoryService.findAll(pageable, form));
			return "category";
		}
		categoryService.save(category);
		return "redirect:/adminPanel/category"+getParams(pageable, form);
	}

	@RequestMapping(value = "/adminPanel/category/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") CategoryFilter form) {
		categoryService.deleteById(id);
		return "redirect:/adminPanel/category" + getParams(pageable, form);
	}

	@RequestMapping(value = "/adminPanel/category/update/{id}")
	public String updateCategory(Model model, @PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") CategoryFilter form) {
		model.addAttribute("category", categoryService.findById(id));
		model.addAttribute("page", categoryService.findAll(pageable, form));
		return "category";
	}

	private String getParams(Pageable pageable, CategoryFilter form){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		buffer.append("&search=");
		buffer.append(form.getSearch());
		return buffer.toString();
	}
}

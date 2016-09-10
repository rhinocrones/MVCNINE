package ua.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;

import ua.mysite.entity.Brand;
import ua.mysite.service.BrandService;
import ua.mysite.service.implementation.validator.BrandValidator;

@Controller
public class BrandController {

	@Autowired
	private BrandService brandService;

	@ModelAttribute("brand")
	public Brand getBrand() {
		return new Brand();
	}

	@InitBinder("brand")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new BrandValidator(brandService));
	}

	@RequestMapping("/adminPanel/brand")
	public String showBrand(Model model, @PageableDefault(5) Pageable pageable) {
		model.addAttribute("page", brandService.findAll(pageable));
		return "brand";
	}

	@RequestMapping(value = "/adminPanel/brand", method = RequestMethod.POST)
	public String save(@ModelAttribute("brand") @Valid Brand brand,
			BindingResult br, Model model, @PageableDefault(5) Pageable pageable) {
		if (br.hasErrors()) {
			model.addAttribute("page", brandService.findAll(pageable));
			return "brand";
		}
		brandService.save(brand);
		return "redirect:/adminPanel/brand";
	}

	@RequestMapping(value = "/adminPanel/brand/delete/{id}")
	public String delete(
			@PathVariable int id,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort) {
		brandService.deleteById(id);
		return "redirect:/adminPanel/brand?page=" + page + "&size=" + size
				+ "&sort=" + sort;
	}

	@RequestMapping(value="/adminPanel/brand/update/{id}")
	public String updateCategory(@PathVariable int id, Model model,
			@PageableDefault(5) Pageable pageable) {
		model.addAttribute("brand", brandService.findById(id));
		model.addAttribute("page", brandService.findAll(pageable));
		return "brand";
	}
}

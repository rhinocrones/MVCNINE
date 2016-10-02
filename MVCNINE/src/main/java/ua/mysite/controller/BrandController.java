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

import ua.form.BrandFilter;
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

	@ModelAttribute("filter")
	public BrandFilter getFilter() {
		return new BrandFilter();
	}

	@InitBinder("brand")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new BrandValidator(brandService));
	}

	@RequestMapping("/adminPanel/brand")
	public String showBrand(Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") BrandFilter form) {
		model.addAttribute("page", brandService.findAll(pageable, form));
		return "brand";
	}

	@RequestMapping(value = "/adminPanel/brand", method = RequestMethod.POST)
	public String save(@ModelAttribute("brand") @Valid Brand brand,
			BindingResult br, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") BrandFilter form) {
		if (br.hasErrors()) {
			model.addAttribute("page", brandService.findAll(pageable, form));
			return "brand";
		}
		brandService.save(brand);
		return "redirect:/adminPanel/brand" + getParams(pageable, form);
	}

	@RequestMapping(value = "/adminPanel/brand/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") BrandFilter form) {
		brandService.deleteById(id);
		return "redirect:/adminPanel/brand" + getParams(pageable, form);
	}

	@RequestMapping(value = "/adminPanel/brand/update/{id}")
	public String updateCategory(@PathVariable int id, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") BrandFilter form) {
		model.addAttribute("brand", brandService.findById(id));
		model.addAttribute("page", brandService.findAll(pageable, form));
		return "brand";
	}

	private String getParams(Pageable pageable, BrandFilter form){
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

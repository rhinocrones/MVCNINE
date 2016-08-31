package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.mysite.entity.Brand;
import ua.mysite.service.BrandService;

@Controller
public class BrandController {

	@Autowired
	private BrandService brandService;

	@ModelAttribute("brand")
	public Brand getBrand() {
		return new Brand();
	}

	@RequestMapping("/adminPanel/brand")
	public String showBrand(Model model) {
		model.addAttribute("brands", brandService.findAll());
		return "brand";
	}

	@RequestMapping(value = "/adminPanel/brand", method = RequestMethod.POST)
	public String save(@ModelAttribute("brand") Brand brand) {
		brandService.save(brand);
		return "redirect:/adminPanel/brand";
	}

	@RequestMapping(value = "/adminPanel/brand/delete/{id}")
	public String delete(@PathVariable int id) {
		brandService.deleteById(id);
		return "redirect:/adminPanel/brand";
	}

	@RequestMapping("/adminPanel/brand/update/{id}")
	public String updateBrand(@PathVariable int id, Model model) {
		model.addAttribute("brand", brandService.findById(id));
		model.addAttribute("brands", brandService.findAll());
		return "brand";
	}
}

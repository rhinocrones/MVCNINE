package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.mysite.service.BrandService;

@Controller
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@RequestMapping(value= "/adminPanel/brand", method=RequestMethod.POST)
	public String save(@RequestParam String brand){
		brandService.save(brand);
		return "redirect:/adminPanel/brand";
	}
	
	@RequestMapping(value= "/adminPanel/brand/delete/{id}")
	public String delete(@PathVariable int id){
		brandService.deleteById(id);
		return "redirect:/adminPanel/brand";
	}
}

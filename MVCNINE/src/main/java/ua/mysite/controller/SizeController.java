package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.mysite.service.SizeService;

@Controller
public class SizeController {

	@Autowired
	private SizeService sizeService;
	
	@RequestMapping(value= "/adminPanel/size", method=RequestMethod.POST)
	public String save(@RequestParam int size){
		sizeService.save(size);
		return "redirect:/adminPanel/size";
	}
	
	@RequestMapping(value= "/adminPanel/size/delete/{id}")
	public String delete(@PathVariable int id){
		sizeService.deleteById(id);
		return "redirect:/adminPanel/size";
	}
}

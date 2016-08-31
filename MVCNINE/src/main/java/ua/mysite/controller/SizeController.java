package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.mysite.entity.Size;
import ua.mysite.service.SizeService;

@Controller
public class SizeController {

	@Autowired
	private SizeService sizeService;

	@ModelAttribute("size")
	public Size getSize() {
		return new Size();
	}

	@RequestMapping("/adminPanel/size")
	public String showSize(Model model) {
		model.addAttribute("sizes", sizeService.findAll());
		return "size";
	}

	@RequestMapping(value = "/adminPanel/size", method = RequestMethod.POST)
	public String save(@ModelAttribute("size") Size size) {
		sizeService.save(size);
		return "redirect:/adminPanel/size";
	}

	@RequestMapping(value = "/adminPanel/size/delete/{id}")
	public String delete(@PathVariable int id) {
		sizeService.deleteById(id);
		return "redirect:/adminPanel/size";
	}

	@RequestMapping("/adminPanel/size/update/{id}")
	public String updateSize(@PathVariable int id, Model model) {
		model.addAttribute("size", sizeService.findById(id));
		model.addAttribute("sizes", sizeService.findAll());
		return "size";
	}
}

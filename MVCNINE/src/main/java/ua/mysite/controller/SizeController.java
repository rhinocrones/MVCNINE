package ua.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.form.SizeForm;
import ua.mysite.service.SizeService;
import ua.mysite.service.implementation.validator.SizeFormValidator;

@Controller
public class SizeController {

	@Autowired
	private SizeService sizeService;

	@ModelAttribute("form")
	public SizeForm getSize(){
		return new SizeForm();
	}
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new SizeFormValidator(sizeService));
	}

	@RequestMapping("/adminPanel/size")
	public String showSize(Model model) {
		model.addAttribute("sizes", sizeService.findAll());
		return "size";
	}

	@RequestMapping(value= "/adminPanel/size", method=RequestMethod.POST)
	public String save(@ModelAttribute("form") @Valid SizeForm form, BindingResult br, Model model){
		if(br.hasErrors()){
			model.addAttribute("sizes", sizeService.findAll());
			return "size";
		}
		sizeService.save(form);
		return "redirect:/adminPanel/size";
	}

	@RequestMapping(value = "/adminPanel/size/delete/{id}")
	public String delete(@PathVariable int id) {
		sizeService.deleteById(id);
		return "redirect:/adminPanel/size";
	}

	@RequestMapping(value="/adminPanel/size/update/{id}")
	public String update(Model model, @PathVariable int id){
		model.addAttribute("form", sizeService.findForForm(id));
		model.addAttribute("sizes", sizeService.findAll());
		return "size";
	}
}

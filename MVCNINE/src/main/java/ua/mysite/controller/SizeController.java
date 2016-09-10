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
	public String showBrand(Model model, @PageableDefault(5) Pageable pageable) {
		model.addAttribute("page", sizeService.findAll(pageable));
		return "size";
	}

	@RequestMapping(value = "/adminPanel/size", method = RequestMethod.POST)
	public String save(@ModelAttribute("form") @Valid SizeForm form,
			BindingResult br, Model model, @PageableDefault(5) Pageable pageable) {
		if (br.hasErrors()) {
			model.addAttribute("page", sizeService.findAll(pageable));
			return "size";
		}
		sizeService.save(form);
		return "redirect:/adminPanel/size";
	}

	@RequestMapping(value = "/adminPanel/size/delete/{id}")
	public String delete(
			@PathVariable int id,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort) {
		sizeService.deleteById(id);
		return "redirect:/adminPanel/size?page=" + page + "&size=" + size
				+ "&sort=" + sort;
	}

	@RequestMapping(value="/adminPanel/size/update/{id}")
	public String updateSize(@PathVariable int id, Model model,
			@PageableDefault(5) Pageable pageable){
		model.addAttribute("form", sizeService.findForForm(id));
		model.addAttribute("page", sizeService.findAll(pageable));
		return "size";
	}
}

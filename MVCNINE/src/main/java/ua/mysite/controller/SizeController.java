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

import ua.form.SizeForm;
import ua.form.SizeFormFilter;
import ua.mysite.service.SizeService;
import ua.mysite.service.implementation.validator.SizeFormValidator;

@Controller
public class SizeController {

	@Autowired
	private SizeService sizeService;

	@ModelAttribute("form")
	public SizeForm getSize() {
		return new SizeForm();
	}

	@ModelAttribute("filter")
	public SizeFormFilter getFilter() {
		return new SizeFormFilter();
	}

	@InitBinder("form")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new SizeFormValidator(sizeService));
	}

	@RequestMapping("/adminPanel/size")
	public String showSize(Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") SizeFormFilter form) {
		model.addAttribute("page", sizeService.findAll(pageable, form));
		return "size";
	}

	@RequestMapping(value = "/adminPanel/size", method = RequestMethod.POST)
	public String save(@ModelAttribute("form") @Valid SizeForm form,
			BindingResult br, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") SizeFormFilter form1) {
		if (br.hasErrors()) {
			model.addAttribute("page", sizeService.findAll(pageable, form1));
			return "size";
		}
		sizeService.save(form);
		return "redirect:/adminPanel/size" + getParams(pageable, form1);
	}

	@RequestMapping(value = "/adminPanel/size/delete/{id}")
	public String delete(
			@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") SizeFormFilter form) {
		sizeService.deleteById(id);
		return "redirect:/adminPanel/size" + getParams(pageable, form);
	}

	@RequestMapping(value = "/adminPanel/size/update/{id}")
	public String updateSize(@PathVariable int id, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") SizeFormFilter form) {
		model.addAttribute("form", sizeService.findForForm(id));
		model.addAttribute("page", sizeService.findAll(pageable, form));
		return "size";
	}
	
	private String getParams(Pageable pageable, SizeFormFilter form){
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

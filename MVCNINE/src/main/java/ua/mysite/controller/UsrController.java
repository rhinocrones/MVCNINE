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
import ua.form.UsrFilter;
import ua.mysite.entity.Role;
import ua.mysite.entity.Usr;
import ua.mysite.service.RoleService;
import ua.mysite.service.UsrService;
import ua.mysite.service.implementation.editor.RoleEditor;
import ua.mysite.service.implementation.validator.UsrValidator;

@Controller
public class UsrController {

	@Autowired
	private UsrService usrService;

	@Autowired
	private RoleService roleService;

	@ModelAttribute("filter")
	public UsrFilter getFilter() {
		return new UsrFilter();
	}

	@InitBinder("usr")
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Role.class, new RoleEditor(roleService));
		binder.setValidator(new UsrValidator(usrService));
	}

	@ModelAttribute("usr")
	public Usr getUsr() {
		return new Usr();
	}

	@RequestMapping("/adminPanel/usr")
	public String showUsr(Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") UsrFilter form) {
		model.addAttribute("page", usrService.findAll(pageable, form));
		model.addAttribute("roles", roleService.findAll());
		return "usr";
	}

	@RequestMapping(value = "/adminPanel/usr/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") UsrFilter form) {
		usrService.deleteById(id);
		return "redirect:/adminPanel/usr"+getParams(pageable, form);
	}

	@RequestMapping(value = "/adminPanel/usr/update/{id}")
	public String update(@PathVariable int id, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") UsrFilter form) {
		model.addAttribute("usr", usrService.findById(id));
		model.addAttribute("page", usrService.findAll(pageable, form));
		model.addAttribute("roles", roleService.findAll());
		return "usr";
	}

	@RequestMapping(value = "/adminPanel/usr", method = RequestMethod.POST)
	public String save(@ModelAttribute("usr") @Valid Usr usr, BindingResult br,
			Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") UsrFilter form) {
		if (br.hasErrors()) {
			model.addAttribute("page", usrService.findAll(pageable, form));
			model.addAttribute("roles", roleService.findAll());
			return "usr";
		}
		usrService.save(usr);
		return "redirect:/adminPanel/usr"+ getParams(pageable, form);
	}
	
	private String getParams(Pageable pageable, UsrFilter form){
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
		for(Integer i : form.getRoleIds()){
			buffer.append("&roleIds=");
			buffer.append(i.toString());
		}
		return buffer.toString();
	}
	
	@RequestMapping("/registration")
	public String register(){
		return "registration";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String save(@ModelAttribute("user") Usr user){
		usrService.save(user);
		return "redirect:/login";
	}
}

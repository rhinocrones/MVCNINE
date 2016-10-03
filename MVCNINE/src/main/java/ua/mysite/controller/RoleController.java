package ua.mysite.controller;

import java.io.Serializable;

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

import ua.form.RoleFilter;
import ua.mysite.entity.Role;
import ua.mysite.service.RoleService;
import ua.mysite.service.implementation.validator.RoleValidator;

@Controller
public class RoleController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8962913040966848188L;
	@Autowired
	private RoleService roleService;

	@ModelAttribute("role")
	public Role getRole() {
		return new Role();
	}

	@ModelAttribute("filter")
	public RoleFilter getFilter() {
		return new RoleFilter();
	}

	@InitBinder("role")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new RoleValidator(roleService));
	}

	@RequestMapping("/adminPanel/role")
	public String showBrand(Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") RoleFilter form) {
		model.addAttribute("page", roleService.findAll(pageable, form));
		return "role";
	}

	@RequestMapping(value = "/adminPanel/role", method = RequestMethod.POST)
	public String save(@ModelAttribute("role") @Valid Role role,
			BindingResult br, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") RoleFilter form) {
		if (br.hasErrors()) {
			model.addAttribute("page", roleService.findAll(pageable, form));
			return "role";
		}
		roleService.save(role);
		return "redirect:/adminPanel/role" + getParams(pageable, form);
	}

	@RequestMapping(value = "/adminPanel/role/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") RoleFilter form) {
		roleService.deleteById(id);
		return "redirect:/adminPanel/role" + getParams(pageable, form);
	}

	@RequestMapping(value = "/adminPanel/role/update/{id}")
	public String updateCategory(@PathVariable int id, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") RoleFilter form) {
		model.addAttribute("role", roleService.findById(id));
		model.addAttribute("page", roleService.findAll(pageable, form));
		return "role";
	}

	private String getParams(Pageable pageable, RoleFilter form){
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

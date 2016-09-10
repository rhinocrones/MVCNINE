package ua.mysite.controller;

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

import ua.mysite.entity.Product;
import ua.mysite.entity.UserOrder;
import ua.mysite.entity.Usr;
import ua.mysite.service.ProductService;
import ua.mysite.service.UserOrderService;
import ua.mysite.service.UsrService;
import ua.mysite.service.implementation.editor.ProductEditor;
import ua.mysite.service.implementation.editor.UsrEditor;

@Controller
public class UserOrderController {

	@Autowired
	private UserOrderService userOrderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UsrService usrService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Product.class, new ProductEditor(
				productService));
		binder.registerCustomEditor(Usr.class, new UsrEditor(usrService));
	}

	@ModelAttribute("userOrder")
	public UserOrder getUserOrder() {
		return new UserOrder();
	}

	@RequestMapping("/adminPanel/userOrder")
	public String showUserOrder(Model model,
			@PageableDefault(5) Pageable pageable) {
		model.addAttribute("page", userOrderService.findAll(pageable));
		model.addAttribute("products", productService.findAll());
		model.addAttribute("usrs", usrService.findAll());
		return "userOrder";
	}

	@RequestMapping(value = "/adminPanel/userOrder/delete/{id}")
	public String delete(
			@PathVariable int id,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort) {
		userOrderService.deleteById(id);
		return "redirect:/adminPanel/userOrder?page=" + page + "&size=" + size
				+ "&sort=" + sort;
	}

	@RequestMapping(value = "/adminPanel/userOrder/update/{id}")
	public String update(@PathVariable int id, Model model,
			@PageableDefault(5) Pageable pageable) {
		model.addAttribute("userOrder", userOrderService.findById(id));
		model.addAttribute("page", userOrderService.findAll(pageable));
		model.addAttribute("products", productService.findAll());
		model.addAttribute("usrs", usrService.findAll());
		return "userOrder";
	}

	@RequestMapping(value = "/adminPanel/userOrder", method = RequestMethod.POST)
	public String save(@ModelAttribute("usr") UserOrder userOrder,
			BindingResult br, Model model, @PageableDefault(5) Pageable pageable) {
		if (br.hasErrors()) {
			model.addAttribute("page", userOrderService.findAll(pageable));
			model.addAttribute("products", productService.findAll());
			model.addAttribute("usrs", usrService.findAll());
			return "userOrder";
		}
		userOrderService.save(userOrder);
		return "redirect:/adminPanel/userOrder";
	}
}

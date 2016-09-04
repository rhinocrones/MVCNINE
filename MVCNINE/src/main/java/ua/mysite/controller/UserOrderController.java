package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		binder.registerCustomEditor(Product.class, new ProductEditor(productService));
		binder.registerCustomEditor(Usr.class, new UsrEditor(usrService));
	}
	
	@ModelAttribute("userOrder")
	public UserOrder getUserOrder() {
		return new UserOrder();
	}
	
	@RequestMapping("/adminPanel/userOrder")
	public String showUserOrder(Model model) {
		model.addAttribute("userOrders", userOrderService.userOrders());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("usrs", usrService.findAll());
		return "userOrder";
	}

	@RequestMapping(value = "/adminPanel/userOrder/delete/{id}")
	public String delete(@PathVariable int id) {
		userOrderService.deleteById(id);
		return "redirect:/adminPanel/userOrder";
	}
	
	@RequestMapping(value = "/adminPanel/userOrder/update/{id}")
	public String update(Model model, @PathVariable int id) {
		model.addAttribute("userOrder", userOrderService.findById(id));
		model.addAttribute("userOrders", userOrderService.userOrders());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("usrs", usrService.findAll());
		return "userOrder";
	}

	@RequestMapping(value = "/adminPanel/userOrder", method = RequestMethod.POST)
	public String save(@ModelAttribute("usr") UserOrder userOrder) {
		userOrderService.save(userOrder);
		return "redirect:/adminPanel/userOrder";
	}
}

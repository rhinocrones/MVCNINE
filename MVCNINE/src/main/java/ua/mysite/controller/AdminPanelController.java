package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.mysite.service.BrandService;
import ua.mysite.service.CategoryService;
import ua.mysite.service.ProductService;
import ua.mysite.service.RoleService;
import ua.mysite.service.SizeService;
import ua.mysite.service.UserOrderService;
import ua.mysite.service.UsrService;

@Controller
public class AdminPanelController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private SizeService sizeService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UsrService usrService;

	@Autowired
	private UserOrderService userOrderService;

	@RequestMapping("/adminPanel/product")
	public String showProduct(Model model) {
		model.addAttribute("products", productService.products());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("sizes", sizeService.findAll());
		return "product";
	}

	@RequestMapping("/adminPanel/usr")
	public String showUsr(Model model) {
		model.addAttribute("usrs", usrService.usrs());
		model.addAttribute("roles", roleService.findAll());
		return "usr";
	}

	@RequestMapping("/adminPanel/userOrder")
	public String showUserOrder(Model model) {
		model.addAttribute("userOrders", userOrderService.userOrders());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("usrs", usrService.findAll());
		return "userOrder";
	}
}

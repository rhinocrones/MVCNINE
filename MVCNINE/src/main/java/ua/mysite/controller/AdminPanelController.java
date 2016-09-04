package ua.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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


}

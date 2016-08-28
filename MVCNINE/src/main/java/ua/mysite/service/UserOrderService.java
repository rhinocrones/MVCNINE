package ua.mysite.service;

import java.util.List;

import ua.mysite.entity.UserOrder;

public interface UserOrderService {

	void save(String username, String name);
	
	UserOrder findById(int id);
	
	void deleteById(int id);
	
	List<UserOrder> findAll();
	
	List<UserOrder> userOrders();
}

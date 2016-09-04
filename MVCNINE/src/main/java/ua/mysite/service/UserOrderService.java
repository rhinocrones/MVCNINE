package ua.mysite.service;

import java.util.List;

import ua.mysite.entity.UserOrder;

public interface UserOrderService {

	void save(UserOrder userOrder);
	
	UserOrder findById(int id);
	
	void deleteById(int id);
	
	List<UserOrder> findAll();
	
	List<UserOrder> userOrders();
}

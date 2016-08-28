package ua.mysite.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.mysite.entity.Product;
import ua.mysite.entity.UserOrder;
import ua.mysite.entity.Usr;
import ua.mysite.repository.ProductRepository;
import ua.mysite.repository.UserOrderRepository;
import ua.mysite.repository.UsrRepository;
import ua.mysite.service.UserOrderService;

@Service
public class UserOrderServiceImpl implements UserOrderService {

	@Autowired
	private UserOrderRepository userOrderRepository;

	@Autowired
	private UsrRepository usrRepository;

	@Autowired
	private ProductRepository productRepository;

	public void save(String username, String name) {
		UserOrder userOrder = new UserOrder();

		Usr usr = usrRepository.findByUsername(username);
		userOrder.setUsr(usr);
		
		Product product = productRepository.findByName(name);
		userOrder.setProduct(product);
		
		LocalDateTime localDateTime = LocalDateTime.now();
		userOrder.setLocalDateTime(localDateTime);

		userOrderRepository.save(userOrder);
	}

	public UserOrder findById(int id) {
		return userOrderRepository.findById(id);
	}

	public void deleteById(int id) {
		userOrderRepository.deleteById(id);

	}

	public List<UserOrder> findAll() {
		return userOrderRepository.findAll();
	}

	public List<UserOrder> userOrders() {
		return userOrderRepository.userOrders();
	}

}

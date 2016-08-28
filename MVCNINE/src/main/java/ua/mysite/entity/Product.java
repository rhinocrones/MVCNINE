package ua.mysite.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    private String name;
 
    private double price;
 
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
 
    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;
 
    @ManyToOne(fetch = FetchType.LAZY)
    private Size size;
 
    @OneToMany(mappedBy="product")
    private List<UserOrder> userOrders;
   
    public List<UserOrder> getUserOrders() {
        return userOrders;
    }
 
    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    public Category getCategory() {
        return category;
    }
 
    public void setCategory(Category category) {
        this.category = category;
    }
 
    public Brand getBrand() {
        return brand;
    }
 
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
 
    public Size getSize() {
        return size;
    }
 
    public void setSize(Size size) {
        this.size = size;
    }

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", category=" + category + ", brand=" + brand + ", size="
				+ size + ", userOrders=" + userOrders + "]";
	}
    
}

package ua.mysite.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Brand {
	 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
 
    private String brand;
   
    @OneToMany(mappedBy="brand")
    private List<Product> products;
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getBrand() {
        return brand;
    }
 
    public void setBrand(String brand) {
        this.brand = brand;
    }
 
    public List<Product> getProducts() {
        return products;
    }
 
    public void setProducts(List<Product> products) {
        this.products = products;
    }
 
}
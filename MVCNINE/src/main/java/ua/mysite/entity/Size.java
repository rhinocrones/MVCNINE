package ua.mysite.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Size {
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
 
    private int size;
 
    @OneToMany(mappedBy="size")
    private List<Product> products;
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public int getSize() {
        return size;
    }
 
    public void setSize(int size) {
        this.size = size;
    }
 
    public List<Product> getProducts() {
        return products;
    }
 
    public void setProducts(List<Product> products) {
        this.products = products;
    }
 
}

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
public class Usr {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    private String username;
 
    private String email;
 
    private String password;
 
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;
   
    @OneToMany(mappedBy="usr")
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
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public Role getRole() {
        return role;
    }
 
    public void setRole(Role role) {
        this.role = role;
    }

	@Override
	public String toString() {
		return "Usr [id=" + id + ", username=" + username + ", email=" + email
				+ ", password=" + password + ", role=" + role + ", userOrders="
				+ userOrders + "]";
	}
 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author WeeJing
 */
@Entity(name="Orders")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
   
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderID;
    private Long orderTime;
    private double totalValue;
    @ManyToOne
    private UserEntity username = new UserEntity();
    @ManyToMany(cascade ={CascadeType.PERSIST})
    private Set <ProductEntity> products = new HashSet<ProductEntity>();
    

    public void create (double totalValue, UserEntity username)
    {
        this.setOrderTime();
        this.totalValue = totalValue;
        this.username = username;
    }
    
    public void setUser(UserEntity username)
    {
        this.username =username;
    }
    public void setProducts(Set<ProductEntity> products)
    {
        this.products = products;
    }
    
    public Long getorderID() {
        return orderID;
    }
    public UserEntity getUsers()
    {
        return username;
    }

    public void setId(Long orderID) {
        this.orderID = orderID;
    }
    
    public void setOrderTime()
    {
        this.orderTime = System.nanoTime();
    }
    
    
    

   
}

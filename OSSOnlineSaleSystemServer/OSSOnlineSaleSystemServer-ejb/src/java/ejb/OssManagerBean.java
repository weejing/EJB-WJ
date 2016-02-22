/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.Set;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author WeeJing
 */
@Stateful
public class OssManagerBean implements OssManagerBeanRemote {    
    
    @PersistenceContext()
    EntityManager em;
    private UserEntity userEntity;
    private OrderEntity orderEntity;
    private ProductEntity productEntity;
    private ArrayList<OrderEntity> orders;
    
    public OssManagerBean ()
    {
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override 
    public boolean checkUser(String userName)
    {
        userEntity=em.find(UserEntity.class, userName);
        if(userEntity==null)
            return false;
          
        return true;
    }
    @Override
    public void addUser(String userName, String password, int contact, String email, String address)
    {   
            userEntity = new UserEntity();
            userEntity.create(userName,password,contact,email,address);
            em.persist(userEntity);  
    }
    @Override
    public void deleteUser(String userName)
    {
        
    }
    
    @Override
    public boolean checkProduct(String brand, String model)
    {
        Query query = em.createQuery("SELECT p FROM Products p WHERE"
                + " p.brand = :brand and p.model = :model")
                .setParameter("brand", brand)
                .setParameter("model", model);
        
        productEntity = (ProductEntity)query.getSingleResult();
        
        if(productEntity == null)
            return false;
        
        return true;
    }
    
    @Override
    public void addProduct(String productType, String brand, String model, String description,
            double unitPrice, int stockQuantity, int reorderLevel)
    {
        productEntity = new ProductEntity();
        productEntity.create(productType, brand,model,description,unitPrice,stockQuantity,reorderLevel);
        em.persist(productEntity);
    }
    
    @Override 
    public String deleteProduct(String brand, String model)
    {
        String response ="Product deleted succesfully";
        boolean check = checkProduct(brand,model);
        if(check == false)
        {
            response ="Product does not exist";
            return response;
        }
        Set<OrderEntity> orders = productEntity.getOrders();
        if(orders == null)
        {
            response ="Product is associated wiht unshipped orders.";
            return response;
        }
        em.remove(productEntity);
        return response;
    }
    
    @Override
    public void updateProduct()
    {
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Remote;

/**
 *
 * @author WeeJing
 */
@Remote
public interface OssManagerBeanRemote {
    
    public void addUser(String userName, String password, int contact, String email, String address);
    public void deleteUser(String userName);
    public boolean checkUser(String userName);
    public void addProduct(String productType, String brand, String model, String description,
            double unitPrice, int stockQuantity, int reorderLevel);
    public boolean checkProduct(String brand, String model);
    public String deleteProduct(String brand, String model);
    public void updateProduct();

    
}

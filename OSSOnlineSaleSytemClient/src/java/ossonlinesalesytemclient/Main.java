/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ossonlinesalesytemclient;

import ejb.OssManagerBeanRemote;
import java.util.Scanner;
import javax.ejb.EJB;

/**
 *
 * @author WeeJing
 */
public class Main {

    @EJB
    private static OssManagerBeanRemote ossManagerBean;
    Scanner sc;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Main client = new Main();
        client.enterChoice();
       
    }
    
    public void enterChoice()
    {
        int choice;
        sc = new Scanner(System.in);
        
        while(true)
        {
            System.out.println("Please enter an option from the list below, enter '0' to exit ");
            System.out.println("'1' to add a new User \n'2' to delete an existing user");
            System.out.println("'3' to add a new product \n'4 to delete an existing product");
            System.out.print("Choice:");
            choice = sc.nextInt();
            if(choice ==0)
                break;
            if(choice == 1)
                createUser();
            if(choice == 3)
                createProduct();
            if(choice==4)
                deleteProduct();
        }
    }
    
    public void createUser()
    {
        boolean userExist = false;
        System.out.print("Enter the new user's Username:");
        String username = sc.next();
        try{
           userExist = ossManagerBean.checkUser(username);
        }catch(Exception ex){}
        if(userExist ==true)
        {
            System.out.println("User account already exists");
            return;
        }
        
        System.out.print("Enter the new user's password:");
        String password = sc.next();
        System.out.print("Enter the new user's contact: ");
        int contact = sc.nextInt();
        System.out.print("Enter the new user's email:");
        String email = sc.next();
        System.out.print("Enter the new user's address:");
        String address = sc.next();
        
        try{
            ossManagerBean.addUser(username, password, contact, email, address);
        }catch (Exception ex)
        {
            System.err.println("Caught an unxpected exception when adding user");
            ex.printStackTrace();
        }
        
    }
    
    public void createProduct()
    {
        boolean productExist =false;
        System.out.println("Enter new products's type ");
        String type = sc.next();
        System.out.println("Enter product's brand");
        String brand = sc.next();
        System.out.println("Enter product's model");
        String model = sc.next();
        
        try{
            productExist =ossManagerBean.checkProduct(brand, model);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        if(productExist == true)
        {
           System.out.println("Product exists");
           return;
        }
        
        System.out.println("Enter product's Description ");
        String fluff = sc.nextLine();
        String description = sc.nextLine();
        System.out.println("Enter product's unit price");
        double unitPrice = sc.nextDouble();
        System.out.println ("Enter products's stock count");
        int stockQuantity = sc.nextInt();
        System.out.println ("Enter product's reoder level");
        int reorder = sc.nextInt();
        
        try
        {
            ossManagerBean.addProduct(type,brand,model,description,unitPrice,stockQuantity,reorder);
        }catch(Exception ex)
        {
            System.err.println("caught an unexpected exception when adding products");
            ex.printStackTrace();        
        }     
    }
    
    public void deleteProduct()
    {
        System.out.println("Enter product's brand");
        String brand = sc.next();
        System.out.println("Enter product's model");
        String model = sc.next();
        
        try{
            String response = ossManagerBean.deleteProduct(brand, model);
            System.out.println(response);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}

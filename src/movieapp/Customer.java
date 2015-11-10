/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author user
 */
public class Customer extends Person {
    
    static CustomerDB custObj[] = new CustomerDB[10];
    
    static JSONObject custJSON[] = new JSONObject[10];
  
    static int id = 0;
    
    protected int booked[] = new int[10];
    
    private Customer cObject[] = new Customer[10];
    
    @Override
    public void login (){
        try{
        int ch;
        boolean flag;
        do{
            super.login();
           flag = CustomerDB.checkUser(username, password);
            if ( flag == false ){
                System.out.println("Either username or password is wrong.");
                System.out.println("1. Sign up 2. Login");
                ch = sc.nextInt();
                if(ch == 1)
                {
                    signup();
                    login();
                    flag = true;
                }
            
            }
        } while (flag !=true);
        displayMenu();
        }
        catch(IOException | ParseException e)
        {
            e.getMessage();
        }
        
    }
        
    
    @Override
    public void signup (){
        super.signup();
        Customer c = new Customer();
        c.id++;
        c.name = name;
        c.username = username;
        c.password = password;
        c.age = age;
        c.gender = gender;
        c.emailID = emailID;
        c.phone = phone;
        c.postalCode = postalCode;
        c.booked = new int[]{0,0,0,0,0,0,0,0,0,0};
        
        try{
            cObject[id] = c;
            custJSON[id] = custObj[id].addCustomer(c);
        } catch(IOException | ParseException e)
        {
            e.getMessage();
        }
                
    }
    
    @Override
    public void displayMenu()
    {
        int choice;
        choice = sc.nextInt();
        System.out.println("Menu:\n1. List All Movies\n2. Search by name5"
                + "\n3. Search by type\n4. Top 5 by rating\n"
                + "5. Logout\t\t\t\t6. Exit");
        switch(choice)
        {
            case 1: 
                break; // cases shall be added as and when the code is implemented
        }
    }
    
    public int[] getBookingHistory(Customer cObject)
    {
        return cObject.booked;
    }
}

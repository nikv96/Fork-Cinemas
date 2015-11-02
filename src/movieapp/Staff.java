/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;

import java.util.*;
import java.io.*;

/**
 *
 * @author user
 */
public class Staff extends Person{
    private String cineplex;
    
    Scanner sc = new Scanner(System.in);
    public void login()
    {
        int ch = 0;
        boolean flag =false;
        do{
            super.login();
            //flag = checkUsernamePassword();
            if ( flag == false ){
                System.out.println("Either username or password is wrong.");
                System.out.println("1. Sign up 2. Login");
                ch = sc.nextInt();
                break;
            }
        } while (flag ==true);
        
        if (ch==1)
            signup();
        else if (ch==2)
            login();
    }
    
    public void signup()
    {
       super.signup();
       System.out.println("Enter cineplex name: ");
       cineplex = sc.next();
       StaffDB.addStaff(name, username, password, emailID, age, phone, postalCode, sex, cineplex); // will replace variable names with
       //get and set methods
    }
    
    public void displayMenu()
    {
        int choice;
        choice = sc.nextInt();
        System.out.println("Menu:\n1. List All Movies\n2. Search by name\n3. Search by type\n4. Top 5 by rating\n5. Logout\t\t\t\t6. Exit");
        switch(choice)
        {
            case 1: break;//will complete once MovieMenu is done
        }
        
        
 
    }
    
    public void configSettings() throws IOException
    {
        String userConfig;
        System.out.println("Enter username of account to configure:");
        userConfig = sc.next();
        if(StaffDB.configUser(userConfig))
        {
            System.out.println("Menu:\n1. Change username\n2. Change password\n3. Change name\n4. Change email ID\n5. Logout\t\t\t\t6. Exit");
            //Call customerDB's function to update customer account settings
        }
        
        
    }
    
    
}

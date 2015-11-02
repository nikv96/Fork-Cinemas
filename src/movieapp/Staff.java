/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;

import java.util.*;

/**
 *
 * @author user
 */
public class Staff extends Person{
    private String cineplex;
    private int choice;
    
    Scanner sc = new Scanner(System.in);
    public boolean login()
    {
        System.out.println("Enter username");
        
        System.out.println("Enter password");
        
        return true;
    }
    
    public void signup()
    {
       System.out.println("Enter Name");
       System.out.println("Enter username");
       System.out.println("Enter password");
       System.out.println("Enter email id");
       System.out.println("Enter age");
       System.out.println("Enter phone number");
       System.out.println("Enter postal code");
       System.out.println("Enter gender");                 
    }
    
    public int displayMenu()
    {
        choice = sc.nextInt();
        System.out.println("Menu:\n1. List All Movies\n2. Search by name\n3. Search by type\n4. Top 5 by rating\n5. Logout\t\t\t\t6. Exit");
        switch(choice)
        {
            case 1: break;
        }
        
        
        
        
        return choice;
 
    }
    
    public void configSettings()
    {
        
    }
    
    
}

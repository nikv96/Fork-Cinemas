/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;


import java.io.*;
import java.util.Scanner;

/**
 *
 * @author user
 */
public abstract class Person {
    
    protected String username;
    
    protected String password;
    
    protected String name;
    
    protected int age;
    
    protected String emailID;
    
    protected long phone;
    
    protected long postalCode;
    
    protected char gender;
    
    protected char type;
    
    Scanner sc = new Scanner(System.in);
    
    public String getUserName(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getName(){
        return name;
    }
    
    public int getAge(){
        return age;
    }
    
    public String getEmailID(){
        return emailID;
    }
    
    public long getPhone(){
        return phone;
    }
    
    public void login() {
        System.out.println("Username: ");
        username = sc.next();
        System.out.println("Password: ");
        password = sc.next();
    }
    
    public void signup() {
        int flag = 0;
        System.out.println("Username: ");
        username = sc.next();
        System.out.println("Password: ");
        password = sc.next();
        System.out.println("Name: ");
        name = sc.next();
        System.out.println("Age: ");
        age = sc.nextInt();
        do {
            System.out.println("Email ID: ");
            emailID = sc.next();
            System.out.println("Confirm Email ID: ");
            if (!sc.next().equals(emailID)){
                System.out.println("Email IDs do not match! Please re-enter email ID.");
                flag = 0;
            }
            else
                flag = 1;
        }while(flag == 0);
        System.out.println("Contact Number: ");
        phone = sc.nextLong();
        System.out.println("Postal Code: ");
        postalCode = sc.nextLong();
        System.out.println("Gender(M/F): ");
        gender = sc.next().charAt(0);
    }
    
    public void checkType ()
    {
        
        System.out.println("Enter  \nC if you are a  Customer OR\nS if you are a Staff Member.");
        type = sc.next().charAt(0);
        if(type == 'C')
        {
            Customer cObj = new Customer();
            cObj.login();
        }
        else if(type == 'S')
        {
            Staff sObj = new Staff();
            sObj.login();
        }
        
    }
    
    public abstract void displayMenu() throws IOException;
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class Person {
    private String username;
    private String password;
    private String name;
    private int age;
    private String emailID;
    private long phone;
    private long postalCode;
    private char gender;
    
    Scanner sc = new Scanner(System.in);
    
    public void login() {
        System.out.println("Username: ");
        username = sc.next();
        System.out.println("Password: ");
        password = sc.next();
    }
    
    public void signup() {
        int flag = 0;
        System.out.println("Name: ");
        name = sc.next();
        System.out.println("Age: ");
        age = sc.nextInt();
        do {
            System.out.println("Email ID: ");
            emailID = sc.next();
            System.out.println("Confirm Email ID: ");
            if (sc.next() != emailID){
                System.out.println("Email IDs do not match! Please re-enter email ID.");
                flag = 0;
            }
            else
                flag = 1;
        }while( flag == 0 );
        System.out.println("Contact Number: ");
        phone = sc.nextLong();
        System.out.println("Postal Code: ");
        postalCode = sc.nextLong();
        System.out.println("Gender(M/F): ");
        gender = sc.next().charAt(0);
        System.out.println("Username: ");
        username = sc.next();
        System.out.println("Password: ");
        password = sc.next();
    }
    
    public void checkType (){
    }
    
    public void displayMenu() {
    }
    
}

package movieapp;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Team Fork
 */
public class Person {
    
    protected String username;
    
    protected String password;
    
    protected String name;
    
    protected int age;
    
    protected String emailID;
    
    protected long phone;
    
    protected long postalCode;
    
    protected String gender;
    
    protected char type;
    
    Scanner sc = new Scanner(System.in);
    Console c = System.console();
    
    //getter and setters
    
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
    
    /*
    * Super login
    */
    
    public void login() {
        System.out.println("Username: ");
        username = sc.next();
        System.out.println("Password: ");
        password = sc.next();
    }
    
    /*
    * Super sign up
    */
    public void signup() {
        int flag = 0;
        System.out.println("Username: ");
        username = sc.next();
        System.out.println("Password: ");
        password = sc.next();
        System.out.println("Last Name: ");
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
        gender = sc.next();
    }
    
    /*
    * Super check type
    */
    
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
        
        System.out.println("End of checkType\n");
        
    }
    
    /*
    * Abstract displayMenu
    */
    
    public void displayMenu() throws IOException
    {
        
    }
    
}

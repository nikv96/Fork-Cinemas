package movieapp;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Team Fork
 */
public class Person {
    
    private String username;
    
    private String password;
    
    private String name;
    
    private int age;
    
    private String emailID;
    
    private long phone;
    
    private long postalCode;
    
    private String gender;
    
    private char type;
    
    Scanner sc = new Scanner(System.in);
    
    /*
    * Constructors
    */
    
    public Person(){
    }
    
    public Person(String name,int age,String emailId,long phno,long postal,
            String gender,String user,String pass){
        this.name = name;
        this.age = age;
        this.emailID = emailId;
        this.phone = phno;
        this.postalCode = postal;
        this.gender = gender;
        this.username =user;
        this.password = pass;
    }
    
    /*
    *Getter and Setter methods
    */
    
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
    
    public long getPostal(){
        return postalCode;
    }
    
    public String getGender(){
        return gender;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPassword(String pass){
        this.password = pass;
    }
    
    public void setEmailID(String email){
        this.emailID = email;
    }
    
    public void setGender(String gender){
        this.gender = gender;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
    /*
    * super login method
    */
    
    public void login() {
        System.out.print("\nUsername: ");
        username = sc.next();
        System.out.print("\nPassword: ");
        password = sc.next();
    }
    
    /*
    * super signup method
    */
    
    public void signup() {
        int flag = 0;
        System.out.println("Signup\n");
        System.out.print("\nUsername: ");
        username = sc.next();
        System.out.print("\nPassword: ");
        password = sc.next();
        System.out.print("\nName: ");
        name = sc.nextLine() + sc.nextLine();
        System.out.print("\nAge: ");
        age = sc.nextInt();
        do {
            System.out.print("\nEmail ID: ");
            emailID = sc.next();
            System.out.print("\nConfirm Email ID: ");
            if (!sc.next().equals(emailID)){
                System.out.print("\nEmail IDs do not match! Please re-enter email ID.");
                flag = 0;
            }
            else
                flag = 1;
        }while(flag == 0);
        System.out.print("\nContact Number: ");
        phone = sc.nextLong();
        System.out.println("\nPostal Code: ");
        postalCode = sc.nextLong();
        System.out.println("\nGender(M/F): ");
        gender = sc.next();
    }
    
    /*
    * This method checks the type of the user
    */
    
    public void checkType (){
        do
        {
        System.out.print("\nEnter 'C' if you are a  Customer OR 'S' if you are a Staff Member: ");
        type = sc.next().charAt(0);
        if(type == 'C'){
            Customer cObj = new Customer();
            System.out.print("\n1. Sign up 2. Login\nEnter you choice: ");
            if(sc.nextInt() == 2)
                cObj.login();
            else
                cObj.signup();
        }
        else if(type == 'S'){
            Staff sObj = new Staff();
            System.out.print("\n1. Sign up 2. Login\nEnter you choice: ");
            if(sc.nextInt() == 2)
                sObj.login();
            else
                sObj.signup();
        }
        }while(true);
    }
    
    
    public void displayMenu() throws IOException{
        
    }
    
}

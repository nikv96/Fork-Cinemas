package movieapp;

import java.util.*;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Team Fork
 */
public class Staff extends Person{
    protected String cineplex;
    
    static StaffDB staffObj[] = new StaffDB[10];
    
    JSONObject staffJSON[] = new JSONObject[10];
  
    int id;
    
    Scanner sc = new Scanner(System.in);
    
    
    @Override
    public void login(){
        try{
        int ch;
        boolean flag;
        do{
            super.login();
            flag = StaffDB.readStaff(username, password);
            if (flag == false){
                System.out.println("Either username or password is wrong.");
                System.out.println("1. Sign up 2. Login");
                ch = sc.nextInt();
                if(ch == 1){
                    signup();
                    login();
                    flag = true;
                }
            
            }
        } while (flag !=true);
        displayMenu();
        }
        catch(IOException | ParseException e){
            e.getMessage();
        }
    }
    
    @Override
    public void signup(){
       super.signup();
       System.out.println("Enter cineplex name: ");
       cineplex = sc.next();
       Staff s = new Staff();
        s.id++;
        s.name = name;
        s.username = username;
        s.password = password;
        s.age = age;
        s.gender = gender;
        s.emailID = emailID;
        s.phone = phone;
        s.postalCode = postalCode;
        s.cineplex = cineplex;
       
       try {
           staffJSON[id] = staffObj[id].addStaff(s);
       }
       catch(IOException | ParseException e){
           e.getMessage();
       }
    }
    
    @Override
    public void displayMenu(){
        String userConfig;
        int ch; char c; boolean check;
        System.out.println("Enter M for Movies Update OR U for User Account Update:");
        c = sc.next().charAt(0);
        if(c == 'U'){
            userEdits();
        }
        else if(c == 'M'){
           movieEdits();
        }
  
 
    }
    
    public void userEdits(){
        //will be updated as the get & set methods are completed
        String userConfig;
        int ch; char c; boolean check;
        try{
        System.out.println("Enter username of account to configure:");
        userConfig = sc.next();
        System.out.println("Enter type of account to configure(S for Staff OR C for Customer:");
        c = sc.next().charAt(0);
        check = c == 'S'? StaffDB.configUser(userConfig):CustomerDB.configUser(userConfig);
        
        if(check){
            System.out.println("Menu:\n1. Change username\n2. Change password\n3. Change name\n4. Change email ID\n5. Logout\t\t\t\t6. Exit");
           ch = sc.nextInt();
           switch(ch){
               case 1:
                   break;//cases shall be added as the code is completed
                   
           }
        }
        }
        catch(IOException | ParseException e){
            e.getMessage();
        }
    }
    
    public void movieEdits(){
        //to be completed
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;

/**
 *
 * @author user
 */
public class Customer extends Person {
    
    public void login (){
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
    
    public void signup (){
        super.signup();
        //add to dat file
    }
    
    public int displayMenu()
    {
        int choice;
        choice = sc.nextInt();
        System.out.println("Menu:\n1. List All Movies\n2. Search by name\n3. Search by type\n4. Top 5 by rating\n5. Logout\t\t\t\t6. Exit");
        switch(choice)
        {
            case 1: break;
        }
        return choice;
    }
}

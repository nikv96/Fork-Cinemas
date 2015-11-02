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
public class MovieMenu {
    private int menuChoice;
    
    public void setMenuChoice(int choice)
    {
        menuChoice = choice;
    }
    
    public void movieListing()
    {
        switch(menuChoice)
        {
            case 1: //List all
                break;
            case 2: //Search by name
                break;
            case 3: //Search by something else
                break;
            case 4: //Top 5
                break;
            case 5: //View Booking History
                break;
            case 6: //Back
                break;
            case 7: //Logout
                break;
            case 8: //Exit
                break;
        }
    }
    
    public void calcPrice(MovieDB movies[])
    {
        //will complete later
    }
    
}

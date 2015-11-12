package movieapp;

import static java.lang.System.exit;
import java.util.Scanner;

/**
 *
 * @author Team Fork
 */
public class MovieMenu {
    
    private int menuCChoice;
    
    MovieList movieList = new MovieList();
    
    Scanner sc = new Scanner (System.in);
    
    /*
    * This method displays a menu for the customer
    */
    public void customerMenu(MovieDB[] movies, Customer customer){
        do {
            System.out.println("1. List All Movies\n"
                    + "2. Search Movies by Name\n"
                    + "3. Search Movies by Type\n"
                    + "4. List Top 5 Movies\n"
                    + "5. View Booking History\nEnter your choice");
            menuCChoice = sc.nextInt();
            switch(menuCChoice){
                case 1: //List alls
                    movieList.listAllMovies(movies, customer);
                    break;
                case 2: //Search by name
                    System.out.println("Enter a movie name: ");
                    movieList.listSearchByName(movies, sc.next(), customer);
                    break;
                case 3: //Search by something else
                    System.out.println("Enter a movie type: ");
                    movieList.listSearchByType(movies, sc.next(), customer);
                    break;
                case 4: //Top 5
                    movieList.listTop5(movies, customer);
                    break;
                case 5: //View Booking History
                    movieList.viewBookingHistory(movies, customer);
                    break;
                case 6: //Back
                    break;
                case 7: //Logout
                    break;
                case 8: //Exit
                    exit(0);
                    break;
            }
        } while (menuCChoice<6);
    }
    
    /*
    * This method calculates the price for the tickets
    */
    public void calcPrice(MovieDB movies[]){
        //will complete later
    }
    
}

package movieapp;

import java.io.IOException;
import static java.lang.System.exit;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

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
            System.out.print("\n1. List All Movies\n"
                    + "2. Search Movies by Name\n"
                    + "3. Search Movies by Type\n"
                    + "4. List Top 5 Movies\n"
                    + "5. View Booking History\n[6] Logout\t\t\t\t[7] Exit\nEnter your choice: ");
            menuCChoice = sc.nextInt();
            switch(menuCChoice){
                case 1: {
                try {
                    //List all
                    movieList.listAllMovies(movies, customer);
                } catch (IOException ex) {
                    Logger.getLogger(MovieMenu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(MovieMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                case 2: //Search by name
                    System.out.println("Enter a movie name: ");
                    movieList.listSearchByName(movies, sc.next(), customer);
                    break;
                case 3: //Search by something else
                    System.out.println("Enter a movie type: ");
                    movieList.listSearchByType(movies, sc.next(), customer);
                    break;
                case 4: {
                try {
                    //Top 5
                    movieList.listTop5(movies, customer);
                } catch (IOException ex) {
                    Logger.getLogger(MovieMenu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(MovieMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                case 5: //View Booking History
                    movieList.viewBookingHistory(movies, customer);
                    break;
                case 6: //Back
                    break;
                case 7: //Exit
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

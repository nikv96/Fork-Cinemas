package movieapp;

import java.io.Console;
import java.util.Scanner;

/**
 *
 * @author Team Fork
 */
public class MovieDetails {
    
    Scanner sc = new Scanner(System.in);
    Console c = System.console();
    
    CustomerDB cust = new CustomerDB();
    
    /*
    * This method gets the movie details
    */
    
    public void getMovieDetails(MovieDB movie, Customer cObj)
    {
        int ch = 0; 
        char choice;
        System.out.println(movie.getMovieName());
        System.out.println("Movie Type: " + movie.getMovieType());
        System.out.println("Show Timings: ");
        for (String showTiming : movie.getShowTimings()) {
            System.out.print(showTiming + "\t");
        }
            
        System.out.println("Deluxe Price: " + movie.calcPrice("Deluxe",cObj));
        System.out.println("Platinum Price: " + movie.calcPrice("Platinum", cObj));
        float sum = 0;
        for (int i=0;i<movie.getRating().length;i++) {
            sum += movie.getRating()[i];
        }
        System.out.println("Ratings: " + sum/movie.getRating().length);
        do
        {
        System.out.println("1. See Reviews 2. Check Seats 3. Back");
        ch = sc.nextInt();
        switch (ch){
            case 1:
                System.out.println("Reviews");
                for (String review : movie.getReviews()) {
                        System.out.println(review);
                }
                System.out.println("\n\nAdd a review? Y/N");
                choice = sc.next().charAt(0);
                if (choice == 'Y')
                    addReview(movie,cObj);
                break;
            case 2:
                System.out.println("Which slot do you want to go for?");
                int id;
                id = sc.nextInt();
                checkSeatAvailability(movie, id);
                System.out.println("\n\nBook a seat? Y/N");
                choice = sc.next().charAt(0);
                if (choice == 'Y'){
                    System.out.println("Enter seat number(Row -- Column): ");
                    booking(movie, sc.nextInt(), sc.nextInt(), id, cObj);
                }
                break;
            case 3:
            default:
                break;
        }
        }while ( ch!=3 );
    }
    
    /*
    * This method books the seat
    */
    
    public void booking(MovieDB movie, int row, int column, int id, Customer cObject)
    {
        System.out.println("Please enter your 16 digit credit card number: ");
        sc.nextLine();
        System.out.println("Please enter your 3 digit CVV number: ");
        sc.nextLine();
        movie.setSeat(id, row, column);
        cust.setBooking(cObject, movie.getId());
        System.out.println("Payment Successful. Your ticket number is " + movie.getId() + (int)Math.random()*1000000 + "\nThank You.");
    }
    
    /*
    * This method adds a new review
    */
    
    public void addReview(MovieDB movie, Customer cObj){
        System.out.println("Your review here");
        String review = sc.nextLine();
        movie.setReview("@" + cObj.getUserName() + "," + review);
    }
    
    /*
    * This methods displays seats availability
    */
    public void checkSeatAvailability(MovieDB movie, int id){
        int i;
        boolean[][][] allSeats = movie.getSeats();
        boolean[][] seats = allSeats[id];
        for (boolean[] seat : seats) {
            System.out.print("\n");
            for (i =0;i < seat.length;i++){
                if(seat[i]==true){
                    System.out.print("1\t");
                } else {
                    System.out.print("x\t");
                }
            }
        }
    }
    
    
}

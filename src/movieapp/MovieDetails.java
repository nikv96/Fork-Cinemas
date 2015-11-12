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
    
    public void getMovieDetails(MovieDB movie, Customer cObj)
    {
        int ch = 0; double finalPrice = 0.0;
        char choice;
        System.out.println(movie.getMovieName());
        System.out.println("Movie Type: " + movie.getMovieType());
        System.out.println("Show Timings: ");
        for (String showTiming : movie.getShowTimings()) {
            System.out.print(showTiming + "\t");
        }
        finalPrice = movie.getPrice();
        
        if(cObj.getAge() >= 60)
        {
            finalPrice = finalPrice * 0.94;
        }
        if(movie.getMovieType().equals("3D"))
        {
            finalPrice = finalPrice * 1.05;
        }
        
            
        System.out.println("Deluxe Price: " + finalPrice);
        System.out.println("Platinum Price: " + finalPrice*1.10);
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
                    addReview(movie);
                break;
            case 2:
                checkSeatAvailability(movie);
                System.out.println("\n\nBook a seat? Y/N");
                choice = sc.next().charAt(0);
                if (choice == 'Y'){
                    System.out.println("Enter seat number(Row -- Column): ");
                    booking(movie, sc.nextInt(), sc.nextInt(), cObj);
                }
                break;
            case 3:
            default:
                break;
        }
        }while ( ch!=3 );
    }
    
    public void booking(MovieDB movie, int row, int column, Customer cObject)
    {
        System.out.println("Please enter your 16 digit credit card number: ");
        c.readPassword();
        System.out.println("Please enter your 3 digit CVV number: ");
        c.readPassword();
        movie.setSeat(row, column);
        cust.setBooking(cObject, movie.getId());
        System.out.println("Payment Successful. Your ticket number is " + movie.getId() + (int)Math.random()*1000000 + "\nThank You.");
    }
    
    public void addReview(MovieDB movie){
        System.out.println("Your review here");
        String review = sc.next();
        //write code to get customer's username here
        movie.setReview(review);
    }
    
    public void checkSeatAvailability(MovieDB movie){
        int i;
        boolean[][] seats = movie.getSeats();
        for (boolean[] seat : seats) {
            System.out.print("\n");
            for (i =0;i < seat.length;i++){
                if(seat[i]==true){
                    System.out.print("1 ");
                } else {
                    System.out.print("x");
                }
            }
        }
    }
    
    
}

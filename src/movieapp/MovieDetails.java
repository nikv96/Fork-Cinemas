package movieapp;

import java.io.Console;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Team Fork
 */
public class MovieDetails {
    
    Scanner sc = new Scanner(System.in);
    Console c = System.console();
    
    CustomerDB cust = new CustomerDB();
    
    public void getMovieDetails(MovieDB movie, Customer cObj) throws IOException, ParseException {
        int ch = 0;
        char choice; String slot = "";
        System.out.println(movie.getMovieName());
        System.out.println("Movie Type: " + movie.getMovieType());
        System.out.println("Director: " + movie.getDirector());
        System.out.println("Show Status: " + movie.getShowStatus());
        System.out.println("SYNOPSIS: " + movie.getSynopsis());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Show Timings: ");
        for (String showTiming : movie.getShowTimings()) {
            System.out.print(showTiming.substring(1) + "\t");
        System.out.println();
        }
       
        
        float sum = 0;
        for (int i=0;i<movie.getRating().length;i++)
        {
            sum += movie.getRating()[i];
        }
        if(movie.getRating().length >1)
            System.out.println("\nRatings: " + sum/movie.getRating().length);
        else
            System.out.println("\nRatings: NA");
        do
        {
        System.out.print("1. See Reviews 2. Check Seats and Book 3.Back"
                + "\nEnter your choice: ");
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
                
                System.out.println("\n\nAdd a rating? Y/N");
                choice = sc.next().charAt(0);
                if (choice == 'Y')
                    addRating(movie,cObj);
                break;
            case 2:
                System.out.println("Which slot do you want to go for?");
                for(int i = 0; i < movie.getShowTimings().length;i++)
                {
                    System.out.println(movie.getShowTimings()[i].charAt(0) + ". " + movie.getShowTimings()[i].substring(1));
                    System.out.printf("Deluxe Suite Price: %.2f \n", movie.calcPrice("Deluxe",cObj,movie.getShowTimings()[i]));
                    System.out.printf("Platinum Suite Price: %.2f \n", movie.calcPrice("Platinum",cObj,movie.getShowTimings()[i]));
                    System.out.println();                    
                }
                System.out.println("*All prices are in Singapore Dollar (SGD) and inclusive of Good and Services Tax(GST).");
                int id2;
                id2 = sc.nextInt();
                checkSeatAvailability(movie, id2);
                System.out.println("\n\nBook a seat? Y/N");
                choice = sc.next().charAt(0);
                if (choice == 'Y'){
                    System.out.println("1. Deluxe Suite\n2. Platinum Suite:\nEnter your choice");
                    
                    if(cObj.getAge() >=60)
                    {
                        System.out.println("Congratulations! You are eligible for our senior citizen discount!");
                    }
                    movie.calcPrice(sc.nextInt() == 1?"Deluxe":"Platinum", cObj,movie.getShowTimings()[id2]);
                    System.out.println("Enter seat row number(1-40) and seat column number(1-10):");
                    booking(movie, sc.nextInt()-1, sc.nextInt()-1, id2, cObj);
                }
                break;
            case 3:
            default:
                break;
        }
        }while ( ch!=3 );
    }
    
    public void booking(MovieDB movie, int row, int column, int id, Customer cObject) throws IOException, ParseException
    {
        String name, emailID; long phone;
        System.out.print("Please confirm the following details for identification purposes: ");
        System.out.print("\nName: ");
        name = sc.nextLine() + sc.nextLine();
        System.out.print("\nEmail ID: ");
            emailID = sc.next();
        System.out.print("\nContact Number: ");
        phone = sc.nextLong();
        System.out.println("Please enter your 16 digit credit card number: ");
        sc.next();
        System.out.println("Please enter your 3 digit CVV number: ");
        sc.next();
        movie.setSeat(id,row,column);
        cust.setBooking(cObject, movie.getId(),id);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        System.out.println("Payment Successful. Your Transaction ID is " + movie.getId() + dateFormat.format(date) +".\nThank You for using Fork Cinemas.");
    }
    
    public void addReview(MovieDB movie, Customer cObj){
        System.out.println("Your review here:");
        String review = sc.nextLine() + sc.nextLine();
        //write code to get customer's username here
        movie.setReview("@" + cObj.getUserName() + "," + review);
    }
    public void addRating(MovieDB movie, Customer cObj){
        System.out.println("Your rating here:");
        double r = sc.nextDouble();
        //write code to get customer's username here
        movie.setRating(r);
    }
    
    public void checkSeatAvailability(MovieDB movie, int id){
        int i;
        boolean[][][] allSeats = movie.getSeats();
        boolean[][] seats = allSeats[id];
        for (boolean[] seat: seats) {
            System.out.print("\n");
            for (i =0;i < seat.length;i++){
                if(seat[i]==true){
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
        }
    }
    
    
}

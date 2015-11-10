/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class MovieDetails {
    
    Scanner sc = new Scanner(System.in);
    
    public void getMovieDetails(MovieDB movie){
        int ch = 0;
        char choice;
        System.out.println(movie.getMovieName());
        System.out.println("Movie Type: " + movie.getMovieType());
        System.out.println("Show Timings: ");
        for (String showTiming : movie.getShowTimings()) {
            System.out.print(showTiming + "\t");
        }
        System.out.println("Price: " + movie.getPrice());
        float sum = 0;
        for (int i=0;i<movie.getRating().length;i++) {
            sum += movie.getRating()[i];
        }
        System.out.println("Ratings: " + sum/movie.getRating().length);
        do{
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
                    addReview();
                break;
            case 2:
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
                System.out.println("\n\nBook a seat? Y/N");
                choice = sc.next().charAt(0);
                if (choice == 'Y')
                    booking();
                break;
            case 3:
            default:
                break;
        }
        }while ( ch!=3 );
        return;
    }
    
    public void booking(){
        
    }
    
    public void addReview(){
        //will be added later
    }
    
    public void checkSeatAvailability(){
        //will be added later
    }
    
    
}

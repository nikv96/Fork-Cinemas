package movieapp;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Team Fork
 */
public class MovieList {
    protected MovieDB movies[];
    
    protected Customer cust;
    
    Scanner sc = new Scanner(System.in);
    
    protected MovieDetails movieChoice = new MovieDetails();
    
    /*
    * This method lists all the the movies in a table
    */
    public void listAllMovies(MovieDB movieList[], Customer cObj) throws IOException, ParseException{
        int count = 0;
        movies = movieList;
       
        int ch;
        int id=1;
        System.out.format("%4s%16s%16s\n","#","Movie Name","Movie Type");
        for (MovieDB movie : movies) {
            if(!movie.getShowStatus().equalsIgnoreCase("End of Showing"))
                System.out.format("%4d%16s%16s \n", id++, movie.getMovieName(), movie.getMovieType());
        }
        System.out.print("Choose your movie:");
        ch = sc.nextInt();
        movieChoice.getMovieDetails(movies[ch-1], cObj);
    }
    
    /*
    * This method lists all the the movies with a particular name in a table
    */
    public void listSearchByName(MovieDB movieList[], String searchName, Customer cObj){
        int count = 1;int ch;
        for (MovieDB movie : movieList) {
            if (movie.getMovieName().toLowerCase().contains(searchName.toLowerCase()) && !movie.getShowStatus().equals("End of Showing")) {
                movies[count - 1] = movie; 
                System.out.format("%16s%16s%16s%16s", count++, movie.getMovieName(), movie.getMovieType(), Arrays.toString(movie.getShowTimings()));
            }
        }
        System.out.println("Choose your movie:");
        ch = sc.nextInt();
        
        try {
            movieChoice.getMovieDetails(movies[ch-1], cObj);
        } catch (IOException ex) {
            Logger.getLogger(MovieList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MovieList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    * This method lists all Movies by type in a table
    */
    public void listSearchByType(MovieDB movieList[], String searchType, Customer cObj){
        
        int count = 1;int ch;
        for (MovieDB movie : movieList) {
            if (movie.getMovieType().toLowerCase().contains(searchType.toLowerCase()) && !movie.getShowStatus().equals("End of Showing")) {
                movies[count - 1] = movie;
                System.out.format("%16s%16s%16s%16s", count++, 
                        movie.getMovieName(), movie.getMovieType(), 
                        Arrays.toString(movie.getShowTimings()));
            }
        }
        System.out.println("Choose your movie:");
        ch = sc.nextInt();
        
        try {
            movieChoice.getMovieDetails(movies[ch-1], cObj);
        } catch (IOException ex) {
            Logger.getLogger(MovieList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MovieList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    * This method lists the top 5 movies in a table
    */
    public void listTop5(MovieDB movieList[], Customer cObj) throws IOException, ParseException{
        int count = 1;int ch;
        movies = sortMovieListByReviews(movieList);
        for(MovieDB movie : movies){
            if(!movie.getShowStatus().equals("End of Showing"))
                System.out.format("%16s%16s%16s%16s", count++, 
                        movie.getMovieName(), movie.getMovieType(), 
                        Arrays.toString(movie.getShowTimings()));
            if( count == 6 )
                break;
        }
        System.out.println("Choose your movie:");
        ch = sc.nextInt();
        
        movieChoice.getMovieDetails(movies[ch-1], cObj);
    }
    
    /*
    * This method lists the customer's booking history in a table 
    */
    public void viewBookingHistory(MovieDB movieList[], Customer cObject){
        int count = 1;
        int bookHistory[];
        bookHistory = cObject.getBookingHistory(cObject);
        for (int i = 0; i < movieList.length;i++) {
            if (bookHistory[i] > 0) {
                movies[count - 1] = movieList[i];
                for(int j = 0; j < movieList[i].getShowTimings().length;j++)
                {
                    if(bookHistory[i]-1 == j)
                System.out.println( ""+ count++ +"\t" +  movieList[i].getMovieName() + "\t" +
                        movieList[i].getMovieType() + "\t" + movieList[i].getShowTimings()[j].substring(1));
                }
            }
        }
    }
    
    /*
    *This method sorts the movie list according to reviews
    */
    public MovieDB[] sortMovieListByReviews(MovieDB[] movies){
        MovieDB temp;
        //Bubble Sort Algorithm
        for(int i =0 ; i<movies.length-1;i++){
            for(int j=0; j<movies.length - i - 1; j++){
                if ( movies[j].getRatingAverage() > movies[j+1].getRatingAverage() ){
                    temp = movies[j+1];
                    movies[j+1] =movies[j];
                    movies[j] = temp;
                }
            }
        }
        return movies;
    }
}

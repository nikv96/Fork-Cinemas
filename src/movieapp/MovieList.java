package movieapp;

import java.util.*;

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
    * This method lists all the the movies in a tablee
    */
    public void listAllMovies(MovieDB movieList[], Customer cObj){
        movies = movieList;
       
        int ch;
         
        for (MovieDB movie : movies) {
            System.out.format("%16s%16s", movie.getMovieName(), movie.getMovieType());
        }
        System.out.println("Choose your movie:");
        ch = sc.nextInt();
        movieChoice.getMovieDetails(movies[ch-1], cObj);
    }
    
    /*
    * This method lists all the the movies with a particular name in a table
    */
    public void listSearchByName(MovieDB movieList[], String searchName, Customer cObj){
        int count = 1;int ch;
        for (MovieDB movie : movieList) {
            if (movie.getMovieName().toLowerCase().contains(searchName.toLowerCase())) {
                movies[count - 1] = movie; 
                System.out.format("%16s%16s%16s%16s%16s", count++, movie.getMovieName(), movie.getMovieType(), Arrays.toString(movie.getShowTimings()), movie.getPrice());
            }
        }
        System.out.println("Choose your movie:");
        ch = sc.nextInt();
        
        movieChoice.getMovieDetails(movies[ch-1], cObj);
    }
    
    /*
    * This method lists all Movies by type in a table
    */
    public void listSearchByType(MovieDB movieList[], String searchType, Customer cObj){
        
        int count = 1;int ch;
        for (MovieDB movie : movieList) {
            if (movie.getMovieType().toLowerCase().contains(searchType.toLowerCase())) {
                movies[count - 1] = movie;
                System.out.format("%16s%16s%16s%16s%16s", count++, 
                        movie.getMovieName(), movie.getMovieType(), 
                        Arrays.toString(movie.getShowTimings()), movie.getPrice());
            }
        }
        System.out.println("Choose your movie:");
        ch = sc.nextInt();
        
        movieChoice.getMovieDetails(movies[ch-1], cObj);
    }
    
    /*
    * This method lists the top 5 movies in a table
    */
    public void listTop5(MovieDB movieList[], Customer cObj){
        int count = 1;int ch;
        movies = sortMovieListByReviews(movieList);
        for(MovieDB movie : movies){
            System.out.format("%16s%16s%16s%16s%16s", count++, 
                        movie.getMovieName(), movie.getMovieType(), 
                        Arrays.toString(movie.getShowTimings()), movie.getPrice());
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
        boolean bookHistory[];
        bookHistory = cObject.getBookingHistory(cObject);
        for (int i = 0; i < movieList.length;i++) {
            if (bookHistory[i] == true) {
                movies[count - 1] = movieList[i];
                System.out.format("%16s%16s%16s%16s%16s", count++, movieList[i].getMovieName(), movieList[i].getMovieType(), Arrays.toString(movieList[i].getShowTimings()), movieList[i].getPrice());
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

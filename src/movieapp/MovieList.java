/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;

import java.util.*;

/**
 *
 * @author user
 */
public class MovieList {
    protected MovieDB movies[];
    
    protected CustomerDB cust[];
    
    public void listAllMovies(MovieDB movieList[])
    {
        movies = movieList;
         
        for (MovieDB movie : movies) {
            System.out.format("%16s%16s%16s%16s", movie.getMovieName(), movie.getMovieType(), Arrays.toString(movie.getShowTimings()), movie.getPrice());
        }
    }
    
    public void listSearchByName(MovieDB movieList[], String searchName)
    {
        int count = 1;
        for (MovieDB movie : movieList) {
            if (movie.getMovieName().toLowerCase().contains(searchName.toLowerCase())) {
                movies[count - 1] = movie; 
                System.out.format("%16s%16s%16s%16s%16s", count++, movie.getMovieName(), movie.getMovieType(), Arrays.toString(movie.getShowTimings()), movie.getPrice());
            }
        }
    }
    
    public void listSearchByType(MovieDB movieList[], String searchType)
    {
        
        int count = 1;
        for (MovieDB movie : movieList) {
            if (movie.getMovieName().toLowerCase().contains(searchType.toLowerCase())) {
                movies[count - 1] = movie;
                System.out.format("%16s%16s%16s%16s%16s", count++, movie.getMovieName(), movie.getMovieType(), Arrays.toString(movie.getShowTimings()), movie.getPrice());
            }
        }
    }
    
    public void listTop5()
    {
        //will do later
    }
    
    public void viewBookingHistory(MovieDB movieList[], Customer cObject)
    {
        int count = 1;
        int bookHistory[] = new int[10];
        bookHistory = cObject.getBookingHistory(cObject);
        for (int i = 0; i < movieList.length;i++) {
            if (bookHistory[i] == 1) {
                movies[count - 1] = movieList[i];
                System.out.format("%16s%16s%16s%16s%16s", count++, movieList[i].getMovieName(), movieList[i].getMovieType(), Arrays.toString(movieList[i].getShowTimings()), movieList[i].getPrice());
            }
        }
    }
    
    
}

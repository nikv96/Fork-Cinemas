package movieapp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.parser.ParseException;

/**
 *
 * @author Team Fork
 */
public class MovieApp {

    public static void main(String[] args)
    {
        
        //call movie create multiple times
        //String movieName, String movieType, String[] showTimings,
           // double price, String[] reviews, double[] rating
       
      
        String[] showTimings = {"Wednesday 3:00 pm", "Wednesday 5:30 pm"};
        String[] reviews = {"@nikv96", "Huge Fan"};
        double[] rating = {4.5, 2.5, 3.5};
        MovieDB movieInit = new MovieDB("Madagascar", "Comedy", showTimings, 12.0, reviews, rating);
        
        System.out.println("Movie name is " + movieInit.getMovieName() + "\n\n");
        
        try
        {
            movieInit.createMovie(movieInit);
            
            Person pObj = new Person();
            pObj.checkType();
            
            System.out.println("End of movieapp");
        
        }
        catch(IOException | ParseException e)
        {
            e.getMessage();
        }
        
       
       
       
        
        
    }
    
}

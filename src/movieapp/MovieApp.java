package movieapp;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.parser.ParseException;

/**
 *
 * @author Team Fork
 */
public class MovieApp {

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException
    {
        System.out.println("********************************FORK CINEMAS**********************************");
        String[] showTimings = {"Wednesday 3:00 pm", "Wednesday 5:30 pm"};
        String[] reviews = {"@nikv96, Huge Fan"};
        double[] rating = {4.5, 2.5, 3.5};
        MovieDB movieInit = new MovieDB("Madagascar", "Comedy", showTimings, 12.0, reviews, rating);
        
        
        String[] showTimings1 = {"Wednesday 3:00 pm", "Wednesday 5:30 pm"};
        String[] reviews1 = {"@nikv96", "Huge Fan"};
        double[] rating1 = {4.5, 2.5, 3.5};
        MovieDB movieInit1 = new MovieDB("Idiot", "Thriller", showTimings1, 12.0, reviews1, rating1);
        
        
        movieInit.createMovie(movieInit);
        movieInit.createMovie(movieInit1);

        MovieDB.setTotalId();

        Person pObj = new Person();
        pObj.checkType();

        System.out.println("Thank you and hope to see you again soon! =D");
    }
    
}

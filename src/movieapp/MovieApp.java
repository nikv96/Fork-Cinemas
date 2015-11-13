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
        String[] showTimings = {"0Wednesday,3:00pm - Golden Village", "1Wednesday,5:30pm - Cathay Cineplex", "2Sunday,2:00pm - Filmgarde Cinplex"};
        String[] reviews = {"@nikv96, Huge Fan"};
        double[] rating = {4.5, 4.5, 4.5};
        MovieDB movieInit = new MovieDB("Madagascar", "3D", showTimings, reviews, rating, "Director", "Synopsis", "Showing", "Animation");
        
        
        String[] showTimings1 = {"0Tuesday,3:00pm", "1Thursday,5:30pm"};
        String[] reviews1 = {"@bobby", "Love it!"};
        double[] rating1 = {4.5, 2.5, 3.5};
        MovieDB movieInit1 = new MovieDB("Spectre", "Blockbuster", showTimings1, reviews1, rating1, "Director", "Synopsis", "Showing", "James Bond");
        
        
        movieInit.createMovie(movieInit);
        movieInit.createMovie(movieInit1);

        MovieDB.setTotalId();

        Person pObj = new Person();
        pObj.checkType();

        System.out.println("Thank you and hope to see you again soon! =D");
    }
    
}

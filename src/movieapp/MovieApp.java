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
        MovieDB movieInit = new MovieDB("Madagascar", "3D", showTimings, reviews, rating, "Erin Darnell", "The film tells the story of four "
                + "Central Park Zoo animals who have spent their lives in blissful captivity and are unexpectedly shipped back to Africa, "
                + "getting shipwrecked on the island of Madagascar.", "End Of Showing", "Ben Stiller, Chris Rock");
        
        
        String[] showTimings1 = {"0Tuesday,3:00pm", "1Thursday,5:30pm"};
        String[] reviews1 = {"@bobby", "Love it!"};
        double[] rating1 = {4.5, 2.5, 3.5};
        MovieDB movieInit1 = new MovieDB("Skyfall", "Blockbuster", showTimings1, reviews1, rating1, "Sam Mendes", "The story centres on Bond investigating an "
                + "attack on MI6", "Now Showing", "Daniel Craig, Judi Dench");
        
        String[] showTimings2 = {"0Monday,3:00pm", "1Thursday,4:30pm"};
        String[] reviews2 = {"@nikv96", "Love it!"};
        double[] rating2 = {4.5, 2.5, 3.5};
        MovieDB movieInit2 = new MovieDB("The Dark Knight", "Blockbuster", showTimings2, reviews2, rating2, "Christopher Nolan", "A billionaire "
                + "socialite who dedicates himself to protecting Gotham City from its criminal underworld as a bat-masked vigilante hailed as "
                + "its \"Dark Knight\" at night", "Now Showing", "Christian Bale, Michael Caine");
        
        
        String[] showTimings3 = {"0Sunday,3:00pm", "1Tuesday,5:30pm"};
        String[] reviews3 = {"@bobby", "Love it!"};
        double[] rating3 = {4.5, 2.5, 3.5};
        MovieDB movieInit3 = new MovieDB("Finding Nemo", "Blockbuster", showTimings3, reviews3, rating3, "Alec McMurtry", "The story centres around a lost goldfish", "Now Showing", "Francesc Fabregas");
        
        
        String[] showTimings4 = {"0Tuesday,3:00pm", "1Saturday,5:30pm"};
        String[] reviews4 = {"@bobby", "Love it!"};
        double[] rating4 = {4.5, 2.5, 3.5};
        MovieDB movieInit4 = new MovieDB("Titanic", "3D", showTimings4, reviews4, rating4, "Roy Montgomery", "The story of the tragic sinking ship", "Now Showing", "Scott Dann, Mick McCarthy");
        
        movieInit.createMovie(movieInit);
        movieInit.createMovie(movieInit1);
        movieInit.createMovie(movieInit2);
        movieInit.createMovie(movieInit3);
        movieInit.createMovie(movieInit4);

        MovieDB.setTotalId();

        Person pObj = new Person();
        pObj.checkType();

        System.out.println("Thank you and hope to see you again soon! =D");
    }
    
}

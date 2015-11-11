package movieapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Team Fork
 */
public class MovieDB {
    
    private String movieName;
    
    private String movieType;
    
    private String[] showTimings;
    
    private boolean[][] seats;
    
    private double price;
    
    private String[] reviews;
    
    private double[] rating;
            
    private static int id = 1;
    
    public MovieDB(){
    }
    
    public MovieDB(String movieName, String movieType, String[] showTimings,
            double price, String[] reviews, double[] rating){
        this.movieName = movieName;
        this.movieType = movieType;
        this.price = price;
        this.showTimings = showTimings;
        this.seats = new boolean[10][40];
        for(int i =0;i<10;i++)
            for(int j=0;j<40;j++)
                this.seats[i][j]=false;
        this.reviews = reviews;
        this.rating = rating;
    }
    
    public MovieDB(String movieName, String movieType, String[] showTimings,
            boolean[][] seats, double price,String[] reviews, double[] rating){
        this.movieName = movieName;
        this.movieType = movieType;
        this.price = price;
        this.showTimings = showTimings;
        this.seats = seats;
        this.reviews = reviews;
        this.rating = rating;
    }
    
    public String getMovieName(){
        return this.movieName;
    }
    
    public String getMovieType(){
        return movieType;
    }
    
    public String[] getShowTimings(){
        return showTimings;
    }
    
    public boolean[][] getSeats(){
        return seats;
    }
    
    public double getPrice(){
        return price;
    }
    
    public int getId(){
        return id;
    }
    
    public String[] getReviews(){
        return reviews;
    }
    
    public double[] getRating(){
        return rating;
    }
    
    public double getRatingAverage()
    {
        double sum =0;
        for(int i=0;i<rating.length;i++)
            sum += rating[i];
        return sum/rating.length;
    }
    
    public void setSeat(int row, int column){
        this.seats[row][column] = true;
    }
    
    public void setReview(String review) {
        reviews[reviews.length] = new String(review);
    }
    
    public void setRating(double rating) {
        this.rating[this.rating.length] = rating;
    }
    
    /*
    * This method adds a new movie to the JSON file
    */
    
    public void createMovie (MovieDB movie) throws IOException, ParseException{
      JSONParser parser = new JSONParser();
      JSONObject obj = new JSONObject();
      File f = new File("movieData.txt");
      if(f.exists()) { 
          obj = (JSONObject) parser.parse(new FileReader("movieData.txt"));
      }
      JSONArray jsonArray = new JSONArray();
      jsonArray.add(movie.movieName);
      jsonArray.add(movie.movieType);
      JSONArray parentJsonArray = new JSONArray();
      parentJsonArray.addAll(Arrays.asList(movie.showTimings));
      jsonArray.add(parentJsonArray);
      parentJsonArray = new JSONArray();
      for (boolean[] seat : movie.seats) {
            JSONArray childJsonArray = new JSONArray();
            for (int j = 0; j<movie.seats.length; j++) {
                childJsonArray.add(seat[j]);
            }
            parentJsonArray.add(childJsonArray);
        }
      jsonArray.add(parentJsonArray);
      jsonArray.add(movie.price);
      parentJsonArray = new JSONArray();
      parentJsonArray.addAll(Arrays.asList(movie.reviews));
      jsonArray.add(parentJsonArray);
      parentJsonArray = new JSONArray();
      for(int i=0; i<rating.length; i++)
          parentJsonArray.add(rating[i]);
      jsonArray.add(parentJsonArray);
      obj.put(movie.id++, jsonArray);
      try (FileWriter file = new FileWriter("movieData.txt")) 
      {
               file.write(obj.toJSONString());
	}
    }
    /*
    * This method deletes a particular movie from the JSON file
    */
    
    public void deleteMovie (String movieName) throws ParseException, IOException{
      JSONParser parser = new JSONParser();
      JSONObject obj1 = (JSONObject) parser.parse(new FileReader("movieData.txt"));
      while (obj1.get(id)!=null){
          if (movieName == ((JSONArray)obj1.get(id)).get(0)){
              obj1.remove(id);
          }
      }
      try (FileWriter file = new FileWriter("movieData.txt")) {
            file.write(obj1.toJSONString());
        }
    }
    
    /*
    * This method updates a particular movie in the database
    */
    
    public void updateMovie (int id, int edit) throws IOException, ParseException {
      JSONParser parser = new JSONParser();
      Scanner sc = new Scanner (System.in);
      JSONObject obj = (JSONObject) parser.parse(new FileReader("movieData.txt"));
      JSONArray arr = (JSONArray) obj.get(id);
      arr.remove(edit - 1);
      switch (edit){
          /* 1. Movie Name
           * 2. Movie Type
           * 3. Show timings
           * 4. Price
           */
          case 1:
              System.out.println("Please enter the new Movie Name:");
              arr.add(0, sc.next()); 
              break;
          case 2:
              System.out.println("Please enter the new Movie Type:");
              arr.add(1, sc.next()); 
              break; 
          case 4:
              System.out.println("Please enter the new Price:");
              arr.add(3, sc.nextFloat()); 
              break;
          default:
              break;
      }
      try (FileWriter file = new FileWriter("movieData.txt")) {
		file.write(obj.toJSONString());
	}
    }
    
    /*
    * This method gets the entire set of movies from the database and puts them in objects
    */
    
    public void getMovieArray (MovieDB movies []) throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();
        int id = 0;
        JSONObject obj1 = (JSONObject) parser.parse(new FileReader("movieData.txt"));
        JSONArray arr,arr2,arr3;
        String[] s,s1;
        double[] s2;
        int i,j;
        boolean[][] seats=null;
        while (obj1.get(id)!=null){
            arr = (JSONArray)obj1.get(id);
            
            //Showtimings
            arr2 = new JSONArray();
            arr2 = (JSONArray)arr.get(2);
            s = new String[arr2.size()];
            for(i=0;i<arr2.size();i++)
                s[i] = ((JSONObject)arr2.get(i)).toString();
            
            //Seats Available
            arr2 = new JSONArray();
            arr2 = (JSONArray)arr.get(3);
            for(i=0;i<arr2.size();i++){
                arr3 = new JSONArray();
                arr3 = (JSONArray)arr2.get(i);
                seats = new boolean[10][40];
                for(j=0;j<arr3.size();j++)
                    seats[i][j]=(boolean) arr3.get(j);
            }
            
            //Reviews
            arr2 = new JSONArray();
            arr2 = (JSONArray)arr.get(5);
            s1 = new String[arr2.size()];
            for(i=0;i<arr2.size();i++)
                s1[i] = ((JSONObject)arr2.get(i)).toString();
            
            //Ratings
            arr2 = new JSONArray();
            arr2 = (JSONArray)arr.get(5);
            s2 = new double[arr2.size()];
            for(i=0;i<arr2.size();i++)
                s2[i] = (double) arr2.get(i);
            
            movies[id] = new MovieDB(((JSONObject)arr.get(0)).toString(),
                    ((JSONObject)arr.get(1)).toString(),
                    s,
                    seats,
                    (double)arr.get(4),
                    s1,
                    s2);
        }
    }
    
    /*
    * This method updates the entire movie listings
    */
    
    public void updateMovieListings (MovieDB movies[]) throws IOException {
        //write this code
        JSONObject obj = new JSONObject();
        JSONArray jsonArray;
        for (MovieDB movie : movies) {
            jsonArray = new JSONArray();
            jsonArray.add(movie.getMovieName());
            jsonArray.add(movie.getMovieType());
            JSONArray parentJsonArray = new JSONArray();
            parentJsonArray.addAll(Arrays.asList(movie.getShowTimings()));
            jsonArray.add(parentJsonArray);
            parentJsonArray = new JSONArray();
            for (boolean[] show : movie.getSeats()) {
                JSONArray childJsonArray = new JSONArray();
                for (int j = 0; j<seats.length; j++) {
                    childJsonArray.add(show[j]);
                }
                parentJsonArray.add(childJsonArray);
            }
            jsonArray.add(parentJsonArray);
            parentJsonArray = new JSONArray();
            parentJsonArray.addAll(Arrays.asList(movie.reviews));
            jsonArray.add(parentJsonArray);
            parentJsonArray = new JSONArray();
            for(int i=0; i<rating.length; i++)
          parentJsonArray.add(rating[i]);
            jsonArray.add(parentJsonArray);
            jsonArray.add(movie.getPrice());
            obj.put(movie.id++, jsonArray);
        }
        try (FileWriter file = new FileWriter("movieData.txt")) {
               file.write(obj.toJSONString());
	}
    }
}
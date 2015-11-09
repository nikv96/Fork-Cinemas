/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;

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
 * @author user
 */
public class MovieDB {
    
    private String movieName;
    private String movieType;
    private String[] showTimings;
    private boolean[][] seats;
    private float price;
    private int id;
    
    public MovieDB(String movieName, String movieType, String[] showTimings, float price){
        this.movieName = movieName;
        this.movieType = movieType;
        this.price = price;
        this.showTimings = showTimings;
        for(int i =0;i<40;i++)
            for(int j=0;j<40;j++)
                seats[i][j]=false;
    }
    
    public MovieDB(String movieName, String movieType, String[] showTimings,boolean[][] seats, float price){
        this.movieName = movieName;
        this.movieType = movieType;
        this.price = price;
        this.showTimings = showTimings;
        this.seats = seats;
    }
    
    public String getMovieName(){
        return movieName;
    }
    
    public String getMovieType(){
        return movieType;
    }
    
    public String [] getShowTimings(){
        return showTimings;
    }
    
    public boolean[][] getSeats(){
        return seats;
    }
    
    public float getPrice(){
        return price;
    }
    
    public int getId(){
        return id;
    }
    
    public void createMovie (MovieDB movie) throws IOException, ParseException{
      JSONParser parser = new JSONParser();
      JSONObject obj = (JSONObject) parser.parse(new FileReader("movieData.txt"));
      JSONArray jsonArray = new JSONArray();
      jsonArray.add(movie.movieName);
      jsonArray.add(movie.movieType);
      JSONArray parentJsonArray = new JSONArray();
        for (boolean[] seat : seats) {
            JSONArray childJsonArray = new JSONArray();
            for (int j = 0; j<seats.length; j++) {
                childJsonArray.add(seat[j]);
            }
            parentJsonArray.add(childJsonArray);
        }
      jsonArray.add(parentJsonArray);
      parentJsonArray = new JSONArray();
      parentJsonArray.addAll(Arrays.asList(showTimings));
      jsonArray.add(parentJsonArray);
      jsonArray.add(movie.price);
      obj.put(movie.id, jsonArray);
      try (FileWriter file = new FileWriter("movieData.txt")) {
               file.write(obj.toJSONString());
	}
    }
    
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
          case 3:
              System.out.println("Please enter the new Show Timings:");
              arr.add(2, sc.next()); 
              break;  
          case 4:
              System.out.println("Please enter the new Price:");
              arr.add(3, sc.next()); 
              break;
          default:
              break;
      }
      try (FileWriter file = new FileWriter("movieData.txt")) {
		file.write(obj.toJSONString());
	}
    }
    
    public void getMovieArray (MovieDB movies []) throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();
        int id = 0;
        JSONObject obj1 = (JSONObject) parser.parse(new FileReader("movieData.txt"));
        JSONArray arr;
        while (obj1.get(id)!=null){
            arr = (JSONArray)obj1.get(id);
            movies[id] = new MovieDB(arr.get(0).toString(),arr.get(1),arr.get(2),arr.get(3),arr.get(4));
        }
    }
    
    public void updateMovieListings (MovieDB movies[]) throws IOException {
        //write this code
        JSONObject obj = new JSONObject();
        JSONArray jsonArray;
        for (MovieDB movie : movies) {
            jsonArray = new JSONArray();
            jsonArray.add(movie.getMovieName());
            jsonArray.add(movie.getMovieType());
            JSONArray parentJsonArray = new JSONArray();
            for (boolean[] show : movie.getSeats()) {
                JSONArray childJsonArray = new JSONArray();
                for (int j = 0; j<seats.length; j++) {
                    childJsonArray.add(show[j]);
                }
                parentJsonArray.add(childJsonArray);
            }
            jsonArray.add(parentJsonArray);
            parentJsonArray = new JSONArray();
            parentJsonArray.addAll(Arrays.asList(movie.getShowTimings()));
            jsonArray.add(parentJsonArray);
            jsonArray.add(movie.getPrice());
            obj.put(movie.id, jsonArray);
        }
        try (FileWriter file = new FileWriter("movieData.txt")) {
               file.write(obj.toJSONString());
	}
    }
}
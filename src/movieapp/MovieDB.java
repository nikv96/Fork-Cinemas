/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    private String shows[][];
    private float price;
    private int id;
    
    public String getMovieName(){
        return movieName;
    }
    
    public String getMovieType(){
        return movieType;
    }
    
    public String[][] getShows(){
        return shows;
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
      jsonArray.add(movie.shows);
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
    
    public void updateMovieListings (MovieDB movies[]) throws IOException {
        //write this code
        JSONObject obj = new JSONObject();
        JSONArray jsonArray;
        for (MovieDB movie : movies) {
            jsonArray = new JSONArray();
            jsonArray.add(movie.getMovieName());
            jsonArray.add(movie.getMovieType());
            jsonArray.add(movie.getShows());
            jsonArray.add(movie.getPrice());
            obj.put(movie.id, jsonArray);
        }
        try (FileWriter file = new FileWriter("movieData.txt")) {
               file.write(obj.toJSONString());
	}
            
    }
}
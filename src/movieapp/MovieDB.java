/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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
    private String id;
    
    public void createMovie () throws IOException, ParseException{
      JSONObject obj = new JSONObject();
      JSONParser parser = new JSONParser();
      String s = "{"+id+":{\"Name\":"+movieName+",\"Movie Type\":"+movieType+",\"Shows\":"+Arrays.toString(shows)+",\"Price\":"+price+"}}";
      obj = (JSONObject) parser.parse(s);
      try (FileWriter file = new FileWriter("movieData.txt")) {
			file.write(obj.toJSONString());
	}
    }
    
    public void deleteMovie (String movieName) throws ParseException, IOException{
      JSONParser parser = new JSONParser();
      JSONObject obj1 = (JSONObject) parser.parse(new FileReader("movieData.txt"));
      while (obj1.get(id)!=null){
          if (movieName== ((JSONObject)obj1.get(id)).get("Name")){
              obj1.remove(id);
          }
      }
      try (FileWriter file = new FileWriter("movieData.txt")) {
			file.write(obj1.toJSONString());
	}
    }
    
    public void updateMovie (String movieName, String edit) throws IOException, ParseException {
      JSONParser parser = new JSONParser();
      JSONObject obj1 = (JSONObject) parser.parse(new FileReader("movieData.txt"));
      String s;
      while (obj1.get(id)!=null){
          if (movieName== ((JSONObject)obj1.get(id)).get("Name")){
              //do something here
          }
      }
      try (FileWriter file = new FileWriter("movieData.txt")) {
			file.write(obj1.toJSONString());
	}
    }
    
    public void updateMovieListings () {
        //write this ccode
    }
}
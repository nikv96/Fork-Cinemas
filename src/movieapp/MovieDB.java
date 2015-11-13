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
    
    private boolean[][][] seats;
    
    private double price;
    
    private String[] reviews;
    
    private double[] rating;
            
    private static int total_id = 0;
    
    private int id;
    
    private String director;
    
    private String synopsis;
    
    private String showStatus;
    
    private String cast;
    
    private MovieDB allMovies[];
    
    public MovieDB(){
    }
    
    public MovieDB(String movieName, String movieType, String[] showTimings, 
            String[] review, double[] rating, String director, String synopsis, 
            String showStatus, String cast){
        this.movieName = movieName;
        this.movieType = movieType;
        this.showTimings = showTimings;
        this.price = 20;
        this.seats = new boolean[10][40][10];
        for(int i =0;i<10;i++)
            for(int j=0;j<40;j++)
                for(int k=0;k<10;k++)
                    this.seats[i][j][k]=false;
        this.reviews = review;
        this.rating = rating;
        this.director = director;
        this.showStatus = showStatus;
        this.synopsis = synopsis;
        this.cast = cast;
    }
    
    public MovieDB(String movieName, String movieType, String[] showTimings,
            boolean[][][] seats, double price,String[] reviews, 
            double[] rating,String director, String synopsis, String showStatus, String cast){
        this.movieName = movieName;
        this.movieType = movieType;
        this.price = price;
        this.showTimings = showTimings;
        this.seats = seats;
        this.reviews = reviews;
        this.rating = rating;
        this.director = director;
        this.showStatus = showStatus;
        this.synopsis = synopsis;
        this.cast = cast;
    }
    
    public int getTotalId(){
        return total_id;
    }
    
    public String getMovieName(){
        return this.movieName;
    }
    
    public String getMovieType(){
        return this.movieType;
    }
    
    public String[] getShowTimings(){
        return this.showTimings;
    }
    
    public boolean[][][] getSeats(){
        return this.seats;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String[] getReviews(){
        return this.reviews;
    }
    
    public double[] getRating(){
        return this.rating;
    }
    
    public double getRatingAverage(){
        double sum =0;
        for(int i=0;i<rating.length;i++)
            sum += rating[i];
        return sum/rating.length;
    }
    
    public String getDirector(){
        return this.director;
    }
    
    public String getSynopsis(){
        return this.synopsis;
    }
    
    public String getCast(){
        return this.cast;
    }
    
    public String getShowStatus(){
        return this.showStatus;
    }
    
    public String getShowTiming(int id){
        String[] s = this.getShowTimings();
        return s[id];
    }
    
    public void setShowStatus(String status){
        this.showStatus = status;
    }
    
    public void setSeat(int showId, int column, int row){
        this.seats[showId][column][row] = true;
    }
    
    public void setReview(String review) {
        String[] temp_reviews = new String[this.reviews.length+1];
        for(int i=0;i<this.reviews.length;i++)
            temp_reviews[i]= reviews[i];
        temp_reviews[this.reviews.length] = review;
        this.reviews = temp_reviews;
    }
    
    public void setRating(double r) {
        double[] temp_ratings = new double[this.rating.length+1];
        for(int i=0;i<this.rating.length;i++)
            temp_ratings[i]= rating[i];
        temp_ratings[this.rating.length] = r;
        this.rating = temp_ratings;
    }
    
    public static void setTotalId() throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject obj1 = (JSONObject) parser.parse(new FileReader("movieData.txt"));
        total_id = obj1.size();
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
      for (boolean[][] seatsCheck : movie.seats) {
            JSONArray childJsonArray1 = new JSONArray();
            for (boolean[] seat : seatsCheck) {
                JSONArray childJsonArray2 = new JSONArray();
                for(int k=0; k<seat.length ; k++)
                    childJsonArray2.add(seat[k]);
                childJsonArray1.add(childJsonArray2);
            }
            parentJsonArray.add(childJsonArray1);
        }
      jsonArray.add(parentJsonArray);
      jsonArray.add(movie.price);
      if(movie.reviews != null){
      parentJsonArray = new JSONArray();
      parentJsonArray.addAll(Arrays.asList(movie.reviews));
      jsonArray.add(parentJsonArray);
      }
      else
      {
          movie.setReview("");
      }
      parentJsonArray = new JSONArray();
      for(int i=0; i<rating.length; i++)
          parentJsonArray.add(rating[i]);
      jsonArray.add(parentJsonArray);
      
      jsonArray.add(movie.director);
      
      jsonArray.add(movie.synopsis);
      
      jsonArray.add(movie.showStatus);
      
      jsonArray.add(movie.cast);
      
      obj.put(total_id++, jsonArray);
      try (FileWriter file = new FileWriter("movieData.txt")) 
      {
               file.write(obj.toJSONString());
	}
    }
    
    /*
    * This method deletes a particular movie from the JSON file
    */
    
    public void deleteMovie (String movieName) throws ParseException, IOException{
        int i = 0;
      JSONParser parser = new JSONParser();
      JSONObject obj1 = (JSONObject) parser.parse(new FileReader("movieData.txt"));
      JSONArray jarr = new JSONArray();
      String s = new String();
      while (obj1.get(Integer.toString(i))!=null){
          if(movieName.equals( ((JSONArray)obj1.get(Integer.toString(i))).get(0).toString() ))
          {
              jarr = (JSONArray)obj1.get(Integer.toString(i));
              s = jarr.get(10).toString();
              jarr.remove(10);
              jarr.remove(9);
              jarr.add("End Of Showing");
              jarr.add(s);
              break;
          }
          i++;
      }
      
      obj1.replace(Integer.toString(i), jarr);
      
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
      JSONArray arr = (JSONArray) obj.get(Integer.toString(id));
      switch (edit){
          /* 1. Movie Name
           * 2. Movie Type
           * 3. Show timings
           * 4. Price
           */
          case 1:
              arr.remove(edit - 1);
              System.out.print("\nPlease enter the new Movie Name: ");
              arr.add(0, sc.nextLine()); 
              break;
          case 2:
              arr.remove(edit - 1);
              System.out.print("Please enter the new Movie Type: ");
              arr.add(1, sc.next()); 
              break; 
          case 3:
              arr.remove(edit - 1);
              JSONArray childJsonArray = new JSONArray();
              System.out.print("Please enter the new Show Timings: ");
                char choice = 'y';
                do {
                    childJsonArray.add(sc.nextLine());
                    System.out.println("More shows? ");
                    choice = sc.next().charAt(0);
                } while (choice == 'y'|| choice == 'Y');
                arr.add(2,childJsonArray);
              break;
          case 4:
              String temp_status;
              String name;
              System.out.print("Please enter new showing status: ");
              temp_status = sc.nextLine();
              if(temp_status.equalsIgnoreCase("End of Showing")){
                    System.out.println("Enter Movie Name to confirm: ");
                    
                        deleteMovie(sc.nextLine() + sc.nextLine());
                        return;
              }
              else{
                  arr.remove(9);
                  arr.add(9,temp_status);
              }
          break;
          default:
              break;
      }
      obj.put(id,arr);
      try (FileWriter file = new FileWriter("movieData.txt")) {
		file.write(obj.toJSONString());
	}
    }
    
    /*
    * This method gets the entire set of movies from the database and puts them in objects
    */
    
    public MovieDB[] getMovieArray (MovieDB movies []) throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();
        JSONObject obj1 = (JSONObject) parser.parse(new FileReader("movieData.txt"));
        JSONArray arr,arr2,arr3,arr4;
        int id=0;
        String[] s,s1;
        double[] s2;
        int i,j,k;
        boolean[][][] seats=null;
        while (obj1.get(Integer.toString(id))!=null){
            arr = (JSONArray)obj1.get(Integer.toString(id));
            
            //Showtimings
            arr2 = new JSONArray();
            arr2 = (JSONArray)arr.get(2);
            s = new String[arr2.size()];
            for(i=0;i<arr2.size();i++)
                s[i] = (String) arr2.get(i);
            
            //Seats Available
            arr2 = new JSONArray();
            arr2 = (JSONArray)arr.get(3);
            seats = new boolean[10][40][10];
            for(i=0;i<arr2.size();i++){
                arr3 = new JSONArray();
                arr3 = (JSONArray)arr2.get(i);
                for(j=0;j<arr3.size();j++){
                    arr4 = new JSONArray();
                    arr4 = (JSONArray)arr3.get(j);
                    for(k=0;k<arr4.size();k++)
                        seats[i][j][k]=(boolean) arr4.get(k);
                }
            }
            
            //Reviews
            arr2 = new JSONArray();
            arr2 = (JSONArray)arr.get(5);
            s1 = new String[arr2.size()];
            for(i=0;i<arr2.size();i++)
                s1[i] = (String) arr2.get(i);
            
            //Ratings
            arr2 = new JSONArray();
            arr2 = (JSONArray)arr.get(6);
            s2 = new double[arr2.size()];
            for(i=0;i<arr2.size();i++)
                s2[i] = (double) arr2.get(i);
            //Director, showStatus, synopsis, cast
            
            movies[id] = new MovieDB((arr.get(0)).toString(),
                    (arr.get(1)).toString(),
                    s,
                    seats,
                    (double)arr.get(4),
                    s1,
                    s2, arr.get(7).toString(),
                    arr.get(8).toString(), 
                    arr.get(9).toString(), 
                    arr.get(10).toString());
            
            id++;
        }
        return movies;
    }
    
    public MovieDB[] getMovieArray (MovieDB movies [], int check_id) throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();
        JSONObject obj1 = (JSONObject) parser.parse(new FileReader("movieData.txt"));
        JSONArray arr,arr2,arr3,arr4;
        int id=0,id1=0;
        String[] s,s1;
        double[] s2;
        int i,j,k;
        boolean[][][] seats=null;
        if(id==check_id)
            id++;
        while (obj1.get(Integer.toString(id))!=null){
            arr = (JSONArray)obj1.get(Integer.toString(id));
            
            //Showtimings
            arr2 = new JSONArray();
            arr2 = (JSONArray)arr.get(2);
            s = new String[arr2.size()];
            for(i=0;i<arr2.size();i++)
                s[i] = (String) arr2.get(i);
            
            //Seats Available
            arr2 = new JSONArray();
            arr2 = (JSONArray)arr.get(3);
            seats = new boolean[10][40][10];
            for(i=0;i<arr2.size();i++){
                arr3 = new JSONArray();
                arr3 = (JSONArray)arr2.get(i);
                for(j=0;j<arr3.size();j++){
                    arr4 = new JSONArray();
                    arr4 = (JSONArray)arr3.get(j);
                    for(k=0;k<arr4.size();k++)
                        seats[i][j][k]=(boolean) arr4.get(k);
                }
            }
            
            //Reviews
            arr2 = new JSONArray();
            arr2 = (JSONArray)arr.get(5);
            s1 = new String[arr2.size()];
            for(i=0;i<arr2.size();i++)
                s1[i] = (String) arr2.get(i);
            
            //Ratings
            arr2 = new JSONArray();
            arr2 = (JSONArray)arr.get(6);
            s2 = new double[arr2.size()];
            for(i=0;i<arr2.size();i++)
                s2[i] = (double) arr2.get(i);
            
            movies[id1++] = new MovieDB((arr.get(0)).toString(),
                    (arr.get(1)).toString(),
                    s,
                    seats,
                    (double)arr.get(4),
                    s1,
                    s2,arr.get(7).toString(),(arr.get(8)).toString(), (arr.get(9)).toString(),
                    (arr.get(10)).toString());
            id++;
            if(id==check_id)
                id++;
        }
        return movies;
    }
    
    /*
    * This method updates the entire movie listings
    */
    
    public void updateMovieListings (MovieDB movies[]) throws IOException {
        //write this code
        int id=0;
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
            for (boolean[][] seatsCheck : movie.seats) {
            JSONArray childJsonArray1 = new JSONArray();
            for (boolean[] seat : seatsCheck) {
                JSONArray childJsonArray2 = new JSONArray();
                for(int k=0; k<seat.length ; k++)
                    childJsonArray2.add(seat[k]);
                childJsonArray1.add(childJsonArray2);
            }
            parentJsonArray.add(childJsonArray1);
            }
            jsonArray.add(parentJsonArray);
            parentJsonArray = new JSONArray();
            parentJsonArray.addAll(Arrays.asList(movie.reviews));
            jsonArray.add(parentJsonArray);
            parentJsonArray = new JSONArray();
            for(int i=0; i<movie.rating.length; i++)
                parentJsonArray.add(movie.rating[i]);
            jsonArray.add(parentJsonArray);
            
            jsonArray.add(movie.director);
      
            jsonArray.add(movie.synopsis);
      
            jsonArray.add(movie.showStatus);
      
            jsonArray.add(movie.cast);
            
            obj.put(id++, jsonArray);
        }
        try (FileWriter file = new FileWriter("movieData.txt")) {
               file.write(obj.toJSONString());
	}
    }
    
    public double calcPrice(String cineType, Customer cObj, String showTime){
        double finalPrice = 0.0;
        finalPrice = this.getPrice();
        
        if(cObj.getAge() >= 60)
        {
            finalPrice = finalPrice * 0.94;
        }
        if(this.getMovieType().equals("3D")){
            finalPrice = finalPrice * 1.05;
        }
        if(cineType.equals("Platinum"))
            finalPrice = finalPrice*1.10;
        
        if(showTime.substring(1, 4).equals("Sun") || showTime.substring(1,4).equals("Sat") )
            finalPrice = finalPrice + 2;
        
        return finalPrice;
    }
    
}
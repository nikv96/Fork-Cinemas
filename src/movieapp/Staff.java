package movieapp;

import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Team Fork
 */
public class Staff extends Person{
    private String cineplex;
    
    static StaffDB staffObj[] = new StaffDB[10];
    
    JSONObject staffJSON[] = new JSONObject[10];
  
    static int id = 0;
    
    Scanner sc = new Scanner(System.in);
    
    MovieDB movieEditObj = new MovieDB();
    
    MovieList listEdit = new MovieList();
    
    //get method
    
    public String getCineplex(){
        return cineplex;
    }
    
    public Staff(){
    }
    
    public Staff(String name, int age, String emailid, long phno, long postal, String gender, String user, String pass, String cineplex) {
        super(name,age,emailid,phno,postal,gender,user,pass);
        this.cineplex = cineplex;
    }
    
    @Override
    public void login()
    {
        System.out.println("\nStaff - login\n");
        try{
        int ch;
        boolean flag;
        do{
            super.login();
            flag = StaffDB.readStaff(this.getUserName(), this.getPassword());
            if (flag == false){
                System.out.print("\nEither username or password is wrong.");
                System.out.print("\n1. Sign up 2. Login\nEnter you choice: ");
                ch = sc.nextInt();
                if(ch == 1){
                    signup();
                    flag = true;
                }
            
            }
                
        } while (flag !=true);
        displayMenu();
        }
        catch(IOException | ParseException e){
            e.getMessage();
        }
    }
    
    /*
    *Staff implementation of signup
    */
    
    @Override
    public void signup(){
       super.signup();
       System.out.print("\nEnter cineplex name: ");
       cineplex = sc.next();
       Staff.id++;
       try {
           staffJSON[Staff.id] = StaffDB.addStaff(this);
       }
       catch(IOException | ParseException e){
           e.getMessage();
       }
       login();
    }
    
    /*
    * Displays menu 
    */
    
    @Override
    public void displayMenu(){
        char c;
        System.out.print("\nEnter 'M' for editing Movie Details or 'C' for editing Customer Details: ");
        c = sc.next().charAt(0);
        if(c == 'C' || c == 'c'){
            userEdits();
        }
        else if(c == 'M' || c=='m'){
           movieEdits();
        }
    }
    
    public void userEdits(){
        String userConfig;
        int ch; int check_id;
        try{
        System.out.print("\nEnter username of customer account to configure: ");
        userConfig = sc.next();
        check_id = CustomerDB.configUser(userConfig);
        
        if(check_id>=0)
        {
            System.out.print("\n\t\t1. Change username"
                    + "\n\t\t2. Change password\n"
                    + "\t\t3. Change name\n"
                    + "\t\t4. Change email ID\n"
                    + "5. Logout\t\t\t\t6. Exit\nPlease enter your choice: ");
            ch = sc.nextInt();
            JSONParser parser = new JSONParser();
            Scanner sc = new Scanner (System.in);
            JSONObject obj = (JSONObject) parser.parse(new FileReader("customerData.txt"));
            JSONArray arr = (JSONArray) obj.get(Integer.toString(check_id));
            switch (ch){
                /* 1. Username
                 * 2. Password
                 * 3. Name
                 * 4. Email ID
                 */
                case 1:
                    System.out.print("Please enter the new username: ");
                    arr.remove(6);
                    arr.add(6, sc.next());
                    break;
                case 2:
                    System.out.print("Please enter the new password: ");
                    arr.remove(7);
                    arr.add(7, sc.next()); 
                    break; 
                case 3:
                    System.out.print("Please enter the new name: ");
                    arr.remove(0);
                    arr.add(0, sc.nextLine());
                    break;
                case 4:
                    System.out.print("Please enter the new email ID: ");
                    arr.remove(2);
                    arr.add(2, sc.next());
                    break;
                default:
                    break;
            }
            obj.replace(check_id, arr);
            try (FileWriter file = new FileWriter("customerData.txt")) {
                      file.write(obj.toJSONString());
              }
           
        }
        }
        catch(IOException | ParseException e){
            e.printStackTrace();
        }
        
    }
    
    public void movieEdits()
    {
        String movieName;
        String[] showTimings = new String[0];
        String timings;
        int ch; int c2; int check_id; int id;
        char choice = 0;
        int i=0;
        try{
            do{
            System.out.print("\n1. Create Movie"
                    + "\n2. Update Movie"
                    + "\n3. Update Movie Listing"
                    + "\n[5] Logout\t\t\t\t[6] Exit\nPlease enter your choice: ");
           ch = sc.nextInt();
           
           MovieDB editObj = new MovieDB();
                   MovieDB allMovies[] = new MovieDB[movieEditObj.getTotalId()];
            
                   allMovies = editObj.getMovieArray(allMovies);
           
           switch(ch)
           {
               case 1:
                   showTimings = new String[0];
                   
                   MovieDB createObj = new MovieDB();
                   MovieDB newMovies[] = new MovieDB[movieEditObj.getTotalId() + 1];
            
                   newMovies = createObj.getMovieArray(newMovies);
                   
                   if(newMovies[newMovies.length - 1] == null)
                       id = 0;
                   else
                       id = newMovies[newMovies.length - 1].getShowTimings().length - 1;
                   System.out.print("\nEnter Movie Name: ");
                   movieName = sc.next();
                   System.out.print("\nEnter Movie Type(Comedy,Thriller,etc): ");
                   String movieType = sc.next();
                   System.out.println("Enter Show Timings.");
                   do{
                       System.out.print("Enter day,time (ex: Wednesday,1:00PM): ");
                       timings = Integer.toString(id)+sc.next();
                       showTimings = setNewShowTimings(showTimings, timings);
                       
                       System.out.print("Enter cineplex where show shall take place: ");
                       timings = timings + " - " + sc.nextLine() + sc.nextLine();
                        
                       System.out.println("More shows?");
                       choice = sc.next().charAt(0);
                   }while(choice == 'y' || choice == 'Y');
                   
                   System.out.print("Enter Expert Review: ");
                   String[] review = new String[1];
                   review[0] = sc.nextLine() + sc.nextLine();
                   
                   System.out.print("Enter Expert Rating(out of 5): ");
                   double[] rating = new double[1];
                   rating[0] = sc.nextDouble();
                   String director, showStatus, synopsis, cast;
                   
                   System.out.println("Enter name of director: ");
                   director = sc.nextLine() + sc.nextLine();
                   
                   System.out.println("Enter synopsis of movie: ");
                   synopsis = sc.nextLine() + sc.nextLine();
                   
                   System.out.println("Enter Show Status: ");
                   showStatus = sc.nextLine() + sc.nextLine();
                   
                   System.out.println("Enter name of cast members: ");
                   cast = sc.nextLine() + sc.nextLine();
                   
                   movieEditObj = new MovieDB(movieName,movieType,showTimings, review, rating, director, synopsis, showStatus, cast);
                   movieEditObj.createMovie(movieEditObj);
                   break;
               case 2:
                    System.out.format("\t%4s%16s%16s%50s\n", "#", "Movie Name", "Movie Type","Show Timings");
                    System.out.println("\t-------------------------------------------------------------------------------------------------");
                    int j = 1;
                    for (MovieDB movie : allMovies) {
                        String s = new String();
                        
                        for(i=0;i<movie.getShowTimings().length;i++)
                            s += " " + (movie.getShowTimings())[i];
                        
                        if(!movie.getShowStatus().equalsIgnoreCase("End of showing"))
                            System.out.format("\t%4d%16s%16s%56s\n", j++,
                                    movie.getMovieName(), movie.getMovieType(),s);
                    }
                    System.out.print("\nChoose your movie(Enter serial number): ");
                    ch = sc.nextInt();

                    System.out.print("\n\t\t1. Change Movie Name\n"
                            + "\t\t2. Change Movie Type\n"
                            + "\t\t3. Update Show Timings\n"
                            + "\t\t4. Configure Showing Status\n"
                            + "[5] Logout\t\t\t\t[6] Exit\n"
                            + "Enter your choice: ");
                    c2 = sc.nextInt();
                    movieEditObj.updateMovie(ch-1, c2);
                   break;
               case 4: movieEditObj.updateMovieListings(allMovies);
               default:
           }
            }while(ch < 4);
        }
        catch(IOException | ParseException e)
        {
            e.getMessage();
        }
        
        
        }
    public String[] setNewShowTimings(String[] showTimings, String timing)
        {
            String[] newShowTimings;int i = 0;
            newShowTimings = new String[showTimings.length + 1];
                       for(int j = 0; j < showTimings.length;j++)
                           newShowTimings[j] = showTimings[j];
                        newShowTimings[showTimings.length] = timing;
                        
            return newShowTimings;
        }
}

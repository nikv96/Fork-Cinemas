package movieapp;

import java.util.*;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Team Fork
 */
public class Staff extends Person{
    protected String cineplex;
    
    static StaffDB staffObj[] = new StaffDB[10];
    
    JSONObject staffJSON[] = new JSONObject[10];
  
    static int id = 0;
    
    Scanner sc = new Scanner(System.in);
    
    MovieDB movieEditObj = new MovieDB();
    
    MovieList listEdit = new MovieList();
    
    @Override
    public void login()
    {
        System.out.println("\nStaff - login\n");
        try{
        int ch;
        boolean flag;
        do{
            super.login();
            flag = StaffDB.readStaff(username, password);
            if (flag == false)
            {
                System.out.println("Either username or password is wrong.");
                System.out.println("1. Sign up 2. Login");
                ch = sc.nextInt();
                if(ch == 1){
                    signup();
                    login();
                    flag = true;
                }
            
            }
                
        } while (flag !=true);
        System.out.println("Menu\n");
        displayMenu();
        }
        catch(IOException | ParseException e){
            e.getMessage();
        }
    }
    
    @Override
    public void signup(){
       super.signup();
       System.out.println("Enter cineplex name: ");
       cineplex = sc.next();
       Staff s = new Staff();
        Staff.id++;
        s.name = name;
        s.username = username;
        s.password = password;
        s.age = age;
        s.gender = gender;
        s.emailID = emailID;
        s.phone = phone;
        s.postalCode = postalCode;
        s.cineplex = cineplex;
       
       try {
           staffJSON[Staff.id] = StaffDB.addStaff(s);
       }
       catch(IOException | ParseException e){
           e.getMessage();
       }
    }
    
    @Override
    public void displayMenu(){
        String userConfig;
        int ch; char c; boolean check;
        System.out.println("Enter M for Movies Update OR U for User Account Update:");
        c = sc.next().charAt(0);
        if(c == 'U'){
            userEdits();
        }
        else if(c == 'M'){
           movieEdits();
        }
  
 
    }
    
    public void userEdits(){
        //will be updated as the get & set methods are completed
        String userConfig;
        int ch; char c; int check_id;
        try{
        System.out.println("Enter username of account to configure:");
        userConfig = sc.next();
        System.out.println("Enter type of account to configure(S for Staff OR C for Customer:");
        c = sc.next().charAt(0);
        check_id = c == 'S'? StaffDB.configUser(userConfig):CustomerDB.configUser(userConfig);
        
        
        if(check_id>=0 && c=='S')
        {
            System.out.println("Menu:\n1. Change username\n2. Change password\n3. Change name\n4. Change email ID\n5. Logout\t\t\t\t6. Exit");
           ch = sc.nextInt();
           switch(ch)
           {
               case 1:
                   staffJSON[check_id].replace(check_id, username, sc.next());
                   break;
               case 2:
                   staffJSON[check_id].replace(check_id, password, sc.next());
                   break;
               case 3:
                   staffJSON[check_id].replace(check_id, name, sc.next());
                   break;
               case 4:
                   staffJSON[check_id].replace(check_id, emailID, sc.next());
                   break;
               default:
                   //later
           }
           //update .txt file
           
        }
        if(check_id>=0 && c=='C')
        {
            System.out.println("Menu:\n1. Change username\n2. Change password\n3. Change name\n4. Change email ID\n5. Logout\t\t\t\t6. Exit");
           ch = sc.nextInt();
           switch(ch)
           {
               case 1:
                   Customer.custJSON[check_id].replace(check_id, username, sc.next());
                   break;
               case 2:
                   Customer.custJSON[check_id].replace(check_id, password, sc.next());
                   break;
               case 3:
                   Customer.custJSON[check_id].replace(check_id, name, sc.next());
                   break;
               case 4:
                   Customer.custJSON[check_id].replace(check_id, emailID, sc.next());
                   break;
               default:
                   //later
           }
           //update .txt file
        }
        }
        catch(IOException | ParseException e){
            e.getMessage();
        }
        
    }
    
    public void movieEdits()
    {
        String movieName;
        int ch; int c2; int check_id;
        try{
            MovieDB allMovies[] = new MovieDB[movieEditObj.getId() + 1];
            movieEditObj.getMovieArray(allMovies);
            System.out.println("Menu:\n1. Create Movie\n2. Update Movie\n3. Delete Movie\n4. Update Movie Listing\n5. Logout\t\t\t\t6. Exit");
           ch = sc.nextInt();
           
           switch(ch)
           {
               case 1:
                   System.out.println("Enter Movie Name");
                   movieEditObj.movieName = sc.nextLine();
                   System.out.println("Enter Movie Type");
                   movieEditObj.movieType = sc.nextLine();
                   System.out.println("Enter Show Timings");
                   movieEditObj.showTimings[0] = sc.nextLine();
                   System.out.println("Enter Price");
                   movieEditObj.price = sc.nextDouble();
                   for (int i = 0;i < 10;i++)
                   {
                       for(int j = 0; j < 40; j++)
                        movieEditObj.seats[i][j] = false;
                   }
                   movieEditObj.createMovie(movieEditObj);
                   break;
               case 2:
        for (MovieDB movie : allMovies) {
            System.out.format("%16s%16s%16s%16s", movie.getMovieName(), movie.getMovieType(), Arrays.toString(movie.getShowTimings()), movie.getPrice());
        }
        System.out.println("Choose your movie:");
        ch = sc.nextInt();
        
        System.out.println("Menu:\n1. Change Movie Name\n2. Change Movie Type\n3. Update Show Timings\n4. Change Movie Price\n5. Logout\t\t\t\t6. Exit");
        c2 = sc.nextInt();
        movieEditObj.updateMovie(ch, c2);
                   break;
               case 3:
                   System.out.println("Enter Movie Name");
                   movieEditObj.deleteMovie(sc.nextLine());
                   break;
               case 4: movieEditObj.updateMovieListings(allMovies);
               default:
                   //later
           }
        }
        catch(IOException | ParseException e)
        {
            e.getMessage();
        }
        }
}

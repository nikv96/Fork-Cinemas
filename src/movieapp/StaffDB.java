package movieapp;

import java.io.*;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Team Fork
 */
public class StaffDB {
    //inj
    
    public String getName(Staff s)
    {
        return s.name;
    }
    
    public String getPassword(Staff s)
    {
        return s.password;
    }
    
    public String getEmailID(Staff s)
    {
        return s.emailID;
    }
    
    public String getCineplex(Staff s)
    {
        return s.cineplex;
    }
    
    public void setUsername(Staff s, String user) throws IOException, FileNotFoundException, ParseException
    {
        s.username = user;
        JSONParser parser = new JSONParser();
        JSONObject objFull = new JSONObject();
      File f = new File("staffData.txt");
      if(f.exists()) {
          objFull = (JSONObject) parser.parse(new FileReader("staffData.txt"));
      }
      
    }
    
    public void setPassword(Staff s, String pass)
    {
        s.password = pass;
    }
    
    public void setEmailID(Staff s, String email)
    {
        s.emailID = email;
    }
    
    public void setGender(Staff s, String gender)
    {
        s.gender = gender;
    }
    
    public void setAge(Staff s, int age)
    {
        s.age = age;
    }
    
    
    
    public static boolean readStaff(String user, String pwd) throws IOException, FileNotFoundException, ParseException{
    //returns boolean to check for authentication
        System.out.println("checking..");
        JSONParser parser = new JSONParser();
        JSONObject objFull = new JSONObject();
      File f = new File("staffData.txt");
      if(f.exists()) { 
          objFull = (JSONObject) parser.parse(new FileReader("staffData.txt"));
      }
        JSONArray arrRow;
        boolean present = false;
       System.out.println("checking2..");
        for ( int i = 0; i<objFull.size();i++){
            arrRow = (JSONArray) objFull.get(Integer.toString(i+1));
            if (user.equals(arrRow.get(6).toString()))
            {
                if (pwd.equals(arrRow.get(7).toString()))
                    present = true;
            }
        }

        return present;
}
    
    public static JSONObject addStaff(Staff s) throws IOException, FileNotFoundException, ParseException{
      //adds staff record to the .dat file
      //basic writing code
     JSONObject obj = new JSONObject();
      JSONArray arr = new JSONArray();
      arr.add(s.name); //0
      arr.add(s.age);//1
      arr.add(s.emailID);//2
      arr.add(s.phone);//3
      arr.add(s.postalCode);//4
      arr.add(s.gender);//5
      arr.add(s.username);//6
      arr.add(s.password);//7
      arr.add(s.cineplex);//8
      obj.put(Staff.id,arr);
      try (FileWriter file = new FileWriter("staffData.txt"))
      {
		file.write(obj.toJSONString());
        }
      
      return obj;
       
    }
    
    public static int configUser(String user) throws IOException, FileNotFoundException, ParseException{
    //looks for the appropriate username and returns if existing, allowing access to that customer's details
        
        JSONParser parser = new JSONParser();
        JSONObject objFull = (JSONObject) parser.parse(new FileReader("staffData.txt"));
        JSONArray arrRow;
        boolean present = false;
        
        for ( int i = 0; i<objFull.size();i++)
        {
            arrRow = (JSONArray) objFull.get(i);
            if (user.equals(arrRow.get(6)))
            {
                    present = true;
                    return i;
            }
        }
        
        return -1;
    
    }
    
    public void updateUser (int id, int edit) throws IOException, ParseException {
      JSONParser parser = new JSONParser();
      Scanner sc = new Scanner (System.in);
      JSONObject obj = (JSONObject) parser.parse(new FileReader("staffData.txt"));
      JSONArray arr = (JSONArray) obj.get(id);
      arr.remove(edit - 1);
      switch (edit){
          /* 1.  Username
           * 2. Password
           * 3. email ID
           * 4. Cineplex
           */
          case 1:
              System.out.println("Please enter the new username:");
              arr.add(6, sc.next()); 
              break;
          case 2:
              System.out.println("Please enter the new password:");
              arr.add(7, sc.next()); 
              break; 
          case 3:
              System.out.println("Please enter the new email ID:");
              arr.add(2, sc.nextFloat()); 
              break;
          case 4:
              System.out.println("Please enter the new cineplex:");
              arr.add(8, sc.nextFloat()); 
              break;
          default:
              break;
      }
      try (FileWriter file = new FileWriter("staffData.txt")) {
		file.write(obj.toJSONString());
	}
      
      
    }
    
    public void getStaffsArray (Staff[] staff) throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();
        int id = 0;
        JSONObject obj1 = (JSONObject) parser.parse(new FileReader("customerData.txt"));
        JSONArray arr;
        while (obj1.get(Integer.toString(id))!=null){
            arr = (JSONArray) obj1.get(Integer.toString(id));
            
            staff[id] = new Staff((arr.get(0)).toString(),
                    (int)arr.get(1),
                    arr.get(2).toString(),
                    (long) arr.get(3),
                    (long) arr.get(4),
                    (String) arr.get(5),
                    (String) arr.get(6), (String) arr.get(7),
                    (String) arr.get(8));
            id++;
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;

import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author user
 */
public class StaffDB {
    
    public static boolean readStaff(String user, String pwd) throws IOException, FileNotFoundException, ParseException{
    //returns boolean to check for authentication
        JSONParser parser = new JSONParser();
        JSONObject obj1 = (JSONObject) parser.parse(new FileReader("staffData.txt"));
        JSONObject obj2 = new JSONObject();
        boolean present = false;
        
        for ( int i = 0; i<obj1.size();i++){
            obj2 = (JSONObject) obj1.get(i);
            if (user.equals(obj2.get("Username"))){
                if (pwd.equals(obj2.get("Password")))
                    present = true;
            }
        }
        
        return present;
}
    
    public static void addStaff(Staff s) throws IOException, FileNotFoundException, ParseException{
      //adds staff record to the .dat file
      //basic writing code
      JSONObject obj = new JSONObject();
      JSONParser parser = new JSONParser();
      String s = "{"+s.id+":{\"Name\":"+s.name+",\"Age\":"+s.age+",\"EmailID\":"+s.email+",\"Number\":"+s.phno+",\"Postal Code\":"+s.pcode+",\"Gender\":"+s.sex+",\"Username\":"+s.user+",\"Password\":"+s.pwd+",\"Cineplex\":"+s.cineplex+"}}";
      obj = (JSONObject) parser.parse(s);
      try (FileWriter file = new FileWriter("staffData.txt")) {
			file.write(obj.toJSONString());
	}
       
    }
    
    public static boolean configUser(String user) throws IOException, FileNotFoundException, ParseException{
    //looks for the appropriate username and returns if existing, allowing access to that customer's details
        
        JSONParser parser = new JSONParser();
        JSONObject obj1 = (JSONObject) parser.parse(new FileReader("customerData.txt"));
        JSONObject obj2 = new JSONObject();
        boolean present = false;
        
        for ( int i = 0; i<obj1.size();i++){
            obj2 = (JSONObject) obj1.get(i);
            if (user.equals(obj2.get("Username"))){
                    present = true;
            }
        }
        
        //do something here
        
        return present;
    
    }
    
}

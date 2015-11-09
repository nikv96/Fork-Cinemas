/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;

import java.io.*;
import org.json.simple.JSONArray;
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
        JSONObject objFull = (JSONObject) parser.parse(new FileReader("staffData.txt"));
        JSONArray arrRow;
        boolean present = false;
        
        for ( int i = 0; i<objFull.size();i++){
            arrRow = (JSONArray) objFull.get(i);
            if (user.equals(arrRow.get(6)))
            {
                if (pwd.equals(arrRow.get(7)))
                    present = true;
            }
        }

        return present;
}
    
    public JSONObject addStaff(Staff s) throws IOException, FileNotFoundException, ParseException{
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
      obj.put(s.id,arr);
      try (FileWriter file = new FileWriter("staffData.txt"))
      {
		file.write(obj.toJSONString());
        }
      
      return obj;
       
    }
    
    public static boolean configUser(String user) throws IOException, FileNotFoundException, ParseException{
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
            }
        }
        
        return present;
    
    }
    
}

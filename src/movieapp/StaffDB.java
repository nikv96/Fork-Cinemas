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
        JSONObject objFull = (JSONObject) parser.parse(new FileReader("staffData.txt"));
        JSONObject objRow = new JSONObject();
        boolean present = false;
        
        for ( int i = 0; i<objFull.size();i++){
            objRow = (JSONObject) objFull.get(i);
            if (user.equals(objRow.get("Username")))
            {
                if (pwd.equals(objRow.get("Password")))
                    present = true;
            }
        }

        return present;
}
    
    public JSONObject addStaff(Staff s) throws IOException, FileNotFoundException, ParseException{
      //adds staff record to the .dat file
      //basic writing code
     JSONObject obj = new JSONObject();
      JSONParser parser = new JSONParser();
      String str = "{"+s.id+":{\"Name\":"+s.name+",\"Age\":"+s.age+",\"EmailID\":"+s.emailID+",\"Number\":"+s.phone+",\"Postal Code\":"+s.postalCode+",\"Gender\":"+s.gender+",\"Username\":"+s.username+",\"Password\":"+s.password+"\"Cineplex\":"+ s.cineplex +"}}";
      obj = (JSONObject) parser.parse(str);
      try (FileWriter file = new FileWriter("customerData.txt")) {
			file.write(obj.toJSONString());
	}
      return obj;
       
    }
    
    public static boolean configUser(String user) throws IOException, FileNotFoundException, ParseException{
    //looks for the appropriate username and returns if existing, allowing access to that customer's details
        
        JSONParser parser = new JSONParser();
        JSONObject objFull = (JSONObject) parser.parse(new FileReader("customerData.txt"));
        JSONObject objRow = new JSONObject();
        boolean present = false;
        
        for ( int i = 0; i<objFull.size();i++)
        {
            objRow = (JSONObject) objFull.get(i);
            if (user.equals(objRow.get("Username")))
            {
                    present = true;
            }
        }
        
        return present;
    
    }
    
}

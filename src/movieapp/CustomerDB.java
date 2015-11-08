/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author user
 */
public class CustomerDB {
    
    public static boolean checkUser(String user, String pwd) throws IOException, FileNotFoundException, ParseException
    {
        JSONParser parser = new JSONParser();
        JSONObject objFull = (JSONObject) parser.parse(new FileReader("customerData.txt"));
        JSONObject objRow = new JSONObject();
        boolean present = false;
        
        for ( int i = 0; i<objFull.size();i++){
            objRow = (JSONObject) objFull.get(i);
            if (user.equals(objRow.get("Username"))){
                if (pwd.equals(objRow.get("Password")))
                    present = true;
            }
        }
        
        return present;
    }
    
    public JSONObject addCustomer(Customer c) throws IOException, FileNotFoundException, ParseException{
      //basic writing code
      JSONObject obj = new JSONObject();
      JSONParser parser = new JSONParser();
      String s = "{"+c.id+":{\"Name\":"+c.name+",\"Age\":"+c.age+",\"EmailID\":"+c.emailID+",\"Number\":"+c.phone+",\"Postal Code\":"+c.postalCode+",\"Gender\":"+c.gender+",\"Username\":"+c.username+",\"Password\":"+c.password+"}}";
      obj = (JSONObject) parser.parse(s);
      try (FileWriter file = new FileWriter("customerData.txt"))
      {
		file.write(obj.toJSONString());
        }
      
      return obj;
    }
    
    
}

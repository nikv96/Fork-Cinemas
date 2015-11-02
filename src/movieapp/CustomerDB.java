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
    
    public boolean checkUser(String user, String pwd) throws IOException, FileNotFoundException, ParseException{
        JSONParser parser = new JSONParser();
        JSONObject obj1 = (JSONObject) parser.parse(new FileReader("customerData.txt"));
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
    
    public void addCustomer(int id, String name, String user, String pwd, String email, int age, long phno, long pcode, char sex) throws IOException, FileNotFoundException, ParseException{
      //basic writing code
      JSONObject obj = new JSONObject();
      JSONParser parser = new JSONParser();
      String s = "{"+id+":{\"Name\":"+name+",\"Age\":"+age+",\"EmailID\":"+email+",\"Number\":"+phno+",\"Postal Code\":"+pcode+",\"Gender\":"+sex+",\"Username\":"+user+",\"Password\":"+pwd+"}}";
      obj = (JSONObject) parser.parse(s);
      try (FileWriter file = new FileWriter("customerData.txt")) {
			file.write(obj.toJSONString());
	}
    }
    
    
}

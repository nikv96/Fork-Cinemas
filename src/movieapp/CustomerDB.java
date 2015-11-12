package movieapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Team Fork
 */
public class CustomerDB {
    
    public String getName(Customer c)
    {
        return c.name;
    }
    
    public String getPassword(Customer c)
    {
        return c.password;
    }
    
    public String getEmailID(Customer c)
    {
        return c.emailID;
    }
    
    public void setUsername(Customer c, String user)
    {
        c.username = user;
    }
    
    public void setPassword(Customer c, String pass)
    {
        c.password = pass;
    }
    
    public void setEmailID(Customer c, String email)
    {
        c.emailID = email;
    }
    
    public void setBooking(Customer c, int id)
    {
        c.booked[id] = true;
    }
    
    public void setAge(Customer c, int age)
    {
        c.age = age;
    }
    
    
    public static boolean checkUser(String user, String pwd) throws IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        JSONObject objFull = new JSONObject();
        File f = new File("customerData.txt");
        if(f.exists()) { 
            objFull = (JSONObject) parser.parse(new FileReader("customerData.txt"));
        }
        JSONArray arrRow;
        boolean present = false;
        
        for (int i = 0; i<objFull.size(); i++){
            arrRow = (JSONArray) objFull.get(Integer.toString(i));
            if (user.equals(arrRow.get(6).toString())){
                if (pwd.equals(arrRow.get(7).toString()))
                    present = true;
            }
        }
        return present;
    }
    
    public static JSONObject addCustomer(Customer c) throws IOException, FileNotFoundException, ParseException{
      //basic writing code
      JSONObject obj = new JSONObject();
      JSONArray arr = new JSONArray();
      arr.add(c.name); //0
      arr.add(c.age);//1
      arr.add(c.emailID);//2
      arr.add(c.phone);//3
      arr.add(c.postalCode);//4
      arr.add(c.gender);//5
      arr.add(c.username);//6
      arr.add(c.password);//7
      JSONArray childJsonArray = new JSONArray();
      for(int i=0;i<c.booked.length;i++){
          childJsonArray.add(c.booked[i]);
      }
      arr.add(childJsonArray);//8
      obj.put(c.id,arr);
      try (FileWriter file = new FileWriter("customerData.txt"))
      {
		file.write(obj.toJSONString());
        }
      
      return obj;
    }
    
    public static int configUser(String user) throws IOException, FileNotFoundException, ParseException{
    //looks for the appropriate username and returns if existing, allowing access to that customer's details
        
        JSONParser parser = new JSONParser();
        JSONObject objFull = new JSONObject();
      File f = new File("customerData.txt");
      if(f.exists()) { 
          objFull = (JSONObject) parser.parse(new FileReader("customerData.txt"));
      }
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
    
    /*
     * This method reads the entire customer database and stores the values in objects
    */
    
    public static void getCustomersArray (Customer[] customer) throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();
        int id = 0;
        JSONObject obj1 = (JSONObject) parser.parse(new FileReader("customerData.txt"));
        JSONArray arr,childArray;
        boolean book[];
        while (obj1.get(Integer.toString(id))!=null){
            arr = (JSONArray) obj1.get(Integer.toString(id));
            
            //booked
            childArray = new JSONArray();
            childArray = (JSONArray)arr.get(8);
            book = new boolean[childArray.size()];
            for(int i=0;i<childArray.size();i++)
                book[i] = (boolean) childArray.get(i);
            customer[id] = new Customer((arr.get(0)).toString(),
                    java.lang.Math.toIntExact((long) arr.get(1)),
                    arr.get(2).toString(),
                    (long) arr.get(3),
                    (long) arr.get(4),
                    (String) arr.get(5),
                    (String) arr.get(6), (String) arr.get(7),
                    book);
            id++;
        }
    }
    
}

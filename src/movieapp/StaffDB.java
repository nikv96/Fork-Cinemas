/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieapp;

import java.io.*;
/**
 *
 * @author user
 */
public class StaffDB {
    
    public static void createStaffDB() throws IOException
    {//creating random values for initial .dat file
        FileOutputStream foStream
            = new FileOutputStream("staffData.dat");
      BufferedOutputStream boStream
            = new BufferedOutputStream(foStream);
      ObjectOutputStream doStream;
              
       doStream = new ObjectOutputStream(boStream);

       doStream.writeUTF("StaffName");
       doStream.writeUTF("StaffUser");
       doStream.writeUTF("StaffPwd");
       doStream.writeUTF("email");
       doStream.writeUTF("phno");
       doStream.writeUTF("pcode");
       doStream.writeInt(32);
       doStream.writeChar('F');
       doStream.writeUTF("Golden Village");
       
       doStream.writeUTF("name");
       doStream.writeUTF("user");
       doStream.writeUTF("pwd");
       doStream.writeUTF("email");
       doStream.writeUTF("phno");
       doStream.writeUTF("pcode");
       doStream.writeInt(32);
       doStream.writeChar('F');
       doStream.writeUTF("Golden Village");
       
       
       doStream.writeUTF("name");
       doStream.writeUTF("user");
       doStream.writeUTF("pwd");
       doStream.writeUTF("email");
       doStream.writeUTF("phno");
       doStream.writeUTF("pcode");
       doStream.writeInt(32);
       doStream.writeChar('F');
       doStream.writeUTF("Golden Village");
       
       
       
       
       doStream.close();
    }
    
    public static boolean readStaff(String user, String pwd) throws IOException, FileNotFoundException{
    //returns boolean to check for authentication
        FileInputStream fiStream
            = new FileInputStream("staffData.dat");
      BufferedInputStream biStream
            = new BufferedInputStream(fiStream);
        try (ObjectInputStream diStream = new ObjectInputStream(biStream)) {
            String username = diStream.readLine();
            while(username != null){
            username = diStream.readLine();
            String password;
            if(username.equals(user))
            {
                password = diStream.readUTF();
                if(password.equals(pwd))
                    return true;
            } } }
      return false;
}
    
    public static void addStaff(String name, String user, String pwd, String email, int age, String phno, String pcode, char sex, String cineplex) throws IOException, FileNotFoundException{
      //adds staff record to the .dat file
      FileOutputStream foStream
            = new FileOutputStream("staffData.dat");
      BufferedOutputStream boStream
            = new BufferedOutputStream(foStream);
      ObjectOutputStream doStream;
              
       doStream = new ObjectOutputStream(boStream);

       doStream.writeUTF(name);
       doStream.writeUTF(user);
       doStream.writeUTF(pwd);
       doStream.writeUTF(email);
       doStream.writeUTF(phno);
       doStream.writeUTF(pcode);
       doStream.writeInt(age);
       doStream.writeChar(sex);
       doStream.writeUTF(cineplex);
       
       doStream.close();

        
    }
    
public static boolean configUser(String user) throws IOException, FileNotFoundException{
    //looks for the appropriate username and returns if existing, allowing access to that customer's details
        FileInputStream fiStream
            = new FileInputStream("customerData.dat");
      BufferedInputStream biStream
            = new BufferedInputStream(fiStream);
        try (ObjectInputStream diStream = new ObjectInputStream(biStream)) {
            String username = diStream.readLine();
            while(username != null){
            username = diStream.readLine();
     
            if(username.equals(user))
            {
               return true;
            } } }
      return false;
}
    
    
    
}

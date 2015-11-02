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
    
    public boolean readStaff(String user, String pwd) throws IOException, FileNotFoundException{
    //basic reading code
        FileInputStream fiStream
            = new FileInputStream("staffData.dat");
      BufferedInputStream biStream
            = new BufferedInputStream(fiStream);
      ObjectInputStream diStream
            = new ObjectInputStream(biStream);
      
      diStream.close();

    
return false;
}
    
    public void addStaff(String name, String user, String pwd, String email, int age, String phno, String pcode, char sex) throws IOException, FileNotFoundException{
      //basic writing code
      FileOutputStream foStream
            = new FileOutputStream("staffData.dat");
      BufferedOutputStream boStream
            = new BufferedOutputStream(foStream);
      ObjectOutputStream doStream;
              
       doStream = new ObjectOutputStream(boStream);

       doStream.writeUTF(name);
       doStream.writeInt(age);
       doStream.writeChar(sex); 
       
       doStream.close();

        
    }
    
    
    
}

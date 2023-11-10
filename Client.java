package Beta;

/* 
 * File: Client
 * Copy: Copyright (c) 2023 Samuel W. Messer
 * BlazerID: swmesser
 * Vers: 1.0.0 Nov 10, 2023 SWM - Original Coding
 * Desc: Driver for testing concepts
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client {
    private Socket connection = null;
    private DataOutputStream output = null;
    
    private DataInputStream input = null;

    public Client(String address, int port) {
        try {
            //Create connection
            connection = new Socket(address, port);
            System.out.println("Connected!");
            
            //Creating input stream to catch from server
            input = new DataInputStream( connection.getInputStream() );
            
            //Creating output stream to socket connection
            output = new DataOutputStream( connection.getOutputStream() );
        } catch (IOException ex) {
            System.out.println( ex.toString() );
        }
        
        Scanner scanner = new Scanner( System.in );
        //For reading the CMD into
        String line = "";
        
        while ( !line.equals("Over")){
            try{
                line = scanner.nextLine();
                output.writeUTF(line);
            } catch ( IOException ex ){
                System.out.println( ex.toString() );
            }
        }
        
        
        //Receiving input from server connection
        String msg = "";
        try{
            msg = input.readUTF();
            System.out.println("Server: " + msg);
        } catch (IOException ex ){
            System.out.println( ex.toString() );
        }
        
        try{
           if (msg.equals("Over")){
               input.close();
               output.close();
               connection.close();
           } 
        } catch (IOException ex ){
            System.out.println( ex.toString() );
        }
    }
    
    public static void main(String[] args ){
        Client client = new Client("127.0.0.1", 2000);
    }
}

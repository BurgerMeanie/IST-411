/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4team1;

import com.sun.net.httpserver.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/* 
Project: Lab 4 Group Work
Purpose Details: Get and Post
Course: IST 411
Author: Team 1
Date Developed: 2/10/2023
Last Date Changed:
Revision: 1
*/

public class IndexHandler implements HttpHandler {
    // Overrides the handle method in HttpHandler to handle HTTP requests
    @Override
    
    public void handle(HttpExchange exchange)throws IOException{
        
        // Get the request method
        String requestMethod = exchange.getRequestMethod();
        System.out.println(requestMethod);
        System.out.println(exchange.getRemoteAddress());
        
        // Check if the request method is POST
        if(requestMethod.equalsIgnoreCase("POST")){
            
            // Get and convert the request body to a string
            InputStream in = exchange.getRequestBody();
            String input = postInput(in);
            
            // Try to add the entry to the diary file
            if(!addEntryToDiary(input)){
                // If there is an error, send a 500 Internal Server Error response
                String response = "ERROR - Unable to append to diary file";
                exchange.sendResponseHeaders(500, response.length());
                OutputStream out = exchange.getResponseBody();
                out.write(response.toString().getBytes());
                out.close();
                return;
            }
            // If the entry is added successfully, send a 200 OK response
            String response = "OK";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream out = exchange.getResponseBody();
            out.write(response.toString().getBytes());
            out.close();
        } 
            // Check if the request method is GET
            else if (requestMethod.equalsIgnoreCase("GET")){
            String response = getDiaryEntries();
            exchange.sendResponseHeaders(200, response.length());
            OutputStream out = exchange.getResponseBody();
            out.write(response.toString().getBytes());
            out.close();
        } 
            // If the request method is not GET or POST
            else {
            System.out.println("NOT GET/POST " + requestMethod);
            String response = "ERROR - not a GET/POST request";
            // Send a 400 Bad Request response
            exchange.sendResponseHeaders(400, response.length());
            OutputStream out = exchange.getResponseBody();
            out.write(response.toString().getBytes());
            out.close();
        }
    }
    
    private String getDiaryEntries() {
        try{
            // Open the diary file
            BufferedReader br = new BufferedReader(new FileReader("Diary.txt"));
            String inputLine;
            StringBuilder response = new StringBuilder();
            // Read the diary file line by line
            while((inputLine = br.readLine()) != null){
                response.append(inputLine);
                response.append("\n");
            }
            br.close();
            // Return the diary entries as a string
            return response.toString();
        }catch(IOException ex){
            // If there is an error, print the stack trace
            ex.printStackTrace();
        }
        return "";
    }
    
    private String postInput(InputStream in){
        if(in != null){
            // Check if the input stream is not empty
            
            try{
                // Create a BufferedReader from the input stream
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String inputLine;
                StringBuilder response = new StringBuilder();
                
                // Read each line from the input stream
                while((inputLine = br.readLine()) != null){
                    response.append(inputLine);
                }
                br.close();
                // Return the contents of the input stream as a string
                return response.toString();
            } catch(IOException ex){
                ex.printStackTrace();
            }
        } else {
            System.out.println("Request body is empty");
        }
        // Return an empty string if the input stream is empty or there is an error
        return "";
    }
    
    public static boolean addEntryToDiary(String entry){
        try{
            // Open a BufferedWriter for the file "Diary.txt" in append mode
            BufferedWriter out = new BufferedWriter(new FileWriter(""
                    + "Diary.txt", true));
            // Write the entry to the file and close the writer
            out.write(entry);
            out.write("\n");
            out.close();
            // Return true if the write was successful
            return true;
        } catch(IOException e){
            System.out.println("Error writingg to diary");
        }
        // Return false if there was an error writing to the file
        return false;
    }
}

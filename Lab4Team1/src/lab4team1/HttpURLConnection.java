/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4team1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

/* 
Project: Lab 4 Group Work
Purpose Details: Get and Post
Course: IST 411
Author: Team 1
Date Developed: 2/10/2023
Last Date Changed:
Revision: 1
*/

public class HttpURLConnection {
    
    public static void main(String[] args) throws Exception{
        
        // Loop that takes user input and sends it to the server
        while(true){
            // Prompt user for diary entry, scan for respone
            System.out.println("Type diary entry: ");
            Scanner scanner = new Scanner(System.in);
            String inputDiary = scanner.nextLine();
            
            HttpURLConnection http = new HttpURLConnection();
            // Send a POST request to the server with the user input
            http.sendPost(inputDiary);
            // Send a GET request to the server
            http.sendGet();
        }
    }
    
    private void sendPost(String inf_toSend) throws Exception{
        
        // String to be sent to the server
        String toSend = inf_toSend;
        // Address of the server
        String serverAddress = "http://localhost:8080";
        // URL object representing the server address
        URL url = new URL(serverAddress);
        
        try{
            
            // Open connection to the server and set the connection to allow output
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent", "NBClient");
            OutputStream toPost = connection.getOutputStream();
            
            //Create a new Printwriter and write string to output stream
            PrintWriter out = new PrintWriter(toPost, true);
            out.println(toSend);
            
            //Get response code from the server and print it
            int responseCode = connection.getResponseCode();
            System.out.println("Post Response Code: " + responseCode);
            
            //
            if(responseCode == 200){
                // If response is 200 then output response
                String response = getResponse(connection);
                System.out.println("Post Response Message: " + response.toString());
            } else {
                // Else output error message
                System.out.println("Bad Response Message: " + responseCode);
            }
        } catch(IOException ex){
            // Print the stack trace of the exception if one occurs
            ex.printStackTrace();
        }
    }
    
    private void sendGet() throws Exception{
        
    }
    
    private String getResponse(java.net.HttpURLConnection connection){
        try{
            // Create a BufferedReader from the input stream of the connection
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            
            // Read each line of the response until there are no more lines
            while((inputLine = br.readLine()) != null){
                response.append(inputLine);
                response.append("\n");
            }
            br.close();
            return response.toString();
        } catch(IOException ex){
            // Print the stack trace of the exception if one occurs
            ex.printStackTrace();
        }
        return "";
    }
}


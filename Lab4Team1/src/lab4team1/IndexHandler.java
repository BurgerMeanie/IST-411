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
    @Override
    public void handle(HttpExchange exchange)throws IOException{
        String requestMethod = exchange.getRequestMethod();
        System.out.println(requestMethod);
        System.out.println(exchange.getRemoteAddress());
        if(requestMethod.equalsIgnoreCase("POST")){
            InputStream in = exchange.getRequestBody();
            String input = postInput(in);
            if(!addEntryToDiary(input)){
                String response = "ERROR - Unable to append to diary file";
                exchange.sendResponseHeaders(500, response.length());
                OutputStream out = exchange.getResponseBody();
                out.write(response.toString().getBytes());
                out.close();
                return;
            }
            String response = "OK";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream out = exchange.getResponseBody();
            out.write(response.toString().getBytes());
            out.close();
        } else if (requestMethod.equalsIgnoreCase("GET")){
            String response = getDiaryEntries();
            exchange.sendResponseHeaders(200, response.length());
            OutputStream out = exchange.getResponseBody();
            out.write(response.toString().getBytes());
            out.close();
        } else {
            System.out.println("NOT GET/POST " + requestMethod);
            String response = "ERROR - not a GET/POST request";
            exchange.sendResponseHeaders(400, response.length());
            OutputStream out = exchange.getResponseBody();
            out.write(response.toString().getBytes());
            out.close();
        }
    }
    
    private String getDiaryEntries() {
        try{
            BufferedReader br = new BufferedReader(new FileReader("Diary.txt"));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while((inputLine = br.readLine()) != null){
                response.append(inputLine);
                response.append("\n");
            }
            br.close();
            return response.toString();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return "";
    }
    
    private String postInput(InputStream in){
        if(in != null){
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while((inputLine = br.readLine()) != null){
                    response.append(inputLine);
                }
                br.close();
                return response.toString();
            } catch(IOException ex){
                ex.printStackTrace();
            }
        } else {
            System.out.println("Request body is empty");
        }
        return "";
    }
    
    public static boolean addEntryToDiary(String entry){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter("Diary.txt", true));
            out.write(entry);
            out.write("\n");
            out.close();
            return true;
        } catch(IOException e){
            System.out.println("Error writingg to diary");
        }
        return false;
    }
}

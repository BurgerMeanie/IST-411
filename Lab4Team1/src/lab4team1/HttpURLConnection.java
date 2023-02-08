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
        while(true){
            System.out.println("Type diary entry: ");
            Scanner scanner = new Scanner(System.in);
            String inputDiary = scanner.nextLine();
            HttpURLConnection http = new HttpURLConnection();
            http.sendPost(inputDiary);
            http.sendGet();
        }
    }
    
    private void sendPost(String inf_toSend) throws Exception{
        String toSend = inf_toSend;
        String serverAddress = "http://localhost:8080";
        URL url = new URL(serverAddress);
        try{
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent", "NBClient");
            OutputStream toPost = connection.getOutputStream();
            PrintWriter out = new PrintWriter(toPost, true);
            out.println(toSend);
            int responseCode = connection.getResponseCode();
            System.out.println("Post Response Code: " + responseCode);
            if(responseCode == 200){
                String response = getResponse(connection);
                System.out.println("Post Response Message: " + response.toString());
            } else {
                System.out.println("Bad Response Message: " + responseCode);
            }
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    private void sendGet() throws Exception{
        
    }
    
    private String getResponse(java.net.HttpURLConnection connection){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while((inputLine = br.readLine()) != null){
                response.append(inputLine);
                response.append("\n");
            }
            br.close();
            return response.toString();
        } catch(IOException ex){
            ex.printStackTrace();
        }
        return "";
    }
}


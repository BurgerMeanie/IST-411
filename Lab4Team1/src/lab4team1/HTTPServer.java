/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4team1;

import com.sun.net.httpserver.HttpServer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;

/* 
Project: Lab 4 Group Work
Purpose Details: Get and Post
Course: IST 411
Author: Team 1
Date Developed: 2/10/2023
Last Date Changed:
Revision: 1
*/
public class HTTPServer {
    public static void main(String[] args) throws IOException{
        System.out.println("MyHTTPServer Started");
        initDiary();
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new IndexHandler());
        server.start();
        
    }
    
    private static void initDiary(){
        try{
           BufferedWriter writer = new BufferedWriter(new FileWriter("Diary.txt"));
           writer.write("");
           writer.close();
        } catch (IOException e){
            System.out.println("Unable to create the diary file");
        }
    }
}

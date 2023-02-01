
package noaa.web.services;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Project: Lab 3
Purpose Details: NOAA Web Services
Course: IST 411
Author: Team 1 (Kelly Bergamini, William Allwein, Hun Bae, Phillip Berry, 
    Allea McFarlane, Jason Fang)
Date Developed: 1/31/2023
Last Date Changed:
Revision: 1
*/


public class NOAAWebServices {

    
    public static void main(String[] args) {
       
        String accessToken = "ShJOLHmRudsXXUhmOFCRHbLEEFOLwVEd";
        Gson gson = new Gson();
        String response;
        HttpURLConnection connection = null;
        
        try{
        
            URL url = new URL("https://www.ncdc.noaa.gov/cdo-web/api/v2/datacategories?limit=41");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("token", accessToken);
                   
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            response = br.readLine();
            NoaaData noaa = gson.fromJson(response, NoaaData.class);
            
            System.out.println("Result Set");
            System.out.println("----------");
            System.out.printf("%-15s%s\n", "Offset", noaa.getMetaData().getResultSet().getOffset());
            System.out.printf("%-15s%s\n", "Count", noaa.getMetaData().getResultSet().getCount());
            System.out.printf("%-15s%s\n", "Limit", noaa.getMetaData().getResultSet().getLimit());
            System.out.println();
            
            int count = 1;
            for (Results res : noaa.getResults()){
                System.out.println("Result " + count++);
                System.out.println("----------");
                System.out.printf("%-15s%s\n", "Name", res.getName());
                System.out.printf("%-15s%s\n", "ID", res.getId());
                System.out.println();
            }
        } catch (IOException ex) {
            Logger.getLogger(NOAAWebServices.class.getName()).log(
                    Level.SEVERE, null, ex);
        } finally{
            connection.disconnect();
        }
    }
}
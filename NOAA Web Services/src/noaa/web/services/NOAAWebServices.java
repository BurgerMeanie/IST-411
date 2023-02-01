
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
            connection.setRequestMethod("GET");
            connection.setRequestProperty("token", accessToken);
            connection.connect();
                   
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            response = br.readLine();
            System.out.println("NOAA had the follwing to say: " + response);    //test line just to see what response has to say. Remove when done.
            //NoaaData noaa = gson.fromJson(response, NoaaData.class);// method that needs to be built in the NoaaData class.
            
        } catch (IOException ex) {
            Logger.getLogger(NOAAWebServices.class.getName()).log(
                    Level.SEVERE, null, ex);
        } finally{
            if (connection != null){
                connection.disconnect();
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cis2087finalproject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Jade
 */
public class AnimeQuoteGenerator {
    
    // Gson library object for parsing JSON.
    private Gson gson;
    
    public AnimeQuoteGenerator()
    {
        gson = new Gson();
    }
    
    public String getAnimeQuote(String getAnimeName)
    {
        
        AnimeListingInfo anime = new AnimeListingInfo();
        
        // Call the API and receive a JSON String.
        String url = "https://animechan.vercel.app/api/quotes/anime?title=" + getAnimeName;
        String json = callApi(url);
        
        // Uncomment if you would like to see the json returned by the API.
        //System.out.println("JSON = " + json);
        String animeQuoteText;
        
        if(json.startsWith("{"))
        {
            // Parse the JSON into an object.
            // We will only get one object back from the API call (instead of an array)
            // since we are only asking for one fact.
            Quote animeQuote = gson.fromJson(json, new TypeToken<Quote>(){}.getType());
            
            // Pull the text out of the object.
            animeQuoteText = animeQuote.getText();
        }
        else
        {
            // String probably contains an error message from the server.
            animeQuoteText = json;
        }
        
        // Uncomment to see the text.
        System.out.println(animeQuoteText);        
        return animeQuoteText;
    }
    
    public static String callApi(String rawUrl)
    {
        // Set up variables to call the URL and read the result.
        URL url;
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader reader = null;
        String jsonResult = "";

        try
        {
            // Create the URL with the address.. to the server.
            url = new URL(rawUrl);
            
            // Call the url and create a Buffered Reader on the result.
            inputStream = url.openStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
                
            // Keep reading lines while more still exist.
            // Append the result to a String.  Should use a StringBuilder if we
            // are expecting a lot of lines.
            while (line != null) {
                jsonResult += line;
                line = reader.readLine();
            }
        }
        catch (MalformedURLException malformedURLException) {
            // URL was bad.
            jsonResult = malformedURLException.getMessage();
        }
        catch (IOException ioException) {
            // An error occurred during the reading process.
            jsonResult = ioException.getMessage();
        }
        finally
        {
            // Close the reader and the underlying objects.
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException ioException) {
                jsonResult += ioException.getMessage();
            }
        }
        
        return jsonResult;
    }
}

package edu.escuelaing.arep.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * This class return all information of the connection with the heroku service
 *  @autor  Juan C. Posso
 *  @version 8/24/2021/2
 */
public class HttpHerokuTest {
    private static HashMap<URL, String> cache = new HashMap <URL, String> ();
    /**
     * This method return all information of the connection with the heroku service
     * this use a hashmap to handle previous queries made to the API
     * @param nUrl String url website heroku service
     * @return String with the content of the website
     * @throws IOException   handles exceptions thrown by the read buffer
     */
    public static String testHerokuConnection(String nUrl) throws IOException {
        String str= "";
        String inputLine = "None";
        URL obj = new URL(nUrl);
        if(!(cache.containsKey(obj))) {
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            BufferedReader in = new BufferedReader( new InputStreamReader( con.getInputStream() ) );
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append( inputLine );
            }
            str= response.toString();
            cache.put(obj,str);
        }else{
            str=cache.get(obj);
        }
        return str;
    }
}
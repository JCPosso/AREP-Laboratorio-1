package edu.escuelaing.arep.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * @author Juan c. Posso
 * @version 1.0
 */
public class HttpHerokuTest {
    private static HashMap<URL, String> cache = new HashMap <URL, String> ();
    /**
     * @param nUrl
     * @return
     * @throws IOException
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
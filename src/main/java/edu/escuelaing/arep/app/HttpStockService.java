package edu.escuelaing.arep.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public abstract class HttpStockService {

    private static  final String USER_AGENT = "Mozilla/5.0";
    private HashMap<URL, String> cache = new HashMap <URL, String> ();
    /**
     * This method create a new connection HTTP  with the external API
     * @return String   response from the connection
     * @throws IOException if the buffer reader  fail
     */

    public  String TimeSeries() throws IOException {
        String str = "None";
        URL obj = new URL(getURL());
        if(!(cache.containsKey(obj))) {
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod( "GET" );
            con.setRequestProperty( "User-Agent", USER_AGENT );

            //The following invocation perform the connection implicitly before getting the code
            int responseCode = con.getResponseCode();
            System.out.println( "GET Response Code :: " + responseCode );

            if ( responseCode == HttpURLConnection.HTTP_OK ) { // success
                BufferedReader in = new BufferedReader( new InputStreamReader(
                        con.getInputStream() ) );
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append( inputLine );
                }
                in.close();

                // print result
                str = response.toString();
                System.out.println( str );
            } else {
                System.out.println( "GET request not worked" );
            }
            System.out.println( "GET DONE" );
            cache.put(obj,str);
        }else{
            return cache.get(obj);
        }
        return str;
    }

    /**
     * This method return the url from the current service
     * @return String url from the API
     * */
    public abstract String getURL();

    /**
     * This method set the provided identifier of the stock exchange in the API url
     * */
    public abstract void setStock(String stock);

    /**
     * This method set the the period of time in which you
     * want to make the query, either in days, weeks or per month.
     * */
    public abstract void setPeriod(String time);
}

package edu.escuelaing.arep.app.testClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class HttpStockService {

    private static  final String USER_AGENT = "Mozilla/5.0";
    private static  final String GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=fb&apikey=Q1QZFVJQ21K7C6XM";

    public  String TimeSeriesDaily() throws IOException {
        String str="None";
        URL obj = new URL(getURL());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            str= response.toString();
            System.out.println(str);
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return str;
    }

    public abstract String getURL();
    public abstract void setStock(String stock);
    public abstract String getStock();
    public abstract void setPeriod(String time);
}

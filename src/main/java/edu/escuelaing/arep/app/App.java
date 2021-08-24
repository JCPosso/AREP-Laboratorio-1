package edu.escuelaing.arep.app;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static spark.Spark.*;
/**
 * Hello world!
 *
 */
public class App 
{
    private static ArrayList<String> logs;
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location( "/public" );
        HashMap<String,String> cache = new HashMap<String,String>();
        get("/facadealpha", "application/json", (req, res) -> facadeAlpha(req,res,cache));
        get("/facadeiex", "application/json", (req, res) -> facadeIEX(req,res,cache));
    }

    /**
     * This method reads the default port as specified by the PORT variable in
     * the environment.
     *
     * Heroku provides the port automatically so you need this to run the
     * project on Heroku.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

    private static String  facadeAlpha(Request req,  Response res,HashMap cache){
        String stock = req.queryParams("st");
        String time = req.queryParams("se");
        String key = stock+":"+time;
        String response ="None";
        HttpStockService stockService = CurrentServiceInstance.getInstance().getServiceAlpha();
        if (!cache.containsKey( key )){
            if ( (stock!=null && stock!="") &&(time!=null && time!="")) {
                stockService.setStock(stock);
                stockService.setPeriod(time);
            }
            try {
                response=stockService.TimeSeries();
                cache.put(key,response);
            } catch (IOException e) {
                Logger.getLogger(App.class.getName()).log( Level.SEVERE,null,e);
            }
        }
        response = cache.get(key).toString();
        return response;
    }
    private static String  facadeIEX(Request req, Response res, HashMap cache){
        String stock = req.queryParams("st");
        String response ="None";
        HttpStockService stockService = CurrentServiceInstance.getInstance().getServiceIEX();
        if (!cache.containsKey( stock )) {
            if ( stock != null && stock != "" ) {
                stockService.setStock( stock );
            }
            try {
                response = stockService.TimeSeries();
                cache.put(stock,response);
            } catch (IOException e) {
                Logger.getLogger( App.class.getName() ).log( Level.SEVERE, null, e );
            }
        }
        response = cache.get(stock).toString();
        return response;
    }

    public static ArrayList<String> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<String> logs) {
        this.logs = logs;
    }
}

package edu.escuelaing.arep.app;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static spark.Spark.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location( "/public" );
        get("/facadealpha", "application/json", (req, res) -> facadeAlpha(req,res));
        get("/facadeiex", "application/json", (req, res) -> facadeIEX(req,res));
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

    private static String  facadeAlpha(Request req,  Response res){
        String stock = req.queryParams("st");
        String time = req.queryParams("se");
        String response ="None";
        HttpStockService stockService = CurrentServiceInstance.getInstance().getServiceAlpha();
        if ( (stock!=null && stock!="") && (time!=null && time!="")){
            stockService.setStock(stock);
            stockService.setPeriod(time);
        }
        try {
            response=stockService.TimeSeriesDaily();
        } catch (IOException e) {
            Logger.getLogger(App.class.getName()).log( Level.SEVERE,null,e);
        }
        return response;
    }
    private static String  facadeIEX(Request req,  Response res){
        String stock = req.queryParams("st");
        String response ="None";
        HttpStockService stockService = CurrentServiceInstance.getInstance().getServiceIEX();
        if (stock!=null && stock!=""){
            stockService.setStock(stock);
        }
        try {
            response=stockService.TimeSeriesDaily();
        } catch (IOException e) {
            Logger.getLogger(App.class.getName()).log( Level.SEVERE,null,e);
        }
        return response;
    }
}

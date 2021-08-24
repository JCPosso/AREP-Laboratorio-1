package edu.escuelaing.arep.app;
import spark.Request;
import spark.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static spark.Spark.*;

/**
 *Spark-web REST API Facade
 * @autor  Juan c. Posso
 * @version 8/24/2021/2
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
    /**
    * This method create a new URL to the AlphaHttpStockService, create a new connection http ,
     * and return response from the external API
     * @param req web client request
     * @param res web server response
    * @return an html response with the info from the web client
    */
    private static String  facadeAlpha(Request req,  Response res){
        String stock = req.queryParams("st");
        String time = req.queryParams("se");
        String key = stock+":"+time;
        String response ="None";
        HttpStockService stockService = CurrentServiceInstance.getInstance().getServiceAlpha();
        if ( (stock!=null && stock!="") &&(time!=null && time!="")) {
            stockService.setStock(stock);
            stockService.setPeriod(time);
        }
        try {
            response=stockService.TimeSeries();
        } catch (IOException e) {
            Logger.getLogger(App.class.getName()).log( Level.SEVERE,null,e);
        }
        return response;
    }
    /**
     * This method create a new URL to the IEXHttpStockService, create a new connection http ,
     * and return response from the external API
     * @param req web client request
     * @param res web server response
     * @return an html response with the info from the web client
     */
    private static String  facadeIEX(Request req, Response res){
        String stock = req.queryParams("st");
        String response ="None";
        HttpStockService stockService = CurrentServiceInstance.getInstance().getServiceIEX();
        if ( stock != null && stock != "" ) {
                stockService.setStock( stock );
            }
        try {
                response = stockService.TimeSeries();
        } catch (IOException e) {
                Logger.getLogger( App.class.getName() ).log( Level.SEVERE, null, e );
        }
        return response;
    }
}

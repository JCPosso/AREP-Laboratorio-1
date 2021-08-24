package edu.escuelaing.arep.app;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * @author Juan C. Posso
 * @version 1.0
 */
public class ClientTest {

    /**
     * @throws IOException
     * @throws JSONException
     */
    @Test
    public void TestAlphaFacadeAndAlphaHerokuAreEquals() throws IOException, JSONException {
        String Response="";
        String url="https://heroku-app-arep.herokuapp.com/facadealpha?st=IBM&se=TIME_SERIES_DAILY";
        String HerokuResponse="";
        //connection facade ALPHA
        HttpStockService stockService = CurrentServiceInstance.getInstance().getServiceAlpha();
        stockService.setStock( "IBM" );
        Response = stockService.TimeSeries();
        //connection HEROKU
        HerokuResponse = HttpHerokuTest.testHerokuConnection(url);
        JSONAssert.assertEquals(Response,HerokuResponse.toString(),true);
    }

    /**
     * @throws IOException
     * @throws JSONException
     */
    @Test
    public void AlphaTWTRisDifferentToAlphaIBM() throws IOException, JSONException{
        String Response="";
        String url="https://heroku-app-arep.herokuapp.com/facadealpha?st=TWTR&se=TIME_SERIES_DAILY";
        String HerokuResponse="";
        //connection facade ALPHA
        HttpStockService stockService = CurrentServiceInstance.getInstance().getServiceAlpha();
        Response = stockService.TimeSeries();
        stockService.setStock( "IBM" );
        //connection HEROKU
        HerokuResponse = HttpHerokuTest.testHerokuConnection(url);
        JSONAssert.assertNotEquals(Response,HerokuResponse.toString(),true);
    }

    /**
     * @throws IOException
     * @throws JSONException
     */
    @Test
    public void TestIEXFacadeAndIEXHerokuAreEquals() throws IOException, JSONException{
        String Response="";
        String site="https://cloud.iexapis.com/stable/stock/aapl/quote?token=pk_8a6cc2e8c79a4d01a8e938fb171f1d9c";
        String HerokuResponse="";
        //connection facade IEX
        HttpStockService stockService = CurrentServiceInstance.getInstance().getServiceIEX();
        Response = stockService.TimeSeries();
        //connection HEROKU IEX
        HerokuResponse = HttpHerokuTest.testHerokuConnection(site);
        assertTrue(Response.contains("AAPL"));
        assertTrue(HerokuResponse.toString().contains("AAPL"));
    }
}
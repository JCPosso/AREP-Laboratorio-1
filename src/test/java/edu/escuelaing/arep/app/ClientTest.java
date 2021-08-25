package edu.escuelaing.arep.app;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * This class tests different connections made to the web service deploy in heroku
 *  @autor  Juan C. Posso
 *  @version 8/24/2021/2
 */
public class ClientTest {

    /**
     * This class tests that the results obtained from the Alpha facade
     * are similar to those obtained from the application displayed in heroku
     * @throws IOException   handles exceptions thrown by the read buffer
     * @throws JSONException handles the exceptions generated with the tests made with the json
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
     * This test checks that when connecting to different
     * types of stock, the same response is not obtained
     * @throws IOException   handles exceptions thrown by the read buffer
     * @throws JSONException handles the exceptions generated with the tests made with the json
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
     * Here it is verified that the requests made to the IEX Cloud services generate
     * the same response both in the front client and in the heroku
     * @throws IOException   handles exceptions thrown by the read buffer
     * @throws JSONException handles the exceptions generated with the tests made with the json
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
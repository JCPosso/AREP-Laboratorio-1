package edu.escuelaing.arep.app.testClient;

public class IEXHttpStockService extends HttpStockService {
    String stock="aapl";
    @Override
    public String getURL() {
        return "https://heroku-app-arep.herokuapp.com/facadeiex?&st="+stock;
    }
    @Override
    public void setStock(String stock) {
    }
    @Override
    public String getStock() {
        return null;
    }

    @Override
    public void setPeriod(String time) {

    }
}

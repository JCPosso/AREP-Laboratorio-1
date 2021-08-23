package edu.escuelaing.arep.app.testClient;

public class AlphaHttpStockService extends HttpStockService {
    String stock = "fb";
    String fun = "TIME_SERIES_DAILY";
    @Override
    public String getURL() {
        return "https://heroku-app-arep.herokuapp.com/facadealpha?se="+fun+"&st="+stock;
    }

    @Override
    public void setStock(String stock) {
        this.stock = stock;
    }

    @Override
    public String getStock() {
        return stock;
    }

    @Override
    public void setPeriod(String time) {

    }

}

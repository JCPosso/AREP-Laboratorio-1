package edu.escuelaing.arep.app;

public class AlphaHttpStockService extends HttpStockService{
    String stock = "fb";
    String fun = "TIME_SERIES_DAILY";
    @Override
    public String getURL() {
        return "https://www.alphavantage.co/query?function="+fun+"&symbol="+stock+"&apikey=Q1QZFVJQ21K7C6XM";
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

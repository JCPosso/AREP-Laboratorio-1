package edu.escuelaing.arep.app;
/**
 *Alpha Http Stock Service has main information to create a connection with the External AplhaVantage API
 * @autor  Juan C. Posso
 * @version 8/24/2021/2
 *  */
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
    public void setPeriod(String time) {
        this.fun= time;
    }

}

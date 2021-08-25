package edu.escuelaing.arep.app;
/**
 * Create the necessary services to query related api services
 *  @autor  Juan C. Posso
 *  @version 8/24/2021/2
 * */
public class CurrentServiceInstance {
    private static  CurrentServiceInstance _instance  = new CurrentServiceInstance();
    private HttpStockService serviceAlpha;
    private HttpStockService serviceIEX;

    /**
     * Class constructor,
     * it is in charge of initializing the services
     * */
    private CurrentServiceInstance(){
        serviceAlpha = new AlphaHttpStockService();
        serviceIEX = new IEXHttpStockService();
    }

    /**
     * Return Current instance
     * @return  current instance
     * */
    public static CurrentServiceInstance getInstance(){
        return _instance;
    }

    /**
     * Return AlphaVantage service
     * @return seriviceAlpha Object
     * */
    public HttpStockService getServiceAlpha() {
        return serviceAlpha;
    }

    /**
     *Return IEX service
     * @return IEX service object
     * */
    public HttpStockService getServiceIEX() {
        return serviceIEX;
    }
}

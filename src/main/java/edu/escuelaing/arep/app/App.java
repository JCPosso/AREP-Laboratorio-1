package edu.escuelaing.arep.app;
import static spark.Spark.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello Heroku");
    }
}

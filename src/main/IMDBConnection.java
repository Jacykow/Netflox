package main;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Products.Movie;
import main.Products.Product;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class IMDBConnection {

    private String key;
    private int calls;

    public IMDBConnection(String key){
        this.key = key;
        calls = 0;
    }

    public Product getRandomProduct() throws IOException {
        while (true){
            String id = getRandomID();
            try {
                Product p = getProductFromObject(getObjectFromUrl(getUrlFromId(id)));
                reportCallAmount();
                return p;
            } catch (NoSuchFieldException ignored) {
                System.err.println("missed at call " + calls + "\tid: " + id);
            }
        }
    }

    public Product getProductFromTitle(String title) throws IOException, NoSuchFieldException {
        return getProductFromObject(getObjectFromUrl(getUrlFromTitle(title)));
    }

    public void reportCallAmount(){
        System.out.println("Total calls: " + calls);
    }



    private String getRandomID(){
        return "tt" + (Simulation.random.nextInt(5000000) + 1000000);
    }

    private String getUrlFromId(String id){
        return "http://www.omdbapi.com/?i=" + id + "&apikey=" + key;
    }

    public String getUrlFromTitle(String title){
        return "http://www.omdbapi.com/?t=" + title + "&apikey=" + key;
    }

    private JsonObject getObjectFromUrl(String url) throws IOException {
        if(calls > 30){
            throw new IOException("Too many calls to the API! Don't exhaust my key please.");
        }
        URLConnection request = new URL(url).openConnection();

        request.setConnectTimeout(2000);
        request.setReadTimeout(2000);
        request.connect();
        calls++;

        return new JsonParser().parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonObject();
    }

    private Product getProductFromObject(JsonObject object) throws NoSuchFieldException {
        if(object.get("Response").getAsString().equals("False")){
            throw new NoSuchFieldException(object.get("Error").getAsString());
        }

        return new Movie(object);
    }
}
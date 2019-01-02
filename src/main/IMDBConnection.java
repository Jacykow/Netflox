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
            try {
                Product p = getProductFromObject(getObjectFromUrl(getUrlFromId(getRandomKey())));
                reportCallAmount();
                return p;
            } catch (NoSuchFieldException ignored) {}
        }
    }

    public Product getProductFromTitle(String title) throws IOException, NoSuchFieldException {
        return getProductFromObject(getObjectFromUrl(getUrlFromTitle(title)));
    }

    public void reportCallAmount(){
        System.err.println("Total calls: " + calls);
    }



    private String getRandomKey(){
        return "tt" + Simulation.random.nextInt(6000000);
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

        Movie m = new Movie();
        m.setTitle(object.get("Title").getAsString());
        return m;
    }
}
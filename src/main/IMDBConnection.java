package main;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Misc.RetryWithStringArgException;
import main.Products.LiveStream;
import main.Products.Movie;
import main.Products.Product;
import main.Products.Series.Series;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class IMDBConnection {

    public final static String STUB_MESSAGE = "Probably a stub.";

    private String key;
    private int calls;

    public IMDBConnection(String key){
        this.key = key;
        calls = 0;
    }

    public Product getRandomProduct() throws IOException {
        String id = getRandomID();
        while (true){
            try {
                Product p = getProductFromObject(getObjectFromUrl(getUrlFromId(id)));
                System.out.println("Found at call\t" + calls + "\tid: " + id);
                return p;
            } catch (NoSuchFieldException ignored) {
                System.err.println("Missed at call\t" + calls + "\tid: " + id);
            } catch (RetryWithStringArgException e) {
                System.err.println("Retry at call\t" + calls + "\tid: " + id);
                id = e.getString();
                continue;
            }
            id = getRandomID();
        }
    }

    public Product getProductFromTitle(String title) throws IOException, NoSuchFieldException {
        try {
            return getProductFromObject(getObjectFromUrl(getUrlFromTitle(title)));
        } catch (RetryWithStringArgException e) {
            throw new NoSuchFieldException(e.getMessage());
        }
    }



    private String getRandomID(){
        return "tt" + (Simulation.getRandom().nextInt(5000000) + 1000000);
    }

    private String getUrlFromId(String id){
        return "http://www.omdbapi.com/?i=" + id + "&plot=full&apikey=" + key;
    }

    public String getUrlFromTitle(String title){
        return "http://www.omdbapi.com/?t=" + title.replace(" ","%20") + "&plot=full&apikey=" + key;
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

    private Product getProductFromObject(JsonObject json) throws NoSuchFieldException, RetryWithStringArgException {
        if(!json.get("Response").getAsBoolean()){
            throw new NoSuchFieldException(json.get("Error").getAsString());
        }
        String type = json.get("Type").getAsString();
        if(type.equals("episode")){
            String seriesId = json.get("seriesID").getAsString();
            if(seriesId.equals("N/A")){
                throw new NoSuchFieldException(STUB_MESSAGE + " No series ID.");
            }
            else {
                throw new RetryWithStringArgException(seriesId);
            }
        }
        switch (type) {
            case "N/A":
                throw new NoSuchFieldException(STUB_MESSAGE + " No type.");
            case "series":
                return new Series(json);
            case "movie":
                if (Simulation.getRandom().nextFloat() < 0.33f) {
                    return new Movie(json);
                } else if (Simulation.getRandom().nextFloat() < 0.5f){
                    return new LiveStream(json);
                } else {
                    return new Series(json);
                }
            default:
                throw new NoSuchFieldException("Wrong product type: " + type);
        }
    }
}
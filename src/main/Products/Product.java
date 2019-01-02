package main.Products;

import com.google.gson.JsonObject;
import main.Entities.Distributor;

import java.awt.*;
import java.time.Duration;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static main.IMDBConnection.STUB_MESSAGE;

public abstract class Product implements IWatchable{

    private float userScore;
    private String country;
    private Distributor distributor;
    protected Duration duration;
    private String premierDate;
    private String description;
    private Image image;
    private String title;
    private int value;

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern( "yyyy-MM-dd" )
                    .withLocale( Locale.getDefault() )
                    .withZone( ZoneId.systemDefault() );

    public String getLongDescription(){
        String d = getDescription();
        d+="\n";
        d+="PremierDate: " + premierDate;
        d+="\n";
        return d;
    }

    public Product(JsonObject json) throws NoSuchFieldException {
        if(setTitle(json) == 0){
            throw new NoSuchFieldException(STUB_MESSAGE);
        }

        int a=0;
        a += setPremierDate(json);
        a += 2 * setDescription(json);
        if((float)a / 3 < 0.5f){
            throw new NoSuchFieldException(STUB_MESSAGE);
        }
    }


    private int setTitle(JsonObject json){
        setTitle(json.get("Title").getAsString());
        return isNotAvailable(getDescription()) ? 0 : 1;
    }

    private int setPremierDate(JsonObject json){
        setPremierDate(json.get("Released").getAsString());
        return isNotAvailable(getDescription()) ? 0 : 1;
    }

    private int setDescription(JsonObject json){
        setDescription(json.get("Plot").getAsString());
        return isNotAvailable(getDescription()) ? 0 : 1;
    }

    private boolean isNotAvailable(String s){
        return s==null || s.isEmpty() || s.equals("N/A");
    }



    public float getUserScore() {
        return userScore;
    }

    public void setUserScore(float userScore) {
        this.userScore = userScore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPremierDate() {
        return premierDate;
    }

    public void setPremierDate(String premierDate) {
        this.premierDate = premierDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

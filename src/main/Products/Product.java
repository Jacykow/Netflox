package main.Products;

import com.google.gson.JsonObject;
import main.Entities.Distributor;
import main.Simulation;
import main.gui.IDescribable;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Random;

public abstract class Product implements IWatchable, IDescribable {

    private float userScore;
    private String country;
    private Distributor distributor;
    private Duration duration;
    private Instant premierDate;
    private String description;
    private String imageUrl;
    private String title;
    private int value;

    static final DateTimeFormatter FMT = new DateTimeFormatterBuilder()
            .appendPattern("dd MM yyyy")
            .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
            .toFormatter()
            .withZone(ZoneId.of("Asia/Tokyo"));

    public String getLongDescription(){
        String d="";
        d+="PremierDate: " + FMT.format(getPremierDate());
        d+="\n";
        d+="Country: " + getCountry();
        d+="\n";
        try {
            d+="Distributor: " + getDistributor().getGUILabel();
        }catch (Exception e){
            d+="Distributor: " + "Self distributed";
        }
        d+="\n";
        d+="Duration: " + getDuration().toMinutes() + " minutes";
        d+="\n";
        d+="Value: " + getValue() + " PLN";
        d+="\n";
        d+="ImageUrl: " + getImageUrl();
        d+="\n";
        d+="User Score: " + getUserScore();
        d+="\n";
        d+=getDescription();
        return d;
    }

    public Product(JsonObject json) {
        views = new ArrayList<>();
        try {
            setUserScore(json.get("imdbRating").getAsFloat());
        } catch (Exception ignored){
            setUserScore(0f);
        }
        setCountry(json.get("Country").getAsString());
        ArrayList<Distributor> list =Simulation.getInstance().getVod().getDistributors();
        if(list.size() > 0){
            setDistributor(list.get(Simulation.getRandom().nextInt(list.size())));
        }
        setDuration(Duration.ofMinutes(Integer.valueOf(json.get("Runtime").getAsString()
                .replaceFirst("\\D.*",""))));
        setPremierDate(FMT.parse(json.get("Released").getAsString()
                .replaceFirst("[A-Z,a-z]+", "0" +
                        (Simulation.getRandom().nextInt(9)+1)), Instant::from));
        setDescription(json.get("Plot").getAsString());
        setImage(json.get("Poster").getAsString());
        setTitle(json.get("Title").getAsString());
        setValue(0);
    }

    public static boolean isNotAvailable(String s){
        return s==null || s.isEmpty() || s.equals("N/A");
    }

    public String getGUIDescription(){
        return getLongDescription();
    }

    public String getGUILabel(){
        return getTitle();
    }


    public ArrayList<Instant> getViews() {
        return views;
    }

    private ArrayList<Instant> views;

    public void Watch(){
        getViews().add(Simulation.getInstance().getSimTime());
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImage(String image) {
        this.imageUrl = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getPremierDate() {
        return premierDate;
    }

    public void setPremierDate(Instant premierDate) {
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

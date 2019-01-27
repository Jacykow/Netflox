package main.Products;

import com.google.gson.JsonObject;
import main.Simulation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

public class Movie extends Product{

    private ArrayList<String> actors;
    private ArrayList<String> trailers;
    private Duration reviewTime;
    private Promotion promotion;

    public Movie(JsonObject json) {
        super(json);
        setActors(new ArrayList<>(Arrays.asList(json.get("Actors").getAsString().split(","))));
        setReviewTime(getDuration().multipliedBy(2));
    }

    @Override
    public String getLongDescription() {
        return "A movie!\n" + super.getLongDescription();
    }

    @Override
    public int getValue() {
        try {
            return (int)(super.getValue() * getPromotion().getMultiplier());
        } catch (Exception e){
            return super.getValue();
        }
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getTrailers() {
        return trailers;
    }

    public void setTrailers(ArrayList<String> trailers) {
        this.trailers = trailers;
    }

    public Duration getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Duration reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}

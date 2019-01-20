package main.Products;

import com.google.gson.JsonObject;

import java.time.Duration;
import java.util.ArrayList;

public class Movie extends Product{

    private ArrayList<String> actors;
    private ArrayList<String> trailers;
    private Duration reviewTime;
    private Promotion promotion;

    public Movie(JsonObject json) {
        super(json);
    }

    @Override
    public String getLongDescription() {
        return "A movie!\n" + super.getLongDescription();
    }

    @Override
    public int getValue() {
        return (int)(super.getValue() * getPromotion().getMultiplier());
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

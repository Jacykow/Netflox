package main.Products;

import main.Entities.Distributor;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;

public abstract class Product implements IWatchable{

    private float userScore;
    private String country;
    private Distributor distributor;
    protected Duration duration;
    private Instant premierDate;
    private String description;
    private Image image;
    private String title;
    private int value;



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

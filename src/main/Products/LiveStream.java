package main.Products;

import com.google.gson.JsonObject;

import java.time.Instant;

public class LiveStream extends Product{

    private Instant airDate;
    private Promotion promotion;

    public LiveStream(JsonObject json) {
        super(json);
    }

    @Override
    public String getLongDescription() {
        return getDescription();
    }

    @Override
    public int getValue() {
        return (int)(super.getValue() * getPromotion().getMultiplier());
    }

    public Instant getAirDate() {
        return airDate;
    }

    public void setAirDate(Instant airDate) {
        this.airDate = airDate;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}

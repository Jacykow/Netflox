package java.Products;

import java.time.Instant;

public class LiveStream extends Product{

    private Instant airDate;
    private Promotion promotion;

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

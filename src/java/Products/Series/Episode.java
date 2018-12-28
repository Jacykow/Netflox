package java.Products.Series;

import java.Products.IWatchable;
import java.time.Duration;
import java.time.Instant;

public class Episode implements IWatchable {

    private Instant premierDate;
    private Duration duration;

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
}

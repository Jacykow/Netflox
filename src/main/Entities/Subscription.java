package main.Entities;

import java.io.Serializable;

public class Subscription implements Serializable {
    private int value;
    private String maxQuality;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMaxQuality() {
        return maxQuality;
    }

    public void setMaxQuality(String maxQuality) {
        this.maxQuality = maxQuality;
    }
}

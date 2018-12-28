package main.Entities;

public class Subscription {

    private Type type;
    private int value;
    private String maxQuality;



    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

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


    public enum Type{
        None,
        Basic,
        Family,
        Premium
    }
}

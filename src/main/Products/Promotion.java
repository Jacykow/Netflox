package main.Products;

import java.time.Instant;

public class Promotion {

    private Instant beginningDate;
    private Instant endDate;
    private float value;

    public float getMultiplier(){
        return 1f - getValue();
    }



    public Instant getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Instant beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}

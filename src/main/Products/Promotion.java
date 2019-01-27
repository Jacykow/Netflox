package main.Products;

import main.Simulation;

import java.time.Duration;
import java.time.Instant;

public class Promotion {

    private Instant beginningDate;
    private Instant endDate;
    private float value;

    public float getMultiplier(){
        return 1f - getValue();
    }



    public void init(float value){
        setValue(value);
        setBeginningDate(Simulation.getInstance().getSimTime());
        setEndDate(getBeginningDate().plus(Duration.ofDays(14)));
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

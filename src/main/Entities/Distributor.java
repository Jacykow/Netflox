package main.Entities;

public class Distributor {
    private int costPerView;
    private int costPerMonth;

    public int getCostPerView() {
        return costPerView;
    }

    public void setCostPerView(int costPerView) {
        this.costPerView = costPerView;
    }

    public int getCostPerMonth() {
        return costPerMonth;
    }

    public void setCostPerMonth(int costPerMonth) {
        this.costPerMonth = costPerMonth;
    }
}

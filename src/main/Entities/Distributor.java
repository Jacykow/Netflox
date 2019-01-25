package main.Entities;

import main.Simulation;
import main.gui.IDescribable;

public class Distributor implements IDescribable {
    private String name;
    private int costPerView;
    private int costPerMonth;

    public Distributor random(){
        Distributor d = new Distributor();
        StringBuilder name = new StringBuilder();
        for (int x=0;x<3;x++){
            name.append((char) (Simulation.getRandom().nextInt('Z' - 'A') + 'A'));
        }
        name.append(" Company");
        d.setName(name.toString());
        if(Simulation.getRandom().nextFloat() > 0.5f){
            setCostPerMonth(Simulation.getRandom().nextInt(900)+100);
        }else {
            setCostPerView(Simulation.getRandom().nextInt(40)+10);
        }
        return d;
    }

    @Override
    public String getGUILabel() {
        return getName();
    }

    @Override
    public String getGUIDescription() {
        return (getCostPerMonth() > 0)?("Cost per month: "+getCostPerMonth()+"\n"):
                (getCostPerView() > 0)?("Cost per view: "+getCostPerView()+"\n"):"No cost!";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

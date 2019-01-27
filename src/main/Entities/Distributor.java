package main.Entities;

import main.Products.Product;
import main.Simulation;
import main.gui.IDescribable;

import java.io.IOException;
import java.io.Serializable;

public class Distributor implements IDescribable, Serializable, Runnable {
    private String name;
    private int costPerView;
    private int costPerMonth;

    public static Distributor random(String name){
        if(name.equals("")){
            return random();
        }

        Distributor d = new Distributor();
        d.setName(name);
        d.setCostPerMonth(Simulation.getRandom().nextInt(900)+100);
        d.setCostPerView(Simulation.getRandom().nextInt(40)+10);
        return d;
    }

    public static Distributor random(){
        StringBuilder name = new StringBuilder();
        for (int x=0;x<3;x++){
            name.append((char) (Simulation.getRandom().nextInt('Z' - 'A') + 'A'));
        }
        name.append(" Company");
        return random(name.toString());
    }

    @Override
    public String getGUILabel() {
        return getName();
    }

    @Override
    public String getGUIDescription() {
        return "Cost per month: "+getCostPerMonth()+"\n"+"Cost per view: "+getCostPerView()+"\n";
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

    @Override
    public void run() {
        try {
            Thread.sleep(Simulation.getRandom().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (Simulation.running()){
            synchronized (Simulation.getInstance().getVod().getProducts()){
                try {
                    Product product = Simulation.getInstance().getImdbConnection().getProductFromTitle(
                            Simulation.getInstance().getFileData().GetRandomMovieTitle());
                    Simulation.getInstance().getVod().addProduct(product);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException ignored) {}
            }

            try {
                Thread.sleep(Simulation.getRandom().nextInt(10000)+2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

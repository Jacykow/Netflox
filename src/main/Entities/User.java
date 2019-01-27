package main.Entities;

import com.sun.javafx.binding.StringFormatter;
import com.sun.xml.internal.ws.util.StringUtils;
import javafx.util.StringConverter;
import main.Products.Product;
import main.Simulation;
import main.gui.IDescribable;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class User implements IDescribable, Runnable, Serializable {

    private Instant birthDate;
    private String email;
    private String creditCardNumber;
    private Subscription subscription;
    private String name;

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static User random(String name){
        if(name.equals("")){
            return random();
        }
        User u = new User();
        u.setName(name);
        u.setCreditCardNumber("1234 1234 1234 1234 1234 1234 1234");
        u.setEmail(name.replace(' ','.').toLowerCase()+"@netfloxmail.com");
        // TODO subscriptions
        //u.setSubscription();
        u.setBirthDate(Instant.now().minus(Duration.of((Simulation.getRandom().nextInt(70)+5)*365, ChronoUnit.DAYS)));
        return u;
    }

    public static User random(){
        return random(Simulation.getInstance().getFileData().GetRandomName());
    }

    @Override
    public String getGUILabel() {
        return getName();
    }

    @Override
    public String getGUIDescription() {
        return getEmail();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(Simulation.getRandom().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (Simulation.running()){
            int k;
            synchronized (Simulation.getInstance().getVod().getProducts()){
                Product product = Simulation.getInstance().getVod().getProducts().get(Simulation.getRandom()
                        .nextInt(Simulation.getInstance().getVod().getProducts().size()));
                product.Watch();
                System.out.println(getGUILabel() + " is watching " + product.getGUILabel());
                k = (int) product.getDuration().toMinutes();
            }
            try {
                Thread.sleep(k);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

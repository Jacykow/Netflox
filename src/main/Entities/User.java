package main.Entities;

import main.gui.IDescribable;

import java.time.Instant;

public class User implements IDescribable {

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

    @Override
    public String getGUILabel() {
        return getName();
    }

    @Override
    public String getGUIDescription() {
        return getEmail();
    }
}

package main.Entities;

import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Misc.IMDBConnection;
import main.Misc.FileData;
import main.Products.Product;
import main.Products.Promotion;
import main.Simulation;

import java.io.IOException;
import java.io.Serializable;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class VOD implements Serializable {

    private final ArrayList<Product> products = new ArrayList<>();
    private transient ObservableList<String> productLabels;

    private final ArrayList<User> users = new ArrayList<>();
    private transient ObservableList<String> userLabels;

    private final ArrayList<Distributor> distributors = new ArrayList<>();
    private transient ObservableList<String> distributorLabels;

    private ArrayList<Subscription> subscriptions;
    private ArrayList<Promotion> promotions;

    private int currentMonth;
    private ArrayList<Integer> monthlyBalance;

    public void changeBalance(int difference){
        if(Simulation.getInstance().getSimTime().get(ChronoField.MONTH_OF_YEAR) != currentMonth){
            checkMonthlyBalance();
            getMonthlyBalance().add(0);
        }
        int i = getMonthlyBalance().size()-1;
        getMonthlyBalance().set(i, getMonthlyBalance().get(i)+difference);
    }

    private void checkMonthlyBalance(){
        int i = getMonthlyBalance().size()-1;
        Integer b = getMonthlyBalance().get(i);
        synchronized (Simulation.getInstance().getVod().getUsers()){
            for (User u :
                    Simulation.getInstance().getVod().getUsers()) {
                b += u.getSubscription().getValue();
            }
        }
        synchronized (Simulation.getInstance().getVod().getProducts()){
            for (Product p :
                    Simulation.getInstance().getVod().getProducts()) {
                b -= p.getDistributor().getCostPerMonth();
            }
        }
        System.out.println("MONTHLY BALANCE: " + b);
        getMonthlyBalance().set(i,b);
        if(getMonthlyBalance().size() >= 3){
            if(getMonthlyBalance().get(i) < 0 || getMonthlyBalance().get(i-1) < 0 || getMonthlyBalance().get(i-2) < 0){
                Simulation.end();
            }
        }
    }

    public void addUser(User user){
        synchronized (users){
            System.out.println("Adding user: " + user.getGUILabel());
            new Thread(user).start();
            getUsers().add(user);
            Platform.runLater(() -> getUserLabels().add(user.getGUILabel()));
        }
    }

    public void addDistributor(Distributor distributor){
        synchronized (distributors){
            System.out.println("Adding distributor: " + distributor.getGUILabel());
            new Thread(distributor).start();
            getDistributors().add(distributor);
            Platform.runLater(() -> getDistributorLabels().add(distributor.getGUILabel()));
        }
    }

    public void addProduct(Product product){
        synchronized (products){
            System.out.println("Adding product: " + product.getGUILabel());
            getProducts().add(product);
            Platform.runLater(() -> getProductLabels().add(product.getGUILabel()));
        }
    }

    public VOD(){
        currentMonth = -1;
        setMonthlyBalance(new ArrayList<>());
        setProducts(new ArrayList<>());
        setDistributors(new ArrayList<>());
        setUsers(new ArrayList<>());
        afterSerializationRoutine();
    }

    public void afterSerializationRoutine(){
        setDistributorLabels(FXCollections.observableArrayList());
        getDistributorLabels().addAll(getDistributors().stream().map(Distributor::getGUILabel).collect(Collectors.toList()));
        setProductLabels(FXCollections.observableArrayList());
        getProductLabels().addAll(getProducts().stream().map(Product::getGUILabel).collect(Collectors.toList()));
        setUserLabels(FXCollections.observableArrayList());
        getUserLabels().addAll(getUsers().stream().map(User::getGUILabel).collect(Collectors.toList()));
    }

    public void addRandomProducts(int amount, IMDBConnection connection, FileData fileData) throws IOException {
        for(int x=0;x<amount;x++){
            try {
                addProduct(connection.getProductFromTitle(fileData.GetRandomMovieTitle()));
            } catch (NoSuchFieldException e) {
                x--;
            }
        }
    }

    public void addRandomProduct() throws IOException {
        addRandomProducts(1, Simulation.getInstance().getImdbConnection(), Simulation.getInstance().getFileData());
    }

    public void instantiateDefaults(){
        setSubscriptions(new ArrayList<>());
        setPromotions(new ArrayList<>());
        Promotion promotion = new Promotion();
        promotion.init(0.5f);
        getPromotions().add(promotion);
    }



    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        synchronized (this.products){
            products.clear();
            this.products.addAll(products);
        }
    }

    public ObservableList<String> getProductLabels() {
        return productLabels;
    }

    public void setProductLabels(ObservableList<String> productLabels) {
        this.productLabels = productLabels;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        synchronized (this.users){
            users.clear();
            this.users.addAll(users);
        }
    }

    public ObservableList<String> getUserLabels() {
        return userLabels;
    }

    public void setUserLabels(ObservableList<String> userLabels) {
        this.userLabels = userLabels;
    }

    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(ArrayList<Distributor> distributors) {
        synchronized (this.distributors){
            distributors.clear();
            this.distributors.addAll(distributors);
        }
    }

    public ObservableList<String> getDistributorLabels() {
        return distributorLabels;
    }

    public void setDistributorLabels(ObservableList<String> distributorLabels) {
        this.distributorLabels = distributorLabels;
    }

    public ArrayList<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(ArrayList<Promotion> promotions) {
        this.promotions = promotions;
    }

    public ArrayList<Integer> getMonthlyBalance() {
        return monthlyBalance;
    }

    public void setMonthlyBalance(ArrayList<Integer> monthlyBalance) {
        this.monthlyBalance = monthlyBalance;
    }
}

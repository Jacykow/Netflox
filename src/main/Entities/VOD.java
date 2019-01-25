package main.Entities;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.IMDBConnection;
import main.Misc.FileData;
import main.Products.Product;

import java.io.IOException;
import java.util.ArrayList;

public class VOD {

    private ArrayList<Product> products;
    private ObservableList<String> productLabels;

    private ArrayList<User> users;
    private ObservableList<String> userLabels;

    private ArrayList<Distributor> distributors;
    private ObservableList<String> distributorLabels;

    public void addUser(User user){
        getUsers().add(user);
        Platform.runLater(() -> getUserLabels().add(user.getGUILabel()));
    }

    public void addDistributor(Distributor distributor){
        getDistributors().add(distributor);
        Platform.runLater(() -> getDistributorLabels().add(distributor.getGUILabel()));
    }

    public void addProduct(Product product){
        getProducts().add(product);
        Platform.runLater(() -> getProductLabels().add(product.getGUILabel()));
    }

    public VOD(){
        setProducts(new ArrayList<>());
        setProductLabels(FXCollections.observableArrayList());
    }

    public void addRandomProducts(int amount, IMDBConnection connection, FileData fileData) throws IOException {
        for(int x=0;x<amount;x++){
            try {
                addProduct(connection.getProductFromTitle(fileData.GetRandomMovieTitle()));
            } catch (NoSuchFieldException e) {
                //System.err.println(e.getMessage());
                x--;
            }
        }
    }



    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
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
        this.users = users;
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
        this.distributors = distributors;
    }

    public ObservableList<String> getDistributorLabels() {
        return distributorLabels;
    }

    public void setDistributorLabels(ObservableList<String> distributorLabels) {
        this.distributorLabels = distributorLabels;
    }
}

package main.Entities;

import main.IMDBConnection;
import main.Misc.FileData;
import main.Products.Product;

import java.io.IOException;
import java.util.ArrayList;

public class VOD {

    private ArrayList<Product> products;

    public VOD(){
        products = new ArrayList<>();
    }

    public void addRandomProducts(int amount, IMDBConnection connection, FileData fileData) throws IOException {
        for(int x=0;x<amount;x++){
            try {
                products.add(connection.getProductFromTitle(fileData.GetRandomMovieTitle()));
            } catch (NoSuchFieldException e) {
                System.err.println(e.getMessage());
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
}

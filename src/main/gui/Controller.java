package main.gui;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import main.Simulation;
import main.Products.Product;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    public Button startSimButton;
    public SimClock simDurationClock;
    public ListView<String> products;
    public TitledDescription selectedProduct;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void startSim(ActionEvent actionEvent) {
        startSimButton.setText("Restart");
        Simulation.start();

        /*
        products.setItems(FXCollections.observableArrayList(Simulation.getInstance().getVod().getProducts().
                stream().map(Product::getTitle).collect(Collectors.toList())));
        products.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Optional<Product> productOptional = Simulation.getInstance().getVod().getProducts().stream().
                            filter(product -> product.getTitle().equals(newValue)).findFirst();
                    productOptional.ifPresent(product -> selectedProduct.setContent(product));
                }
        );
        */
        link(products, Simulation.getInstance().getVod().getProducts(), Simulation.getInstance().getVod().getProductLabels(), selectedProduct);
    }

    private <T extends IDescribable> void link(ListView<String> listView, List<T> items, ObservableList<String> labels, TitledDescription selected){
        listView.setItems(labels);
        listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Optional<T> productOptional = items.stream().
                            filter(item -> item.getGUILabel().equals(newValue)).findFirst();
                    productOptional.ifPresent(selected::setContent);
                }
        );
    }
}

package main;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import main.gui.TitledDescription;
import main.Products.Product;
import main.gui.SimClock;

import java.net.URL;
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

        products.setItems(FXCollections.observableArrayList(Simulation.getInstance().getVod().getProducts().
                stream().map(Product::getTitle).collect(Collectors.toList())));
        products.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectedProduct.setTitle(newValue)
        );
    }
}

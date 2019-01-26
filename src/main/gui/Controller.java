package main.gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.Entities.Distributor;
import main.Entities.User;
import main.Simulation;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Button startSimButton;
    public SimClock simDurationClock;
    public ListView<String> products;
    public TitledDescription selectedProduct;
    public ListView<String> users;
    public TitledDescription selectedUser;
    public ListView<String> distributors;
    public TextField productTitleTextField;
    public TextField userTitleTextField;
    public TextField distributorTitleTextField;
    public TitledDescription selectedDistributor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void startSim(ActionEvent actionEvent) {
        startSimButton.setText("Restart");
        Simulation.start();

        link(products, Simulation.getInstance().getVod().getProducts(), Simulation.getInstance().getVod().getProductLabels(), selectedProduct);
        link(users, Simulation.getInstance().getVod().getUsers(),Simulation.getInstance().getVod().getUserLabels(), selectedUser);
        link(distributors, Simulation.getInstance().getVod().getDistributors(),Simulation.getInstance().getVod().getDistributorLabels(), selectedDistributor);
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

    public void showError(String message){
        System.err.println(message);
    }

    public void manualAddProduct(ActionEvent actionEvent) {
        try {
            if(productTitleTextField.getText().isEmpty()){
                Simulation.getInstance().getVod().addRandomProducts(1, Simulation.getInstance().getImdbConnection(), Simulation.getInstance().getFileData());
            } else {
                Simulation.getInstance().getVod().addProduct(Simulation.getInstance().getImdbConnection().getProductFromTitle(productTitleTextField.getText()));
                productTitleTextField.clear();
            }
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    public void manualAddUser(ActionEvent actionEvent) {
        Simulation.getInstance().getVod().addUser(User.random(userTitleTextField.getText()));
        userTitleTextField.clear();
    }

    public void manualAddDistributor(ActionEvent actionEvent) {
        Simulation.getInstance().getVod().addDistributor(Distributor.random(distributorTitleTextField.getText()));
        distributorTitleTextField.clear();
    }
}
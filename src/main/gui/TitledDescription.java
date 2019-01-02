package main.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.Products.Product;

import java.io.IOException;

public class TitledDescription extends VBox {
    public Label titleLabel;
    public Label descriptionLabel;

    public TitledDescription(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "../../layout/titled_description.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setProduct(Product product){
        setTitle(product.getTitle());
        setDescription(product.getLongDescription());
    }

    public void setDescription(String description){
        descriptionLabel.setText(description);
    }

    public void setTitle(String title){
        titleLabel.setText(title);
    }
}

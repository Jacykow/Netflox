package main;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import main.gui.Clock;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Button startSimButton;
    public Clock simDurationClock;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startSim(ActionEvent actionEvent) {
        Simulation.start();
    }
}

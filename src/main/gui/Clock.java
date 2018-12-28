package main.gui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;
import main.Simulation;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Clock extends Label {

    public Clock() {
        bindToTime();
    }

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm" )
                    .withLocale( Locale.getDefault() )
                    .withZone( ZoneId.systemDefault() );

    private void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        actionEvent -> setText("Current time: " + formatter.format(Simulation.time()))
                ),
                new KeyFrame(Duration.millis(20))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}

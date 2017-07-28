package de.kyrtap5.mvgscreen;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Date;

public class ScreenController {
    @FXML
    public Label stopLabel, clockLabel;
    public AnchorPane topPane;
    public VBox mainPane;

    private final MvgTicker ticker = new MvgTicker("Hauptbahnhof");

    public void initialize() {
        stopLabel.setText(ticker.getStation());
        mainPane.setBackground(new Background(new BackgroundFill(Color.MEDIUMBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        topPane.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        refreshData();

        Thread th = new Thread(new TickerTask());
        th.setDaemon(true);
        th.start();
    }


    public class TickerTask extends Task {
        @Override
        public Void call() {
            int counter = 0;
            while (true) {
                try {
                    Platform.runLater(() -> clockLabel.setText(DateHandler.formatDate(new Date(), "HH:mm")));
                    Thread.sleep(1000);
                    if (++counter == 20) {
                        System.out.println("refresh");
                        Platform.runLater(() -> refreshData());
                        counter = 0;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void refreshData() {
        while(mainPane.getChildren().size() != 1) mainPane.getChildren().remove(1);
        for (Departure d : ticker.getDepartures(true, true, true, true)) {
            mainPane.getChildren().add(new StopElement(d));
        }
    }
}

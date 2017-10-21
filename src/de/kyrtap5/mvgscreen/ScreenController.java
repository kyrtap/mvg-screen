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

    private final MvgTicker ticker = new MvgTicker(Main.station);

    /**
     * Initializes the UI
     */
    public void initialize() {
        //Change label texts to application values and change background colors
        stopLabel.setText(ticker.getStation());
        mainPane.setBackground(new Background(new BackgroundFill(Color.MEDIUMBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        topPane.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        //Pull departure information from the parser for the first time and check for exceptions
        try {
            refreshData();
        } catch (Exception e) {
            printException(e);
        }

        //Initialize thread for refreshing the departures
        Thread th = new Thread(new TickerTask());
        th.setDaemon(true);
        th.start();
    }


    /**
     * Task for refreshing the UI in a time interval given in the Main class
     */
    public class TickerTask extends Task {
        @Override
        public Void call() {
            //Run infinite loop which refreshes all data when the refresh interval is reached
            while (true) {
                try {
                    //Refresh time label to show the current time
                    Platform.runLater(() -> clockLabel.setText(DateHandler.formatDate(new Date(), "HH:mm")));
                    //Wait for Main.refreshInterval seconds
                    Thread.sleep(Main.refreshInterval);
                    //Refresh UI by calling refreshData(), print Exception when failed
                    Platform.runLater(() -> {
                        try {
                            refreshData();
                        } catch (Exception e) {
                            printException(e);
                        }
                    });
                } catch (Exception ex) {
                    printException(ex);
                }
            }
        }

    }

    /**
     * Replaces all departure elements by recent ones
     * @throws Exception when an error in the parser occurs, i.e. no connection could be estabilished
     */
    private void refreshData() throws Exception {
        //Replace all departure elements in the screen by new ones assigned by the parser
        while (mainPane.getChildren().size() != 1) mainPane.getChildren().remove(1);
        for (Departure d : ticker.getDepartures(true, true, true, true)) {
            mainPane.getChildren().add(new StopElement(d));
        }
    }

    /**
     * Prints exception to the user and exits application when called
     * @param exception The Exception to be printed out
     */
    private void printException(Exception exception) {
        //Print error and exit application when something went wrong
        System.err.println("An error occured while executing the application: " + exception.toString());
        System.exit(-1);
    }
}

package de.kyrtap5.mvgscreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static String station;
    public static int refreshInterval = 10000;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Screen.fxml"));
        primaryStage.setTitle("MVG Screen");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.out.println("===== MVG Screen v1.1.0 =====\n");

        if (args.length != 0) station = args[0];
        else printUsage();

        if (args.length >= 2) {
            refreshInterval = Integer.parseInt(args[1]) * 1000;
        }

        System.out.println("Starting screen for '" + station + "' with refresh interval " + refreshInterval / 1000 + "s.");

        launch(args);
    }

    private static void printUsage() {
        System.out.println("Example usage: java -jar mvgscreen.jar Ostbahnhof 5\n" +
                "Use the first parameter to define a stop or station and the second one to set the refresh interval in seconds (if empty, the default value of 10s will be used).");
        System.exit(-1);
    }
}

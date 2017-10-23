package de.kyrtap5.mvgscreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    //Parameters and default values
    public static String station;
    public static int refreshInterval = 6 * 1000;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Initialize and start stage
        Parent root = FXMLLoader.load(getClass().getResource("Screen.fxml"));
        primaryStage.setTitle("MVG Screen");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        //Console output
        System.out.println("===== MVG Screen v1.1.0 =====\n");

        //Print usage when no parameters given
        if (args.length != 0) station = args[0];
        else printUsage();

        //Check whether refresh interval was passed and save it
        if (args.length >= 2) {
            //Check whether
            try {
                refreshInterval = Integer.parseInt(args[1]) * 1000;
            } catch (Exception ex) {
                System.err.println("Error: mismatched parameter type!");
                printUsage();
                System.exit(-1);
            }
        }

        //Start output
        System.out.println("Starting screen for '" + station + "' with refresh interval " + refreshInterval / 1000 + "s.");

        //Create Screen
        launch(args);
    }

    /**
     * Prints a message in the console which gives usage information and help
     */
    private static void printUsage() {
        //Print usage with information and help
        System.out.println("Example usage: java -jar mvgscreen.jar Ostbahnhof 5\n" +
                "Use the first parameter to define a stop or station and the second one to set the refresh interval in seconds (if empty, the default value of 10s will be used).");
        System.exit(-1);
    }
}

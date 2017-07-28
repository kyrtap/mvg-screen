package de.kyrtap5.mvgscreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class StopElement extends AnchorPane {
    @FXML
    public AnchorPane anchor;
    public HBox leftPane;
    public HBox rightPane;
    public ImageView typeImage;

    private Node view;
    private StopElementController controller;

    public StopElement(Departure departure) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StopElement.fxml"));
        fxmlLoader.setControllerFactory(param -> controller = new StopElementController(departure));
        try {
            view = fxmlLoader.load();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        getChildren().add(view);
    }
}

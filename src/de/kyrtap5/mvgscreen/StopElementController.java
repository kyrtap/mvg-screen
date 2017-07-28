package de.kyrtap5.mvgscreen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class StopElementController implements Initializable {
    @FXML
    public ImageView typeImage;
    public Label lineLabel;
    public Label destinationLabel;
    public Label timeLabel;

    private final Departure departure;

    public StopElementController(Departure departure) {
        this.departure = departure;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeImage.setImage(new Image("/de/kyrtap5/mvgscreen/res/" + getType(departure) + ".png", typeImage.getLayoutX(),
                typeImage.getLayoutY(), true, true));
        lineLabel.setText(departure.getLine());
        destinationLabel.setText(departure.getDestination());
        timeLabel.setText(Integer.toString(DateHandler.getDifference(departure.getDeparture(), new Date())));
    }

    private String getType(Departure departure) {
        switch (departure.getType()) {
            case BUS:
            case EXPRESSBUS:
            case NACHTBUS:
                return "bus";
            case TRAM:
                return "tram";
            case UBAHN:
                return "u-bahn";
            case SBAHN:
                return "s-bahn";
            default:
                return null;
        }
    }
}

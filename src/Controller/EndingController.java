package src.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class EndingController {

    @FXML
    private Button trueEndButton;

    @FXML
    void quitGame(ActionEvent event) {
        Platform.exit();
    }

}

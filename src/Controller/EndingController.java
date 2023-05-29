package src.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class EndingController {

    @FXML
    void quitGame(ActionEvent event) {
        Platform.exit();
    }

}

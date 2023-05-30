package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import src.Main.Main;
import src.Model.SceneMaker;

import java.io.IOException;

public class BeginningController {

    @FXML
    void showCharacterSelection(ActionEvent event) throws IOException {
        Scene scene = SceneMaker.createScene("src/View/nameCharacter.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

}

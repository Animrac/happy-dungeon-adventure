package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import src.Main.Main;
import src.Model.SceneMaker;

import java.io.IOException;

/**
 * Controller class for the starting scene, start.fxml.
 * @author Carmina Cruz
 */
public class BeginningController {

    /**
     * Shows the character and difficulty selection screen, nameCharacter.fxml.
     * @param event
     * @throws IOException
     */
    @FXML
    void showCharacterSelection(ActionEvent event) throws IOException {
        Scene scene = SceneMaker.createScene("src/View/nameCharacter.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

}

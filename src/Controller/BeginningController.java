package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import src.Main.Main;
import src.Model.SceneMaker;

import java.io.IOException;

/**
 * The controller class for the very first scene.
 *
 * @author Carmina Cruz
 * @version 06/02/23
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

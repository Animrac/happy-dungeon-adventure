package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import src.Main.Main;
import src.Model.DungeonAdventure;
import src.Model.SceneMaker;

import java.io.IOException;

/**
 * The controller class for the how to play scene.
 *
 * @author Carmina Cruz
 * @version 06/02/23
 */
public class ControlsController {

    private DungeonAdventure model;

    public ControlsController() {
        model = DungeonAdventure.getInstance();
    }

    @FXML
    void returnPrevScene(ActionEvent event) throws IOException {
        Scene scene = SceneMaker.createScene(model.getCurrScene());
        Main.getPrimaryStage().setScene(scene);
    }

}

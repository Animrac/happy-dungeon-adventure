package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import src.Main.Main;
import src.Model.DungeonAdventure;
import src.Model.SceneMaker;

/**
 * The controller class for the ending scenes.
 *
 * @author Carmina Cruz
 * @version 06/02/23
 */
public class EndingController {
    private DungeonAdventure model;

    public EndingController() {
        model = DungeonAdventure.getInstance();
    }
    @FXML
    void quitGame(ActionEvent event) {
//        Platform.exit();
        model.setInGame(false);

        model.getMyInventory().emptyInventory();

        Scene scene = SceneMaker.createScene("src/View/nameCharacter.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

}

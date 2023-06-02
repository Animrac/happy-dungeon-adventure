package src.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import src.Main.Main;
import src.Model.DungeonAdventure;
import src.Model.SceneMaker;

public class EndingController {
    private DungeonAdventure model
            ;

    public EndingController() {
        model = DungeonAdventure.getInstance();
    }
    @FXML
    void quitGame(ActionEvent event) {
//        Platform.exit();
        model.setInGame(false);
        Scene scene = SceneMaker.createScene("src/View/nameCharacter.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

}

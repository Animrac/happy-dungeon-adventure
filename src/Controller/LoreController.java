package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import src.Main.Main;
import src.Model.DungeonAdventure;
import src.Model.SceneMaker;

import java.io.IOException;

public class LoreController {
    private DungeonAdventure model;

    public LoreController() {
        model = DungeonAdventure.getInstance();
    }

    @FXML
    void returnPrevScene(ActionEvent event) throws IOException {
        Scene scene = SceneMaker.createScene(model.getCurrScene());
        Main.getPrimaryStage().setScene(scene);
    }

}

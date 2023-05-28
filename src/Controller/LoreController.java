package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import src.Main.Main;
import src.Model.DungeonAdventure;

import java.io.IOException;

public class LoreController {
    private DungeonAdventure dungeonModel;

    public LoreController() {
        dungeonModel = DungeonAdventure.getInstance();
    }

    @FXML
    private AnchorPane lore;
    @FXML
    private MenuItem menuLore;

    @FXML
    void returnPrevScene(ActionEvent event) throws IOException {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(dungeonModel.getCurrScene()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Main.getPrimaryStage().setScene(new Scene(root));
    }

    public MenuItem getMenuLore() {
        return menuLore;
    }
    public AnchorPane getLore() {
        return lore;
    }
    public void setMenuLore(MenuItem menuLore) {
        this.menuLore = menuLore;
    }
    public void setLore(AnchorPane lore) {
        this.lore = lore;
    }


}

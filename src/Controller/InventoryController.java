package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import src.Main.Main;
import src.Model.DungeonAdventure;
import src.Model.SceneMaker;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    @FXML
    private Text abstractionPillar;

    @FXML
    private Button backButton;

    @FXML
    private Text encapsulationPillar;

    @FXML
    private Text healthInventory;

    @FXML
    private Text inheritancePillar;

    @FXML
    private Text pillarInventory;

    @FXML
    private Text polymorphismPillar;

    @FXML
    private Button useHealthButton;

    @FXML
    private Button useVisionButton;

    @FXML
    private Text visionInventory;

    @FXML
    private Text currentHealth;


    private DungeonAdventure model;

    public InventoryController() {
        model = DungeonAdventure.getInstance();
    }

    @FXML
    void returnGame(ActionEvent event) {
        Scene scene = SceneMaker.createScene("src/View/mainGame.fxml");
        Main.getPrimaryStage().setScene(scene);
    }


    @FXML
    void useHealth(ActionEvent event) {
        model.getMyInventory().removeHealthPotion();
        healthInventory.setText("x" + model.getMyInventory().getHealthPotionCount());
        checkPotions();
        //TODO ADD HEALTH BACK TO PLAYER
    }

    @FXML
    void useVision(ActionEvent event) {
        model.getMyInventory().removeVisionPotion();
        visionInventory.setText("x" + model.getMyInventory().getVisionPotionCount());
        checkPotions();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkPotions();
        pillarInventory.setText("x" + model.getMyInventory().getPillarCount());
        visionInventory.setText("x" + model.getMyInventory().getVisionPotionCount());
        healthInventory.setText("x" + model.getMyInventory().getHealthPotionCount());
    }

    private void checkPotions(){

        if (model.getMyInventory().getHealthPotionCount() == 0){
            useHealthButton.setDisable(true);
        }
        else {
            useHealthButton.setDisable(false);
        }
        if (model.getMyInventory().getVisionPotionCount() == 0){
            useVisionButton.setDisable(true);
        }
        else {
            useVisionButton.setDisable(false);
        }

    }

}

package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import src.Main.Main;
import src.Model.DungeonAdventure;
import src.Model.SceneMaker;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The controller class for the inventory scene.
 *
 * @author Carmina Cruz
 * @version 06/02/23
 */
public class InventoryController implements Initializable, StateHandler {

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
    private Text myHealth;

    @FXML
    private Text myName;

    @FXML
    private Text myClass;


    private DungeonAdventure model;

    public InventoryController() {
        model = DungeonAdventure.getInstance();
    }

    @FXML
    void returnGame(ActionEvent event) {
        Scene scene = SceneMaker.createScene(model.getCurrScene());
        Main.getPrimaryStage().setScene(scene);
    }


    @FXML
    void useHealth(ActionEvent event) {

        model.getMyInventory().removeHealthPotion();
        healthInventory.setText("x" + model.getMyInventory().getHealthPotionCount());
        checkPotions();

        int healthRandom = ThreadLocalRandom.current().nextInt(5, 15 + 1);
        model.getMyHero().setHealth(model.getMyHero().getHealth() + healthRandom);

        myHealth.setText(Integer.toString(model.getMyHero().getHealth())); //TODO REPEATED CODE?

    }

    @FXML
    void useVision(ActionEvent event) {
        model.getMyInventory().removeVisionPotion();
        visionInventory.setText("x" + model.getMyInventory().getVisionPotionCount());
        checkPotions();

        Scene scene = SceneMaker.createScene("src/View/map.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        checkPotions();

        pillarInventory.setText("x" + model.getMyInventory().getPillarCount());
        visionInventory.setText("x" + model.getMyInventory().getVisionPotionCount());
        healthInventory.setText("x" + model.getMyInventory().getHealthPotionCount());

        myName.setText(model.getMyName());
        myClass.setText(model.getMyClass());
        myHealth.setText(Integer.toString(model.getMyHero().getHealth()));

        for (int i = 0; i < model.getMyInventory().getPillarCount(); i ++){
            if (model.getMyPillars()[i].equals("ABSTRACTION")){
                abstractionPillar.setOpacity(1);
            }
            else if (model.getMyPillars()[i].equals("INHERITANCE")){
                inheritancePillar.setOpacity(1);
            }
            else if (model.getMyPillars()[i].equals("ENCAPSULATION")){
                encapsulationPillar.setOpacity(1);
            }
            else if (model.getMyPillars()[i].equals("POLYMORPHISM")){
                polymorphismPillar.setOpacity(1);
            }
        }
    }

    private void checkPotions(){

        if (model.getMyInventory().getHealthPotionCount() == 0){
            useHealthButton.setDisable(true);
        }
        else {
            useHealthButton.setDisable(false);
        }
        if (model.getMyInventory().getVisionPotionCount() == 0 || model.getInBattle()){
            useVisionButton.setDisable(true);
        }
        else {
            useVisionButton.setDisable(false);
        }

    }

}

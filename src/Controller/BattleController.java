package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import src.Main.Main;
import src.Model.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class BattleController implements Initializable {

    @FXML
    private ImageView bossMonster;

    @FXML
    private ImageView player;

    @FXML
    private Text myHealth;

    @FXML
    private TextArea myLog;

    @FXML
    private Text myMonsterHealth;

    @FXML
    private Text myMonsterName;

    @FXML
    private Text myName;

    @FXML
    private Button pokemonButton;

    @FXML
    private Button runButton;

    @FXML
    private Button inventoryButton;

    @FXML
    private Button attackButton;

    @FXML
    private Button specialAttackButton;

    /**
     * The instance of DungeonAdventure, the current game.
     */
    private DungeonAdventure model;

    /**
     * Constructor of the class implementing the Singleton design pattern
     * to get the single instance of the DungeonAdventure class.
     */
    public BattleController() {
        model = DungeonAdventure.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        model.setCurrScene("src/View/battle.fxml");

        myName.setText(model.getMyName());
//        myHealth.setText(model.getMyHealth());
        myLog.setText("What will " + model.getMyName() + " do?");
        //TODO set the monster, monster health, current health, names, battledialogue
    }

    @FXML
    void attack(ActionEvent event) {
        myLog.setText(model.getMyName() + " attacks!");
    }

    @FXML
    void specialAttack(ActionEvent event) {
        myLog.setText(model.getMyName() + " uses a special attack!");

    }

    @FXML
    void noPokemon(ActionEvent event) {
        myLog.setText("What are you doing???");
    }

    @FXML
    void openInventory(ActionEvent event) {
        Scene scene = SceneMaker.createScene("src/View/inventory.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void runAway(ActionEvent event) {
        model.getMyRoom().removeRoomMonster();

        Scene scene = SceneMaker.createScene("src/View/mainGame.fxml");
        Main.getPrimaryStage().setScene(scene);
    }





    public static void battle(Hero hero, Monster monster) {
        while (hero.alive() && monster.alive() && !hero.runAway()) {
            System.out.println(hero.getName() + " hit points: " + hero.getHealth());
            System.out.println(monster.getName() + "hit points: " + monster.getHealth());
            hero.attack(monster);

            if(!hero.runAway()) {
                monster.attack(hero);
            }
            else {
                System.out.println(hero.getName() + " bravely ran away...");
            }
        }
        if(monster.alive()) {
            System.out.println(monster.getName() + " defeated " + hero.getName() + "!");
        }
        else {
            System.out.println(hero.getName() + " defeated " + monster.getName() + "!");
        }
    }

    /**
     *
     */
//    public void gamePlay() {
//        Monster monster = spawnMonster();
//        System.out.println("Oh no! A " + monster.getClass() + " has spawned!");
//        battle(hero, monster);
//    }



}

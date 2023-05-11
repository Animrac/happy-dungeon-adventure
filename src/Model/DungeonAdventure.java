package src.Model;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Button;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * This is the driver class for the entire Heroes versus Monsters game.
 *
 * @author Anastasia Vilenius, Carmina Cruz, Hui Wagner
 * @version 05/9/23
 */
public class DungeonAdventure extends Application implements Initializable {

    /**
     * Random object used to generate random monsters.
     */
    private static final Random MY_RANDOM = new Random();

    @FXML
    private MenuBar myMenuBar;

    @FXML
    private MenuItem menuControls;

    @FXML
    private MenuItem menuLore;

    @FXML
    private Button startButton;

    private String currScene;

    @FXML
    private AnchorPane currPane;

    @FXML
    private AnchorPane lore;
    @FXML
    private AnchorPane rootPane;

    @FXML private VBox mainVBox;

    @FXML
    void newGame(ActionEvent event) throws IOException {
//        currScene = "mainGame";
        System.out.println("You thought there was a game???");

        Dungeon layout = new Dungeon();

        Parent root = FXMLLoader.load(getClass().getResource("resources/mainGame.fxml"));
        VBox vboxMainGame = FXMLLoader.load(getClass().getResource("resources/mainGame.fxml"));
        rootPane.getChildren().setAll(vboxMainGame);
//        currPane = rootPane;
    }

    @FXML
    void showLore(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("resources/lore.fxml"));
        VBox vboxLore = FXMLLoader.load(getClass().getResource("resources/lore.fxml"));
        rootPane.getChildren().setAll(vboxLore);
    }
    @FXML //TODO i need to put all of these methods in a view class
    void returnPrevScene(ActionEvent event) throws IOException {
////        Parent root = FXMLLoader.load(getClass().getResource("resources/"+currScene+".fxml"));
////        VBox vboxPrevScene = FXMLLoader.load(getClass().getResource("resources/"+currScene+".fxml"));
//        FXMLLoader loader = FXMLLoader(this.getClass().getResource("resources/nameCharacter.fxml"));
//        AnchorPane anchorPane = loader.load();
//        VBox vboxPrevScene = FXMLLoader.load(getClass().getResource("resources/nameCharacter.fxml"));
//        rootPane.getChildren().setAll(vboxPrevScene);
        //i think i need a main window
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        currScene = "nameCharacter";
        Parent root = FXMLLoader.load(getClass().getResource("resources/nameCharacter.fxml"));
        primaryStage.setTitle("Happy Dungeon Adventure!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Driver method for class (and entire game)
     * Calls displayIntro() class to display an introduction to the game.
     * Runs game play.
     *
     * @param theArgs
     */
    public static void main(String[] theArgs) { //this main method is just to test
        launch(theArgs);

//        gamePlay();

    }


    /**
     * Prompts user to enter name of monster,
     * and then randomly generates a kind of monster.
     *
     * @return randoMonster
     */
    public static Monster spawnMonster() {
        Scanner in  = new Scanner(System.in);

        System.out.print("First, choose a name for a monster: ");
        String name = in.next();

        int rand = MY_RANDOM.nextInt(3);

        Monster randoMonster = null;

        if (rand == 0) {
            randoMonster = new monsterOne(name);
        } else if (rand == 1) {
            randoMonster = new monsterTwo(name);
        } else {
            randoMonster = new monsterThree(name);
        }
        return randoMonster;
    }

    /**
     * Prompts user to name their hero,
     * and then allows them to pick between three types of heroes to play as.
     *
     * @return chosenHero
     */
    public static Hero chooseHero() {
        Scanner in = new Scanner(System.in);

        System.out.print("Now choose a name for your hero: ");
        String name = in.next();

        System.out.println("Time to choose your hero!");
        System.out.println();
        System.out.println("1: heroOne \n2: heroTwo \n3: heroThree");
        System.out.println();

        Hero chosenHero = null;

        if (in.nextInt() == 1) {
            chosenHero = new heroOne(name);
        } else if (in.nextInt() == 2) {
            chosenHero = new heroTwo(name);
        } else if (in.nextInt() == 3) {
            chosenHero = new heroThree(name);
        }
        return chosenHero;
    }

    public static void battle(Hero hero, Monster monster) {
        while (hero.alive() && monster.alive() && !hero.runAway()) {
            System.out.println(hero.getName() + " hit points: " + hero.getHealth());
            System.out.println(monster.getName() + "hit points: " + monster.getHealth());
            hero.attack(monster);

            if(!hero.runAway()) {
                monster.attack(hero);
                System.out.println("Press enter for next round");
                Adventurer.input.nextLine();
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
    public static void gamePlay() {
        Monster monster = spawnMonster();
        System.out.println("Oh no! A " + monster.getClass() + " has spawned!\nIt is named " + monster.getName());
        System.out.println();

        Hero hero = chooseHero();
        System.out.println("Woah! " + hero.getName() + ", a " + hero.getClass() + ", dares to enter the dungeon!");
        System.out.println();

        battle(hero, monster);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

package src.Model;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * This is the driver class for the entire Heroes versus Monsters game.
 *
 * @author Anastasia Vilenius, Carmina Cruz, Hui Wagner
 * @version 05/18/23
 */
public class DungeonAdventure extends Application implements Initializable {

    /**
     * Random object used to generate random monsters.
     */
    private static final Random MY_RANDOM = new Random();

    @FXML
    private Label textRoom;

    @FXML
    private MenuBar myMenuBar;

    @FXML
    private MenuItem menuControls;

    @FXML
    private TextField heroName;

    @FXML
    private MenuItem menuLore;

    @FXML
    private ChoiceBox heroChoice;

    @FXML
    private AnchorPane currPane;

    @FXML
    private AnchorPane lore;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField heroSummary = new TextField("<-- Select your hero!");

    @FXML
    private VBox mainVBox;

    @FXML
    private Button startButton;

    @FXML
    private Button buttonEast;

    @FXML
    private Button buttonNorth;

    @FXML
    private Button buttonSouth;

    @FXML
    private Button buttonWest;

    //TODO this should not be static i think, can't save it otherwise
    private static Dungeon dungeonLayout;

    private int currCol = -1;
    private int currRow = -1;

    private Stage stage;

    private Scene scene;

    private Parent root;

    private String currScene;

    @FXML
    void newGame(ActionEvent event) throws IOException {
        String name = heroName.getText();
        Details heroChosen = (Details) heroChoice.getValue();
        System.out.println(name + " is a " +heroChosen);
        Parent root = FXMLLoader.load(getClass().getResource("resources/mainGame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showLore(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("resources/lore.fxml"));
        VBox vboxLore = FXMLLoader.load(getClass().getResource("resources/lore.fxml"));
        rootPane.getChildren().setAll(vboxLore);
    }

    @FXML //TODO i need to put all of these methods in a view class I think
    void returnPrevScene(ActionEvent event) throws IOException {
        //TODO this doesn't actually return to the previous scene. I have not figured out how to do it yet.
        Parent root = FXMLLoader.load(getClass().getResource("resources/nameCharacter.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("resources/nameCharacter.fxml"));
        primaryStage.setTitle("Happy Dungeon Adventure!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Driver method for class (and the entire game).
     * Runs game play.
     *
     * @param theArgs
     */
    public static void main(String[] theArgs) { //TODO currently lots of test code

        Scanner input = new Scanner(System.in);

        System.out.println("--Would you like a custom dungeon? Y/N--");
        String custom = input.nextLine();  // Read user input

        if (custom.equals("Y") || custom.equals("y") || custom.equals("yes") || custom.equals("Yes")) { //i know there are better ways but im lazy rn
            System.out.println("--Rows (must be at least 5):--");
            int cRows = Integer.parseInt(input.nextLine()) + 2;
            System.out.println("--Columns (must be at least 5):--");
            int cCols = Integer.parseInt(input.nextLine()) + 2;
            dungeonLayout = new Dungeon(cRows, cCols);
        } else {
            dungeonLayout = new Dungeon();
        }

        //TODO: USE REGEX FOR INVALID INPUT
        //VISUALIZATION TEST CODE
        System.out.println("Dungeon:");
        for (int i = 0; i < dungeonLayout.getMyDungeonLayout().length; i++) {
            for (int j = 0; j < dungeonLayout.getMyDungeonLayout()[i].length; j++) {
                System.out.print(dungeonLayout.getMyDungeonLayout()[i][j]);
            }
            System.out.println();
        }


        //getMyDungeonRooms()[y/row][x/column]
        System.out.println();

        System.out.println("Room [1][1]:");
        System.out.print(dungeonLayout.getMyDungeonRooms()[1][1].toString());

        System.out.println();

        System.out.println("Room [5][3]:");
        System.out.print(dungeonLayout.getMyDungeonRooms()[5][3].toString());

        System.out.println();

        System.out.println("Room [4][2]:");
        System.out.print(dungeonLayout.getMyDungeonRooms()[4][2].toString());

        System.out.println();

//        //PRINT TEST ALL THE ROOMS EVERYWHERE, this does not include the boundaries
//        for (int i = 1; i < layout.getMyDungeonRooms().length-1; i++) {
//            for (int j = 1; j < layout.getMyDungeonRooms()[i].length-1; j++) {
//                System.out.println("Room [" + i + "][" + j + "]");
//                System.out.println(layout.getMyDungeonRooms()[i][j]);
//            }
//            System.out.println();
//        }


        //GUI
        launch(theArgs);

        //Console Version of the Game
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

        int rand = MY_RANDOM.nextInt(3);

        Monster randoMonster = null;

        if (rand == 0) {
            randoMonster = new monsterOne();
        } else if (rand == 1) {
            randoMonster = new monsterTwo();
        } else {
            randoMonster = new monsterThree();
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
        System.out.println("Oh no! A " + monster.getClass() + " has spawned!");
        System.out.println();

        Hero hero = chooseHero();
        System.out.println("Woah! " + hero.getName() + ", a " + hero.getClass() + ", dares to enter the dungeon!");
        System.out.println();

        battle(hero, monster);
    }


    @Override
    public void initialize(URL theURL, ResourceBundle theResourceBundle) { //this is every time a Parent is called i think

//        for (int i = 0; i < dungeonLayout.getMyDungeonLayout().length; i++) {
//            for (int j = 0; j < dungeonLayout.getMyDungeonLayout().length; j++) {
//                System.out.print(dungeonLayout.getMyDungeonLayout()[i][j]);
//            }
//            System.out.println();
//        }
        System.out.println();
        System.out.println("Start Row: " + dungeonLayout.getStartRow());
        System.out.println("Start Col: " + dungeonLayout.getStartCol());
        currRow = dungeonLayout.getStartRow();
        currCol = dungeonLayout.getStartCol();

        System.out.println(dungeonLayout.getMyDungeonRooms()[currRow][currCol].toString());

        checkSurroundings(); // For arrow buttons.
        textRoom.setText(dungeonLayout.getMyDungeonRooms()[currRow][currCol].toString());

        ObservableList<Details> heroData = FXCollections.observableArrayList();

        heroData.add(new Details("Warrior", "so that I can kill things easily."));
        heroData.add(new Details("Thief", "so that I am sneaky."));
        heroData.add(new Details("Priestess", "so that I can help myself."));

        heroChoice.setItems(heroData);
        heroChoice.getSelectionModel().selectFirst();
        heroSummary.setText(heroData.get(0).getText());
        heroChoice.valueProperty().addListener((o, ov, nv) -> {
            Details d = (Details) nv;
            heroSummary.setText(d.getText());
        });

    }

    private void checkSurroundings(){
        if (dungeonLayout.getMyDungeonRooms()[currRow][currCol].getCanGoWest() == false){
            buttonWest.setDisable(true);
        }
        else {
            buttonWest.setDisable(false);
        }

        if (dungeonLayout.getMyDungeonRooms()[currRow][currCol].getCanGoEast() == false){
            buttonEast.setDisable(true);
        }
        else {
            buttonEast.setDisable(false);
        }

        if (dungeonLayout.getMyDungeonRooms()[currRow][currCol].getCanGoNorth() == false){
            buttonNorth.setDisable(true);
        }
        else {
            buttonNorth.setDisable(false);
        }

        if (dungeonLayout.getMyDungeonRooms()[currRow][currCol].getCanGoSouth() == false){
            buttonSouth.setDisable(true);
        }
        else {
            buttonSouth.setDisable(false);
        }
    }

    @FXML
    void goEast(ActionEvent event) {

        currCol += 1;
        textRoom.setText(dungeonLayout.getMyDungeonRooms()[currRow][currCol].toString());

        checkSurroundings();

    }

    @FXML
    void goNorth(ActionEvent event) {

        currRow -= 1;
        textRoom.setText(dungeonLayout.getMyDungeonRooms()[currRow][currCol].toString());

        checkSurroundings();

    }

    @FXML
    void goSouth(ActionEvent event) {

        currRow += 1;
        textRoom.setText(dungeonLayout.getMyDungeonRooms()[currRow][currCol].toString());

        checkSurroundings();

    }

    @FXML
    void goWest(ActionEvent event) {

        currCol -= 1;
        textRoom.setText(dungeonLayout.getMyDungeonRooms()[currRow][currCol].toString());

        checkSurroundings();

    }

}

package src.Model;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import javax.swing.*;
import java.awt.*;
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
    private Button startButton;

    private String currScene;


//    Details heroDat[] = {new Details("Warrior", "so that I can kill things easily."),
//            new Details("Thief", "so that I am sneaky."),
//            new Details("Priestess", "so that I can help myself.")};
//    @FXML
//    private ChoiceBox heroChoice = new ChoiceBox(FXCollections.observableArrayList(heroDat));;

    @FXML
    private ChoiceBox heroChoice;

    private Dungeon layout = new Dungeon();

    @FXML
    private AnchorPane currPane;

    @FXML
    private AnchorPane lore;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField heroSummary = new TextField("<-- Select your hero!");

    @FXML private VBox mainVBox;

    private int currCol = -1;
    private int currRow = -1;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button buttonEast;

    @FXML
    private Button buttonNorth;

    @FXML
    private Button buttonSouth;

    @FXML
    private Button buttonWest;
    @FXML
    void newGame(ActionEvent event) throws IOException {
        String name = heroName.getText();
        Details heroChosen = (Details) heroChoice.getValue();
        System.out.println(name + " is a " +heroChosen);
        System.out.println("You thought there was a game???");
//        layout = new Dungeon();
        Parent root = FXMLLoader.load(getClass().getResource("resources/mainGame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


////        currScene = "mainGame";
//        System.out.println("You thought there was a game???");
//
//        Dungeon layout = new Dungeon();
//
//        Parent root = FXMLLoader.load(getClass().getResource("resources/mainGame.fxml"));
//        VBox vboxMainGame = FXMLLoader.load(getClass().getResource("resources/mainGame.fxml"));
//        rootPane.getChildren().setAll(vboxMainGame);
////        currPane = rootPane;
    }

    @FXML
    void showLore(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("resources/lore.fxml"));
        VBox vboxLore = FXMLLoader.load(getClass().getResource("resources/lore.fxml"));
        rootPane.getChildren().setAll(vboxLore);
    }
    @FXML //TODO i need to put all of these methods in a view class
    void returnPrevScene(ActionEvent event) throws IOException {
        //TODO this doesn't actually return to the previous scene. I couldn't figure out how to do it.
        Parent root = FXMLLoader.load(getClass().getResource("resources/nameCharacter.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//        VBox vboxChar = FXMLLoader.load(getClass().getResource("resources/nameCharacter.fxml"));
//        rootPane.getChildren().setAll(vboxChar);

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

//        ObservableList<Details> heroData = FXCollections.observableArrayList();
////        String st[] = { "Arnab", "Andrew", "Ankit", "None" };
//        heroData.add(new Details("Warrior", "so that I can kill things easily."));
//        heroData.add(new Details("Thief", "so that I am sneaky."));
//        heroData.add(new Details("Priestess", "so that I can help myself."));
//        heroChoice.setItems(heroData);
//        heroChoice.getSelectionModel().selectFirst();
//        heroSummary.setText(heroData.get(0).getText());
//        heroChoice.valueProperty().addListener((o, ov, nv) -> {
//            Details d = (Details) nv;
//            heroSummary.setText(d.getText());
//        });

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
//    public static void main(String[] theArgs) { //this main method is just to test
//        displayIntro();
//        Dungeon layout = null;
//        Scanner input = new Scanner(System.in);
//
//        System.out.println("--Would you like a custom dungeon? Y/N--");
//        String custom = input.nextLine();  // Read user input

//        gamePlay();

    }

        System.out.println();

        System.out.println("Room (3,5):");
        System.out.print(layout.getMyDungeonRooms()[5][3].toString());

        System.out.println();

        System.out.println("Room (2,4):");
        System.out.print(layout.getMyDungeonRooms()[4][2].toString());

        //PRINT TEST ALL THE ROOMS EVERYWHERE, this does not include the boundary
        for (int i = 1; i < layout.getMyDungeonRooms().length; i++) {
            for (int j = 1; j < layout.getMyDungeonRooms()[i].length; j++) {
                System.out.println("Room (" + j + ", " + i + ")");
                System.out.println(layout.getMyDungeonRooms()[i][j]);
            }
            System.out.println();
        }

        gamePlay();

    }


//        System.out.println();
//        System.out.println("Traversed:"); //shouldn't show any P, E, or S
//        for (int i = 0; i < tempDungeon.length; i++) {
//            for (int j = 0; j < tempDungeon[i].length; j++) {
//                System.out.print(tempDungeon[i][j]);
//            }
//            System.out.println();
//        }

    /**
     * Prints out an introduction to the game.
     */
    public static void displayIntro() {
        System.out.println("You are about to begin a Happy Adventure!");
        System.out.println("\nBut wait!!!"
                + "\n\nUnfortunately, you were thrown in a dungeon by the scary Tom Nook because you forgot to pay your bills on time. "
                + "\n\nNow, you must venture on a dangerous expedition to collect the four pillars of OO, all while paying your rent to make it out of the dungeon alive."
                + "\n\nGood luck! You'll need it!\n");
    }

    /**
     * Prompts user to enter name of monster,
     * and then randomly generates a kind of monster.
     *
     * @return randoMonster
     */
    public static Monster spawnMonster() {

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

        int choice = in.nextInt(); //attempt to fix bug with choosing heroThree => works!

        Hero chosenHero = null;

        if (choice == 1) {
            chosenHero = new heroOne(name);
        } else if (choice == 2) {
            chosenHero = new heroTwo(name);
        } else if (choice == 3) {
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
    public void initialize(URL url, ResourceBundle resourceBundle) { //this is every time a Parent is called i think

        for (int i = 0; i < layout.getMyDungeonLayout().length; i++) {
            for (int j = 0; j < layout.getMyDungeonLayout().length; j++) {
                System.out.print(layout.getMyDungeonLayout()[i][j]);
            }
            System.out.println();
        }
        System.out.println("Start Row: " + layout.getStartRow());
        System.out.println("Start Col: " + layout.getStartCol());
        currRow = layout.getStartRow();
        currCol = layout.getStartCol();
        System.out.println(layout.getMyDungeonRooms()[currRow][currCol].toString());
        checkSurroundings();
        textRoom.setText(layout.getMyDungeonRooms()[currRow][currCol].toString());


        ObservableList<Details> heroData = FXCollections.observableArrayList();
//        String st[] = { "Arnab", "Andrew", "Ankit", "None" };
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

    private static class Details {

        private final StringProperty name;
        private final StringProperty text;

        public Details(String name, String text) {
            this.name = new SimpleStringProperty(name);
            this.text = new SimpleStringProperty(text);
        }
        public String getText() {
            return text.get();
        }
        public String getName() {
            return name.get();
        }
        @Override
        public String toString() {
            return getName();
        }
    }

    private void checkSurroundings(){
        //TODO im so sorry
        if (layout.getMyDungeonRooms()[currRow][currCol].getCanGoWest() == false){
            buttonWest.setDisable(true);
        }
        else {
            buttonWest.setDisable(false);
        }

        if (layout.getMyDungeonRooms()[currRow][currCol].getCanGoEast() == false){
            buttonEast.setDisable(true);
        }
        else {
            buttonEast.setDisable(false);
        }

        if (layout.getMyDungeonRooms()[currRow][currCol].getCanGoNorth() == false){
            buttonNorth.setDisable(true);
        }
        else {
            buttonNorth.setDisable(false);
        }

        if (layout.getMyDungeonRooms()[currRow][currCol].getCanGoSouth() == false){
            buttonSouth.setDisable(true);
        }
        else {
            buttonSouth.setDisable(false);
        }
    }
    @FXML
    void goEast(ActionEvent event) {

        currCol += 1;
//        currRow += 1;
        textRoom.setText(layout.getMyDungeonRooms()[currRow][currCol].toString());

        checkSurroundings();


    }

    @FXML
    void goNorth(ActionEvent event) {

        currRow -= 1;
        textRoom.setText(layout.getMyDungeonRooms()[currRow][currCol].toString());

        checkSurroundings();

    }

    @FXML
    void goSouth(ActionEvent event) {

        currRow += 1;
        textRoom.setText(layout.getMyDungeonRooms()[currRow][currCol].toString());

        checkSurroundings();

    }

    @FXML
    void goWest(ActionEvent event) {

        currCol -= 1;
//        currRow -= 1;
        textRoom.setText(layout.getMyDungeonRooms()[currRow][currCol].toString());

        checkSurroundings();

    }



}

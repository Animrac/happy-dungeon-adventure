package src.Controller;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import src.Main.Main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import src.Model.*;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The controller class for the game scene, mainGame.fxml.
 *
 * @author Carmina Cruz
 * @version 06/02/23
 */
public class GameController implements Initializable, StateHandler {

    /**
     * Notification text when entering a room with a pit.
     */
    private static final String PIT_TEXT = "ouch! you fell in a pit :(";

    /**
     * Notification text when all four pillars were collected and the game can be completed.
     */
    private final String ALL_PILLARS = "you found all the pillars! get to the exit!";

    /**
     * Notification text when the player collects an item.
     */
    private final String COLLECTED = "you collected a ";

    /**
     * Full opacity constant at 100%. Shown.
     */
    private final double FULL_OPACITY = 1.0;

    /**
     * No opacity constant at 0%. Hidden.
     */
    private final double NO_OPACITY = 0.0;

    /**
     * An image of the player. TODO Can change based on what character you choose.
     */
    @FXML
    private ImageView player;

    /**
     * An image to signify in game if the current room is the entrance.
     */
    @FXML
    private ImageView startSign;

    /**
     * An image to signify in game if the current room is the exit.
     */
    @FXML
    private ImageView exitSign;

    /**
     * Images to signify walls.
     */
    @FXML
    private ImageView wallEast1;
    @FXML
    private ImageView wallEast2;
    @FXML
    private ImageView wallEast3;
    @FXML
    private ImageView wallEast4;

    @FXML
    private ImageView wallNorth1;
    @FXML
    private ImageView wallNorth2;

    @FXML
    private ImageView wallSouth1;
    @FXML
    private ImageView wallSouth2;
    @FXML
    private ImageView wallSouth3;
    @FXML
    private ImageView wallSouth4;

    @FXML
    private ImageView wallWest1;
    @FXML
    private ImageView wallWest2;
    @FXML
    private ImageView wallWest3;
    @FXML
    private ImageView wallWest4;

    /**
     * Image to signify a pit in the current room in game.
     */
    @FXML
    private ImageView pit;

    /**
     * Image to signify a health potion in the current room in game.
     */
    @FXML
    private ImageView healthpotion;

    /**
     * Image to signify a vision in the current room in game.
     */
    @FXML
    private ImageView visionpotion;

    /**
     * Image to signify a pillar in the current room in game.
     */
    @FXML
    private ImageView pillar;

    /**
     * A button that, when clicked, allows the player to collect an item in the current room.
     */
    @FXML
    private Button collectButton;

    /**
     * A button that, when clicked, allows the player to exit the dungeon and complete the game.
     */
    @FXML
    private Button exitButton;

    /**
     * A button that, when clicked, allows the player to go into the room east of the current room.
     */
    @FXML
    private Button buttonEast;

    /**
     * A button that, when clicked, allows the player to go into the room north of the current room.
     */
    @FXML
    private Button buttonNorth;

    /**
     * A button that, when clicked, allows the player to go into the room south of the current room.
     */
    @FXML
    private Button buttonSouth;

    /**
     * A button that, when clicked, allows the player to go into the room west of the current room.
     */
    @FXML
    private Button buttonWest;

    /**
     * Displays the player's current health.
     */
    @FXML
    private Text myHealth;

    /**
     * Displays the player's name chosen at the start of the game.
     */
    @FXML
    private Text myName;

    /**
     * Displays the player's class chosen at the start of the game.
     */
    @FXML
    private Text myClass;

    /**
     * Displays a notification.
     */
    @FXML
    private Text myNotification;

    /**
     * The instance of DungeonAdventure, the current game.
     */
    private DungeonAdventure model;

    /**
     * The sound played when clicking the collect button.
     */
    private Media collectSound = new Media(new File("src/View/pickup.mp3").toURI().toString());

    /**
     * The sound played when clicking an arrow button to move.
     */
    private Media walkSound = new Media(new File("src/View/walk.mp3").toURI().toString());

    /**
     * The transition used for notifications.
     */
    private SequentialTransition notifyTransition = new SequentialTransition();

    /**
     * Constructor of the class implementing the Singleton design pattern
     * to get the single instance of the DungeonAdventure class.
     */
    public GameController() {
        model = DungeonAdventure.getInstance();
    }

    /**
     * When used by the .fxml class, initialize method is called first, similar to a constructor.
     * Creates the transition effect for the notifications in game, sets the current room of the player
     * based on the start of the dungeon, shuffles the pillar order, and generates the dungeon.
     * @param theURL
     * @param theResourceBundle
     */
    @Override
    public void initialize(URL theURL, ResourceBundle theResourceBundle) {

        // Create a fade-in transition
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.2), myNotification);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        // Create a pause transition
        PauseTransition pause = new PauseTransition(Duration.seconds(2));

        // Create a fade-out transition
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), myNotification);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        // Create a sequential transition to chain the animations
        notifyTransition = new SequentialTransition(
                fadeIn, pause, fadeOut
        );

        model.setCurrScene("src/View/mainGame.fxml");

        myName.setText(model.getMyName());
        myClass.setText(model.getMyClass());

//remove pit save
        if (model.getMyHero() == null) {
            model.setMyHero(heroCreation());
        }
        myHealth.setText(Integer.toString(model.getMyHero().getHealth()));

        if (!model.getInGame()){

            Collections.shuffle(Arrays.asList(model.getMyPillars()));

            if (model.getMyDifficulty().equals("Easy-peasy")){
                model.setMyDungeonLayout(new Dungeon(5, 5));

            }
            else if (model.getMyDifficulty().equals("Default")) {
                model.setMyDungeonLayout(new Dungeon()); // 10x10
            }
            else if (model.getMyDifficulty().equals("Why")) {
                model.setMyDungeonLayout(new Dungeon(100, 100));
            }
            else {
                System.out.println("ERROR: Invalid Difficulty, Giving Default Difficulty Instead");
                model.setMyDungeonLayout(new Dungeon()); // 10x10
            }

            model.getMyDungeonLayout().setCurrRow(model.getMyDungeonLayout().getStartRow());
            model.getMyDungeonLayout().setCurrCol(model.getMyDungeonLayout().getStartCol());
            model.setMyRoom(model.getMyDungeonLayout().getMyDungeonRooms()
                    [model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()]);

            exitButton.setDisable(true);
            exitButton.setOpacity(NO_OPACITY);

            model.setInGame(true);

        }

        checkRoom(); // For collection and escape buttons.
        checkSurroundings(); // For arrow buttons.
        setRoom(); // For visualizing the current room.

    }

    /**
     * Shows the inventory screen, inventory.fxml.
     * @param event
     * @throws IOException
     */
    @FXML
    void openInventory(ActionEvent event) {
        Scene scene = SceneMaker.createScene("src/View/inventory.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void collectItem(ActionEvent event) {
        MediaPlayer mediaPlayer = new MediaPlayer(collectSound);
        mediaPlayer.play();

        if (model.getMyRoom().getHasPillar()){
            model.getMyInventory().addPillar();

            // Displays current pillar
            myNotification.setText(COLLECTED + "pillar of " + model.getMyPillars()[model.getMyInventory().getPillarCount()-1]);
            notifyPlayer();

            if (model.getMyInventory().getPillarCount() == 4) {
                myNotification.setText(ALL_PILLARS);
                notifyPlayer();
            }
        }

        if (model.getMyRoom().getHasHealingPotion()){
            model.getMyInventory().addHealthPotion();

            myNotification.setText(COLLECTED + "health potion!");
            notifyPlayer();
        }

        if (model.getMyRoom().getHasVisionPotion()){
            model.getMyInventory().addVisionPotion();

            myNotification.setText(COLLECTED + "vision potion!");
            notifyPlayer();
        }

//        if (model.getMyRoom().getHasMultipleItems()){
//            myNotification.setText(COLLECTED + "health potion and a vision potion!");
//            notifyPlayer();
//        }

        model.getMyRoom().removeRoomItems();
        collectButton.setDisable(true);

        setRoom();
    }

    private void setRoom() {

        model.setMyRoom(model.getMyDungeonLayout().getMyDungeonRooms()
                [model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()]);

        if (model.getMyRoom().getCanGoNorth()) {
            wallNorth1.setOpacity(NO_OPACITY);
            wallNorth2.setOpacity(NO_OPACITY);
        }
        else {
            wallNorth1.setOpacity(FULL_OPACITY);
            wallNorth2.setOpacity(FULL_OPACITY);
        }
        if (model.getMyRoom().getCanGoSouth()) {
            wallSouth1.setOpacity(NO_OPACITY);
            wallSouth2.setOpacity(NO_OPACITY);
            wallSouth3.setOpacity(NO_OPACITY);
            wallSouth4.setOpacity(NO_OPACITY);
        }
        else {
            wallSouth1.setOpacity(FULL_OPACITY);
            wallSouth2.setOpacity(FULL_OPACITY);
            wallSouth3.setOpacity(FULL_OPACITY);
            wallSouth4.setOpacity(FULL_OPACITY);
        }
        if (model.getMyRoom().getCanGoEast()) {
            wallEast1.setOpacity(NO_OPACITY);
            wallEast2.setOpacity(NO_OPACITY);
            wallEast3.setOpacity(NO_OPACITY);
            wallEast4.setOpacity(NO_OPACITY);
        }
        else {
            wallEast1.setOpacity(FULL_OPACITY);
            wallEast2.setOpacity(FULL_OPACITY);
            wallEast3.setOpacity(FULL_OPACITY);
            wallEast4.setOpacity(FULL_OPACITY);
        }
        if (model.getMyRoom().getCanGoWest()) {
            wallWest1.setOpacity(NO_OPACITY);
            wallWest2.setOpacity(NO_OPACITY);
            wallWest3.setOpacity(NO_OPACITY);
            wallWest4.setOpacity(NO_OPACITY);
        }
        else {
            wallWest1.setOpacity(FULL_OPACITY);
            wallWest2.setOpacity(FULL_OPACITY);
            wallWest3.setOpacity(FULL_OPACITY);
            wallWest4.setOpacity(FULL_OPACITY);
        }

        //ITEMS//
        if (model.getMyRoom().getHasPillar()) {
            pillar.setOpacity(FULL_OPACITY);
        }
        else {
            pillar.setOpacity(NO_OPACITY);
        }
        if (model.getMyRoom().getHasHealingPotion()) {
            healthpotion.setOpacity(FULL_OPACITY);
        }
        else {
            healthpotion.setOpacity(NO_OPACITY);
        }
        if (model.getMyRoom().getHasVisionPotion()) {
            visionpotion.setOpacity(FULL_OPACITY);
        }
        else {
            visionpotion.setOpacity(NO_OPACITY);
        }

        //ENTRANCE OR EXIT//
        if (model.getMyRoom().isExit()) {
            exitSign.setOpacity(FULL_OPACITY);
            exitButton.setOpacity(0.4);
            exitButton.setDisable(true);
            if (model.getMyInventory().getPillarCount() == 4){
                exitButton.setOpacity(FULL_OPACITY);
                exitButton.setDisable(false);
            }
        }
        else {
            exitButton.setDisable(true);
            exitButton.setOpacity(NO_OPACITY);
            exitSign.setOpacity(NO_OPACITY);
        }
        if (model.getMyRoom().isEntrance()) {
            startSign.setOpacity(FULL_OPACITY);
        }
        else {
            startSign.setOpacity(NO_OPACITY);
        }

        if (model.getMyRoom().getHasMonster()) {

            model.getMyRoom().removeRoomMonster();
//TODO BATTLE
            Scene scene = SceneMaker.createScene("src/View/battle.fxml");
            Main.getPrimaryStage().setScene(scene);
        }

    }

    @FXML
    void goNorth(ActionEvent event) {

        MediaPlayer mediaPlayer = new MediaPlayer(walkSound);
        mediaPlayer.play();

        model.getMyDungeonLayout()
                .setCurrRow(model.getMyDungeonLayout().getCurrRow() - 1);

        setRoom();
        checkRoom();
        checkSurroundings();

    }

    @FXML
    void goSouth(ActionEvent event) {

        MediaPlayer mediaPlayer = new MediaPlayer(walkSound);
        mediaPlayer.play();

        model.getMyDungeonLayout()
                .setCurrRow(model.getMyDungeonLayout().getCurrRow() + 1);

        setRoom();
        checkRoom();
        checkSurroundings();

    }

    @FXML
    void goEast(ActionEvent event) {

        MediaPlayer mediaPlayer = new MediaPlayer(walkSound);
        mediaPlayer.play();

        model.getMyDungeonLayout()
                .setCurrCol(model.getMyDungeonLayout().getCurrCol() + 1);

        setRoom();
        checkRoom();
        checkSurroundings();

    }
    @FXML
    void goWest(ActionEvent event) {

        MediaPlayer mediaPlayer = new MediaPlayer(walkSound);
        mediaPlayer.play();

        model.getMyDungeonLayout()
                .setCurrCol(model.getMyDungeonLayout().getCurrCol() - 1);

        setRoom();
        checkRoom();
        checkSurroundings();

    }

    @FXML
    void showLore(ActionEvent event) {
        Scene scene = SceneMaker.createScene("src/View/lore.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void showControls(ActionEvent event) {
        Scene scene = SceneMaker.createScene("src/View/controls.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void endGame(ActionEvent event) {
        Scene scene = SceneMaker.createScene("src/View/end.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

    private void checkRoom(){

        if (!model.getMyDungeonLayout().getMyDungeonRooms()
                [model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()]
                .getHasItem()){
            collectButton.setDisable(true);
        }
        else {
            collectButton.setDisable(false);
        }

        if (model.getMyDungeonLayout().getMyDungeonRooms()
                [model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()]
                .getHasPit()) {

            int pitRandom = ThreadLocalRandom.current().nextInt(1, 20 + 1);

//            System.out.println(model.getMyHero().getHealth() - pitRandom);

            model.getMyHero()
                    .setHealth(model.getMyHero().getHealth() - pitRandom);

            // Remove the pit from the room.
//            model.getMyRoom().setHasPit(false);

            myHealth.setText(Integer.toString(model.getMyHero().getHealth()));
            checkHealth();

            myNotification.setText(PIT_TEXT);
            notifyPlayer();
        }


    }

    private void checkHealth() {
        if (model.getMyHero().getHealth() <= 0) {
            Scene scene = SceneMaker.createScene("src/View/badEnd.fxml");
            Main.getPrimaryStage().setScene(scene);
        }
    }

    private void checkSurroundings(){
        if (!model.getMyDungeonLayout().getMyDungeonRooms()
                [model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()]
                .getCanGoWest()){
            buttonWest.setDisable(true);
        }
        else {
            buttonWest.setDisable(false);
        }

        if (!model.getMyDungeonLayout().getMyDungeonRooms()
                [model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()]
                .getCanGoEast()){
            buttonEast.setDisable(true);
        }
        else {
            buttonEast.setDisable(false);
        }

        if (!model.getMyDungeonLayout().getMyDungeonRooms()
                [model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()]
                .getCanGoNorth()){
            buttonNorth.setDisable(true);
        }
        else {
            buttonNorth.setDisable(false);
        }

        if (!model.getMyDungeonLayout().getMyDungeonRooms()
                [model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()]
                .getCanGoSouth()){
            buttonSouth.setDisable(true);
        }
        else {
            buttonSouth.setDisable(false);
        }
    }

    private void notifyPlayer () {
        // Stop existing transition
        notifyTransition.stop();
        notifyTransition.play();
    }

    @FXML
    void quit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void load(ActionEvent event) {
        loadState();
        refreshScene();
    }


    @FXML
    void save(ActionEvent event) {
        saveState();
        myNotification.setText("saved!");
        notifyPlayer();
    }

    public Hero heroCreation() {
        Hero chosenHero = null;

        if (model.getMyClass().equals("Warrior")) {
            chosenHero = new HeroOne(model.getMyName());
        } else if (model.getMyClass().equals("Thief")) {
            chosenHero = new HeroTwo(model.getMyName());
        } else if (model.getMyClass().equals("Priestess")) {
            chosenHero = new HeroThree(model.getMyName());
        }
        return chosenHero;
    }


}

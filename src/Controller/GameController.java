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
import javafx.scene.control.Label;
import src.Model.Dungeon;
import src.Model.DungeonAdventure;
import src.Model.SceneMaker;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import java.util.Collections;

public class GameController implements Initializable {

    private static final String PIT_TEXT = "ouch! you fell in a pit :(";

    private final String ALL_PILLARS = "you found all the pillars! get to the exit!";

    private final String COLLECTED = "you collected a ";

    private final double FULL_OPACITY = 1.0;

    private final double NO_OPACITY = 0.0;

    @FXML
    private Button collectButton;

    @FXML
    private Button exitButton;

    @FXML
    private ImageView player;

    @FXML
    private ImageView startSign;

    @FXML
    private ImageView exitSign;

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

    @FXML
    private ImageView pit;

    @FXML
    private ImageView healthpotion;

    @FXML
    private ImageView visionpotion;

    @FXML
    private ImageView pillar;

    @FXML
    private Button buttonEast;

    @FXML
    private Button buttonNorth;

    @FXML
    private Button buttonSouth;

    @FXML
    private Button buttonWest;

    @FXML
    private Label textRoom;

    @FXML
    private Text myHealth;

    @FXML
    private Text myName;

    @FXML
    private Text myClass;

    @FXML
    private Text myNotification;

    private DungeonAdventure model;

    private Media collectSound = new Media(new File("src/View/pickup.mp3").toURI().toString());

    private Media walkSound = new Media(new File("src/View/walk.mp3").toURI().toString());

    private SequentialTransition notifyTransition = new SequentialTransition();

    public GameController() {
        model = DungeonAdventure.getInstance();
    }

    @Override
    public void initialize(URL theURL, ResourceBundle theResourceBundle) { //this is every time a Parent is called i think

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
        //TODO
//            myHealth.setText(model.getMyHealth());

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


//            System.out.println();
//            System.out.println("Start Row: " + model.getDungeonLayout().getStartRow());
//            System.out.println("Start Col: " + model.getDungeonLayout().getStartCol());
            model.getMyDungeonLayout().setCurrRow(model.getMyDungeonLayout().getStartRow());
            model.getMyDungeonLayout().setCurrCol(model.getMyDungeonLayout().getStartCol());
            model.setMyRoom(model.getMyDungeonLayout().getMyDungeonRooms()
                    [model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()]);

            exitButton.setDisable(true);
            exitButton.setOpacity(NO_OPACITY);

//            System.out.println(model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()].toString());

            model.setInGame(true);

        }

        checkRoom(); // For collection and escape buttons.
        checkSurroundings(); // For arrow buttons.

        //we want to set the text in the view to be the current room as a tostring for now, will change to image tiles later
        //textRoom.setText(model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()].toString());
        setRoom(); // For visualizing the current room.

    }

    @FXML
    void openInventory(ActionEvent event) {
        Scene scene = SceneMaker.createScene("src/View/inventory.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void collectItem(ActionEvent event) {
        MediaPlayer mediaPlayer = new MediaPlayer(collectSound);
        mediaPlayer.play();

        if (model.getMyRoom().isHasPillar()){
            model.getMyInventory().addPillar();

            // Displays current pillar
            myNotification.setText(COLLECTED + "pillar of " + model.getMyPillars()[model.getMyInventory().getPillarCount()-1]);
            notifyPlayer();

            if (model.getMyInventory().getPillarCount() == 4) {
                myNotification.setText(ALL_PILLARS);
                notifyPlayer();
            }
        }

        if (model.getMyRoom().isHasHealingPotion()){
            model.getMyInventory().addHealthPotion();

            myNotification.setText(COLLECTED + "health potion!");
            notifyPlayer();
        }

        if (model.getMyRoom().isHasVisionPotion()){
            model.getMyInventory().addVisionPotion();

            myNotification.setText(COLLECTED + "vision potion!");
            notifyPlayer();
        }

        model.getMyRoom().removeRoomItems();
        collectButton.setDisable(true);

        setRoom();
//        textRoom.setText(model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()].toString());

    }

    private void setRoom() {
        model.setMyRoom(model.getMyDungeonLayout().getMyDungeonRooms()[model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()]);
//        textRoom.setText(model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()].toString());
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
        if (model.getMyRoom().isHasPillar()) {
            pillar.setOpacity(FULL_OPACITY);
        }
        else {
            pillar.setOpacity(NO_OPACITY);
        }
        if (model.getMyRoom().isHasHealingPotion()) {
            healthpotion.setOpacity(FULL_OPACITY);
        }
        else {
            healthpotion.setOpacity(NO_OPACITY);
        }
        if (model.getMyRoom().isHasVisionPotion()) {
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
    }

    @FXML
    void goNorth(ActionEvent event) {

        MediaPlayer mediaPlayer = new MediaPlayer(walkSound);
        mediaPlayer.play();

        model.getMyDungeonLayout().setCurrRow(model.getMyDungeonLayout().getCurrRow() - 1);
        setRoom();

        checkRoom();
        checkSurroundings();

    }

    @FXML
    void goSouth(ActionEvent event) {

        MediaPlayer mediaPlayer = new MediaPlayer(walkSound);
        mediaPlayer.play();

        model.getMyDungeonLayout().setCurrRow(model.getMyDungeonLayout().getCurrRow() + 1);
        setRoom();

        checkRoom();
        checkSurroundings();

    }

    @FXML
    void goEast(ActionEvent event) {

        MediaPlayer mediaPlayer = new MediaPlayer(walkSound);
        mediaPlayer.play();

        model.getMyDungeonLayout().setCurrCol(model.getMyDungeonLayout().getCurrCol() + 1);
        setRoom();

        checkRoom();
        checkSurroundings();

    }
    @FXML
    void goWest(ActionEvent event) {

        MediaPlayer mediaPlayer = new MediaPlayer(walkSound);
        mediaPlayer.play();

        model.getMyDungeonLayout().setCurrCol(model.getMyDungeonLayout().getCurrCol() - 1);
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

    //in the game:

    private void checkRoom(){

        if (!model.getMyDungeonLayout().getMyDungeonRooms()
                [model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()]
                .getHasItem()){
            collectButton.setDisable(true);
        }
        else {
            collectButton.setDisable(false);
        }

        //TODO IF A PIT, REDUCE HEALTH
        if (model.getMyDungeonLayout().getMyDungeonRooms()
                [model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()]
                .isHasPit()) {
            myNotification.setText(PIT_TEXT);
            notifyPlayer();
        }


    }

    private void checkSurroundings(){
        if (!model.getMyDungeonLayout().getMyDungeonRooms()[model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()].getCanGoWest()){
            buttonWest.setDisable(true);
        }
        else {
            buttonWest.setDisable(false);
        }

        if (!model.getMyDungeonLayout().getMyDungeonRooms()[model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()].getCanGoEast()){
            buttonEast.setDisable(true);
        }
        else {
            buttonEast.setDisable(false);
        }

        if (!model.getMyDungeonLayout().getMyDungeonRooms()[model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()].getCanGoNorth()){
            buttonNorth.setDisable(true);
        }
        else {
            buttonNorth.setDisable(false);
        }

        if (!model.getMyDungeonLayout().getMyDungeonRooms()[model.getMyDungeonLayout().getCurrRow()][model.getMyDungeonLayout().getCurrCol()].getCanGoSouth()){
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
    //TODO load and save
    @FXML
    void load(ActionEvent event) {
        Platform.exit();
    }
    @FXML
    void save(ActionEvent event) {
        Platform.exit();
    }
}

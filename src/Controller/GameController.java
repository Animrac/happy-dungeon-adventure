package src.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import src.Main.Main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import src.Model.DungeonAdventure;
import src.Model.SceneMaker;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private Button collectButton;

    @FXML
    private Button inventoryButton;

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

    private DungeonAdventure model;

    private Media collectSound = new Media(new File("src/View/pickup.mp3").toURI().toString());
    private Media walkSound = new Media(new File("src/View/walk.mp3").toURI().toString());


    public GameController() {
        model = DungeonAdventure.getInstance();
    }
//    private DungeonAdventure model;
//    private GameView view;
//    private Stage stage;
//
//    public GameController(DungeonAdventure model, GameView view, Stage stage) {
//        this.model = model;
//        this.view = view;
//        this.stage = stage;
//    }
//
//    public void switchToScene(String fxmlFileName) {
//        Scene newScene = view.createScene(fxmlFileName);
//        if (newScene != null) {
//            stage.setScene(newScene);
//        }
//    }

    @Override
    public void initialize(URL theURL, ResourceBundle theResourceBundle) { //this is every time a Parent is called i think

        model.setCurrScene("src/View/mainGame.fxml");


//        for (int i = 0; i < dungeonLayout.getMyDungeonLayout().length; i++) {
//            for (int j = 0; j < dungeonLayout.getMyDungeonLayout().length; j++) {
//                System.out.print(dungeonLayout.getMyDungeonLayout()[i][j]);
//            }
//            System.out.println();
//        }



        if (!model.getInGame()){

            System.out.println();
            System.out.println("Start Row: " + model.getDungeonLayout().getStartRow());
            System.out.println("Start Col: " + model.getDungeonLayout().getStartCol());
            model.getDungeonLayout().setCurrRow(model.getDungeonLayout().getStartRow());
            model.getDungeonLayout().setCurrCol(model.getDungeonLayout().getStartCol());
            model.setMyRoom(model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()]);

            exitButton.setDisable(true);
            exitButton.setOpacity(0);

            System.out.println(model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()].toString());

            model.setInGame(true);

        }

        //for View??

        checkRoom();
        checkSurroundings(); // For arrow buttons.

        //we want to set the text in the view to be the current room as a tostring for now, will change to image tiles later
//        textRoom.setText(model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()].toString());
        setRoom();

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
        }

        if (model.getMyRoom().isHasHealingPotion()){
            model.getMyInventory().addHealthPotion();
        }

        if (model.getMyRoom().isHasVisionPotion()){
            model.getMyInventory().addVisionPotion();
        }

        model.getMyRoom().removeRoomItems();
        collectButton.setDisable(true);

        setRoom();
//        textRoom.setText(model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()].toString());

    }

    private void setRoom() {
        model.setMyRoom(model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()]);
//        textRoom.setText(model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()].toString());
        if (model.getMyRoom().getCanGoNorth()) {
            wallNorth1.setOpacity(0);
            wallNorth2.setOpacity(0);
        }
        else {
            wallNorth1.setOpacity(100);
            wallNorth2.setOpacity(100);
        }
        if (model.getMyRoom().getCanGoSouth()) {
            wallSouth1.setOpacity(0);
            wallSouth2.setOpacity(0);
            wallSouth3.setOpacity(0);
            wallSouth4.setOpacity(0);
        }
        else {
            wallSouth1.setOpacity(100);
            wallSouth2.setOpacity(100);
            wallSouth3.setOpacity(100);
            wallSouth4.setOpacity(100);
        }
        if (model.getMyRoom().getCanGoEast()) {
            wallEast1.setOpacity(0);
            wallEast2.setOpacity(0);
            wallEast3.setOpacity(0);
            wallEast4.setOpacity(0);
        }
        else {
            wallEast1.setOpacity(100);
            wallEast2.setOpacity(100);
            wallEast3.setOpacity(100);
            wallEast4.setOpacity(100);
        }
        if (model.getMyRoom().getCanGoWest()) {
            wallWest1.setOpacity(0);
            wallWest2.setOpacity(0);
            wallWest3.setOpacity(0);
            wallWest4.setOpacity(0);
        }
        else {
            wallWest1.setOpacity(100);
            wallWest2.setOpacity(100);
            wallWest3.setOpacity(100);
            wallWest4.setOpacity(100);
        }

        //ITEMS//
        if (model.getMyRoom().isHasPillar()) {
            pillar.setOpacity(100);
        }
        else {
            pillar.setOpacity(0);
        }
        if (model.getMyRoom().isHasHealingPotion()) {
            healthpotion.setOpacity(100);
        }
        else {
            healthpotion.setOpacity(0);
        }
        if (model.getMyRoom().isHasVisionPotion()) {
            visionpotion.setOpacity(100);
        }
        else {
            visionpotion.setOpacity(0);
        }

        //ENTRANCE OR EXIT//
        if (model.getMyRoom().isExit()) {
            exitSign.setOpacity(100);
            exitButton.setOpacity(0.40);
            exitButton.setDisable(true);
            if (model.getMyInventory().getPillarCount() == 4){
                exitButton.setOpacity(100);
                exitButton.setDisable(false);
            }
        }
        else {
            exitButton.setDisable(true);
            exitButton.setOpacity(0);
            exitSign.setOpacity(0);
        }
        if (model.getMyRoom().isEntrance()) {
            startSign.setOpacity(100);
        }
        else {
            startSign.setOpacity(0);
        }
    }

    @FXML
    void goNorth(ActionEvent event) {

        MediaPlayer mediaPlayer = new MediaPlayer(walkSound);
        mediaPlayer.play();

        model.getDungeonLayout().setCurrRow(model.getDungeonLayout().getCurrRow() - 1);
        setRoom();

        checkRoom();
        checkSurroundings();

    }

    @FXML
    void goSouth(ActionEvent event) {

        MediaPlayer mediaPlayer = new MediaPlayer(walkSound);
        mediaPlayer.play();

        model.getDungeonLayout().setCurrRow(model.getDungeonLayout().getCurrRow() + 1);
        setRoom();

        checkRoom();
        checkSurroundings();

    }

    @FXML
    void goEast(ActionEvent event) {

        MediaPlayer mediaPlayer = new MediaPlayer(walkSound);
        mediaPlayer.play();

        model.getDungeonLayout().setCurrCol(model.getDungeonLayout().getCurrCol() + 1);
        setRoom();

        checkRoom();
        checkSurroundings();

    }
    @FXML
    void goWest(ActionEvent event) {

        MediaPlayer mediaPlayer = new MediaPlayer(walkSound);
        mediaPlayer.play();

        model.getDungeonLayout().setCurrCol(model.getDungeonLayout().getCurrCol() - 1);
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
    void endGame(ActionEvent event) {
        Scene scene = SceneMaker.createScene("src/View/end.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

    //in the game:

    private void checkRoom(){

        if (model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()].getHasItem() == false){
            collectButton.setDisable(true);
        }
        else {
            collectButton.setDisable(false);
        }
    }

    private void checkSurroundings(){
        if (model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()].getCanGoWest() == false){
            buttonWest.setDisable(true);
        }
        else {
            buttonWest.setDisable(false);
        }

        if (model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()].getCanGoEast() == false){
            buttonEast.setDisable(true);
        }
        else {
            buttonEast.setDisable(false);
        }

        if (model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()].getCanGoNorth() == false){
            buttonNorth.setDisable(true);
        }
        else {
            buttonNorth.setDisable(false);
        }

        if (model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()].getCanGoSouth() == false){
            buttonSouth.setDisable(true);
        }
        else {
            buttonSouth.setDisable(false);
        }
    }

    public Button getButtonEast() {
        return buttonEast;
    }

    public Button getButtonNorth() {
        return buttonNorth;
    }

    public Button getButtonSouth() {
        return buttonSouth;
    }

    public Button getButtonWest() {
        return buttonWest;
    }
    public Label getTextRoom() {
        return textRoom;
    }

    public void setButtonEast(Button buttonEast) {
        this.buttonEast = buttonEast;
    }

    public void setButtonNorth(Button buttonNorth) {
        this.buttonNorth = buttonNorth;
    }

    public void setButtonSouth(Button buttonSouth) {
        this.buttonSouth = buttonSouth;
    }

    public void setButtonWest(Button buttonWest) {
        this.buttonWest = buttonWest;
    }
    public void setTextRoom(Label textRoom) {
        this.textRoom = textRoom;
    }
}

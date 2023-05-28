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
import src.Model.Inventory;

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
    private ImageView wallEast;

    @FXML
    private ImageView wallNorth;

    @FXML
    private ImageView wallSouth;

    @FXML
    private ImageView wallWest;

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

    private Media sound = new Media(new File("src/View/pickup.mp3").toURI().toString());


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

    }

    @FXML
    void collectItem(ActionEvent event) {
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
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

    @FXML
    void goEast(ActionEvent event) {

        model.getDungeonLayout().setCurrCol(model.getDungeonLayout().getCurrCol() + 1);
        setRoom();

        checkRoom();
        checkSurroundings();

    }

    private void setRoom() {
        model.setMyRoom(model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()]);
//        textRoom.setText(model.getDungeonLayout().getMyDungeonRooms()[model.getDungeonLayout().getCurrRow()][model.getDungeonLayout().getCurrCol()].toString());
        if (model.getMyRoom().getCanGoNorth()) {
            wallNorth.setOpacity(0);
        }
        else {
            wallNorth.setOpacity(100);
        }
        if (model.getMyRoom().getCanGoSouth()) {
            wallSouth.setOpacity(0);
        }
        else {
            wallSouth.setOpacity(100);
        }
        if (model.getMyRoom().getCanGoEast()) {
            wallEast.setOpacity(0);
        }
        else {
            wallEast.setOpacity(100);
        }
        if (model.getMyRoom().getCanGoWest()) {
            wallWest.setOpacity(0);
        }
        else {
            wallWest.setOpacity(100);
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
//            exitButton.setOpacity(20); doesnt seem to work
//            exitButton.setDisable(true);
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

        model.getDungeonLayout().setCurrRow(model.getDungeonLayout().getCurrRow() - 1);
        setRoom();

        checkRoom();
        checkSurroundings();

    }

    @FXML
    void goSouth(ActionEvent event) {

        model.getDungeonLayout().setCurrRow(model.getDungeonLayout().getCurrRow() + 1);
        setRoom();

        checkRoom();
        checkSurroundings();

    }

    @FXML
    void goWest(ActionEvent event) {

        model.getDungeonLayout().setCurrCol(model.getDungeonLayout().getCurrCol() - 1);
        setRoom();

        checkRoom();
        checkSurroundings();

    }

    @FXML
    void showLore(ActionEvent event) {

        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/lore.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Main.getPrimaryStage().setScene(new Scene(root));

    }

    @FXML
    void endGame(ActionEvent event) {

        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/end.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Main.getPrimaryStage().setScene(new Scene(root));

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

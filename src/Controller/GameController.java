package src.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import src.Main.Main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import src.Model.DungeonAdventure;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

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

    private DungeonAdventure dungeonModel;

    public GameController() {
        dungeonModel = DungeonAdventure.getInstance();
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

        dungeonModel.setCurrScene("src/View/mainGame.fxml");


//        for (int i = 0; i < dungeonLayout.getMyDungeonLayout().length; i++) {
//            for (int j = 0; j < dungeonLayout.getMyDungeonLayout().length; j++) {
//                System.out.print(dungeonLayout.getMyDungeonLayout()[i][j]);
//            }
//            System.out.println();
//        }



        if (!dungeonModel.getInGame()){

            System.out.println();
            System.out.println("Start Row: " + dungeonModel.getDungeonLayout().getStartRow());
            System.out.println("Start Col: " + dungeonModel.getDungeonLayout().getStartCol());
            dungeonModel.getDungeonLayout().setCurrRow(dungeonModel.getDungeonLayout().getStartRow());
            dungeonModel.getDungeonLayout().setCurrCol(dungeonModel.getDungeonLayout().getStartCol());
    
            System.out.println(dungeonModel.getDungeonLayout().getMyDungeonRooms()[dungeonModel.getDungeonLayout().getCurrRow()][dungeonModel.getDungeonLayout().getCurrCol()].toString());

            dungeonModel.setInGame(true);

        }

        //for View??

        checkSurroundings(); // For arrow buttons.

        //we want to set the text in the view to be the current room as a tostring for now, will change to image tiles later
        textRoom.setText(dungeonModel.getDungeonLayout().getMyDungeonRooms()[dungeonModel.getDungeonLayout().getCurrRow()][dungeonModel.getDungeonLayout().getCurrCol()].toString());

    }
    
    @FXML
    void goEast(ActionEvent event) {

        dungeonModel.getDungeonLayout().setCurrCol(dungeonModel.getDungeonLayout().getCurrCol() + 1);
        textRoom.setText(dungeonModel.getDungeonLayout().getMyDungeonRooms()[dungeonModel.getDungeonLayout().getCurrRow()][dungeonModel.getDungeonLayout().getCurrCol()].toString());

        checkSurroundings();

    }

    @FXML
    void goNorth(ActionEvent event) {

        dungeonModel.getDungeonLayout().setCurrRow(dungeonModel.getDungeonLayout().getCurrRow() - 1);
        textRoom.setText(dungeonModel.getDungeonLayout().getMyDungeonRooms()[dungeonModel.getDungeonLayout().getCurrRow()][dungeonModel.getDungeonLayout().getCurrCol()].toString());

        checkSurroundings();

    }

    @FXML
    void goSouth(ActionEvent event) {

        dungeonModel.getDungeonLayout().setCurrRow(dungeonModel.getDungeonLayout().getCurrRow() + 1);
        textRoom.setText(dungeonModel.getDungeonLayout().getMyDungeonRooms()[dungeonModel.getDungeonLayout().getCurrRow()][dungeonModel.getDungeonLayout().getCurrCol()].toString());

        checkSurroundings();

    }

    @FXML
    void goWest(ActionEvent event) {

        dungeonModel.getDungeonLayout().setCurrCol(dungeonModel.getDungeonLayout().getCurrCol() - 1);
        textRoom.setText(dungeonModel.getDungeonLayout().getMyDungeonRooms()[dungeonModel.getDungeonLayout().getCurrRow()][dungeonModel.getDungeonLayout().getCurrCol()].toString());

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

    //in the game:
    private void checkSurroundings(){
        if (dungeonModel.getDungeonLayout().getMyDungeonRooms()[dungeonModel.getDungeonLayout().getCurrRow()][dungeonModel.getDungeonLayout().getCurrCol()].getCanGoWest() == false){
            buttonWest.setDisable(true);
        }
        else {
            buttonWest.setDisable(false);
        }

        if (dungeonModel.getDungeonLayout().getMyDungeonRooms()[dungeonModel.getDungeonLayout().getCurrRow()][dungeonModel.getDungeonLayout().getCurrCol()].getCanGoEast() == false){
            buttonEast.setDisable(true);
        }
        else {
            buttonEast.setDisable(false);
        }

        if (dungeonModel.getDungeonLayout().getMyDungeonRooms()[dungeonModel.getDungeonLayout().getCurrRow()][dungeonModel.getDungeonLayout().getCurrCol()].getCanGoNorth() == false){
            buttonNorth.setDisable(true);
        }
        else {
            buttonNorth.setDisable(false);
        }

        if (dungeonModel.getDungeonLayout().getMyDungeonRooms()[dungeonModel.getDungeonLayout().getCurrRow()][dungeonModel.getDungeonLayout().getCurrCol()].getCanGoSouth() == false){
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

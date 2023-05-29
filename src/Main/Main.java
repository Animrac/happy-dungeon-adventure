package src.Main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import src.Model.SceneMaker;

import java.io.File;
import java.io.IOException;


public class Main extends Application {
    private static Stage primaryStage;

    @FXML
    private MenuBar myMenuBar;

    @FXML
    private MenuItem menuControls;

    @FXML
    private AnchorPane currPane;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox mainVBox;

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            playAudio();

            this.primaryStage = primaryStage;
            primaryStage.getIcons().add(new Image("src/View/icon.png"));
            primaryStage.setTitle("Happy Dungeon Adventure!");

            Scene scene = SceneMaker.createScene("src/View/start.fxml");

            primaryStage.setScene(scene);
//            primaryStage.setMaxHeight(400);
//            primaryStage.setMinHeight(400);
//            primaryStage.setMaxWidth(600);
//            primaryStage.setMinWidth(600);
//            primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
//                if (newValue)
//                    primaryStage.setMaximized(false);
//            });
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void playAudio() {
        Media music = new Media(new File("src/View/lullaby.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    /**
     * Driver method for class (and the entire game).
     * Runs game play.
     *
     * @param theArgs
     */
    public static void main(String[] theArgs) {
        launch(theArgs);

        //Console Version of the Game
//        gamePlay();

    }

    @FXML
    void showLore(ActionEvent event) throws IOException {
        Scene scene = SceneMaker.createScene("src/View/lore.fxml");
        primaryStage.setScene(scene);
    }


    public static Stage getPrimaryStage(){
        return primaryStage;
    }


}

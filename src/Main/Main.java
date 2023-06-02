package src.Main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import src.Model.Monster;
import src.Model.MonsterFactory;
import src.Model.SceneMaker;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;


public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            playAudio();

            this.primaryStage = primaryStage;
            primaryStage.getIcons().add(new Image("src/View/icon.png"));
            primaryStage.setTitle("Happy Dungeon Adventure!");

            Scene scene = SceneMaker.createScene("src/View/start.fxml");

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void playAudio() throws MalformedURLException {
        Media music = new Media(new File("src/View/lullaby.mp3").toURI().toURL().toExternalForm());
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

package src.Main;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

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

    public static Scene createScene(String fxmlFilePath) {
        Scene scene = null;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlFilePath));

        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        playAudio();

        this.primaryStage = primaryStage;
//        GameView view = new GameView();
//        DungeonAdventure model = new DungeonAdventure();
//        Scene scene = createScene("src/View/start.fxml");

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/start.fxml"));
//        Parent root = loader.load();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/start.fxml"));
//        Scene root = FXMLLoader.load(getClass().getResource("/View/nameCharacter.fxml"));
        primaryStage.setTitle("Happy Dungeon Adventure!");
//        primaryStage.setScene(root);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
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
        Scene scene = createScene("src/View/lore.fxml");
        primaryStage.setScene(scene);
    }

    @FXML
    void showCharacterSelection(ActionEvent event) throws IOException {
        Scene scene = createScene("src/View/nameCharacter.fxml");
        primaryStage.setScene(scene);
    }
//    @FXML
//    void newGame(ActionEvent event) throws IOException {
//        String name = view.getHeroName().getText();
//        Details heroChosen = (Details) view.getHeroChoice().getValue();
//        System.out.println(name + " is a " +heroChosen);
//        Parent root = FXMLLoader.load(getClass().getResource("resources/mainGame.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }


    public static Stage getPrimaryStage(){
        return primaryStage;
    }


}

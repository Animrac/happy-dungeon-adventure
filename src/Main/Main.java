package src.Main;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.Model.Details;
import src.Model.Dungeon;
import src.Model.DungeonAdventure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


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

    /**
     * Driver method for class (and the entire game).
     * Runs game play.
     *
     * @param theArgs
     */
    public static void main(String[] theArgs) {
//        try (BufferedReader br = new BufferedReader(new FileReader("src/View/start.fxml"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        new DungeonAdventure();
        //GUI
        launch(theArgs);

        //Console Version of the Game
//        gamePlay();

    }

    @FXML
    void showLore(ActionEvent event) throws IOException {
        Scene scene = createScene("src/View/lore.fxml");
        primaryStage.setScene(scene);
//        VBox vboxLore = FXMLLoader.load(getClass().getResource("resources/lore.fxml"));
//        rootPane.getChildren().setAll(vboxLore);
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


//    public static void setPrimaryStage(Scene theScene){
//        primaryStage.setScene(theScene);
//    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }


}

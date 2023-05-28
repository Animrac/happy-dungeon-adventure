package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.Main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BeginningController {

    @FXML
    private Button beginButton;

    @FXML
    void showCharacterSelection(ActionEvent event) throws IOException {

        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/nameCharacter.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Main.getPrimaryStage().setScene(new Scene(root)); // Change back to the original scene
    }

//    //another way to do it if we implement initializable:
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
////        Parent root = null;
////        try {
////            root = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/nameCharacter.fxml"));
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////        Parent finalRoot = root;
////        beginButton.setOnAction(event -> {
////            Main.getPrimaryStage().setScene(new Scene(finalRoot)); // Change back to the original scene
////        });
//    }

}

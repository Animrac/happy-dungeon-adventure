package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import src.Main.Main;
import src.Model.SceneMaker;

import java.io.IOException;

public class BeginningController {

    @FXML
    private Button beginButton;

    @FXML
    void showCharacterSelection(ActionEvent event) throws IOException {
        Scene scene = SceneMaker.createScene("src/View/nameCharacter.fxml");
        Main.getPrimaryStage().setScene(scene);
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

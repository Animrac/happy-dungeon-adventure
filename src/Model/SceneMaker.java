package src.Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import src.Main.Main;

import java.io.IOException;
import java.io.Serializable;

/**
 * Builds a scene based on an .fxml file.
 * @author Carmina Cruz, Hui Wagner
 */
public class SceneMaker implements Serializable {
    private static final String css = Main.class.getResource("/src/View/application.css").toExternalForm();

    public static javafx.scene.Scene createScene(String fxmlFilePath) {
        Parent root = null;

        try {
            root = (Parent)FXMLLoader.load(SceneMaker.class.getClassLoader().getResource(fxmlFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);

        scene.getStylesheets().add(css);

        return scene;

    }

}

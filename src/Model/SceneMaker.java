package src.Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import src.Main.Main;

import java.io.IOException;

public class SceneMaker {
    private static final String css = Main.class.getResource("/src/View/application.css").toExternalForm();

    public static javafx.scene.Scene createScene(String fxmlFilePath) {
//        javafx.scene.Scene scene = null;

        //            scene = new javafx.scene.Scene(loader.load());
//        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlFilePath));

        Parent root = null;
//
        try {
            root = (Parent)FXMLLoader.load(SceneMaker.class.getClassLoader().getResource(fxmlFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        scene.getStylesheets().add(SceneMaker.getStyle());

        scene.getStylesheets().add(css);

        return scene;

    }

    public static String getStyle(){
        return css;
    }

}

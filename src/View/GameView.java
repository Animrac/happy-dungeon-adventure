//package src.View;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.VBox;
//
//import java.io.IOException;
//
//public class GameView {
//
//    public static final String START_SCENE = "StartScene.fxml";
//    public static final String SECOND_SCENE = "GameScene.fxml";
//    public static final String LORE_SCENE = "View/lore.fxml";
//
//
//
//
//
//    // Getters
//
//
//    public MenuBar getMyMenuBar() {
//        return myMenuBar;
//    }
//
//    public MenuItem getMenuControls() {
//        return menuControls;
//    }
//
//
//
//
//    public AnchorPane getCurrPane() {
//        return currPane;
//    }
//
//
//    public AnchorPane getRootPane() {
//        return rootPane;
//    }
//
//
//    public VBox getMainVBox() {
//        return mainVBox;
//    }
//
//
//
//    // Setters
//
//    public void setRootPane(AnchorPane rootPane) {
//        this.rootPane = rootPane;
//    }
//    public void setMyMenuBar(MenuBar myMenuBar) {
//        this.myMenuBar = myMenuBar;
//    }
//
//    public void setMenuControls(MenuItem menuControls) {
//        this.menuControls = menuControls;
//    }
//
//
//
//    public void setCurrPane(AnchorPane currPane) {
//        this.currPane = currPane;
//    }
//
//
//
//
//
//
//
//    public void setMainVBox(VBox mainVBox) {
//        this.mainVBox = mainVBox;
//    }
//
//
//
//    public Scene createScene(String fxmlFilePath) {
//        Scene scene = null;
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
//
//        try {
//            scene = new Scene(loader.load());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return scene;
//    }
//
//}
//

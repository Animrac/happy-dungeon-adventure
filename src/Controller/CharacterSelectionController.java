package src.Controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import src.Model.Details;
import src.Main.*;
import src.Model.DungeonAdventure;
import src.Model.SceneMaker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CharacterSelectionController implements Initializable {

    @FXML
    private ChoiceBox<?> heroChoice;

    @FXML
    private TextField heroName;

    @FXML
    private TextField heroSummary = new TextField("<insert summary of hero>");

    @FXML
    private ChoiceBox<?> difficultyChoice;

    @FXML
    private TextField difficultySummary = new TextField("<insert summary of difficulty>");

    private DungeonAdventure model;

    public CharacterSelectionController() {
        model = DungeonAdventure.getInstance();
    }

    @Override
    public void initialize(URL theURL, ResourceBundle theResourceBundle) { //this is every time a Parent is called i think

        model.setCurrScene("src/View/nameCharacter.fxml");

        ObservableList<Details> heroData = FXCollections.observableArrayList();
        ObservableList<Details> difficultyData = FXCollections.observableArrayList();

        heroData.add(new Details("Warrior", "so that I can kill things easily."));
        heroData.add(new Details("Thief", "so that I am sneaky."));
        heroData.add(new Details("Priestess", "so that I can help myself."));

        difficultyData.add(new Details("Easy-peasy", "Generates a random 5x5 map."));
        difficultyData.add(new Details("Default", "Generates a random 10x10 map."));
        difficultyData.add(new Details("Why", "Generates a random 100x100 map."));

        getHeroChoice().setItems(heroData);
        getHeroChoice().getSelectionModel().selectFirst();
        getHeroSummary().setText(heroData.get(0).getText());
        getHeroChoice().valueProperty().addListener((o, ov, nv) -> {
            Details d = (Details) nv;
            getHeroSummary().setText(d.getText());
        });

        getDifficultyChoice().setItems(difficultyData);
        getDifficultyChoice().getSelectionModel().selectFirst();
        getDifficultySummary().setText(difficultyData.get(0).getText());
        getDifficultyChoice().valueProperty().addListener((o, ov, nv) -> {
            Details d = (Details) nv;
            getDifficultySummary().setText(d.getText());
        });
    }


    @FXML
    void newGame(ActionEvent event) throws IOException {
        model.setMyName(getHeroName().getText());
        model.setMyClass((getHeroChoice().getValue()).toString());
        model.setMyDifficulty(getDifficultyChoice().getValue().toString());

        Scene scene = SceneMaker.createScene("src/View/mainGame.fxml");
        Main.getPrimaryStage().setScene(scene);

    }


    @FXML
    void showLore(ActionEvent event) {
        Scene scene = SceneMaker.createScene("src/View/lore.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void showControls(ActionEvent event) {
        Scene scene = SceneMaker.createScene("src/View/controls.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void quit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void load(ActionEvent event) {
        //GameController.gameLoad();
    }

    public TextField getHeroName() {
        return heroName;
    }

    public ChoiceBox getHeroChoice() {
        return heroChoice;
    }

    public TextField getHeroSummary() {
        return heroSummary;
    }

    public ChoiceBox getDifficultyChoice() {
        return difficultyChoice;
    }

    public TextField getDifficultySummary() {
        return difficultySummary;
    }

}

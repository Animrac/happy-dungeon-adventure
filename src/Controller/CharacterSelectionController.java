package src.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import src.Model.Details;
import src.Main.*;
import src.Model.DungeonAdventure;
import src.Model.SceneMaker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CharacterSelectionController implements Initializable {

    private DungeonAdventure dungeonModel;

    public CharacterSelectionController() {
        dungeonModel = DungeonAdventure.getInstance();
    }

    @FXML
    private ChoiceBox<?> heroChoice;

    @FXML
    private TextField heroName;

    @FXML
    private TextField heroSummary = new TextField("<-- Select your hero!");

    @FXML
    private VBox mainVBox;

    @FXML
    private MenuItem menuControls;

    @FXML
    private MenuItem menuLore;

    @FXML
    private MenuBar myMenuBar;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button startButton;


    @Override
    public void initialize(URL theURL, ResourceBundle theResourceBundle) { //this is every time a Parent is called i think

        dungeonModel.setCurrScene("src/View/nameCharacter.fxml");

        ObservableList<Details> heroData = FXCollections.observableArrayList();

        heroData.add(new Details("Warrior", "so that I can kill things easily."));
        heroData.add(new Details("Thief", "so that I am sneaky."));
        heroData.add(new Details("Priestess", "so that I can help myself."));

        getHeroChoice().setItems(heroData);
        getHeroChoice().getSelectionModel().selectFirst();
        getHeroSummary().setText(heroData.get(0).getText());
        getHeroChoice().valueProperty().addListener((o, ov, nv) -> {
            Details d = (Details) nv;
            getHeroSummary().setText(d.getText());
        });
    }

    @FXML
    void newGame(ActionEvent event) throws IOException {
        String name = getHeroName().getText();
        Details heroChosen = (Details) getHeroChoice().getValue();
        System.out.println(name + " is a " +heroChosen);

        Scene scene = SceneMaker.createScene("src/View/mainGame.fxml");
        Main.getPrimaryStage().setScene(scene);

    }


    @FXML
    void showLore(ActionEvent event) {
        Scene scene = SceneMaker.createScene("src/View/lore.fxml");
        Main.getPrimaryStage().setScene(scene);
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
    public Button getStartButton() {
        return startButton;
    }
    public void setHeroName(TextField heroName) {
        this.heroName = heroName;
    }
    public void setHeroChoice(ChoiceBox heroChoice) {
        this.heroChoice = heroChoice;
    }
    public void setStartButton(Button startButton) {
        this.startButton = startButton;
    }
    public void setHeroSummary(TextField heroSummary) {
        this.heroSummary = heroSummary;
    }

}

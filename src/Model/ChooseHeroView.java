//package src.Model;
//
//import javafx.application.Application;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class ChooseHeroView extends Application implements Initializable {
//
////    Details heroDat[] = {new Details("Warrior", "so that I can kill things easily."),
////            new Details("Thief", "so that I am sneaky."),
////            new Details("Priestess", "so that I can help myself.")};
////    @FXML
////    private ChoiceBox heroChoice = new ChoiceBox(FXCollections.observableArrayList(heroDat));;
//
//    @FXML
//    private ChoiceBox heroChoice;
//    @FXML
//    private TextField heroSummary = new TextField("<-- Select your hero!");
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) { //this is every time a Parent is called i think
//        ObservableList<Details> heroData = FXCollections.observableArrayList();
////        String st[] = { "Arnab", "Andrew", "Ankit", "None" };
//        heroData.add(new Details("Warrior", "so that I can kill things easily."));
//        heroData.add(new Details("Thief", "so that I am sneaky."));
//        heroData.add(new Details("Priestess", "so that I can help myself."));
//        heroChoice.setItems(heroData);
//        heroChoice.getSelectionModel().selectFirst();
//        heroSummary.setText(heroData.get(0).getText());
//        heroChoice.valueProperty().addListener((o, ov, nv) -> {
//            Details d = (Details) nv;
//            heroSummary.setText(d.getText());
//        });
//    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//
////        ObservableList<Details> heroData = FXCollections.observableArrayList();
//////        String st[] = { "Arnab", "Andrew", "Ankit", "None" };
////        heroData.add(new Details("Warrior", "so that I can kill things easily."));
////        heroData.add(new Details("Thief", "so that I am sneaky."));
////        heroData.add(new Details("Priestess", "so that I can help myself."));
////        heroChoice.setItems(heroData);
////        heroChoice.getSelectionModel().selectFirst();
////        heroSummary.setText(heroData.get(0).getText());
////        heroChoice.valueProperty().addListener((o, ov, nv) -> {
////            Details d = (Details) nv;
////            heroSummary.setText(d.getText());
////        });
//
////        currScene = "nameCharacter";
//        Parent root = FXMLLoader.load(getClass().getResource("resources/nameCharacter.fxml"));
//        primaryStage.setTitle("Happy Dungeon Adventure!");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
//    }
//
//
//    private static class Details {
//
//        private final StringProperty name;
//        private final StringProperty text;
//
//        public Details(String name, String text) {
//            this.name = new SimpleStringProperty(name);
//            this.text = new SimpleStringProperty(text);
//        }
//        public String getText() {
//            return text.get();
//        }
//        public String getName() {
//            return name.get();
//        }
//        @Override
//        public String toString() {
//            return getName();
//        }
//    }
//}

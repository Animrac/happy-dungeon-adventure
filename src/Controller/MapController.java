package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import src.Main.Main;
import src.Model.DungeonAdventure;
import src.Model.SceneMaker;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller class for the map scene.
 *
 * @author Carmina Cruz
 * @version 06/02/23
 */
public class MapController implements Initializable {

    @FXML
    private GridPane mapGrid;

    private DungeonAdventure model;

    public MapController() {
        model = DungeonAdventure.getInstance();
    }

    @FXML
    void returnGame(ActionEvent event) {
        Scene scene = SceneMaker.createScene(model.getCurrScene());
        Main.getPrimaryStage().setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapGrid.setAlignment(Pos.CENTER);

//        System.out.println("Dungeon:");
//        for (int i = 0; i < model.getMyDungeonLayout().getMyDungeonLayout().length; i++) {
//            for (int j = 0; j < model.getMyDungeonLayout().getMyDungeonLayout()[i].length; j++) {
//                System.out.print(model.getMyDungeonLayout().getMyDungeonLayout()[i][j]);
//            }
//            System.out.println();
//        }

        // Look at 4 rooms all around player
        for (int mapRow = -4; mapRow <= 4; mapRow++) {
            for (int mapCol = -4; mapCol <= 4; mapCol++) {

                int dungeonRow = -1, dungeonCol = -1; // Should stay at this value if there is nothing
                ImageView imageView = null;

                if (mapRow == 0 && mapCol == 0) { // Where the player will be on the map
                    Image image = new Image("src/View/player.gif");
                    imageView = new ImageView(image);
                }
                else {
                    if (mapRow <= 0 &&
                            model.getMyDungeonLayout().getCurrRow() + mapRow >= 0) { // Not out of bounds:
                        dungeonCol = model.getMyDungeonLayout().getCurrRow() + mapRow;
                    } else if (mapRow >= 1 &&
                            model.getMyDungeonLayout().getCurrRow() + mapRow <= model.getMyDungeonLayout().getMyDungeonRooms().length - 1) {
                        dungeonCol = model.getMyDungeonLayout().getCurrRow() + mapRow;
                    }

                    if (mapCol <= 0 &&
                            model.getMyDungeonLayout().getCurrCol() + mapCol >= 0) { // Not out of bounds:
                        dungeonRow = model.getMyDungeonLayout().getCurrCol() + mapCol;
                    }
                    else if (mapCol >= 1 &&
                            model.getMyDungeonLayout().getCurrCol() + mapCol <= model.getMyDungeonLayout().getMyDungeonRooms()[0].length - 1) {
                        dungeonRow = model.getMyDungeonLayout().getCurrCol() + mapCol;
                    }

                    Image image = null;

                    if (!(dungeonRow == -1 || dungeonCol == -1)) { // The higher on this list, the more priority
//                        System.out.println("Row: " + dungeonRow + ", Column: " + dungeonCol);
                        if (model.getMyDungeonLayout().getMyDungeonRooms()[dungeonCol][dungeonRow].getHasPit()) {
                            image = new Image("src/View/pit.png");
                        }
                        else if (model.getMyDungeonLayout().getMyDungeonRooms()[dungeonCol][dungeonRow].getHasEntrance()) {
                            image = new Image("src/View/entrance.png");
                        }
                        else if (model.getMyDungeonLayout().getMyDungeonRooms()[dungeonCol][dungeonRow].getHasExit()) {
                            image = new Image("src/View/exit.png");
                        }
                        else if (model.getMyDungeonLayout().getMyDungeonRooms()[dungeonCol][dungeonRow].getHasHealingPotion()) {
                            image = new Image("src/View/healthBig.png");
                        }
                        else if (model.getMyDungeonLayout().getMyDungeonRooms()[dungeonCol][dungeonRow].getHasVisionPotion()) {
                            image = new Image("src/View/visionBig.png");
                        }
                        else if (model.getMyDungeonLayout().getMyDungeonRooms()[dungeonCol][dungeonRow].getHasPillar()) {
                            image = new Image("src/View/pillarColumn.png");
                        }
                        else if (model.getMyDungeonLayout().getMyDungeonRooms()[dungeonCol][dungeonRow].getHasMonster()) {
                            image = new Image("src/View/tomnook.png");
                        }
                        else if (model.getMyDungeonLayout().getMyDungeonRooms()[dungeonCol][dungeonRow].getHasWall()) {
                            image = new Image("src/View/tree.png");
                        }
                    }
                    else { // Out of bounds
                        image = new Image("src/View/grass.png");
                    }

                    imageView = new ImageView(image);

                }
                // Add the image view to the grid pane
                if (imageView != null) {
                    imageView.setFitWidth(35);
                    imageView.setFitHeight(35);
                    mapGrid.add(imageView, mapCol+4, mapRow+4);
                }
            }
        }
    }
}
package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import src.Main.Main;
import src.Model.DungeonAdventure;
import src.Model.SceneMaker;

import java.net.URL;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private ImageView healthPotion;

    @FXML
    private GridPane mapGrid;

    @FXML
    private ImageView monster;

    @FXML
    private ImageView pillar;

    @FXML
    private ImageView visionPotion;

    @FXML
    private Rectangle wall;

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

        System.out.println("Dungeon:");
        for (int i = 0; i < model.getMyDungeonLayout().getMyDungeonLayout().length; i++) {
            for (int j = 0; j < model.getMyDungeonLayout().getMyDungeonLayout()[i].length; j++) {
                System.out.print(model.getMyDungeonLayout().getMyDungeonLayout()[i][j]);
            }
            System.out.println();
        }

        // Look at 4 rooms all around player
        for (int row = -4; row <= 4; row++) {
            for (int col = -4; col <= 4; col++) {
                int mapRow = 0, mapCol = 0;
                ImageView imageView = null;// only one image for now

                if (row == 0 && col == 0) { // Where the player is
                    Image image = new Image("src/View/player.gif");
//                    Image image = new Image("src/View/tomnook.png");
                    System.out.println("hit");
                    imageView = new ImageView(image);
                }
                else {
                    if (row <= 0 &&
                            model.getMyDungeonLayout().getCurrRow() + row >= 0) { // Not out of bounds:
                        mapRow = model.getMyDungeonLayout().getCurrRow() + row;
                    } else if (row >= 1 &&
                            model.getMyDungeonLayout().getCurrRow() + row <= model.getMyDungeonLayout().getMyDungeonRooms()[0].length - 1) {
                        mapRow = model.getMyDungeonLayout().getCurrRow() + row;
                    }

                    if (col <= 0 &&
                            model.getMyDungeonLayout().getCurrCol() + col >= 0) { // Not out of bounds:
                        mapCol = model.getMyDungeonLayout().getCurrCol() + col;
                    }
                    else if (col >= 1 &&
                            model.getMyDungeonLayout().getCurrCol() + col <= model.getMyDungeonLayout().getMyDungeonRooms()[0].length - 1) {
                        mapCol = model.getMyDungeonLayout().getCurrCol() + col;
                    }


                    if (mapRow != -1 || mapCol != -1) {
                        if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasPit()) {
                            Image image = new Image("src/View/pit.png");
                            imageView = new ImageView(image);
                        }
                        else if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasHealingPotion()) {
                            Image image = new Image("src/View/healthpotion.png");
                            imageView = new ImageView(image);
                        }
                        else if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasVisionPotion()) {
                            Image image = new Image("src/View/visionpotion.png");
                            imageView = new ImageView(image);
                        }
                        else if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasMonster()) {
                            Image image = new Image("src/View/tomnook.png");
                            imageView = new ImageView(image);
                        }
                        else if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasPillar()) {
                            Image image = new Image("src/View/pillar.png");
                            imageView = new ImageView(image);
                        }
                        else if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasEntrance()) {
                            Image image = new Image("src/View/entrance.png");
                            imageView = new ImageView(image);
                        }
                        else if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasExit()) {
                            Image image = new Image("src/View/exit.png");
                            imageView = new ImageView(image);
                        }
                        else if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasWall()) {
                            Image image = new Image("src/View/tree.png");
                            imageView = new ImageView(image);
                        }


                    }
                }
                // Add the image view to the grid pane
                if (imageView != null) {
                    imageView.setFitWidth(30);
                    imageView.setFitHeight(30);
                    mapGrid.add(imageView, row+4, col+4);
                }
            }
        }
    }
}
//       for (int row = 0; row <= 8; row++) {
//               for (int col = 0; col <= 8; col++) {
//               int mapRow = -1, mapCol = -1;
//               ImageView imageView = null;// only one image for now
//
//               if (row == 4 && col == 4) { // Where the player is
//               Image image = new Image("src/View/player.gif");
////                    Image image = new Image("src/View/tomnook.png");
//               System.out.println("hit");
//               imageView = new ImageView(image);
//               }
//               else {
//               if (row <= 3 &&
//               model.getMyDungeonLayout().getCurrRow() - row >= 0) { // Not out of bounds:
//               mapRow = model.getMyDungeonLayout().getCurrRow() - row;
//               } else if (row >= 4 &&
//               model.getMyDungeonLayout().getCurrRow() - 4 + row <= model.getMyDungeonLayout().getMyDungeonRooms()[0].length - 1) {
//               mapRow = model.getMyDungeonLayout().getCurrRow() - 4 + row;
//               }
//               else {
//               mapRow = 500;
//               }
//
//               if (col <= 3 &&
//               model.getMyDungeonLayout().getCurrCol() + col >= 0) { // Not out of bounds:
//               mapCol = model.getMyDungeonLayout().getCurrCol() + col;
//               }
//               else if (col >= 4 &&
//               model.getMyDungeonLayout().getCurrCol() - 4 + col <= model.getMyDungeonLayout().getMyDungeonRooms()[0].length - 1) {
//               mapCol = model.getMyDungeonLayout().getCurrCol() - 4 + col;
//               }
//               else {
//               mapCol = 500;
//               }
//
//
//               if (!(mapRow == 500 || mapCol == 500)) {
//               if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasPit()) {
//               Image image = new Image("src/View/pit.png");
//               imageView = new ImageView(image);
//               }
//               else if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasHealingPotion()) {
//               Image image = new Image("src/View/healthpotion.png");
//               imageView = new ImageView(image);
//               }
//               else if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasVisionPotion()) {
//               Image image = new Image("src/View/visionpotion.png");
//               imageView = new ImageView(image);
//               }
//               else if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasMonster()) {
//               Image image = new Image("src/View/tomnook.png");
//               imageView = new ImageView(image);
//               }
//               else if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasPillar()) {
//               Image image = new Image("src/View/pillar.png");
//               imageView = new ImageView(image);
//               }
//               else if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasEntrance()) {
//               Image image = new Image("src/View/entrance.png");
//               imageView = new ImageView(image);
//               }
//               else if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasExit()) {
//               Image image = new Image("src/View/exit.png");
//               imageView = new ImageView(image);
//               }
//               else if (model.getMyDungeonLayout().getMyDungeonRooms()[mapCol][mapRow].getHasWall()) {
//               Image image = new Image("src/View/tree.png");
//               imageView = new ImageView(image);
//               }
//
//
//               }
//               }
//               // Add the image view to the grid pane
//               if (imageView != null && (mapCol != 500 || mapRow != 500)) {
//               imageView.setFitWidth(30);
//               imageView.setFitHeight(30);
//               mapGrid.add(imageView, row, col);
//               }
//               }
//               }
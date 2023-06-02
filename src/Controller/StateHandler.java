package src.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import src.Main.Main;
import src.Model.DungeonAdventure;
import src.Model.SceneMaker;

import java.io.*;

public interface StateHandler {

    /**
     * The instance of DungeonAdventure, the current game.
     */
    DungeonAdventure model = DungeonAdventure.getInstance();

    default void refreshScene() {
        Scene scene = SceneMaker.createScene(model.getCurrScene());
        Main.getPrimaryStage().setScene(scene);
    }

    default void loadState() {
        try {
            FileInputStream fileIn = new FileInputStream("saveFile.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            DungeonAdventure loadedInstance = (DungeonAdventure) in.readObject();

            model.setMyHero(loadedInstance.getMyHero());
            model.setMyDungeonLayout(loadedInstance.getMyDungeonLayout());
            model.setCurrScene(loadedInstance.getCurrScene());
            model.setMyName(loadedInstance.getMyName());
            model.setMyClass(loadedInstance.getMyClass());
            model.setInGame(loadedInstance.getInGame());
            model.setMyRoom(loadedInstance.getMyRoom());
            model.setMyDifficulty(loadedInstance.getMyDifficulty());
            model.setMyInventory(loadedInstance.getMyInventory());

            in.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    default void saveState() {
        try {
            FileOutputStream fileOut = new FileOutputStream("saveFile.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(DungeonAdventure.getInstance());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}

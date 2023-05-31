package src.Model;

import java.io.*;

public class SaveLoadGame implements Serializable {
    String savedGame = "SavedGame.txt";

    public void saveGame(){
        try {
            FileOutputStream FileOutputStream = new FileOutputStream(savedGame);
        }

        catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public void loadGame(){

    }
}

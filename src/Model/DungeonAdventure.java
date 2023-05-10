package src.Model;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Button;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the driver class for the entire Heroes versus Monsters game.
 *
 * @author Anastasia Vilenius, Carmina Cruz, Hui Wagner
 * @version 05/9/23
 */
public class DungeonAdventure extends Application {

    /**
     * Random object used to generate random monsters.
     */
    private static final Random MY_RANDOM = new Random();

    @FXML
    private MenuBar myMenuBar;

    @FXML
    private Button startButton;

//    @FXML
//    private Parent lore ;
//    @FXML MenuBar myMenuBar;
    @FXML
    private AnchorPane lore;
    @FXML
    void newGame(ActionEvent event) {
        System.out.println("You thought there was a game???");
    }

//    private void setDynamicPane(AnchorPane dynamicPane){
//        this.lore.getChildren().clear();
//        this.lore.getChildren().add(dynamicPane);
//    }


    //TODO this doesnt work
    @FXML
    void showLore(ActionEvent event) throws IOException {
//        setDynamicPane((FXMLLoader.load(getClass())).getResources("lore.fxml"));
        System.out.println("juicy lore test");
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("lore.fxml"));
        stage.setScene(scene);
        stage.show();

////        Stage stage = (Stage) myMenuBar.getScene().getWindow();
//
////        MenuItem button = (MenuItem)event.getSource();
//
//        Parent root = FXMLLoader.load(getClass().getResource("lore.fxml"));
//
//        Scene loreScene = new Scene(root);
//
//        primaryStage.setScene(loreScene);
////        loreScene.setRoot(root);

    }
    Button button;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("DunAdv.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("lore.fxml"));

        primaryStage.setTitle("Happy Dungeon Adventure!");
        primaryStage.setScene(new Scene(root));




//        Label intro = new Label(displayIntro());
//
//        button = new Button();
//        button.setText("click me");
//
//        button.setOnAction(e -> System.out.println("you just clicked me!"));
////        button.setOnAction(this);
//
//        StackPane layout = new StackPane();
//        layout.getChildren().add(intro);
//        layout.getChildren().add(button);
//
//        Scene scene = new Scene(layout, 600, 400);
//        primaryStage.setScene(scene);

        primaryStage.show();
    }

    //if we did this, we have to implement EventHandler, but lambda expressions seem easier
//    @Override
//    public void handle(Event event) {
//        if (event.getSource() == button){
//            //do something
//        }
//    }

    /**
     * Driver method for class (and entire game)
     * Calls displayIntro() class to display an introduction to the game.
     * Runs game play.
     *
     * @param theArgs
     */
    public static void main(String[] theArgs) { //this main method is just to test
//        displayIntro();

        launch(theArgs);


//        //1. Create the frame.
//        JFrame frame = new JFrame("Happy Dungeon Adventure!");
//        frame.setBounds(600, 400, 600, 400);
//
//        //Set the frame icon to an image loaded from a file.
////        frame.setIconImage(new ImageIcon(imgURL).getImage());
//
//        //2. Optional: What happens when the frame closes?
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //3. Create components and put them in the frame.
//        //...create emptyLabel...
////        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
//
//        //4. Size the frame.
//        frame.pack();
//
//        //5. Show it.
//        frame.setVisible(true);
//
//        Dungeon layout = null;
//        Scanner input = new Scanner(System.in);
//
//        System.out.println("--Would you like a custom dungeon? Y/N--");
//        String custom = input.nextLine();  // Read user input
//
//        if (custom.equals("Y") || custom.equals("y") || custom.equals("yes") || custom.equals("Yes")) { //i know there are better ways but im lazy rn
//            System.out.println("--Rows (must be at least 5):--");
//            int cRows = Integer.parseInt(input.nextLine()) + 2;
//            System.out.println("--Columns (must be at least 5):--");
//            int cCols = Integer.parseInt(input.nextLine()) + 2;
//            layout = new Dungeon(cRows, cCols);
//        } else {
//            layout = new Dungeon();
//        }
//
//        //TODO: USE REGEX FOR INVALID INPUT
//        //VISUALIZATION TEST CODE
//        System.out.println("Dungeon:");
//        for (int i = 0; i < layout.getMyDungeonLayout().length; i++) {
//            for (int j = 0; j < layout.getMyDungeonLayout()[i].length; j++) {
//                System.out.print(layout.getMyDungeonLayout()[i][j]);
//            }
//            System.out.println();
//        }
//
//
//        //getMyDungeonRooms()[y][x]
//        System.out.println();
//
//        System.out.println("Room (1,1):");
//        System.out.print(layout.getMyDungeonRooms()[1][1].toString());
//
//        System.out.println();
//
//        System.out.println("Room (3,5):");
//        System.out.print(layout.getMyDungeonRooms()[5][3].toString());
//
//        System.out.println();
//
//        System.out.println("Room (2,4):");
//        System.out.print(layout.getMyDungeonRooms()[4][2].toString());
//
//        //PRINT TEST ALL THE ROOMS EVERYWHERE, this does not include the boundary
//        for (int i = 1; i < layout.getMyDungeonRooms().length; i++) {
//            for (int j = 1; j < layout.getMyDungeonRooms()[i].length; j++) {
//                System.out.println("Room (" + j + ", " + i + ")");
//                System.out.println(layout.getMyDungeonRooms()[i][j]);
//            }
//            System.out.println();
//        }
//
//        gamePlay();

    }


//        System.out.println();
//        System.out.println("Traversed:"); //shouldn't show any P, E, or S
//        for (int i = 0; i < tempDungeon.length; i++) {
//            for (int j = 0; j < tempDungeon[i].length; j++) {
//                System.out.print(tempDungeon[i][j]);
//            }
//            System.out.println();
//        }

    /**
     * Prints out an introduction to the game.
     */
    public static String displayIntro() {
        StringBuilder s = new StringBuilder();
        s.append("You are about to begin a Happy Adventure!");
        s.append("\nBut wait!!!"
                + "\n\nUnfortunately, you were thrown in a dungeon by the scary Tom Nook because you forgot to pay your bills on time. "
                + "\n\nNow, you must venture on a dangerous expedition to collect the four pillars of OO, all while paying your rent to make it out of the dungeon alive."
                + "\n\nGood luck! You'll need it!\n");
        return s.toString();
    }

    /**
     * Prompts user to enter name of monster,
     * and then randomly generates a kind of monster.
     *
     * @return randoMonster
     */
    public static Monster spawnMonster() {
        Scanner in  = new Scanner(System.in);

        System.out.print("First, choose a name for a monster: ");
        String name = in.next();

        int rand = MY_RANDOM.nextInt(3);

        Monster randoMonster = null;

        if (rand == 0) {
            randoMonster = new monsterOne(name);
        } else if (rand == 1) {
            randoMonster = new monsterTwo(name);
        } else {
            randoMonster = new monsterThree(name);
        }
        return randoMonster;
    }

    /**
     * Prompts user to name their hero,
     * and then allows them to pick between three types of heroes to play as.
     *
     * @return chosenHero
     */
    public static Hero chooseHero() {
        Scanner in = new Scanner(System.in);

        System.out.print("Now choose a name for your hero: ");
        String name = in.next();

        System.out.println("Time to choose your hero!");
        System.out.println();
        System.out.println("1: heroOne \n2: heroTwo \n3: heroThree");
        System.out.println();

        Hero chosenHero = null;

        if (in.nextInt() == 1) {
            chosenHero = new heroOne(name);
        } else if (in.nextInt() == 2) {
            chosenHero = new heroTwo(name);
        } else if (in.nextInt() == 3) {
            chosenHero = new heroThree(name);
        }
        return chosenHero;
    }

    public static void battle(Hero hero, Monster monster) {
        while (hero.alive() && monster.alive() && !hero.runAway()) {
            System.out.println(hero.getName() + " hit points: " + hero.getHealth());
            System.out.println(monster.getName() + "hit points: " + monster.getHealth());
            hero.attack(monster);

            if(!hero.runAway()) {
                monster.attack(hero);
                System.out.println("Press enter for next round");
                Adventurer.input.nextLine();
            }
            else {
                System.out.println(hero.getName() + " bravely ran away...");
            }
        }
        if(monster.alive()) {
            System.out.println(monster.getName() + " defeated " + hero.getName() + "!");
        }
        else {
            System.out.println(hero.getName() + " defeated " + monster.getName() + "!");
        }
    }

    /**
     *
     */
    public static void gamePlay() {
        Monster monster = spawnMonster();
        System.out.println("Oh no! A " + monster.getClass() + " has spawned!\nIt is named " + monster.getName());
        System.out.println();

        Hero hero = chooseHero();
        System.out.println("Woah! " + hero.getName() + ", a " + hero.getClass() + ", dares to enter the dungeon!");
        System.out.println();

        battle(hero, monster);
    }



}

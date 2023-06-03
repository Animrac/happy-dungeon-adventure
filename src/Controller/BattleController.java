package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import src.Main.Main;
import src.Model.*;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * The controller class for the battle scene.
 *
 * @author Carmina Cruz
 * @version 06/02/23
 */
public class BattleController implements Initializable {

    final static Random MY_RANDOM = new Random();

    @FXML
    private ImageView bossMonster;

    @FXML
    private ImageView player;

    @FXML
    private Text myHealth;

    @FXML
    private TextArea myLog;

    @FXML
    private Text myMonsterHealth;

    @FXML
    private Text myMonsterName;

    @FXML
    private Text myName;

    @FXML
    private Button pokemonButton;

    @FXML
    private Button runButton;

    @FXML
    private Button inventoryButton;

    @FXML
    private Button attackButton;

    @FXML
    private Button specialAttackButton;

    /**
     * The instance of DungeonAdventure, the current game.
     */
    private DungeonAdventure model;

    /**
     * Constructor of the class implementing the Singleton design pattern
     * to get the single instance of the DungeonAdventure class.
     */
    public BattleController() {
        model = DungeonAdventure.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        model.setCurrScene("src/View/battle.fxml");

        model.setInBattle(false);
        myName.setText(model.getMyName());
        myHealth.setText(Integer.toString(model.getMyHero().getHealth()));

        myLog.setText("What will " + model.getMyName() + " do?");
        //TODO set the monster, monster health, current health, names, battledialogue
        model.setMyCurrMonster(model.getRandomMonster());

        myMonsterHealth.setText(Integer.toString(model.getMyCurrMonster().getHealth()));
        myMonsterName.setText(model.getMyCurrMonster().getName());
    }

    @FXML
    void attack(ActionEvent event) {

        StringBuilder log = new StringBuilder();

        double randDoubleHero = MY_RANDOM.nextDouble();
        double randDoubleMonster = MY_RANDOM.nextDouble();

        if (randDoubleHero <= model.getMyHero().getAttackOdds()) {
            int heroDamage = model.getMyHero().generateDamage();
            model.getMyCurrMonster().setHealth(model.getMyCurrMonster().getHealth() - heroDamage);
            myMonsterHealth.setText(Integer.toString(model.getMyCurrMonster().getHealth()));

            log.append(model.getMyName() + " attacks! \n" +
                    model.getMyCurrMonster().getName() + " loses " + heroDamage + " HP!\n");
        } else {
            log.append(model.getMyName() + " misses! Oops, silly you!\n");
        }

        if (model.getMyCurrMonster().getHealth() > 0) { // if the monster didn't die, allow them to attack
            log.append(monsterAttack());
        }

        checkHealth();

        myLog.setText(log.toString());

    }

    @FXML
    void specialAttack(ActionEvent event) { //TODO JUST A NORMAL ATTACK

        StringBuilder log = new StringBuilder();

        double randDoubleHero = MY_RANDOM.nextDouble();

        //HERO TIME
        if (randDoubleHero <= model.getMyHero().getAttackOdds()) {
//            int heroDamage = model.getMyHero().generateSpecialDamage(); TODO ANASTASIA PUT GENERATE SPECIAL DAMAGE IN HERO
            int heroDamage = model.getMyHero().generateDamage();
            model.getMyCurrMonster().setHealth(model.getMyCurrMonster().getHealth() - heroDamage);
            myMonsterHealth.setText(Integer.toString(model.getMyCurrMonster().getHealth()));

            log.append(model.getMyName() + " uses a special attack! \n" +
                    model.getMyCurrMonster().getName() + " loses " + heroDamage + " HP!\n");
            
        } else {
            log.append(model.getMyName() + " misses! Oops, silly you!\n");
        }

        if (model.getMyCurrMonster().getHealth() > 0) { // if the monster didn't die, allow them to attack
            log.append(monsterAttack());
        }

        checkHealth();

        myLog.setText(log.toString());

    }

    private StringBuilder monsterAttack() {

        StringBuilder log = new StringBuilder();

        double randDoubleMonster = MY_RANDOM.nextDouble();

        //MONSTER TIME
        if (randDoubleMonster <= model.getMyCurrMonster().getAttackOdds()) {

            int monsterDamage = model.getMyCurrMonster().generateDamage();
            model.getMyHero().setHealth(model.getMyHero().getHealth() - monsterDamage);
            myHealth.setText(Integer.toString(model.getMyHero().getHealth()));

            log.append(model.getMyCurrMonster().getName() + " bites you!\n" +
                    model.getMyName() + " loses " + monsterDamage + " HP!");
        } else {
            log.append(model.getMyCurrMonster().getName() + " misses! Hooray!");
        }

        checkHealth();

        return log;
    }

    @FXML
    void noPokemon(ActionEvent event) {
        myLog.setText("What are you doing???");
    }

    @FXML
    void openInventory(ActionEvent event) {
        Scene scene = SceneMaker.createScene("src/View/inventory.fxml");
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void runAway(ActionEvent event) {
        model.setInBattle(false);
        model.getMyRoom().removeRoomMonster();

        Scene scene = SceneMaker.createScene("src/View/mainGame.fxml");
        Main.getPrimaryStage().setScene(scene);
    }





    public static void battle(Hero hero, Monster monster) {
        while (hero.alive() && monster.alive() && !hero.runAway()) {
            System.out.println(hero.getName() + " hit points: " + hero.getHealth());
            System.out.println(monster.getName() + "hit points: " + monster.getHealth());
            hero.attack(monster);

            if(!hero.runAway()) {
                monster.attack(hero);
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

    private void checkHealth() {
        if (model.getMyCurrMonster().getHealth() <= 0) {
            myLog.setText(model.getMyCurrMonster().getName() + " was killed :D");

            Scene scene = SceneMaker.createScene("src/View/mainGame.fxml");
            Main.getPrimaryStage().setScene(scene);
        }
        if (model.getMyHero().getHealth() <= 0) {
            Scene scene = SceneMaker.createScene("src/View/badEnd.fxml");
            Main.getPrimaryStage().setScene(scene);
        }
    }

    /**
     *
     */
//    public void gamePlay() {
//        Monster monster = spawnMonster();
//        System.out.println("Oh no! A " + monster.getClass() + " has spawned!");
//        battle(hero, monster);
//    }



}

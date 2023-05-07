package src.Model;
import java.util.Random;

public class Room {
    private static final int HEALING_POTION_CHANCE = 10;
    private static final int VISION_POTION_CHANCE = 10;
    private static final int PIT_CHANCE = 10;

    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private boolean hasHealingPotion;
    private boolean hasVisionPotion;
    private boolean hasPit;
    private char pillar;
    private boolean hasPillar;
    private boolean isEntrance;
    private boolean isExit;

    // Room constructor
    public Room(){

        Random random = new Random();

        hasHealingPotion = Math.random() < (HEALING_POTION_CHANCE / 100.0);
        hasVisionPotion = Math.random() < (VISION_POTION_CHANCE / 100.0);
        hasPit = Math.random() < (PIT_CHANCE / 100.0);

        // Initialize the pillar and hasPillar properties
        pillar = ' ';
        hasPillar = false;

        // Initialize the entrance and exit properties to false
        isEntrance = false;
        isExit = false;
    }

    // Generates a string representation of the room
    @Override
    public String toString() {
        String top;
        String bottom;

        // Check if there is a north room, if not, display wall (***), otherwise display a door (*-*)
        if (north == null) {
            top = "***";
        } else {
            top = "*-*";
        }

        // Check if there is a south room, if not, display wall (***), otherwise display a door (*-*)
        if (south == null) {
            bottom = "***";
        } else {
            bottom = "*-*";
        }

        // Check if there is a west room, if not, display wall (*), otherwise display a door (|)
        char westChar;
        if (west == null) {
            westChar = '*';
        } else {
            westChar = '|';
        }

        // Check if there is an east room, if not, display wall (*), otherwise display a door (|)
        char eastChar;
        if (east == null) {
            eastChar = '*';
        } else {
            eastChar = '|';
        }

        // Get the content character (item or empty) for the current room
        char contentChar = getContentChar();

        // Build the middle row of the room representation
        String middle = westChar + Character.toString(contentChar) + eastChar;

        // Combine top, middle, and bottom rows to create the full room representation
        return top + "\n" + middle + "\n" + bottom;
    }

    // Determine the content character for the room
    private char getContentChar() {
        if (isEntrance) {
            return 'i';
        } else if (isExit) {
            return 'O';
        } else if (hasPillar) {
            return pillar;
        }else if (hasHealingPotion && hasVisionPotion && hasPit) {
                return 'M';
        } else if (hasHealingPotion) {
                return 'H';
        } else if (hasVisionPotion) {
                return 'V';
        } else if (hasPit) {
                return 'X';
        } else if (pillar != ' ') {
                return pillar;
        }
        return ' ';
    }

        public boolean isEntrance () {
            return isEntrance;
        }

        public void setEntrance ( boolean isEntrance){
            this.isEntrance = isEntrance;
        }

        public boolean isExit () {
            return isExit;
        }

        public void setExit ( boolean isExit){
            this.isExit = isExit;
        }

        // Getters and setters
        public Room getNorth () {
            return north;
        }

        public void setNorth (Room north){
            this.north = north;
        }

        public Room getSouth () {
            return south;
        }

        public void setSouth (Room south){
            this.south = south;
        }

        public Room getEast () {
            return east;
        }

        public void setEast (Room east){
            this.east = east;
        }

        public Room getWest () {
            return west;
        }

        public void setWest (Room west){
            this.west = west;
        }


        // Getter and setter methods for other properties
        public boolean isHasHealingPotion () {
            return hasHealingPotion;
        }

        public void setHasHealingPotion ( boolean hasHealingPotion){
            this.hasHealingPotion = hasHealingPotion;
        }

        public boolean isHasVisionPotion () {
            return hasVisionPotion;
        }

        public void setHasVisionPotion ( boolean hasVisionPotion){
            this.hasVisionPotion = hasVisionPotion;
        }

        public boolean isHasPit () {
            return hasPit;
        }

        public void setHasPit ( boolean hasPit){
            this.hasPit = hasPit;
        }

        public char getPillar () {
            return pillar;
        }

        public void setPillar ( char pillar){
            this.pillar = pillar;
        }

        public boolean isHasPillar () {
            return hasPillar;
        }

        public void setHasPillar ( boolean hasPillar){
            this.hasPillar = hasPillar;
        }
    }


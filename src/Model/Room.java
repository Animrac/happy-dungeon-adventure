package src.Model;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Room {
    private static final int HEALING_POTION_CHANCE = 10;
    private static final int VISION_POTION_CHANCE = 10;
    private static final int PIT_CHANCE = 10;

    private static final int MONSTER_CHANCE = 20; //idk yet

    private char[][] currRoom = new char[3][3]; //3x3 character array to represent the room

    private char north;
    private char south;
    private char east;
    private char west;
    private boolean hasHealingPotion;
    private boolean hasVisionPotion;
    private boolean hasPit;
    private char pillar;
    private boolean hasPillar;
    private boolean isEntrance;
    private boolean isExit;
    private boolean isAtLeastOneItem;
    private boolean hasMonster;

    // Room constructor
    public Room(char[][] theDungeonLayout, int theX, int theY, char c){
        //actually, we have the coordinates x and y in the Dungeon class for myDungeon
        if (theX == 0){
            this.west = '/';
        }
        else {
            this.west = theDungeonLayout[theX-1][theY];
        }
        if (theX == theDungeonLayout.length - 1){
            this.east = '/';
        }
        else {
            this.east = theDungeonLayout[theX+1][theY];
        }
        if (theY == 0){
            this.north = '/';
        }
        else {
            this.north = theDungeonLayout[theX][theY-1];
        }
        if (theY == theDungeonLayout.length - 1){
            this.south = '/';
        }
        else {
            this.south = theDungeonLayout[theX][theY+1];
        }


        //in a way, im already making the toString here, since it'll be a 2d array
        for(int i = 0; i < this.currRoom.length; i++){
            for(int j = 0; j < this.currRoom.length; j++){
                this.currRoom[i][j] = '*'; //fill the array all with walls for now, will overwrite later
            }
        }

        //if not a wall or edge, make it passable
        if (north != 'X' && north != '/') {
            this.currRoom[1][0] = '|';
        }
        if (south != 'X' && south != '/') {
            this.currRoom[1][2] = '|';
        }
        if (west != 'X' && west != '/') {
            this.currRoom[0][1] = '-';
        }
        if (east != 'X' && east != '/') {
            this.currRoom[2][1] = '-';
        }

        if (c == 'O'){ //empty room
            this.currRoom[1][1] = ' '; //the middle of the room, empty
            generateItem();
            generateMonster();
        }
        else if (c == 'S'){ //the entrance
            this.currRoom[1][1] = 'i';
            this.isEntrance = true;
        }
        else if (c == 'E'){ //the exit
            this.currRoom[1][1] = 'O';
            this.isExit = true;
        }
        else if (c == 'P'){ //a pillar
            this.currRoom[1][1] = 'P'; //TODO: this needs to be randomized somehow to A, E, I, P. Not just P.
            this.hasPillar = true;
            this.hasMonster = true; //since pillar should contain difficult boss monster
        }

    }

    private void generateMonster() {
        //now we'll try generating monsters for empty rooms
        int randomMonster = ThreadLocalRandom.current().nextInt(1,  100 + 1); //for health potion
        if (randomMonster <= MONSTER_CHANCE){ //also need to randomize type of monster
            this.hasMonster = true;
        }
    }

    private void generateItem() {
        //now we'll try generating items for the rooms
        int randomHeal = ThreadLocalRandom.current().nextInt(1,  100 + 1); //for health potion
//            System.out.println(randomHeal);
        if (randomHeal <= HEALING_POTION_CHANCE){
            putItemSymbol('H');
            this.hasHealingPotion = true;
        }
        int randomVision = ThreadLocalRandom.current().nextInt(1,  100 + 1); //for vision potion
//            System.out.println(randomVision);
        if (randomVision <= VISION_POTION_CHANCE){
            putItemSymbol('V');
            this.hasVisionPotion = true;
        }
        int randomPit = ThreadLocalRandom.current().nextInt(1,  100 + 1); //for potion
//            System.out.println(randomPit);
        if (randomPit <= PIT_CHANCE){
            putItemSymbol('X');
            this.hasPit = true;
        }
    }

    private void putItemSymbol(char item) {
        if (this.isAtLeastOneItem) {
            this.currRoom[1][1] = 'M';
        }
        else {
            this.currRoom[1][1] = item;
        }
        this.isAtLeastOneItem = true;
    }

    // Generates a string representation of the room
    @Override
    public String toString() {
        StringBuilder room = new StringBuilder();
        for(int i = 0; i < this.currRoom.length; i++){ //fill it all with walls for now, will overwrite later
            for(int j = 0; j < this.currRoom.length; j++){
                room.append(this.currRoom[i][j]);
            }
            room.append("\n");
        }
        return room.toString();
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

    //might need these later, not sure yet
//        public Room getNorth () {
//            return north;
//        }
//
//        public void setNorth (Room north){
//            this.north = north;
//        }
//
//        public Room getSouth () {
//            return south;
//        }
//
//        public void setSouth (Room south){
//            this.south = south;
//        }
//
//        public Room getEast () {
//            return east;
//        }
//
//        public void setEast (Room east){
//            this.east = east;
//        }
//
//        public Room getWest () {
//            return west;
//        }
//
//        public void setWest (Room west){
//            this.west = west;
//        }


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

        public boolean HasMonster () {
        return hasMonster;
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
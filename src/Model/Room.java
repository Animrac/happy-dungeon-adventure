package src.Model;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

    /*

    Room 2d Array Key:
    M - Multiple Items
    X - Pit
    i - Entrance (In)
    O - Exit (Out)
    V - Vision Potion
    H - Healing Potion ▪ <space> - Empty Room
    A, E, I, P - Pillars

     */

/**
 * The model class for the rooms in the dungeon.
 *
 * @author Carmina Cruz, Hui Wagner
 * @version 06/02/23
 */
public class Room implements Serializable {

    /**
     * The chance there will be a healing potion in the current Room.
     */
    private static final int HEALING_POTION_CHANCE = 10;

    /**
     * The chance there will be a vision potion in the current Room.
     */
    private static final int VISION_POTION_CHANCE = 10;

    /**
     * The chance there will be a pit in the current Room.
     */
    private static final int PIT_CHANCE = 10;

    /**
     * The chance there will be a monster in the current Room.
     */
    private static final int MONSTER_CHANCE = 5;

    /**
     * A 3x3 char array to represent what is inside the current room
     * and the direction the player can go in.
     */
    private char[][] currRoom = new char[3][3];

    /**
     * If you can go north from the current Room.
     */
    private boolean canGoNorth;

    /**
     * If you can go south from the current Room.
     */
    private boolean canGoSouth;

    /**
     * If you can go east from the current Room.
     */
    private boolean canGoEast;

    /**
     * If you can go west from the current Room.
     */
    private boolean canGoWest;

    /**
     * What is north of the current Room.
     */
    private char north;

    /**
     * What is south of the current Room.
     */
    private char south;

    /**
     * What is east of the current Room.
     */
    private char east;

    /**
     * What is west of the current Room.
     */
    private char west;

    /**
     * If the current Room has a Healing Potion.
     */
    private boolean hasHealingPotion;

    /**
     * If the current Room has a Vision Potion.
     */
    private boolean hasVisionPotion;

    /**
     * If the current Room has a Pit.
     */
    private boolean hasPit;

    private char pillar;

    /**
     * If the current Room has a Pillar.
     */
    private boolean hasPillar;

    /**
     * If the current Room is an Entrance.
     */
    private boolean isEntrance;

    /**
     * If the current Room is an Exit.
     */
    private boolean isExit;

    /**
     * If there is more than one item in the current Room.
     */
    private boolean isAtLeastOneItem;

    /**
     * If the current Room has a Monster.
     */
    private boolean hasMonster;

    /**
     * The Room constructor. This is called for every empty room in the Dungeon.
     *
     * @param theDungeonLayout The 2d Dungeon array map with walls, empty rooms,
     *                         pillars, the entrance, and the exit.
     * @param theRow The current row in the 2d Dungeon array.
     * @param theCol The current column in the 2d Dungeon array.
     * @param theKey Helps us determine the type of the current Room in the 2d Dungeon array.
     */
    public Room(char[][] theDungeonLayout, int theRow, int theCol, char theKey){

        //in a way, we're already making the toString here, since it'll be a 2d char array

        for(int i = 0; i < this.currRoom.length; i++){
            for(int j = 0; j < this.currRoom.length; j++){
                this.currRoom[i][j] = '*'; //fill the array all with walls for now, will overwrite later
            }
        }

        makeBorders(theDungeonLayout, theRow, theCol);

        makePassable();

        roomPurpose(theKey);

    }

    /**
     * Based on the Dungeon key, create the purpose of the room.
     * That is, setting if the Room is an Entrance, Exit, empty, or has a pillar.
     * @param theKey
     */
    private void roomPurpose(char theKey) {
        if (theKey == 'O'){ //empty room
            this.currRoom[1][1] = ' '; //the middle of the room, empty
            generateItem();
            generateMonster();
        }
        else if (theKey == 'S'){ //the entrance
            this.currRoom[1][1] = 'i';
            this.isEntrance = true;
        }
        else if (theKey == 'E'){ //the exit
            this.currRoom[1][1] = 'O';
            this.isExit = true;
        }
        else if (theKey == 'P'){ //a pillar
            this.currRoom[1][1] = 'P';
            this.hasPillar = true;
            this.hasMonster = true; //since pillar should contain a difficult boss monster
        }
    }

    /**
     * Looks at other Rooms that are north, east, south, and west of the current Room, then
     * sets the direction to be passable if possible.
     */
    private void makePassable() {
        //if not a wall or edge, make it passable
        if (this.north != 'X' && this.north != '/') {
            this.currRoom[0][1] = '-';
            this.canGoNorth = true;
        }
        if (this.south != 'X' && this.south != '/') {
            this.currRoom[2][1] = '-';
            this.canGoSouth = true;
        }
        if (this.west != 'X' && this.west != '/') {
            this.currRoom[1][0] = '|';
            this.canGoWest = true;
        }
        if (this.east != 'X' && this.east != '/') {
            this.currRoom[1][2] = '|';
            this.canGoEast = true;
        }
    }


    /**
     * To visually see the borders of the Dungeon and to not go out of bounds.
     * @param theDungeonLayout
     * @param theRow
     * @param theCol
     */
    private void makeBorders(char[][] theDungeonLayout, int theRow, int theCol){
        if (theCol == 0){
            this.west = '/';
        }
        else {
            this.west = theDungeonLayout[theRow][theCol-1];
        }
        if (theCol == theDungeonLayout.length - 1){
            this.east = '/';
        }
        else {
            this.east = theDungeonLayout[theRow][theCol+1];
        }
        if (theRow == 0){
            this.north = '/';
        }
        else {
            this.north = theDungeonLayout[theRow-1][theCol];
        }
        if (theRow == theDungeonLayout.length - 1){
            this.south = '/';
        }
        else {
            this.south = theDungeonLayout[theRow+1][theCol];
        }
    }


    //TODO also need to randomize type of monster
    //TODO when hero enters a room with a monster, call battle()
    //TODO make a method for generateBossMonster() for pillar rooms

    /**
     * Attempts to generate monsters for empty rooms.
     */
    private void generateMonster() {
        //now we'll try generating monsters for empty rooms
        int randomMonster = ThreadLocalRandom.current().nextInt(1,  100 + 1); //for monster
        if (randomMonster <= MONSTER_CHANCE){
            this.hasMonster = true;
        }
    }

    /**
     * Attempts to generate items. Should be used with an empty room.
     */
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
        int randomPit = ThreadLocalRandom.current().nextInt(1,  100 + 1); //for pit
//            System.out.println(randomPit);
        if (randomPit <= PIT_CHANCE){
            putItemSymbol('X');
            this.hasPit = true;
        }
    }

    /**
     * Simply overwrites the current item symbol an M symbol if there
     * are multiple items in the current Room.
     * @param item The symbol of the item placed in the current Room.
     *             Should not include pillars, entrance, or exit.
     */
    private void putItemSymbol(char item) {
        if (this.isAtLeastOneItem) {
            this.currRoom[1][1] = 'M';
        }
        else {
            this.currRoom[1][1] = item;
        }
        this.isAtLeastOneItem = true;
    }

    public void removeRoomItems() {
        this.currRoom[1][1] = ' ';
        this.setHasHealingPotion(false);
        this.setHasVisionPotion(false);
        this.setHasPillar(false);

    }

    /**
     * Generates a String representation of the current Room.
     * @return A String representation of the current Room.
     */
    @Override
    public String toString() {
        StringBuilder room = new StringBuilder();
        for(int i = 0; i < this.currRoom.length; i++){
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

    public boolean isExit () {
        return isExit;
    }

    public boolean getCanGoNorth(){
        return canGoNorth;
    }
    public boolean getCanGoSouth(){
        return canGoSouth;
    }
    public boolean getCanGoEast(){
        return canGoEast;
    }
    public boolean getCanGoWest(){
        return canGoWest;
    }

    // Getter and setter methods for other properties
    public boolean getHasHealingPotion() {
        return hasHealingPotion;
    }

    public void setHasHealingPotion (boolean hasHealingPotion){
        this.hasHealingPotion = hasHealingPotion;
    }

    public boolean getHasVisionPotion() {
        return hasVisionPotion;
    }

    public void setHasVisionPotion(boolean hasVisionPotion){
        this.hasVisionPotion = hasVisionPotion;
    }

    public boolean getHasPit() {
        return hasPit;
    }

    public boolean getHasMonster() {
        return hasMonster;
    }

    public void setHasMonster(boolean hasMonster){
        this.hasMonster = hasMonster;
    }

    public char getPillar() {
        return pillar;
    }

    public void setPillar(char pillar){
        this.pillar = pillar;
    }

    public boolean getHasPillar() {
        return hasPillar;
    }

    public void setHasPillar(boolean hasPillar){
        this.hasPillar = hasPillar;
    }

    public boolean getHasItem() {
        return (hasPillar || hasVisionPotion || hasHealingPotion);
    }

    public void removeRoomMonster() {
        this.setHasMonster(false);
    }

    public void setHasPit(boolean hasPit) {
        this.hasPit = hasPit;
    }

    public boolean getHasMultipleItems() {
        return isAtLeastOneItem;
    }

    public boolean getHasWall() {
        return (this.currRoom[1][1] == '*');
    }

    public boolean getHasEntrance() {
        return (this.currRoom[1][1] == 'i');
    }

    public boolean getHasExit() {
        return (this.currRoom[1][1] == 'O');
    }
}
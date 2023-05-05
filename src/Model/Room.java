package src.Model;

public class Room {

//- visionPotion : boolean
//- healPotion : boolean
//- pit : boolean
//- exit : boolean
//- monster : boolean
//- pillar : boolean

    private boolean hasVisionPotion;
    private boolean hasHealingPotion;
    private boolean hasPit;
    private boolean hasEntrance;
    private boolean hasExit;
    private boolean hasMonster;
    private boolean hasPillar;

    public Room() {
    this.hasVisionPotion = false;
    this.hasHealingPotion = false;
    this.hasPit = false;
    this.hasEntrance = false;
    this.hasExit = false;
    this.hasMonster = false;
    this.hasPillar = false;
    }

/**
 * Getters and Setters
 * */

    public boolean hasVisionPotion() {
        return hasVisionPotion;
    }

    public void setVisionPotion(boolean hasVisionPotion) {
        this.hasVisionPotion = hasVisionPotion;
    }

    public boolean hasHealingPotion() {
        return hasHealingPotion;
    }

    public void setHealingPotion(boolean hasHealingPotion) {
        this.hasHealingPotion = hasHealingPotion;
    }

    public boolean hasPit() {
        return hasPit;
    }

    public void setPit(boolean hasPit) {
        this.hasPit = hasPit;
    }

    public boolean hasEntrance() {
        return hasEntrance;
    }

    public void setEntrance(boolean hasEntrance) {
        this.hasEntrance = hasEntrance;
    }

    public boolean hasExit() {
        return hasExit;
    }

    public void setExit(boolean hasExit) {
        this.hasExit = hasExit;
    }

    public boolean hasMonster() {
        return hasMonster;
    }

    public void setMonster(boolean hasMonster) {
        this.hasMonster = hasMonster;
    }

    public boolean hasPillar() {
        return hasPillar;
    }

    public void setPillar(boolean hasPillar) {
        this.hasPillar = hasPillar;
    }
}

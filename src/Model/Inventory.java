package src.Model;

import java.io.Serializable;

public class Inventory implements Serializable {
    private int visionPotionCount;
    private int healthPotionCount;
    private int pillarCount;

    public Inventory() {
        visionPotionCount = 0;
        healthPotionCount = 0;
        pillarCount = 0;
    }

    public int getVisionPotionCount() {
        return visionPotionCount;
    }

    public void addVisionPotion() {
        visionPotionCount++;
    }

    public void removeVisionPotion() {
        if (visionPotionCount > 0) {
            visionPotionCount--;
        }
    }

    public int getHealthPotionCount() {
        return healthPotionCount;
    }

    public void addHealthPotion() {
        healthPotionCount++;
    }

    public void removeHealthPotion() {
        if (healthPotionCount > 0) {
            healthPotionCount--;
        }
    }

    public int getPillarCount() {
        return pillarCount;
    }

    public void addPillar() {
        pillarCount++;
    }

}
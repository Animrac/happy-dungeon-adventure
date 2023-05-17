package src.Model;
public class Inventory {
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
package src.Model;

/**
 * This program creates an outline for a hero character that differs from Adventurer
 * as it has the ability to use a special attack.
 *
 * @author Anastasia Vilenius
 * @version 05/07/23
 */
public abstract class Hero extends Adventurer {

    /**
     * Indicates whether a hero chose to run away.
     */
    private boolean myRunAway;
    /**
     * Indicates the odds of a hero being able to use their special skill in an attack.
     */
    private double mySpecialSkillOdds;

    /**
     * Inventory for the hero
     */
    private Inventory myInventory;

    /**
     * Constructor that initializes fields.
     *
     * @param theName
     * @param theHealth
     * @param theMinDamage
     * @param theMaxDamage
     * @param theAttackOdds
     * @param theBlockOdds
     * @param theAttackSpeed
     * @param theSpecialSkillOdds
     */
    protected Hero(final String theName, final int theHealth, final int theMinDamage, final int theMaxDamage,
                   final double theAttackOdds, final double theBlockOdds, final int theAttackSpeed, final double theSpecialSkillOdds) {
        super(theName, theHealth, theMinDamage, theMaxDamage, theAttackOdds, theBlockOdds, theAttackSpeed);
        setRunAway(false);
        setSpecialSkillOdds(theSpecialSkillOdds);
        myInventory = new Inventory();
    }

    /**
     * Checks if special skill odds are less than or equal to 0 and if so,
     * throws and IllegalArgumentException.
     *
     * @param theSpecialSkillOdds
     */
    private void setSpecialSkillOdds(double theSpecialSkillOdds) {
        if(theSpecialSkillOdds <= 0) {
            throw new IllegalArgumentException("special skill odds must be greater than 0");
        }
        mySpecialSkillOdds = theSpecialSkillOdds;
    }

    /**
     * Checks if hero can use special skill.
     *
     * @return true or false based on random integer generated.
     */
    protected final boolean canUseSpecialSkill() {
        return MY_RANDOM.nextInt(101) <= mySpecialSkillOdds;
    }

    /**
     *
     * @param theRunAway
     */
    private void setRunAway(final boolean theRunAway) {
        myRunAway = theRunAway;
    }

    /**
     *
     * @return myRunAway
     */
    public final boolean runAway() {
        return myRunAway;
    }
    /**
     * Get the inventory
     * @return myInventory
     */
    public Inventory getInventory() {
        return myInventory;
    }

    /**
     * Overrides the attack method in Adventurer class and allows user to choose hero attack.
     *
     * @param theOpponent
     */
    @Override
    public void attack(final Adventurer theOpponent) {
        int attackChoice;
        int numberOfAttacks = theOpponent.getAttackSpeed();

        if(numberOfAttacks == 0) {
            numberOfAttacks = 1;
        }

        while (numberOfAttacks > 0 && theOpponent.alive() && !runAway()) {
            System.out.println(getName() + " hit points: " + getHealth());
            System.out.println(theOpponent.getName() + " hit points: " + theOpponent.getHealth());

            attackChoice = getChoice();
            if(attackChoice == 1) {
                super.attack(theOpponent);
            }
            else if (attackChoice == 2) {
                specialAttack(theOpponent);
            }
            else {
                setRunAway(true);
            }

            numberOfAttacks--;
        }
    }

    /**
     * Allows hero to execute their special attack
     *
     * @param theOpponent
     */
    protected void specialAttack(final Adventurer theOpponent) {}


    /**
     * Overrides subtractHitPoints method in Adventure class by allowing hero a chance to block
     * an attack from a monster.
     *
     * @param theAmount
     */
    @Override
    protected void subtractHitPoints(final int theAmount) {
        if (MY_RANDOM.nextInt(101) <= myBlockOdds) {
            System.out.println(getName() + " blocked the attack!");
        }
        else {
            super.subtractHitPoints(theAmount);
        }
    }

    /**
     * Prompts user to choose whether to attack normally, use special attack, or to run away.
     *
     * @return choice an integer representing user choice of attack
     */
    private final int getChoice() {
        int choice = 0;
        do {
            System.out.println("(1) regular attack\n(2) special attack\n(3) run away!\n");
            if (input.hasNextInt()) {
                choice = input.nextInt();
            }
            else {
                System.out.print("Enter 1, 2, or 3");
            }
        } while (choice < 1 || choice > 3);
        input.nextLine();

        return choice;
    }

    public int getVisionPotionCount() {
        return myInventory.getVisionPotionCount();
    }

    public void addVisionPotion() {
        myInventory.addVisionPotion();
    }

    public void removeVisionPotion() {
        myInventory.removeVisionPotion();
    }

    public int getHealthPotionCount() {
        return myInventory.getHealthPotionCount();
    }

    public void addHealthPotion() {
        myInventory.addHealthPotion();
    }

    public void removeHealthPotion() {
        myInventory.removeHealthPotion();
    }

    public void addPillar() {
        myInventory.addPillar();
    }

    public void getPillarCount() {
        myInventory.getPillarCount();
    }

}





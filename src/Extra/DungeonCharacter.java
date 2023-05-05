package src.Extra;

/*
 * DungeonCharacter.java
 *
 * TCSS 143 - Spring 2021
 * Assignment 5
 */
 
import java.util.Random;
import java.util.Scanner;

/**
 * This program creates an outline for a character with certain characteristics
 * for a simple battle game. 
 * 
 * Code from class.
 *
 * @author Anastasia Vilenius
 * @version 04/27/21
 */

public abstract class DungeonCharacter {

	 /**
	  * Random object for generation of items/monsters.
 	  */	
	  protected final static Random MY_RANDOM = new Random();
   
     /**
      * Scanner for user input.
      */
      public final static Scanner input = new Scanner(System.in);  
    
     /**
      * Indicates the name of the hero or monster.
      */
      private String myName;
      
      /**
       * Indicates the number of hit points a character has.
       */
      private int myHitPoints;
      
      /**
       * Indicates the minimum amount of damage a character can cause in an attack.
       */
      private int myMinDamage;
      
      /**
       * Indicates the maximum amount of damage a character can cause in an attack.
       */
      private int myMaxDamage;
      
      /**
       * Indicates the odds of a characters attack succeeding.
       */
      private double myAttackOdds;
      
      /**
       * Indicates the number of attacks a character has per round. 
       */
      private int myAttackSpeed;

      /**
       * Generates a random integer within a range of numbers
       * @param theLow
       * @param theHigh
       * @return a random integer within a range of numbers
       */
      protected static int generateRangedValue(final int theLow, final int theHigh) {
         return theLow + MY_RANDOM.nextInt(theHigh - theLow + 1);
      }
      
      /**
       * Constructor for Dungeon Character.
       * 
       * @param theName
       * @param theHitPoints
       * @param theMinDamage
       * @param theMaxDamage
       * @param theAttackOdds
       * @param theAttackSpeed
       */
      protected DungeonCharacter(final String theName, final int theHitPoints,final int theMinDamage,
          final int theMaxDamage, final double theAttackOdds, final int theAttackSpeed) {
         
         setName(theName);
         setHitPoints(theHitPoints);
         setMinDamage(theMinDamage);
         setMaxDamage(theMaxDamage);
         setAttackOdds(theAttackOdds);
         setAttackSpeed(theAttackSpeed);
      }
      
      /**
       * Checks to make sure name of character is not empty or null.
       * 
       * @param theName
       */
      protected final void setName(final String theName) {
    	  if (theName == null || theName.length() == 0) {
    		  throw new IllegalArgumentException("name passed to setName was null or empty");
    	  }
    	  myName = theName;
      }
      
      /**
       * Checks to make sure hit points are not negative.
       * 
       * @param theHitPoints
       */
      protected final void setHitPoints(final int theHitPoints) {
    	  if (theHitPoints < 0) {
    		  throw new IllegalArgumentException("hit points passed to setHitPoints are negative");
    	  }
		  myHitPoints = theHitPoints;
      }
      
      /**
       * Checks to make sure the minimum amount of damage that
       * can be caused in an attack is greater than 0.
       * 
       * @param theMinDamage
       */
      protected final void setMinDamage(final int theMinDamage) {
    	  if (theMinDamage <= 0) {
    		  throw new IllegalArgumentException("min damage must be greater than 0");
    	  }
    	  myMinDamage = theMinDamage;
    	  if (myMaxDamage > 0) {
    		  checkMinVersusMax();
    	  }
      }
      
      /**
       * Checks to make sure the maximum amount of damage that 
       * can be caused in an attack is greater than 0.
       * 
       * @param theMaxDamage
       */
      protected final void setMaxDamage(final int theMaxDamage) {
    	  if (theMaxDamage <= 0) {
    		  throw new IllegalArgumentException("max damage must be greater than 0");
    	  }
    	  myMaxDamage = theMaxDamage;
    	  checkMinVersusMax();
      }
      
      /**
       * Checks to make sure the max amount of damage is greater than
       * the minimum amount of damage.
       */
      private void checkMinVersusMax() {
    	  if (myMinDamage > myMaxDamage) {
    		  throw new IllegalArgumentException("min damage cannot be greater than max damage");
    	  }
      }
      
      /**
       * Checks to make sure that the odds of attacking are greater than or equal to 0
       * and less than or equal to 100.
       * 
       * @param theAttackOdds
       */
      protected final void setAttackOdds(final double theAttackOdds) {
    	  if (theAttackOdds <= 0 || theAttackOdds >= 100) {
    		  throw new IllegalArgumentException("chance to hit must be greater than 0 and less than 100");
    	  }
    	  myAttackOdds = theAttackOdds;
      }
      
      /**
       * Checks to make sure that the attack speed of a character is greater than 0.
       * 
       * @param theAttackSpeed
       */
      protected final void setAttackSpeed(final int theAttackSpeed) {
    	  if (theAttackSpeed < 0) {
    		  throw new IllegalArgumentException("attack speed passed to setAttackSpeed is negative");
    	  }
    	  myAttackSpeed = theAttackSpeed;
      }
      
      /**
       * Checks to make sure that hit points is greater than 0.
       * Subtracts hit points from a character if an attack successfully landed on them.
       * A character faints once their hit points reach 0.
       * 
       * @param theAmount
       */
      protected void subtractHitPoints(final int theAmount) {
    	  if(theAmount < 0) {
    		  throw new IllegalArgumentException("hit points passed to subtractHitPoints are negative");
    	  }
    	  System.out.println(getName() + " takes <<" + theAmount + ">> damage");
    	  
    	  myHitPoints -= theAmount;
    	  if (myHitPoints <= 0) {
    		  myHitPoints = 0;
    		  System.out.println(getName() + " has fainted");
    	  }
    	  System.out.println(getName() + " hit points now at <<" + myHitPoints + ">>");
      }
      
      /**
       * 
       * @return myName
       */
      public final String getName() {
    	  return myName;
      }
      
      /**
       * 
       * @return myHitPoints
       */
      public final int getHitPoints() {
    	  return myHitPoints;
      }
      
      /**
       * Checks if character is still alive.
       * 
       * @return true if hit points are greater than 0
       */
      public final boolean alive() {
	  	  return myHitPoints > 0;
      }
      
      /**
       * Checks to make sure there is an opponent to battle.
       * Sets up battle between hero and monster.
       *
       * @param opponent
       */
      public void attack(final DungeonCharacter opponent) {
    	  if (opponent == null) {
    		  throw new IllegalArgumentException("opponent DungeonCharacter passed to attack is null");
    	  }
    	  if (alive()) {
    		  double randDouble = MY_RANDOM.nextDouble();
    		  //System.out.println("rand double: " + randDouble);
    		  if (randDouble <= myAttackOdds) {
    			  int damage = generateDamage();
    			  System.out.println(getName() + " attacks " + opponent.getName());
    			  opponent.subtractHitPoints(damage);
    		  } else {
    			  System.out.println(getName() + "'s attack on " + opponent.getName() + " misses");
    		  }
    	  } else {
    		  System.out.println(getName() + " has fainted so cannot attack");
    	  }
      }
      
      /**
       * Generates random amount of damage in set range.
       * 
       * @return amount of damage in a set range
       */
      private int generateDamage() {
    	  return DungeonCharacter.generateRangedValue(myMinDamage, myMaxDamage);
      }
      
      /**
       *
       * @return myAttackSpeed
       */
      public final int getAttackSpeed() {
    	  return myAttackSpeed;
      }
}
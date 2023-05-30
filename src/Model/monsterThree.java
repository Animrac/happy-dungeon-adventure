package src.Model;

import java.io.Serializable;

/**
 * This program creates a monsterOne character, a monster.
 *
 * @author Anastasia Vilenius
 * @version 05/09/23
 */
public class monsterThree extends Monster implements Serializable {

    /**
     * Constructor that initializes fields.
     */
    public monsterThree () {
        super("Monster Three", 80, 20, 40, .8, 3, 3);
    }
}

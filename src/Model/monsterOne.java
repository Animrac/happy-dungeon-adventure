package src.Model;

import java.io.Serializable;

/**
 * This program creates a monsterOne character, a monster.
 *
 * @author Anastasia Vilenius
 * @version 05/09/23
 */
public class monsterOne extends Monster implements Serializable {

    /**
     * Constructor that initializes fields.
     */
    public monsterOne () {
        super("Monster One", 80, 20, 40, .8, 3, 3);
    }
}

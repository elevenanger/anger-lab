package intro.adventure.characters;

import intro.adventure.weapons.BowAndArrowBehavior;

/**
 * @author Anger
 * created on 2022/7/25
 */
public class Knight extends Character{
    public Knight() {
        this.weaponBehavior = new BowAndArrowBehavior();
    }
}

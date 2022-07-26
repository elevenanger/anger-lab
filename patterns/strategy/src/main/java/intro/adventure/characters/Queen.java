package intro.adventure.characters;

import intro.adventure.weapons.KnifeBehavior;

/**
 * @author Anger
 * created on 2022/7/25
 */
public class Queen extends Character{
    public Queen() {
        this.weaponBehavior = new KnifeBehavior();
    }
}

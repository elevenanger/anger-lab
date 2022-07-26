package intro.adventure.characters;

import intro.adventure.weapons.SwordBehavior;

/**
 * @author Anger
 * created on 2022/7/25
 */
public class King extends Character{
    public King() {
        this.weaponBehavior = new SwordBehavior();
    }
}

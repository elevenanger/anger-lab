package intro.adventure.characters;

import intro.adventure.weapons.AxeBehavior;

/**
 * @author Anger
 * created on 2022/7/25
 */
public class Troll extends Character{
    public Troll() {
        this.weaponBehavior = new AxeBehavior();
    }
}

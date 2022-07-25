package intro.adventure.weapons;

/**
 * @author Anger
 * created on 2022/7/25
 */
public class BowAndArrowBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.print("箭无虚发\n");
    }
}

package intro.adventure.weapons;

/**
 * @author Anger
 * created on 2022/7/25
 */
public class SwordBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.printf("游刃有余%n");
    }
}

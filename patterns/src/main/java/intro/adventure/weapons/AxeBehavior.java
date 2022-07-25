package intro.adventure.weapons;

/**
 * @author Anger
 * created on 2022/7/25
 */
public class AxeBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.print("力劈华山\n");
    }
}

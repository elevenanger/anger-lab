package intro.adventure.characters;

import intro.adventure.weapons.WeaponBehavior;

/**
 * @author Anger
 * created on 2022/7/25
 */
public abstract class Character {
    protected WeaponBehavior weaponBehavior;

    protected void fight(){
        System.out.print(getClass().getSimpleName() + " ");
        weaponBehavior.useWeapon();
    }

    public WeaponBehavior getWeaponBehavior() {
        return weaponBehavior;
    }

    public void setWeaponBehavior(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }
}

package intro.adventure.characters;

import intro.adventure.weapons.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Anger
 * created on 2022/7/25
 */
class CharacterTest {

    List<Character> characters =
        Arrays.asList(
            new King(),
            new Queen(),
            new Knight(),
            new Troll()
        );

    List<WeaponBehavior> behaviors =
        Arrays.asList(
            new AxeBehavior(),
            new SwordBehavior(),
            new KnifeBehavior(),
            new BowAndArrowBehavior()
        );

    @Test
    void baseTest() {
        characters.forEach(Character::fight);
    }

    @Test
    void changeBehavior() {
        characters.stream()
            .peek(
                character ->
                    character.setWeaponBehavior(
                        behaviors.get(
                            new Random().nextInt(behaviors.size() - 1))))
                .forEach(Character::fight);
    }
}
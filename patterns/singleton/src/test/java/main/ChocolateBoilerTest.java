package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Anger
 * created on 2022/8/2
 */
class ChocolateBoilerTest {

    @Test
    void testSingleton() {
        ChocolateBoiler boiler1 = ChocolateBoiler.getChocolateBoilerInstance();
        ChocolateBoiler boiler2 = ChocolateBoiler.getChocolateBoilerInstance();

        assertSame(boiler1, boiler2);

        boiler1.fill();
        boiler2.boil();
        boiler1.drain();

        assertTrue(boiler1.isEmpty());
        assertTrue(boiler1.isBoiled());
    }
}
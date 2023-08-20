package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    @Test
    public void cardConstructorTest() {
        String testName = "Snorlax";
        int testHp = 100;
        int testDamage = 50;

        Card card = new Card(testName, testHp, testDamage);

        assertEquals(testName, card.getName());
        assertEquals(testHp, card.getHP());
        assertEquals(testDamage, card.getDamage());
    }

    @Test
    public void setHPTest() {
        Card card = new Card("Pikachu", 20, 10);
        assertEquals(20, card.getHP());
        card.setHP(30);
        assertEquals(30, card.getHP());
    }

    @Test
    public void toStringTest() {
        String testName = "Arbok";
        int testHp = 100;
        int testDamage = 50;

        Card card = new Card(testName, testHp, testDamage);

        String expectedString = testName + " (" + testHp + " HP, " + testDamage + " damage)";
        String actualString = card.toString();

        assertEquals(expectedString, actualString);
    }
}
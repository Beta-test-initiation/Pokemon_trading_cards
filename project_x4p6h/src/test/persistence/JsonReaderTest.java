package persistence;

import model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    private JsonReader testReader;
    private String testSource;

    @BeforeEach
    public void runBefore() {
        testSource = "./data/testCards.json";
        testReader = new JsonReader(testSource);
    }

    @Test
    public void testReadFile() {
        try {
            String expectedJsonString = new String(Files.readAllBytes(Paths.get(testSource)));
            String actualJsonString = testReader.readFile(testSource);
            assertEquals(expectedJsonString, actualJsonString);
        } catch (IOException e) {
            fail("Unexpected IOException thrown");
        }
    }

    @Test
    public void testReadNonExistentFile() {
        try {
            String nonExistentSource = "./data/nonexistent.json";
            testReader = new JsonReader(nonExistentSource);
            testReader.readFile(nonExistentSource);
            fail("Expected IOException not thrown");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    public void testReadEmptyCardList() {
        try {
            String emptyCardsSource = "./data/emptyCards.json";
            testReader = new JsonReader(emptyCardsSource);
            ArrayList<Card> cards = testReader.read();
            assertEquals(0, cards.size());
        } catch (IOException e) {
            fail("Unexpected IOException thrown");
        }
    }

    @Test
    public void testReadCardList() {
        try {
            ArrayList<Card> cards = testReader.read();
            assertEquals(3, cards.size());

            Card firstCard = cards.get(0);
            assertEquals("Charizard", firstCard.getName());
            assertEquals(100, firstCard.getHP());
            assertEquals(50, firstCard.getDamage());

            Card secondCard = cards.get(1);
            assertEquals("Blastoise", secondCard.getName());
            assertEquals(120, secondCard.getHP());
            assertEquals(40, secondCard.getDamage());

            Card thirdCard = cards.get(2);
            assertEquals("Venusaur", thirdCard.getName());
            assertEquals(90, thirdCard.getHP());
            assertEquals(30, thirdCard.getDamage());

        } catch (IOException e) {
            fail("Unexpected IOException thrown");
        }
    }
}

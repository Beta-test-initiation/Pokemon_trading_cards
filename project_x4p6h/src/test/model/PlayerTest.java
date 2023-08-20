package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private String testName;
    private Player player;
    private ArrayList<Card> testDeck;
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;

    @BeforeEach
    public void setUp() {
        testName = "Ash";

        testDeck = new ArrayList<Card>();

        card1 = new Card("Snorlax", 40, 20);
        card2 = new Card("Geodude", 20, 10);
        card3 = new Card("Butterfree", 30, 70);
        card4 = new Card("Togepi", 30, 70);
        card5 = new Card("Pikachu", 100, 30);

        testDeck.add(card1);
        testDeck.add(card2);
        testDeck.add(card3);
        testDeck.add(card4);
        testDeck.add(card5);

        player = new Player(testName, testDeck);

    }

    @Test
    public void getNumberOfPokemonTest() {
        for (int i = 0; i < 5; i++) {
            player.drawCard();
        }
        assertEquals(5, player.getNumberOfPokemon());
    }


    @Test
    public void playerConstructorTest() {

        assertEquals(testName, player.getName());
        assertEquals(testDeck, player.getDeck());
        assertTrue(player.getHand().isEmpty());
        assertTrue(player.getInPlay().isEmpty());

    }


    @Test
    public void shuffleCardsTest() {
        player.shuffleDeck();

        assertNotNull(player.getDeck());
        assertTrue(testDeck.containsAll(player.getDeck()));
        assertTrue(player.getDeck().containsAll(testDeck));

    }

    @Test
    void addToHandTest() {
        player.addToHand(card1);
        ArrayList<Card> expected = new ArrayList<Card>();
        expected.add(card1);
        assertEquals(expected, player.getHand());
    }

    @Test
    void drawCardTest() {

        assertTrue(player.drawCard());
        assertEquals(4, player.getDeck().size());
        assertEquals(1, player.getHand().size());
        player.drawCard();
        player.drawCard();
        player.drawCard();
        player.drawCard();
        assertEquals(0, player.getDeck().size());
        assertEquals(5, player.getHand().size());


    }

    @Test
    void removeFromHandTest() {
        player.addToHand(card1);
        player.addToHand(card2);
        player.addToHand(card3);
        player.removeFromHand(1);
        ArrayList<Card> expected = new ArrayList<Card>();
        expected.add(card1);
        expected.add(card3);
        assertEquals(expected, player.getHand());

    }

    @Test
    void addToInPlayTest() {
        player.addToInPlay(card1);
        ArrayList<Card> expected = new ArrayList<Card>();
        expected.add(card1);
        assertEquals(expected, player.getInPlay());
    }

    @Test
    void testRemoveFirstFromInPlayTest() {
        player.addToInPlay(card1);
        player.addToInPlay(card2);
        player.removeFirstFromInPlay();
        ArrayList<Card> expected = new ArrayList<Card>();
        expected.add(card2);
        assertEquals(expected, player.getInPlay());
    }

    @Test
    void drawInitialHandTest() {
        player.drawInitialHand();
        assertEquals(5, player.getHand().size());
    }

    @Test
    public void getActivePokemonTest() {
        assertEquals(null, player.getActivePokemon());
        player.addToInPlay(card1);
        assertEquals(card1, player.getActivePokemon());
    }

}

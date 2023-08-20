package model;

import java.util.ArrayList;
import java.util.Scanner;

//Represents a player with a deck, hand and card in play
public class Player {
    private final String name;
    private final ArrayList<Card> deck;
    private final ArrayList<Card> hand;
    private final ArrayList<Card> inPlay;


    public Player(String name, ArrayList<Card> deck) {
        this.name = name;
        this.deck = deck;
        this.hand = new ArrayList<Card>();
        this.inPlay = new ArrayList<Card>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Card> getInPlay() {
        return this.inPlay;
    }


    public int getNumberOfPokemon() {
        return this.hand.size();
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }


    //REQUIRES: a non-empty hand
    //MODIFIES: this
    //EFFECTS: removes the element at the given index from the Hand
    public void removeFromHand(int index) {
        this.hand.remove(index);
        //card has been removed from teh hand??
        EventLog.getInstance().logEvent(new Event("A Card has been removed from hand"));
    }

    //MODIFIES: this
    //EFFECTS: adds the given card to Hand
    public void addToHand(Card card) {
        this.hand.add(card);
        EventLog.getInstance().logEvent(new Event("A Card has been added to hand"));

    }

    //MODIFIES: this
    //EFFECTS: adds the card to inPLay
    public void addToInPlay(Card card) {
        this.inPlay.add(card);
        EventLog.getInstance().logEvent(new Event("A Card has been added to Cards in Play"));
    }

    //REQUIRES: non-empty InPLay
    //MODIFIES: this
    //EFFECTS: removes card at index 0 from InPlay
    public void removeFirstFromInPlay() {
        this.inPlay.remove(0);
        EventLog.getInstance().logEvent(new Event("First Card has been removed from Cards in Play"));
    }


    //MODIFIES: this
    //EFFECTS: shuffles the cards in the players deck
    public void shuffleDeck() {
        for (int i = 0; i < this.deck.size(); i++) {
            int randomIndex = (int) (Math.random() * this.deck.size());
            Card temp = this.deck.get(i);
            this.deck.set(i, this.deck.get(randomIndex));
            this.deck.set(randomIndex, temp);
        }
        EventLog.getInstance().logEvent(new Event("Card deck has been shuffled"));
    }

    //EFFECTS: draws 5 cards for the player to play with
    public void drawInitialHand() {
        for (int i = 0; i < 5; i++) {
            this.drawCard();
        }
        EventLog.getInstance().logEvent(new Event("Player has successfully drawn their hand"));
    }

    //REQUIRES: a non-empty player deck
    //MODIFIES: this
    //EFFECTS: adds a card from the deck to the hand and returns true otherwise false
    public boolean drawCard() {
        Card card = this.getDeck().remove(0);
        this.hand.add(card);
        EventLog.getInstance().logEvent(new Event("Player has drawn a card"));
        return true;

    }

    //EFFECTS: returns the active pokemon else null
    public Card getActivePokemon() {
        if (inPlay.isEmpty()) {
            return null;
        }
        EventLog.getInstance().logEvent(new Event("Getting current player's active pokemon"));
        return inPlay.get(0);

    }


}








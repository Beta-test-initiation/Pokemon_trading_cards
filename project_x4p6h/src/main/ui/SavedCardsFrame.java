package ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import model.Card;
import persistence.JsonReader;

public class SavedCardsFrame extends JFrame {
    private ArrayList<Card> allCards;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/pokemonCards.json";
    private CardListPanel savedCards;

    //initialises a frame for saved cards
    public void savedCardsFrame() {
        jsonReader = new JsonReader(JSON_STORE);
        setSize(600, 600); // Set the size of the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set the default close operation of the frame
        getContentPane().setLayout(new BorderLayout());
        loadCards();
        savedCards = new CardListPanel(allCards);
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.add(savedCards);
        add(topPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    //EFFECTS: loads cards from json file
    private ArrayList<Card> loadCards() {
        try {
            allCards = jsonReader.read();
            System.out.println("Loaded Cards from" + JSON_STORE);
            return allCards;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        return new ArrayList<>();
    }
}

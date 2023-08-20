package ui;

import model.Card;

import model.Event;
import model.EventLog;
import persistence.JsonReader;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    private EventLog eventLog;
    private WelcomePanel welcomePanel;
    private GamePanel gamePanel;
    private JLabel askToLoadLabel;
    private JButton loadYesButton;
    private JButton loadNoButton;
    private CreateCardPanel createCardPanel;
    private PlayPanel playPanel;
    private final JsonReader jsonReader;
    ArrayList<Card> allCards;
    private static final String JSON_STORE = "./data/pokemonCards.json";

    //EFFECTS: initialises the gameFrame, jsonReader and welcomePanel
    public GameFrame(int width, int height) {
        super("Pokemon Cards Game"); // Set the title of the frame
        setSize(width, height); // Set the size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation of the frame
        getContentPane().setLayout(new BorderLayout());
        welcomePanel = new WelcomePanel();
        gamePanel = new GamePanel();
        addPlayButton();
        jsonReader = new JsonReader(JSON_STORE);

        // add a shutdown hook to log events to the console
        eventLog = EventLog.getInstance();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Application is shutting down. Logging events to console...");
            for (Event event : eventLog) {
                System.out.println(event);
                System.out.println("----------------------------");
            }
        }));
    }

    //MODIFIES: this
    //EFFECTS: adds the playButton to the screen
    private void addPlayButton() {
        JButton playButton = new JButton("Play");
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.X_AXIS));

        //playButton.setOpaque(true);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the welcome panel and add the game panel
                getContentPane().remove(welcomePanel);
                getContentPane().remove(playButton);
                setupGamePanel();

                // Resize and display the frame
                pack();
                setVisible(true);
            }
        });


        welcomePanel.add(playButton, BorderLayout.SOUTH);
        add(welcomePanel, BorderLayout.NORTH);
        setVisible(true);
    }

    //MODFIES: this
    //EFFECTS: sets up the loading cards functionality
    private void setupGamePanel() {

        JPanel labelPanel = new JPanel(new GridBagLayout());
        askToLoadLabel = new JLabel("Would you like to load your previously saved cards!");
        askToLoadLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        gamePanel.setLayout(new BorderLayout());
        labelPanel.add(askToLoadLabel);

        gamePanel.add(labelPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // You can also use BoxLayout with Y_AXIS for vertical alignment
        addLoadYesButton();
        buttonPanel.add(loadYesButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // add some space between the buttons
        addLoadNoButton();
        buttonPanel.add(loadNoButton);
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);
        add(gamePanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    //MODIFIES: this.allCards
    //EFFECTS: calls the loadCardsFromJson method
    public void addLoadYesButton() {
        loadYesButton = new JButton("yes");
        loadYesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call loadCards function of the PokemonCardGame instance
                loadAllCardsFromJSon();
                gamePanel.setVisible(false);
                createCardPanel = new CreateCardPanel(allCards);
                getContentPane().setLayout(new BorderLayout());
                add(createCardPanel, BorderLayout.CENTER);


            }
        });
    }

    //MODIFIES: this
    //EFFECTS: initialises the allCards from the sampleCards method
    public void addLoadNoButton() {
        loadNoButton = new JButton("no");
        loadNoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call loadCards function of the PokemonCardGame instance
                loadOnlySampleCards();
                gamePanel.setVisible(false);
                playPanel = new PlayPanel(allCards);
                add(playPanel);
            }
        });
        setVisible(true);
    }

    //MODIFIES: this.allCards
    //EFFECTS: loads the cards from Pokemon.json
    protected ArrayList<Card> loadAllCardsFromJSon() {
        try {
            allCards = jsonReader.read();
            System.out.println("Loaded Cards from" + JSON_STORE);
            return allCards;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        return new ArrayList<>();
    }

    //EFFECTS: loads a list of cards containing sample pokemon
    public ArrayList<Card> loadOnlySampleCards() {
        allCards = new ArrayList<Card>();
        Card charmander = new Card("Charmander", 90, 60);
        Card squirtle = new Card("Squirtle", 50, 12);
        Card bulbasaur = new Card("Bulbasaur", 50, 20);
        Card pikachu = new Card("Pikachu", 50, 40);
        Card onix = new Card("Onix", 30, 40);
        Card jigglypuff = new Card("Jigglypuff", 20, 10);
        Card geodude = new Card("Geodude", 50, 20);
        Card rattata = new Card("Rattata", 50, 20);
        Card butterfree = new Card("Butterfree", 20, 20);
        Card mew = new Card("Mew", 120, 80);

        allCards.add(charmander);
        allCards.add(squirtle);
        allCards.add(bulbasaur);
        allCards.add(pikachu);
        allCards.add(onix);
        allCards.add(jigglypuff);
        allCards.add(geodude);
        allCards.add(rattata);
        allCards.add(mew);
        allCards.add(butterfree);

        return allCards;
    }

}

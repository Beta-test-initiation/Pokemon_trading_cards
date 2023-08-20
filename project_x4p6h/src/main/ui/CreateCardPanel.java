package ui;

import model.Card;
import model.Player;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class CreateCardPanel extends JPanel {
    private JTextField nameTextField;
    private JTextField hpTextField;
    private JTextField damageTextField;
    private JLabel askToCreateCardLabel;
    private JButton createYesButton;
    private JButton createNoButton;
    private CardListPanel currentCards;
    private ArrayList allCards;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JPanel savePanel;
    private JLabel askToSaveCardLabel;
    private JButton saveYesButton;
    private JButton saveNoButton;
    private PlayPanel playPanel;
    private static final String JSON_STORE = "./data/pokemonCards.json";
    private final JsonWriter jsonWriter;
    private SavedCardsFrame savedCardsFrame;


    //MODIFIES: this
    //EFFECTS: creates a Panel that asks the users if they want to create a new card
    public CreateCardPanel(ArrayList<Card> allCards) {
        this.allCards = allCards;
        setPreferredSize(new Dimension(1000, 700));
        setLayout(new BorderLayout());

        jsonWriter = new JsonWriter(JSON_STORE);

        JPanel topPanel = new JPanel(new GridBagLayout());
        currentCards = new CardListPanel(allCards);
        topPanel.add(currentCards);
        add(topPanel, BorderLayout.NORTH);

        JPanel labelPanel = new JPanel(new GridBagLayout());
        askToCreateCardLabel = new JLabel("Would you like to create a new card?");
        askToCreateCardLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        labelPanel.add(askToCreateCardLabel);

        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(labelPanel);
        add(centerPanel, BorderLayout.CENTER);

        bottomPanel = new JPanel(new FlowLayout());
        createYesButton();
        bottomPanel.add(createYesButton);
        bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        createNoButton();
        bottomPanel.add(createNoButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }


    //MODIFIES: this
    //EFFECTS: creates a createCardButton
    public void createYesButton() {
        createYesButton = new JButton("yes");
        createYesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCard(allCards);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: creates a new frame with input fields for Pokemon
    public void createCard(ArrayList<Card> allCards) {
        JFrame frame = new JFrame("Create a New Card");
        frame.setLayout(new GridLayout(4, 2, 10, 10));
        // create a grid layout with 4 rows, 2 columns, and 10 pixels between components

        nameTextField = new JTextField();
        hpTextField = new JTextField();
        damageTextField = new JTextField();

        frame.add(new JLabel("Name:"));
        frame.add(nameTextField);
        frame.add(new JLabel("HP:"));
        frame.add(hpTextField);
        frame.add(new JLabel("Damage:"));
        frame.add(damageTextField);

        createButton(frame);
        cancelButton(frame);

    }

    //MODIFIES: frame
    //EFFECTS: creates a new Card object and adds it to the allCards list
    public void createButton(JFrame frame) {
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameTextField.getText();
                    int hp = Integer.parseInt(hpTextField.getText());
                    int damage = Integer.parseInt(damageTextField.getText());

                    Card newCard = new Card(name, hp, damage);
                    addCard(newCard);

                    JOptionPane.showMessageDialog(frame, "New card created: " + newCard);
                    frame.dispose(); // close the window
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid HP and damage values.");
                }
            }
        });
        frame.add(createButton);
    }

    //MODIFIES: frame
    //EFFECTS: disposes the frame
    public void cancelButton(JFrame frame) {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // close the window
            }
        });
        frame.add(cancelButton);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: opens the askToSavePanel
    public void createNoButton() {
        createNoButton = new JButton("no");
        createNoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //call the ask toSavePanel and stuff
                createAskToSavePanel();
            }
        });
        setVisible(true);
    }

    //MODIFIES: This
    //EFFECTS: removes everything on the screen and prompts the user to save cards
    private void createAskToSavePanel() {
        //get rid of all the existing components
        removeAll();
        revalidate();
        repaint();

        savePanel = new JPanel(new GridBagLayout());
        askToSaveCardLabel = new JLabel("Would you like to save the Cards?");
        askToSaveCardLabel.setFont(new Font("Montserrat", Font.BOLD, 30));
        savePanel.add(askToSaveCardLabel);

        bottomPanel = new JPanel(new FlowLayout());
        createSaveYesButton();
        bottomPanel.add(saveYesButton);
        bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        createSaveNoButton();
        bottomPanel.add(saveNoButton);

        add(savePanel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);

    }

    //MODIFIES: this
    //EFFECTS: calls saveCards, prompts a savedCardsFrame and sets up the game
    private void createSaveYesButton() {
        saveYesButton = new JButton("yes");
        saveYesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call saveCards function
                saveCards(allCards);
                removeAll();
                revalidate();
                repaint();

                savedCardsFrame =  new SavedCardsFrame();
                savedCardsFrame.savedCardsFrame();
                gameSetup(allCards);


            }
        });
        setVisible(true);
    }



    //MODIFIES: this
    //EFFECTS: discards all components on the screen and sets up the game
    private void createSaveNoButton() {
        saveNoButton = new JButton("no");
        saveNoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //game start
                removeAll();
                revalidate();
                repaint();

                gameSetup(allCards);
            }
        });
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds Card to allCards
    public void addCard(Card c) {
        allCards.add(c);
    }

    //MODIFIES: PokemonCards.json
    // EFFECTS: saves allCards to file
    private void saveCards(ArrayList<Card> cards) {
        try {
            jsonWriter.open();
            jsonWriter.write(cards);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: adds a playPanel and sets up the game
    public void gameSetup(ArrayList<Card> allCards) {
        playPanel = new PlayPanel(allCards);
        add(playPanel);
    }


}

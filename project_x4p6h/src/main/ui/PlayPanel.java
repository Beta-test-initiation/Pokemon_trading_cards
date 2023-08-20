package ui;

import model.Card;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayPanel extends JPanel {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player otherPlayer;
    private JPanel cardsPanel;
    private PlayerCardsPanel player1cards;
    private PlayerCardsPanel player2cards;
    private JPanel inputPanel;
    private JTextField inputTextField;
    private JButton enterMoveButton;
    private TurnPanel turnPanel;
    private Player loser;
    private Player winner;
    private JPanel infoPanel;


    //EFFECTS: initialises the playPanel
    public PlayPanel(ArrayList<Card> allCards) {
        setPreferredSize(new Dimension(1000, 300));
        setLayout(new BorderLayout());


        ArrayList<Card> deck1 = new ArrayList<Card>();
        ArrayList<Card> deck2 = new ArrayList<Card>();

        //adds cards to each player deck
        for (int i = 0; i < 5; i++) {
            deck1.add(allCards.get(i));
            deck2.add(allCards.get(i + 5));
        }

        // Create players and start the game
        player1 = new Player("Player 1", deck1);
        player2 = new Player("Player 2", deck2);

        currentPlayer = (Math.random() < 0.5) ? player1 : player2;
        otherPlayer = (currentPlayer == player1) ? player2 : player1;

        initializeDeckAndHand(player1, player2);

    }

    //MODIFIES: this
    //EFFECTS: initializes the deck and hand for each player
    private void initializeDeckAndHand(Player player1, Player player2) {
        player1.shuffleDeck();
        player2.shuffleDeck();

        cardsPanel = new JPanel(new FlowLayout());
        player1.drawInitialHand();
        player1cards = new PlayerCardsPanel(player1);

        player2.drawInitialHand();
        player2cards = new PlayerCardsPanel(player2);


        cardsPanel.add(player1cards);
        cardsPanel.add(player2cards);

        add(cardsPanel, BorderLayout.NORTH);

        infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel infoLabel = new JLabel("Type the card no. to play with or press end to continue with existing card. "
                + currentPlayer.getName() + " goes first.");
        infoLabel.setFont(new Font("Montserrat", Font.BOLD, 15));


        infoPanel.add(infoLabel, BorderLayout.NORTH);


        add(infoPanel, BorderLayout.CENTER);

        createInputPanel();

    }

    //MODIFIES: this
    //EFFECTS: creates an input panel
    private void createInputPanel() {
        inputPanel = new JPanel(new FlowLayout());

        inputTextField = new JTextField(10);

        inputPanel.add(new JLabel("Number of card:"));
        inputPanel.add(inputTextField);


        createEnterMoveButton();
        inputPanel.add(enterMoveButton);
        add(inputPanel, BorderLayout.SOUTH);

    }

    //MODIFIES: this
    //EFFECTS: creates a button to execute a move by a player
    private void createEnterMoveButton() {
        enterMoveButton = new JButton("Enter");
        enterMoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = Integer.parseInt(inputTextField.getText()) - 1;
                    Card card = currentPlayer.getHand().get(index);
                    currentPlayer.removeFromHand(index);

                    addToHandAndRemoveFromInPlayIfInPlayLarge(currentPlayer);
                    currentPlayer.addToInPlay(card);
                    attack(currentPlayer, otherPlayer);

                    if (otherPlayerHasCards(otherPlayer, currentPlayer)) {
                        return;
                    }
                    nextPlayerTurn();
                } catch (NumberFormatException ex) {
                    nextPlayerTurn();
                } catch (IndexOutOfBoundsException ex2) {
                    removeEverythingAndAddGameOverLabel();
                }
            }
        });

    }

    //MODIFIES: currentPlayer
    //EFFECTS: swaps the currentCard with the card in Play if there exists one
    private void addToHandAndRemoveFromInPlayIfInPlayLarge(Player currentPlayer) {
        if (currentPlayer.getInPlay().size() >= 1) {
            currentPlayer.addToHand(currentPlayer.getInPlay().get(0));
            currentPlayer.removeFirstFromInPlay();
        }
    }

    //MODIFIES: this
    //EFFECTS: removes everything and displays who lost or won
    private void removeEverythingAndAddGameOverLabel() {
        removeAll();
        revalidate();
        repaint();
        if (currentPlayer.getInPlay().isEmpty()) {
            loser = currentPlayer;
            winner = otherPlayer;
        }
        if (otherPlayer.getHand().isEmpty()) {
            loser = otherPlayer;
            winner = currentPlayer;
        }

        loser = currentPlayer;
        winner = otherPlayer;
        JLabel gameOverLabel = new JLabel(loser.getName()
                + " is left with no cards to attack with. Game is Over! " + winner.getName()
                + " wins");
        gameOverLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        setLayout(new BorderLayout());
        add(gameOverLabel, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //switches the current and other player
    private void nextPlayerTurn() {
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;

        //repaint the panel with new cards but same functionality
        turnPanel = new TurnPanel(player1, player2, currentPlayer, otherPlayer);
        removeAll();
        revalidate();
        repaint();
        add(turnPanel);
        createInputPanel();
    }

    //EFFECTS: checks if the other player has any Pokemon Cards remaining
    private boolean otherPlayerHasCards(Player currentPlayer, Player otherPlayer) {
        if (otherPlayer.getNumberOfPokemon() == 0) {
            System.out.println(otherPlayer.getName() + " has no Pokemon remaining. " + currentPlayer.getName()
                    + " wins!");
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: deducts the damage points from the health points of the opponent's current active pokemon
    //         if dmg > hp , opponent pokemon faints
    public void attack(Player currentplayer, Player opponent) {
        Card myActivePokemon = currentplayer.getActivePokemon();

        if (myActivePokemon == null) {
            System.out.println("No active Pokemon to attack with!");
            return;
        }

        ArrayList<Card> opponentPokemon = opponent.getInPlay();
        if (opponentPokemon.isEmpty()) {
            return;
        }


        Card opponentActivePokemon = opponentPokemon.get(0);

        int damage = myActivePokemon.getDamage();
        opponentActivePokemon.setHP(opponentActivePokemon.getHP() - damage);



        String attackInfo = myActivePokemon.getName() + " attacks " + opponentActivePokemon.getName() + " for "
                + damage + " damage!";
        JOptionPane.showMessageDialog(null, attackInfo, "Attack Info", JOptionPane.INFORMATION_MESSAGE);




        if (opponentActivePokemon.getHP() <= 0) {
            opponent.getInPlay().remove(0);
            if (opponent.getNumberOfPokemon() == 0) {
                removeEverythingAndAddGameOverLabel();
            }
        }

    }


}

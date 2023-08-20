package ui;

import model.Card;
import model.Player;

import javax.swing.*;
import java.awt.*;


public class TurnPanel extends JPanel {
    private PlayerCardsPanel player1cards;
    private PlayerCardsPanel player2cards;
    private JPanel cardsPanel;

    //initialises a frame that represent the current instance of the game
    public TurnPanel(Player player1, Player player2, Player currentplayer, Player opponent) {

        cardsPanel = new JPanel(new FlowLayout());
        player1cards = new PlayerCardsPanel(player1);
        player2cards = new PlayerCardsPanel(player2);
        cardsPanel.add(player1cards);
        cardsPanel.add(player2cards);


        add(cardsPanel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel infoLabel = new JLabel("Type the card no. to play with or press end to continue with existing card. "
                + currentplayer.getName() + " turn");
        infoLabel.setFont(new Font("Montserrat", Font.BOLD, 15));
        infoPanel.add(infoLabel);

        add(infoPanel, BorderLayout.CENTER);

    }

}

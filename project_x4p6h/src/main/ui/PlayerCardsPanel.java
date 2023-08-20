package ui;

import model.Card;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerCardsPanel extends JPanel implements Scrollable {

    private JPanel contentPanel;
    private JLabel currentCardsLabel;
    private JLabel currentPokemonLabel;
    private JLabel currentPokemon;
    private JLabel spaceLabel;

    //EFFECTS: initialises a panel for a player's cards
    public PlayerCardsPanel(Player p) {
        setLayout(new BorderLayout());
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);
        currentCardsLabel = new JLabel(p.getName() + " cards: ");
        currentCardsLabel.setFont(new Font("Montserrat", Font.BOLD, 20));

        contentPanel.add(currentCardsLabel, BorderLayout.NORTH);

        int i = 1;
        for (Card card : p.getHand()) {
            JLabel cardLabel = new JLabel(Integer.toString(i) + ". " + card.toString());
            contentPanel.add(cardLabel);
            i++;
        }

        addSpaceLabel(contentPanel);


        currentPokemonLabel = new JLabel(p.getName() + " active pokemon: ");
        currentPokemonLabel.setFont(new Font("Montserrat", Font.BOLD, 15));
        contentPanel.add(currentPokemonLabel);

        String currPokemon = p.getActivePokemon() == null ? "No active Pokemon" : String.valueOf(p.getActivePokemon());
        currentPokemon = new JLabel(currPokemon);
        currentPokemon.setFont(new Font("Montserrat", Font.BOLD, 13));
        contentPanel.add(currentPokemon);

        setVisible(true);
    }

    //EFFECTS: adds some whitespace to the panel
    private void addSpaceLabel(JPanel contentPanel) {
        spaceLabel = new JLabel("                           ");
        spaceLabel.setFont(new Font("Montserrat", Font.BOLD, 30));
        contentPanel.add(spaceLabel);
    }


    //EFFECTS: overrides the default getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, contentPanel.getComponentCount() * 25);
    }

    //EFFECTS: overrides the default getScrollableTracksViewportWidth() method
    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    //EFFECTS: overrides the default getScrollableTracksViewportHeight() method
    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    //EFFECTS: overrides the default getPreferredScrollableViewportSize() method
    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return null;
    }

    //EFFECTS: overrides the default getScrollableUnitIncrement method
    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 10;
    }

    //EFFECTS: overrides the default getScrollableBlockIncrement method
    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 100;
    }
}



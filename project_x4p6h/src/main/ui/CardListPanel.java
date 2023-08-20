package ui;

import model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardListPanel extends JPanel implements Scrollable {

    private JPanel contentPanel;
    private JLabel currentCardsLabel;

    //MODIFIES: this
    //EFFECTS: creates a scroll panel that displays the given list of cards
    public CardListPanel(ArrayList<Card> allCards) {
        setLayout(new BorderLayout());
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);
        currentCardsLabel = new JLabel("These are the currently saved cards:");
        currentCardsLabel.setFont(new Font("Montserrat", Font.BOLD, 20));

        contentPanel.add(currentCardsLabel, BorderLayout.NORTH);


        for (Card card : allCards) {
            JLabel cardLabel = new JLabel(card.toString());
            contentPanel.add(cardLabel);
        }

        setVisible(true);
    }


    //EFFECTS: overrides the default getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, contentPanel.getComponentCount() * 25);
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


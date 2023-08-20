package ui;

import model.Card;
import model.Player;

import java.util.ArrayList;
import java.util.Scanner;

//represents the Game class with the functionality of choosing classes and attack
public class Game {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private Player otherPlayer;
    Scanner scanner = new Scanner(System.in);

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }


    //REQUIRES: a non-empty hand
    //MODIFIES: this
    //EFFECTS: removes the card that the player choses from the hand and adds it to the inPlay card,
    //         if already a card in play then removes the card from inplay and adds it back to the hand
    public Card chooseCardToPlay(Player player) {
        System.out.println(player.getName() + ", choose the card number to play or type 'end' to end your turn:");
        for (int i = 0; i < player.getHand().size(); i++) {
            System.out.println((i + 1) + ": " + player.getHand().get(i));
        }

        String input = this.scanner.nextLine();

        if (input.equals("end")) {
            return null;
        }

        try {
            int index = Integer.parseInt(input) - 1;
            Card card = player.getHand().get(index);
            player.removeFromHand(index);

            if (player.getInPlay().size() >= 1) {
                player.addToHand(player.getInPlay().get(0));
                player.removeFirstFromInPlay();
            }
            player.addToInPlay(card);
            System.out.println(player.getName() + " played " + card.getName() + ".");
            return card;

        } catch (Exception e) {
            System.out.println("Not a valid input so no pokemon chosen");
            return null;
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
            System.out.println(opponent.getName() + " has no active Pokemon to attack!");
            return;
        }

        Card opponentActivePokemon = opponentPokemon.get(0);

        int damage = myActivePokemon.getDamage();
        opponentActivePokemon.setHP(opponentActivePokemon.getHP() - damage);

        System.out.println(myActivePokemon.getName() + " attacks " + opponentActivePokemon.getName() + " for " + damage
                + " damage!");

        if (opponentActivePokemon.getHP() <= 0) {
            System.out.println(opponentActivePokemon.getName() + " faints!");
            opponent.getInPlay().remove(0);
            if (opponent.getNumberOfPokemon() == 0) {
                System.out.println(opponent.getName() + " has no active Pokemon left!");
            }
        }
    }

    //EFFECTS: tells the player that their card has been played
    public void playCard(Player player, Card card) {
        System.out.println(player.getName() + " has finished playing their " + card.getName() + ".");
    }


    //MODIFIES: this
    //EFFECTS: chooses the first player and proceeds with playing cards of choice
    public void play() {
        initializeDeckAndHand(player1, player2);

        currentPlayer = (Math.random() < 0.5) ? player1 : player2;
        otherPlayer = (currentPlayer == player1) ? player2 : player1;

        System.out.println("Game has Started!" + currentPlayer.getName() + " goes first.");

        while (true) {
            printCurrentState(currentPlayer, otherPlayer);

            Card cardToPlay = chooseCardToPlay(currentPlayer);

            if (cardToPlay == null) {
                System.out.println(currentPlayer.getName() + " has ended their turn.");

                Player temp = currentPlayer;
                currentPlayer = otherPlayer;
                otherPlayer = temp;
                continue;
            }

            playCard(currentPlayer, cardToPlay);
            attack(currentPlayer, otherPlayer);

            if (otherPlayerHasCards(currentPlayer, otherPlayer)) {
                return;
            }

            Player temp = currentPlayer;
            currentPlayer = otherPlayer;
            otherPlayer = temp;
        }
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
    //EFFECTS: initializes the deck and hand for each player
    private void initializeDeckAndHand(Player player1, Player player2) {
        player1.shuffleDeck();
        player2.shuffleDeck();

        player1.drawInitialHand();
        for (Card c : player1.getHand()) {
            System.out.println(player1.getName() + " drew a " + c.getName() + " from their deck.");
        }
        System.out.println("\n------------------------------");
        player2.drawInitialHand();
        for (Card c : player2.getHand()) {
            System.out.println(player2.getName() + " drew a " + c.getName() + " from their deck.");
        }
        System.out.println("\n------------------------------");

    }

    //EFFECTS: prints the current state of the game on the console
    private void printCurrentState(Player currentPlayer, Player otherPlayer) {
        System.out.println("\n------------------------------");
        System.out.println(currentPlayer.getName() + "'s turn.");
        System.out.println("Current board state:");
        System.out.println(currentPlayer.getName());
        if (currentPlayer.getActivePokemon() == null) {
            System.out.println("No active Pokemon");
        } else {
            System.out.println(currentPlayer.getActivePokemon());
        }
        System.out.println(otherPlayer.getName());
        if (otherPlayer.getActivePokemon() == null) {
            System.out.println("No active Pokemon");
        } else {
            System.out.println(otherPlayer.getActivePokemon());
        }
        System.out.println("\n------------------------------");
    }

}

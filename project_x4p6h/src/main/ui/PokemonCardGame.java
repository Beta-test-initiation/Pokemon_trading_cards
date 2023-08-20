package ui;

import model.Card;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Main code of the game; initializes player, scanner, decks, prints instructions and creates cards
public class PokemonCardGame {
    private static final String JSON_STORE = "./data/pokemonCards.json";

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private GameFrame frame;

    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    ArrayList<Card> allCards;

    public PokemonCardGame() {
        Scanner scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        frame = new GameFrame(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);





        allCards = askToLoad(scanner);

        askToCreateCards(allCards, scanner);


        Collections.shuffle(allCards);

        // Create two decks of 5 cards each
        ArrayList<Card> deck1 = new ArrayList<Card>();
        ArrayList<Card> deck2 = new ArrayList<Card>();

        //adds cards to each player deck
        for (int i = 0; i < 5; i++) {
            deck1.add(allCards.get(i));
            deck2.add(allCards.get(i + 5));
        }

        printInstructions();
        // Create players and start the game
        Player player1 = new Player("Player 1", deck1);
        Player player2 = new Player("Player 2", deck2);

        Game game = new Game(player1, player2);
        game.play();
    }

    //REQUIRES: scanner object
    //MODIFIES: this
    //EFFECTS: asks the user if they want to create cards. If yes, calls createCard();
    private void askToCreateCards(ArrayList<Card> allCards, Scanner scanner) {
        System.out.print("Do you want to create new Pokemon Cards (y/n) ");
        String input = scanner.nextLine().toLowerCase();

        while (!input.equals("y") && !input.equals("n")) {
            System.out.print("Invalid input. Do you want to create new cards? (y/n) ");
            input = scanner.nextLine().toLowerCase();
        }

        if (input.equals("y")) {
            createCard(allCards);
        }
    }





    //EFFECTS: Prints out the game instructions
    protected static void printInstructions() {
        System.out.println("\n------------------------------");
        System.out.println("Here's the instructions for the game");
        System.out.println("1. Each player is given a hand of 5 Pokemon Cards each.");
        System.out.println("2. Starting payer is randomly chosen and chooses card.");
        System.out.println("3. Each card can only attack once per turn. If your pokemon has HP left you can switch"
                + "to another pokemon and use it again later to attack again");
        System.out.println("4. Second player choses their pokemon based on the first player.");
        System.out.println("5. If the hit points of the second players card is more than the health points of the "
                + "second player the first player's pokemon faints, if not the dmg gets deducted from health points.");
        System.out.println("6. The player who runs out of pokemon first loses the game.");
        System.out.println("\n------------------------------");
    }

    //MODIFIES: this
    //EFFECTS: creates a new Card Object and adds it to the allCards array
    public void createCard(ArrayList<Card> allCards) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the name of the new card (or enter 'done' to finish): ");
            String name = scanner.nextLine();

            if (name.equals("done")) {
                askToSave(allCards, scanner);
                break;
            }
            try {
                System.out.print("Enter the HP of the new card: ");
                int hp = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter the damage of the new card: ");
                int damage = scanner.nextInt();
                scanner.nextLine();

                Card newCard = new Card(name, hp, damage);
                this.addCard(newCard);

                System.out.println("New card created: " + newCard);

            } catch (Exception e) {
                System.out.println("Your input type was invalid please make the card again");
                createCard(allCards);
            }


        }
    }

    //REQUIRES: scanner object
    //MODIFIES: ./data/pokemonCards.json
    //EFFECTS: asks the user if they want to save the cards they created. If yes, then calls saveCard()
    //         else prints out not saved
    private void askToSave(ArrayList<Card> allCards, Scanner scanner) {
        System.out.print("Do you want to save these cards? y/n ");
        String askToSave = scanner.nextLine();
        if (askToSave.equals("y")) {
            saveCards(allCards);
        } else {
            System.out.println("Cards not saved. Playing only with cards previously created");
        }
    }


    //MODIFIES: this
    //EFFECTS: adds Card to allCards
    public void addCard(Card c) {
        allCards.add(c);
    }


    // EFFECTS: saves allCards to file
    private void saveCards(ArrayList<Card> cards) {
        try {
            jsonWriter.open();
            jsonWriter.write(cards);
            jsonWriter.close();
            System.out.println("Pokemon Cards saved! You can use these cards later as well!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: loads a list of cards containing sample pokemon
    public ArrayList<Card> loadOnlySampleCards() {
        ArrayList<Card> allCards = new ArrayList<Card>();
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

    // MODIFIES: this
    // EFFECTS: loads All Pokemon Cards from file
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

    //EFFECTS: asks the users if they want to load Cards they have previously saved
    private ArrayList<Card> askToLoad(Scanner scanner) {
        System.out.print("Do you want to load cards previously created by you? y/n ");
        String loadCards = scanner.nextLine().toLowerCase();

        if (loadCards.equals("y")) {
            allCards = loadAllCardsFromJSon();
        } else {
            allCards = loadOnlySampleCards();
        }
        return allCards;
    }

}


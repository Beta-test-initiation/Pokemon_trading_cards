package persistence;

import model.Card;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonReader {

    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    // EFFECTS: reads String from file and returns it
    protected String readFile(String source) throws IOException {
        return new String(Files.readAllBytes(Paths.get(source)));
    }

    //EFFECTS: reads the PokemonCards.json File
    public ArrayList<Card> read() throws IOException {
        ArrayList<Card> cards = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File(source)));
        StringBuilder jsonString = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonString.append(line);
        }
        reader.close();
        JSONArray jsonCards = new JSONArray(new JSONTokener(jsonString.toString()));
        for (Object o : jsonCards) {
            JSONObject jsonCard = (JSONObject) o;
            Card c = new Card(
                    jsonCard.getString("name"),
                    jsonCard.getInt("hp"),
                    jsonCard.getInt("damage")
            );
            cards.add(c);
        }
        return cards;
    }


}

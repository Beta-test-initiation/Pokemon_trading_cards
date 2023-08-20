package persistence;

import model.Card;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest {

    private static final String TEST_FILE = "./data/testCardsWriter.json";
    private JsonWriter testWriter;
    private Card charizard;
    private Card pikachu;

    @BeforeEach
    void runBefore() throws FileNotFoundException {
        testWriter = new JsonWriter(TEST_FILE);
        charizard = new Card("Charizard", 150, 250);
        pikachu = new Card("Pikachu", 100, 50);
    }

    @Test
    void testConstructor() {
        assertEquals(TEST_FILE, testWriter.destination);
    }

    @Test
    void testWriteCards() throws FileNotFoundException {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(charizard);
        cards.add(pikachu);
        testWriter.open();
        testWriter.write(cards);
        testWriter.close();

        try {
            String json = new String(Files.readAllBytes(new File(TEST_FILE).toPath()));
            JSONArray jsonArray = new JSONArray(json);
            assertEquals(2, jsonArray.length());

            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
            assertEquals("Charizard", jsonObject1.getString("name"));
            assertEquals(150, jsonObject1.getInt("hp"));
            assertEquals(250, jsonObject1.getInt("damage"));

            JSONObject jsonObject2 = jsonArray.getJSONObject(1);
            assertEquals("Pikachu", jsonObject2.getString("name"));
            assertEquals(100, jsonObject2.getInt("hp"));
            assertEquals(50, jsonObject2.getInt("damage"));

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}

package persistence;

import model.Card;
import org.json.JSONObject;

public interface Writable {
    JSONObject toJson(Card c);
}

package model;


import org.json.JSONObject;

//Represents a Pokemon Card with name, health points and damage points
public class Card implements persistence.Writable {
    private final String name;
    private int hp;
    private final int damage;

    public Card(String name, int hp, int damage) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    //EFFECTS: overrides the default toString function to produce the String in the desired form
    @Override
    public String toString() {
        return name + " (" + hp + " HP, " + damage + " damage)";
    }

    //EFFECTS: creates a JSON Object for card
    @Override
    public JSONObject toJson(Card c) {
        JSONObject json = new JSONObject();
        json.put("name", c.getName());
        json.put("hp", c.getHP());
        json.put("damage", c.getDamage());
        EventLog.getInstance().logEvent(new Event("A new Card Object has been created"));
        return json;
    }
}


package main.java.com.adventure.model;

import java.util.HashMap;
import java.util.Map;

public class NPC {
    private final String name;
    private final Map<String, String> dialogues;
    private Item itemToGive;

    public NPC(String name) {
        this.name = name;
        this.dialogues = new HashMap<>();
    }

    // Getters

    public String getName() {
        return name;
    }

    // Dialogue methods
    public void addDialogue(String key, String dialogue) {
        dialogues.put(key, dialogue);
    }

    public String getDialogue(String key) {
        return dialogues.getOrDefault(key, "...");
    }

    // Item giving functionality
    public void setItemToGive(Item item) {
        this.itemToGive = item;
    }

    public Item getItemToGive() {
        Item item = itemToGive;
        itemToGive = null;  // Remove item after giving
        return item;
    }

    public boolean hasItem() {
        return itemToGive != null;
    }
}
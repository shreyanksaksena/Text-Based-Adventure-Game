package main.java.com.adventure.model;

import main.java.com.adventure.util.Direction;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class Room {
    private final String id;
    private final String name;
    private final String description;
    private final Map<Direction, Room> exits;
    private final List<Item> items;
    private NPC npc;
    private Enemy enemy;

    public Room(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItems() {
        return items;
    }

    public NPC getNpc() {
        return npc;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    // Room methods
    public void setExit(Direction direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(Direction direction) {
        return exits.get(direction);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public String getExitString() {
        StringBuilder returnString = new StringBuilder("Exits:");
        for (Direction direction : exits.keySet()) {
            returnString.append(" ").append(direction.getName());
        }
        return returnString.toString();
    }

    public String getFullDescription() {
        StringBuilder description = new StringBuilder();
        description.append("\n").append(name).append("\n");
        description.append(this.description).append("\n");
        description.append(getExitString()).append("\n");

        if (!items.isEmpty()) {
            description.append("\nItems in this room:");
            for (Item item : items) {
                description.append(" ").append(item.name());
            }
        }

        if (npc != null) {
            description.append("\nThere is ").append(npc.getName()).append(" here.");
        }

        if (enemy != null) {
            description.append("\nWarning! ").append(enemy.getName()).append(" is here!");
        }

        return description.toString();
    }
}
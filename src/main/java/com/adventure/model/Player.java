package main.java.com.adventure.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private int health;
    private final int maxHealth;
    private Room currentRoom;
    private final List<Item> inventory;
    private int attackPower;

    public Player(String name) {
        this.name = name;
        this.maxHealth = 100;
        this.health = maxHealth;
        this.inventory = new ArrayList<>();
        this.attackPower = 20;
    }

    // Health methods
    public int getHealth() {
        return health;
    }

    public void heal(int amount) {
        health = Math.min(health + amount, maxHealth);
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    public boolean isAlive() {
        return health > 0;
    }

    // Inventory methods
    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public boolean hasItem(String itemId) {
        return inventory.stream().anyMatch(item -> item.id().equals(itemId));
    }

    public List<Item> getInventory() {
        return new ArrayList<>(inventory);
    }

    public String getInventoryString() {
        if (inventory.isEmpty()) {
            return "Your inventory is empty.";
        }
        StringBuilder sb = new StringBuilder("Inventory:\n");
        for (Item item : inventory) {
            sb.append("- ").append(item.toString()).append("\n");
        }
        return sb.toString();
    }

    // Room navigation
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    // Combat methods
    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    // General getters
    public String getName() {
        return name;
    }

    public String getStatus() {
        return String.format("%s - Health: %d/%d", name, health, maxHealth);
    }
}
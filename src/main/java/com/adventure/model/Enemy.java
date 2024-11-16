package main.java.com.adventure.model;

public class Enemy {
    private final String id;
    private final String name;
    private int health;
    private final int maxHealth;
    private final int attackPower;
    private Item loot;

    public Enemy(String id, String name, int maxHealth, int attackPower) {
        this.id = id;
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
    }

    // Combat methods
    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    public int getAttackDamage() {
        return attackPower;
    }

    // Loot methods
    public void setLoot(Item item) {
        this.loot = item;
    }

    public Item getLoot() {
        if (loot == null) {
            return null;
        }
        Item droppedLoot = loot;
        loot = null;  // Remove loot after dropping
        return droppedLoot;
    }

    public boolean hasLoot() {
        return loot != null;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getStatus() {
        return String.format("%s - Health: %d/%d", name, health, maxHealth);
    }

    // Factory methods for creating specific enemies
    public static Enemy createWolf() {
        return new Enemy("wolf", "Fierce Wolf", 50, 15);
    }

    public static Enemy createSkeleton() {
        return new Enemy("skeleton", "Ancient Skeleton", 40, 20);
    }

    public static Enemy createDragon() {
        Enemy dragon = new Enemy("dragon", "Ancient Dragon", 100, 30);
        // Ensure dragon has loot
        Item dragonGem = new Item("dragon_gem", "Dragon's Gem",
                "A precious gem from the dragon's hoard", true);
        dragon.setLoot(dragonGem);
        return dragon;
    }
}
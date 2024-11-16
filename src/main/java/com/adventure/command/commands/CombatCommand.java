package main.java.com.adventure.command.commands;

import main.java.com.adventure.command.Command;
import main.java.com.adventure.game.Game;
import main.java.com.adventure.game.GameState;
import main.java.com.adventure.model.Enemy;
import main.java.com.adventure.model.Item;
import main.java.com.adventure.model.Room;
import main.java.com.adventure.util.GameText;

public class CombatCommand implements Command {
    @Override
    public boolean execute(Game game, String[] args) {
        if (game.getGameState() != GameState.COMBAT) {
            Room currentRoom = game.getPlayer().getCurrentRoom();
            Enemy enemy = currentRoom.getEnemy();

            if (enemy == null) {
                System.out.println(GameText.NO_ENEMY);
                return false;
            }

            game.setGameState(GameState.COMBAT);
            System.out.println(GameText.COMBAT_START);
        }

        // Handle combat round
        handleCombatRound(game);
        return true;
    }

    private void handleCombatRound(Game game) {
        Enemy enemy = game.getPlayer().getCurrentRoom().getEnemy();

        // Player attacks
        int playerDamage = game.getPlayer().getAttackPower();
        enemy.takeDamage(playerDamage);
        System.out.println("You hit " + enemy.getName() + " for " + playerDamage + " damage!");

        // Check if enemy is defeated
        if (!enemy.isAlive()) {
            handleEnemyDefeat(game, enemy);
            return;
        }

        // Enemy attacks
        int enemyDamage = enemy.getAttackDamage();
        game.getPlayer().takeDamage(enemyDamage);
        System.out.println(enemy.getName() + " hits you for " + enemyDamage + " damage!");

        // Display status
        System.out.println("\nStatus:");
        System.out.println("You: " + game.getPlayer().getStatus());
        System.out.println("Enemy: " + enemy.getStatus());

        // Check if player is defeated
        if (!game.getPlayer().isAlive()) {
            game.setGameState(GameState.GAME_OVER);
            System.out.println(GameText.GAME_OVER_MESSAGE);
        }
    }

    private void handleEnemyDefeat(Game game, Enemy enemy) {
        System.out.println("You defeated the " + enemy.getName() + "!");

        // Check if enemy has loot before trying to get it
        if (enemy.hasLoot()) {
            Item loot = enemy.getLoot();
            if (loot != null) {
                game.getPlayer().addItem(loot);
                System.out.println("You found: " + loot.name());
            }
        }

        game.getPlayer().getCurrentRoom().setEnemy(null);
        game.setGameState(GameState.PLAYING);

        // If this was the dragon, possibly trigger win condition check
        if (enemy.getId().equals("dragon")) {
            // Add dragon_gem to player's inventory if not already given as loot
            if (!game.getPlayer().hasItem("dragon_gem")) {
                Item dragonGem = new Item("dragon_gem", "Dragon's Gem",
                        "A precious gem from the dragon's hoard", true);
                game.getPlayer().addItem(dragonGem);
                System.out.println("You found the legendary Dragon's Gem!");
            }
        }
    }

    @Override
    public String getCommandName() {
        return "attack";
    }

    @Override
    public String getDescription() {
        return "attack - Attack an enemy in the current room";
    }
}
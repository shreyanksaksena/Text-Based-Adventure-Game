package main.java.com.adventure.game;

import main.java.com.adventure.command.CommandExecutor;
import main.java.com.adventure.model.*;
import main.java.com.adventure.util.Direction;
import main.java.com.adventure.util.GameText;

import java.util.Scanner;

public class Game {
    private Player player;
    private GameState gameState;
    private final CommandExecutor commandExecutor;
    private final Scanner scanner;
    private boolean isRunning;

    public Game() {
        this.scanner = new Scanner(System.in);
        this.gameState = GameState.MENU;
        this.isRunning = false;
        this.commandExecutor = new CommandExecutor(this);
    }

    public void start() {
        initialize();
        gameLoop();
    }

    private void initialize() {
        System.out.println(GameText.WELCOME_MESSAGE);
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        this.player = new Player(playerName);
        createGameWorld();
        gameState = GameState.PLAYING;
        isRunning = true;
    }

    private void createGameWorld() {
        // Create Rooms
        Room entrance = new Room("entrance", "Castle Entrance",
                "You stand before an ancient castle entrance. The massive wooden doors creak ominously in the wind.");

        Room forest = new Room("forest", "Mysterious Forest",
                "A dense forest surrounds you. Ancient trees tower overhead, their branches creating mysterious shadows.");

        Room dungeon = new Room("dungeon", "Dark Dungeon",
                "Cold, damp walls surround you in this dimly lit dungeon. Chains hang from the walls ominously.");

        Room treasureRoom = new Room("treasure_room", "Treasure Room",
                "Glittering gold and precious gems fill this magnificent chamber. A dragon guards the hoard.");

        // Create and set the dragon with proper initialization
        Enemy dragon = Enemy.createDragon();
        treasureRoom.setEnemy(dragon);

        // Connect Rooms
        entrance.setExit(Direction.NORTH, dungeon);
        entrance.setExit(Direction.EAST, forest);

        forest.setExit(Direction.WEST, entrance);
        forest.setExit(Direction.NORTH, treasureRoom);

        dungeon.setExit(Direction.SOUTH, entrance);
        dungeon.setExit(Direction.EAST, treasureRoom);

        treasureRoom.setExit(Direction.WEST, dungeon);
        treasureRoom.setExit(Direction.SOUTH, forest);

        // Add Items
        entrance.addItem(new Item("torch", "Torch", "A sturdy torch that provides light", true));
        forest.addItem(new Item("potion", "Healing Potion", "Restores 50 health points", true));
        dungeon.addItem(new Item("key", "Ancient Key", "A mysterious key that might be useful", true));

        // Add NPCs
        NPC elder = new NPC("Wise Elder");
        elder.addDialogue("default", "Beware of the dragon! You'll need the ancient key to access the treasure.");
        elder.setItemToGive(new Item("map", "Ancient Map", "Shows a detailed layout of the castle", true));
        forest.setNpc(elder);

        // Add Enemies
        forest.setEnemy(Enemy.createWolf());
        dungeon.setEnemy(Enemy.createSkeleton());
        treasureRoom.setEnemy(Enemy.createDragon());

        // Set starting room
        player.setCurrentRoom(entrance);
        System.out.println(entrance.getFullDescription());
    }

    private void gameLoop() {
        while (isRunning) {
            System.out.print("\n> ");
            String input = scanner.nextLine();

            if (input.trim().isEmpty()) {
                continue;
            }

            commandExecutor.executeCommand(input);

            // Check win condition
            if (checkWinCondition()) {
                gameState = GameState.WIN;
                System.out.println(GameText.WIN_MESSAGE);
                isRunning = false;
            }

            // Check game over condition
            if (gameState == GameState.GAME_OVER) {
                System.out.println(GameText.GAME_OVER_MESSAGE);
                isRunning = false;
            }
        }

        scanner.close();
    }

    private boolean checkWinCondition() {
        Room currentRoom = player.getCurrentRoom();
        return currentRoom.getId().equals("treasure_room") &&
                currentRoom.getEnemy() == null &&
                player.hasItem("dragon_gem");
    }

    // Getters and setters
    public Player getPlayer() {
        return player;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void quit() {
        isRunning = false;
    }
}
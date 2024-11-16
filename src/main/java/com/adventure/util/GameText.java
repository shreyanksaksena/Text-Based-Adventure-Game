package main.java.com.adventure.util;

public class GameText {
    // General game messages
    public static final String WELCOME_MESSAGE =
            "Welcome to the Adventure Game!\n" +
                    "Find the treasure and defeat the dragon to win.\n" +
                    "Type 'help' for a list of commands.\n";

    public static final String HELP_MESSAGE =
            "\nAvailable commands:\n" +
                    "- go <direction>: Move in a direction (north, south, east, west)\n" +
                    "- look: Look around the current room\n" +
                    "- inventory: Check your inventory\n" +
                    "- take <item>: Pick up an item\n" +
                    "- talk: Talk to an NPC if present\n" +
                    "- attack: Attack an enemy if present\n" +
                    "- run: Try to escape from combat\n" +
                    "- quit: Exit the game\n";

    // Combat messages
    public static final String COMBAT_START = "\nCombat started!";
    public static final String COMBAT_END = "\nCombat ended!";
    public static final String COMBAT_FLEE = "You managed to escape!";
    public static final String COMBAT_CANT_FLEE = "You couldn't escape!";

    // Game end messages
    public static final String WIN_MESSAGE =
            "\nCongratulations! You've found the treasure and defeated the dragon!\n" +
                    "You are victorious!\n";

    public static final String GAME_OVER_MESSAGE =
            "\nGame Over!\n" +
                    "Your adventure has come to an end.\n";

    // Error messages
    public static final String INVALID_DIRECTION = "You can't go that way!";
    public static final String INVALID_COMMAND = "Invalid command. Type 'help' for available commands.";
    public static final String NO_ITEM = "There's no such item here.";
    public static final String NO_NPC = "There's no one to talk to here.";
    public static final String NO_ENEMY = "There's no enemy to fight here.";
}
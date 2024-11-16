package main.java.com.adventure.command;

import main.java.com.adventure.game.Game;
import main.java.com.adventure.command.commands.*;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Map<String, Command> commands;
    private final Game game;

    public CommandExecutor(Game game) {
        this.game = game;
        this.commands = new HashMap<>();
        initializeCommands();
    }

    private void initializeCommands() {
        registerCommand(new MoveCommand());
        registerCommand(new LookCommand());
        registerCommand(new InventoryCommand());
        registerCommand(new TakeCommand());
        registerCommand(new TalkCommand());
        registerCommand(new CombatCommand());
        registerCommand(new UseCommand());  // Add this line
        registerCommand(new HelpCommand(this));
        registerCommand(new QuitCommand());
    }

    private void registerCommand(Command command) {
        commands.put(command.getCommandName().toLowerCase(), command);
    }

    public void executeCommand(String input) {
        String[] parts = input.trim().toLowerCase().split("\\s+", 2);
        String commandName = parts[0];
        String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

        Command command = commands.get(commandName);
        if (command != null) {
            command.execute(game, args);
        } else {
            System.out.println("Unknown command. Type 'help' for available commands.");
        }
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
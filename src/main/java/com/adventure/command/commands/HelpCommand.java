package main.java.com.adventure.command.commands;

import main.java.com.adventure.command.Command;
import main.java.com.adventure.command.CommandExecutor;
import main.java.com.adventure.game.Game;

public class HelpCommand implements Command {
    private final CommandExecutor commandExecutor;

    public HelpCommand(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Override
    public boolean execute(Game game, String[] args) {
        System.out.println("\nAvailable commands:");
        commandExecutor.getCommands().values().stream()
                .map(Command::getDescription)
                .sorted()
                .forEach(System.out::println);
        return true;
    }

    @Override
    public String getCommandName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "help - Display this help message";
    }
}
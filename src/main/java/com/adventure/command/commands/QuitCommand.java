package main.java.com.adventure.command.commands;

import main.java.com.adventure.command.Command;
import main.java.com.adventure.game.Game;

public class QuitCommand implements Command {
    @Override
    public boolean execute(Game game, String[] args) {
        System.out.println("Thank you for playing! Goodbye.");
        game.quit();
        return true;
    }

    @Override
    public String getCommandName() {
        return "quit";
    }

    @Override
    public String getDescription() {
        return "quit - Exit the game";
    }
}
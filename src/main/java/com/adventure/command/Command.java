package main.java.com.adventure.command;

import main.java.com.adventure.game.Game;

public interface Command {
    boolean execute(Game game, String[] args);
    String getCommandName();
    String getDescription();
}
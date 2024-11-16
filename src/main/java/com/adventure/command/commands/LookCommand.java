package main.java.com.adventure.command.commands;

import main.java.com.adventure.command.Command;
import main.java.com.adventure.game.Game;

public class LookCommand implements Command {
    @Override
    public boolean execute(Game game, String[] args) {
        System.out.println(game.getPlayer().getCurrentRoom().getFullDescription());
        return true;
    }

    @Override
    public String getCommandName() {
        return "look";
    }

    @Override
    public String getDescription() {
        return "look - Look around the current room";
    }
}
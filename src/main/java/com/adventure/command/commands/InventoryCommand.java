package main.java.com.adventure.command.commands;

import main.java.com.adventure.command.Command;
import main.java.com.adventure.game.Game;

public class InventoryCommand implements Command {
    @Override
    public boolean execute(Game game, String[] args) {
        System.out.println(game.getPlayer().getInventoryString());
        return true;
    }

    @Override
    public String getCommandName() {
        return "inventory";
    }

    @Override
    public String getDescription() {
        return "inventory - Check your inventory contents";
    }
}
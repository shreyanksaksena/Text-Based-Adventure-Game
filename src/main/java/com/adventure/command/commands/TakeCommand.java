package main.java.com.adventure.command.commands;

import main.java.com.adventure.command.Command;
import main.java.com.adventure.game.Game;
import main.java.com.adventure.model.Item;
import main.java.com.adventure.model.Room;
import main.java.com.adventure.util.GameText;

public class TakeCommand implements Command {
    @Override
    public boolean execute(Game game, String[] args) {
        if (args.length < 1) {
            System.out.println("Take what? Please specify an item.");
            return false;
        }

        String itemName = String.join(" ", args).toLowerCase();
        Room currentRoom = game.getPlayer().getCurrentRoom();

        Item itemToTake = null;
        for (Item item : currentRoom.getItems()) {
            if (item.name().toLowerCase().equals(itemName)) {
                itemToTake = item;
                break;
            }
        }

        if (itemToTake == null) {
            System.out.println(GameText.NO_ITEM);
            return false;
        }

        if (!itemToTake.isCollectible()) {
            System.out.println("You can't take that!");
            return false;
        }

        currentRoom.removeItem(itemToTake);
        game.getPlayer().addItem(itemToTake);
        System.out.println("You took the " + itemToTake.name() + ".");
        return true;
    }

    @Override
    public String getCommandName() {
        return "take";
    }

    @Override
    public String getDescription() {
        return "take <item> - Pick up an item from the current room";
    }
}
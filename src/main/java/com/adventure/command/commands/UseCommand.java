package main.java.com.adventure.command.commands;

import main.java.com.adventure.command.Command;
import main.java.com.adventure.game.Game;
import main.java.com.adventure.model.Item;
import main.java.com.adventure.model.Room;

public class UseCommand implements Command {
    @Override
    public boolean execute(Game game, String[] args) {
        if (args.length < 1) {
            System.out.println("Use what? Please specify an item.");
            return false;
        }

        String itemName = String.join(" ", args).toLowerCase();

        // Find the item in player's inventory
        for (Item item : game.getPlayer().getInventory()) {
            if (item.name().toLowerCase().contains(itemName)) {
                return useItem(game, item);
            }
        }

        System.out.println("You don't have that item.");
        return false;
    }

    private boolean useItem(Game game, Item item) {
        Room currentRoom = game.getPlayer().getCurrentRoom();

        switch (item.id().toLowerCase()) {
            case "potion":
            case "healing_potion":
                game.getPlayer().heal(50);
                game.getPlayer().removeItem(item);
                System.out.println("You used the healing potion and restored 50 health points!");
                System.out.println("Current health: " + game.getPlayer().getHealth());
                return true;

            case "key":
            case "rusty_key":
                if (currentRoom.getId().equals("treasure_room") ||
                        currentRoom.getId().equals("dungeon")) {
                    System.out.println("You use the rusty key. It seems to weaken the dragon's magical protection!");
                    // Could add some game mechanic here like weakening the dragon
                    game.getPlayer().setAttackPower(game.getPlayer().getAttackPower() + 10);
                    game.getPlayer().removeItem(item);
                    return true;
                } else {
                    System.out.println("There's nothing to use the key on here.");
                    return false;
                }

            case "torch":
                System.out.println("You hold up the torch, illuminating the area around you.");
                if (currentRoom.getId().equals("dungeon")) {
                    System.out.println("The torch reveals some hidden writing on the wall: 'Beware the dragon's fire!'");
                }
                return true;

            case "map":
                System.out.println("\nThe ancient map shows the castle layout:");
                System.out.println("                [Dungeon]----[Treasure Room]");
                System.out.println("                    |             |");
                System.out.println("             [Castle Entrance]----[Forest]");
                System.out.println("\nYour current location: " + currentRoom.getName());
                return true;

            case "dragon_gem":
                System.out.println("The dragon's gem pulses with ancient power. Perhaps it's part of the treasure you seek!");
                return true;

            default:
                System.out.println("You can't use that item.");
                return false;
        }
    }

    @Override
    public String getCommandName() {
        return "use";
    }

    @Override
    public String getDescription() {
        return "use <item> - Use an item from your inventory";
    }
}
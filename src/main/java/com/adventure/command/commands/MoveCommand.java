package main.java.com.adventure.command.commands;

import main.java.com.adventure.command.Command;
import main.java.com.adventure.game.Game;
import main.java.com.adventure.model.Room;
import main.java.com.adventure.util.Direction;
import main.java.com.adventure.util.GameText;

public class MoveCommand implements Command {
    @Override
    public boolean execute(Game game, String[] args) {
        if (args.length < 1) {
            System.out.println("Go where? Please specify a direction.");
            return false;
        }

        Direction direction = Direction.fromString(args[0]);
        if (direction == null) {
            System.out.println(GameText.INVALID_DIRECTION);
            return false;
        }

        Room currentRoom = game.getPlayer().getCurrentRoom();
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println(GameText.INVALID_DIRECTION);
            return false;
        }

        game.getPlayer().setCurrentRoom(nextRoom);
        System.out.println(nextRoom.getFullDescription());
        return true;
    }

    @Override
    public String getCommandName() {
        return "go";
    }

    @Override
    public String getDescription() {
        return "go <direction> - Move in the specified direction (north, south, east, west)";
    }
}
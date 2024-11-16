package main.java.com.adventure.command.commands;

import main.java.com.adventure.command.Command;
import main.java.com.adventure.game.Game;
import main.java.com.adventure.model.NPC;
import main.java.com.adventure.model.Room;
import main.java.com.adventure.util.GameText;

public class TalkCommand implements Command {
    @Override
    public boolean execute(Game game, String[] args) {
        Room currentRoom = game.getPlayer().getCurrentRoom();
        NPC npc = currentRoom.getNpc();

        if (npc == null) {
            System.out.println(GameText.NO_NPC);
            return false;
        }

        System.out.println("\nTalking to " + npc.getName());
        System.out.println(npc.getDialogue("default"));

        if (npc.hasItem()) {
            System.out.println(npc.getName() + " gives you an item!");
            game.getPlayer().addItem(npc.getItemToGive());
        }

        return true;
    }

    @Override
    public String getCommandName() {
        return "talk";
    }

    @Override
    public String getDescription() {
        return "talk - Talk to an NPC in the current room";
    }
}
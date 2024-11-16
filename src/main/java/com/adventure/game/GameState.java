package main.java.com.adventure.game;

public enum GameState {
    PLAYING("Exploring the world..."),
    COMBAT("In combat!"),
    DIALOG("Talking to NPC..."),
    MENU("Game Menu"),
    WIN("Victory!"),
    GAME_OVER("Game Over!");

    private final String description;

    GameState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
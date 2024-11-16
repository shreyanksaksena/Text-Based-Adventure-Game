package main.java.com.adventure.util;

public enum Direction {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west");

    private final String name;

    Direction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Direction fromString(String text) {
        for (Direction direction : Direction.values()) {
            if (direction.name.equalsIgnoreCase(text)) {
                return direction;
            }
        }
        return null;
    }
}
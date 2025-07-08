package com.mowitow.mower.domain.enums;

/**
 * Énumération des directions cardinales possibles pour la tondeuse
 */
public enum Direction {
    NORTH('N'),
    EAST('E'),
    SOUTH('S'),
    WEST('W');
    
    private final char symbol;
    
    Direction(char symbol) {
        this.symbol = symbol;
    }
    
    public char getSymbol() {
        return symbol;
    }
    
    /**
     * Retourne la direction après rotation à droite (90° clockwise)
     */
    public Direction turnRight() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }
    
    /**
     * Retourne la direction après rotation à gauche (90° counter-clockwise)
     */
    public Direction turnLeft() {
        return switch (this) {
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
        };
    }
    
    /**
     * Trouve une direction à partir de son symbole
     */
    public static Direction fromSymbol(char symbol) {
        for (Direction direction : values()) {
            if (direction.symbol == symbol) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Direction inconnue: " + symbol);
    }
}
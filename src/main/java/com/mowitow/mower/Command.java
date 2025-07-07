package com.mowitow.mower;

/**
 * Énumération des commandes possibles pour la tondeuse
 */
public enum Command {
    TURN_RIGHT('D'),
    TURN_LEFT('G'),
    MOVE_FORWARD('A');
    
    private final char symbol;
    
    Command(char symbol) {
        this.symbol = symbol;
    }
    
    public char getSymbol() {
        return symbol;
    }
    
    /**
     * Trouve une commande à partir de son symbole
     */
    public static Command fromSymbol(char symbol) {
        for (Command command : values()) {
            if (command.symbol == symbol) {
                return command;
            }
        }
        throw new IllegalArgumentException("Commande inconnue: " + symbol);
    }
}
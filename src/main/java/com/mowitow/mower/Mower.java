package com.mowitow.mower;

import java.util.List;

/**
 * Représente une tondeuse automatique qui peut se déplacer sur une pelouse
 */
public class Mower {
    private Position position;
    private Direction direction;
    private final Lawn lawn;
    
    public Mower(Position initialPosition, Direction initialDirection, Lawn lawn) {
        if (!lawn.isValidPosition(initialPosition)) {
            throw new IllegalArgumentException("Position initiale invalide: " + initialPosition);
        }
        this.position = initialPosition;
        this.direction = initialDirection;
        this.lawn = lawn;
    }
    
    /**
     * Exécute une série de commandes
     */
    public void executeCommands(List<Command> commands) {
        for (Command command : commands) {
            executeCommand(command);
        }
    }
    
    /**
     * Exécute une commande unique
     */
    private void executeCommand(Command command) {
        switch (command) {
            case TURN_RIGHT -> direction = direction.turnRight();
            case TURN_LEFT -> direction = direction.turnLeft();
            case MOVE_FORWARD -> moveForward();
        }
    }
    
    /**
     * Avance la tondeuse d'une case si possible.
     * Si la nouvelle position est hors limites, la tondeuse reste sur place.
     */
    private void moveForward() {
        Position newPosition = position.move(direction);
        if (lawn.isValidPosition(newPosition)) {
            position = newPosition;
        }
        // Si la position est invalide, on ne bouge pas (comme spécifié)
    }
    
    public Position getPosition() {
        return position;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    /**
     * Retourne la position et orientation actuelles sous forme de string
     */
    public String getCurrentState() {
        return String.format("%d %d %c", 
            position.getX(), position.getY(), direction.getSymbol());
    }
}
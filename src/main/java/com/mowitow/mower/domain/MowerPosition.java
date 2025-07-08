package com.mowitow.mower.domain;

import com.mowitow.mower.domain.enums.Direction;

/**
 * Représente une position et direction initiales pour une tondeuse
 */
public class MowerPosition {
    private final Position position;
    private final Direction direction;
    
    public MowerPosition(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public Direction getDirection() {
        return direction;
    }
}

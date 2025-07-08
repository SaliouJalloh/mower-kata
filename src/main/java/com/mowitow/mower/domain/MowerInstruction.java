package com.mowitow.mower.domain;

import com.mowitow.mower.domain.enums.Command;

import java.util.List;

/**
 * Représente une instruction complète pour une tondeuse
 */
public class MowerInstruction {
    private final MowerPosition initialPosition;
    private final List<Command> commands;
    private final Lawn lawn;
    
    public MowerInstruction(MowerPosition initialPosition, List<Command> commands, Lawn lawn) {
        this.initialPosition = initialPosition;
        this.commands = commands;
        this.lawn = lawn;
    }
    
    public MowerPosition getInitialPosition() {
        return initialPosition;
    }
    
    public List<Command> getCommands() {
        return commands;
    }
    
    public Lawn getLawn() {
        return lawn;
    }
}
package com.mowitow.mower;

import java.util.List;

/**
 * Configuration complète parsée depuis un fichier
 */
public class MowerConfiguration {
    private final Lawn lawn;
    private final List<MowerInstruction> mowerInstructions;
    
    public MowerConfiguration(Lawn lawn, List<MowerInstruction> mowerInstructions) {
        this.lawn = lawn;
        this.mowerInstructions = mowerInstructions;
    }
    
    public Lawn getLawn() {
        return lawn;
    }
    
    public List<MowerInstruction> getMowerInstructions() {
        return mowerInstructions;
    }
}
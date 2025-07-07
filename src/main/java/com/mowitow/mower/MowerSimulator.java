package com.mowitow.mower;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulateur principal qui orchestre l'exécution des tondeuses
 */
public class MowerSimulator {
    private final MowerFileParser parser;
    
    public MowerSimulator() {
        this.parser = new MowerFileParser();
    }
    
    /**
     * Exécute la simulation à partir d'un fichier
     */
    public List<String> simulateFromFile(Path filePath) throws IOException {
        MowerConfiguration config = parser.parseFile(filePath);
        return simulate(config);
    }
    
    /**
     * Exécute la simulation à partir d'une configuration
     */
    public List<String> simulate(MowerConfiguration config) {
        List<String> results = new ArrayList<>();
        
        // Chaque tondeuse se déplace séquentiellement
        for (MowerInstruction instruction : config.getMowerInstructions()) {
            Mower mower = createMower(instruction);
            mower.executeCommands(instruction.getCommands());
            results.add(mower.getCurrentState());
        }
        
        return results;
    }
    
    /**
     * Crée une tondeuse à partir d'une instruction
     */
    private Mower createMower(MowerInstruction instruction) {
        MowerPosition initialPos = instruction.getInitialPosition();
        return new Mower(
            initialPos.getPosition(),
            initialPos.getDirection(),
            instruction.getLawn()
        );
    }
}
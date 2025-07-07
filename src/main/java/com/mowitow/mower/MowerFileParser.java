package com.mowitow.mower;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Parseur pour les fichiers de configuration des tondeuses
 */
public class MowerFileParser {
    
    /**
     * Parse un fichier et retourne la configuration des tondeuses
     */
    public MowerConfiguration parseFile(Path filePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            return parseContent(reader);
        }
    }
    
    /**
     * Parse le contenu d'un reader
     */
    private MowerConfiguration parseContent(BufferedReader reader) throws IOException {
        String firstLine = reader.readLine();
        if (firstLine == null) {
            throw new IllegalArgumentException("Fichier vide");
        }
        
        Lawn lawn = parseLawnDimensions(firstLine);
        List<MowerInstruction> mowerInstructions = parseMowerInstructions(reader, lawn);
        
        return new MowerConfiguration(lawn, mowerInstructions);
    }
    
    /**
     * Parse les dimensions de la pelouse
     */
    private Lawn parseLawnDimensions(String line) {
        String[] parts = line.trim().split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Format invalide pour les dimensions: " + line);
        }
        
        try {
            int maxX = Integer.parseInt(parts[0]);
            int maxY = Integer.parseInt(parts[1]);
            return new Lawn(maxX, maxY);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Format invalide pour les dimensions: " + line, e);
        }
    }
    
    /**
     * Parse les instructions pour toutes les tondeuses
     */
    private List<MowerInstruction> parseMowerInstructions(BufferedReader reader, Lawn lawn) throws IOException {
        List<MowerInstruction> instructions = new ArrayList<>();
        String line;
        
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            
            // Position et direction initiales
            MowerPosition mowerPos = parseMowerPosition(line);
            
            // Commandes
            String commandLine = reader.readLine();
            if (commandLine == null) {
                throw new IllegalArgumentException("Commandes manquantes pour la tondeuse: " + line);
            }
            
            List<Command> commands = parseCommands(commandLine);
            instructions.add(new MowerInstruction(mowerPos, commands, lawn));
        }
        
        return instructions;
    }
    
    /**
     * Parse la position et direction initiales d'une tondeuse
     */
    private MowerPosition parseMowerPosition(String line) {
        String[] parts = line.trim().split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Format invalide pour la position: " + line);
        }
        
        try {
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            char dirChar = parts[2].charAt(0);
            
            Position position = new Position(x, y);
            Direction direction = Direction.fromSymbol(dirChar);
            
            return new MowerPosition(position, direction);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Position invalide: " + line, e);
        }
    }
    
    /**
     * Parse une série de commandes
     */
    private List<Command> parseCommands(String commandLine) {
        List<Command> commands = new ArrayList<>();
        for (char c : commandLine.trim().toCharArray()) {
            commands.add(Command.fromSymbol(c));
        }
        return commands;
    }
}

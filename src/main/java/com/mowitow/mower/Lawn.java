package com.mowitow.mower;

/**
 * Représente la pelouse rectangulaire sur laquelle évolue la tondeuse
 */
public class Lawn {
    private final int maxX;
    private final int maxY;
    
    public Lawn(int maxX, int maxY) {
        if (maxX < 0 || maxY < 0) {
            throw new IllegalArgumentException("Les dimensions de la pelouse doivent être positives");
        }
        this.maxX = maxX;
        this.maxY = maxY;
    }
    
    /**
     * Vérifie si une position est dans les limites de la pelouse
     */
    public boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() <= maxX 
            && position.getY() >= 0 && position.getY() <= maxY;
    }
    
    public int getMaxX() {
        return maxX;
    }
    
    public int getMaxY() {
        return maxY;
    }
}
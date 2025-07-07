package com.mowitow.mower;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class MowerSimulatorTest {
    
    @Test
    void should_simulate_complete_test_case(@TempDir Path tempDir) throws IOException {
        // Créer le fichier de test
        Path testFile = tempDir.resolve("test.txt");
        String content = """
            5 5
            1 2 N
            GAGAGAGAA
            3 3 E
            AADAADADDA
            """;
        Files.write(testFile, content.getBytes());
        
        MowerSimulator simulator = new MowerSimulator();
        List<String> results = simulator.simulateFromFile(testFile);
        
        // Vérifier les résultats attendus
        assertThat(results).hasSize(2);
        assertThat(results.get(0)).isEqualTo("1 3 N");
        assertThat(results.get(1)).isEqualTo("5 1 E");
    }
    
    @Test
    void should_handle_empty_file(@TempDir Path tempDir) throws IOException {
        Path emptyFile = tempDir.resolve("empty.txt");
        Files.write(emptyFile, "".getBytes());
        
        MowerSimulator simulator = new MowerSimulator();
        
        assertThatThrownBy(() -> simulator.simulateFromFile(emptyFile))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Fichier vide");
    }
    
    @Test
    void should_handle_invalid_lawn_dimensions(@TempDir Path tempDir) throws IOException {
        Path invalidFile = tempDir.resolve("invalid.txt");
        Files.write(invalidFile, "invalid input".getBytes());
        
        MowerSimulator simulator = new MowerSimulator();
        
        assertThatThrownBy(() -> simulator.simulateFromFile(invalidFile))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Format invalide pour les dimensions");
    }
}
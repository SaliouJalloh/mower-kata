package com.mowitow.mower.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

/**
 * Test d'intégration complet du système
 */
class IntegrationTest {
    
    @Test
    void should_execute_complete_kata_test_case(@TempDir Path tempDir) throws IOException {
        // Créer le fichier de test exact du kata
        Path testFile = tempDir.resolve("kata-test.txt");
        String exactContent = """
            5 5
            1 2 N
            GAGAGAGAA
            3 3 E
            AADAADADDA
            """;
        Files.write(testFile, exactContent.getBytes());
        
        // Exécuter la simulation
        MowerSimulator simulator = new MowerSimulator();
        List<String> results = simulator.simulateFromFile(testFile);
        
        // Vérifier le résultat exact attendu
        assertThat(results).containsExactly("1 3 N", "5 1 E");
        
        // Vérifier que le format de sortie est correct
        String output = String.join(" ", results);
        assertThat(output).isEqualTo("1 3 N 5 1 E");
    }
    
    @Test
    void should_handle_mower_hitting_boundaries(@TempDir Path tempDir) throws IOException {
        // Test avec tondeuse qui essaie de sortir des limites
        Path testFile = tempDir.resolve("boundary-test.txt");
        String content = """
            3 3
            0 0 W
            AAA
            3 3 E
            AAA
            """;
        Files.write(testFile, content.getBytes());
        
        MowerSimulator simulator = new MowerSimulator();
        List<String> results = simulator.simulateFromFile(testFile);
        
        // La première tondeuse ne doit pas bouger (déjà au bord ouest)
        assertThat(results.get(0)).isEqualTo("0 0 W");
        // La deuxième tondeuse ne doit pas bouger (déjà au bord est)
        assertThat(results.get(1)).isEqualTo("3 3 E");
    }
    
    @Test
    void should_handle_single_mower(@TempDir Path tempDir) throws IOException {
        Path testFile = tempDir.resolve("single-test.txt");
        String content = """
            2 2
            1 1 N
            ADAGA
            """;
        Files.write(testFile, content.getBytes());
        
        MowerSimulator simulator = new MowerSimulator();
        List<String> results = simulator.simulateFromFile(testFile);
        
        assertThat(results).hasSize(1);
        assertThat(results.get(0)).isEqualTo("2 2 N");
    }
    
    @Test
    void should_handle_complex_path(@TempDir Path tempDir) throws IOException {
        // Test avec un parcours plus complexe
        Path testFile = tempDir.resolve("complex-test.txt");
        String content = """
            10 10
            0 0 N
            AAAAAAAAAA
            10 10 S
            AAAAAAAAAA
            """;
        Files.write(testFile, content.getBytes());
        
        MowerSimulator simulator = new MowerSimulator();
        List<String> results = simulator.simulateFromFile(testFile);
        
        // Premier mower: monte jusqu'au bord nord
        assertThat(results.get(0)).isEqualTo("0 10 N");
        // Deuxième mower: descend jusqu'au bord sud
        assertThat(results.get(1)).isEqualTo("10 0 S");
    }
}
package com.mowitow.mower;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.*;

class MowerFileParserTest {
    
    private final MowerFileParser parser = new MowerFileParser();
    
    @Test
    void should_parse_valid_file(@TempDir Path tempDir) throws IOException {
        Path testFile = tempDir.resolve("test.txt");
        String content = """
            5 5
            1 2 N
            GAGAGAGAA
            3 3 E
            AADAADADDA
            """;
        Files.write(testFile, content.getBytes());
        
        MowerConfiguration config = parser.parseFile(testFile);
        
        assertThat(config.getLawn().getMaxX()).isEqualTo(5);
        assertThat(config.getLawn().getMaxY()).isEqualTo(5);
        assertThat(config.getMowerInstructions()).hasSize(2);
        
        // Première tondeuse
        MowerInstruction first = config.getMowerInstructions().get(0);
        assertThat(first.getInitialPosition().getPosition()).isEqualTo(new Position(1, 2));
        assertThat(first.getInitialPosition().getDirection()).isEqualTo(Direction.NORTH);
        assertThat(first.getCommands()).hasSize(9);
        
        // Deuxième tondeuse
        MowerInstruction second = config.getMowerInstructions().get(1);
        assertThat(second.getInitialPosition().getPosition()).isEqualTo(new Position(3, 3));
        assertThat(second.getInitialPosition().getDirection()).isEqualTo(Direction.EAST);
        assertThat(second.getCommands()).hasSize(10);
    }
    
    @Test
    void should_handle_file_with_whitespace(@TempDir Path tempDir) throws IOException {
        Path testFile = tempDir.resolve("test.txt");
        String content = """
            5 5
            
            1 2 N
            GAGAGAGAA
            
            3 3 E
            AADAADADDA
            """;
        Files.write(testFile, content.getBytes());
        
        MowerConfiguration config = parser.parseFile(testFile);
        
        assertThat(config.getMowerInstructions()).hasSize(2);
    }
    
    @Test
    void should_throw_exception_for_invalid_lawn_format(@TempDir Path tempDir) throws IOException {
        Path testFile = tempDir.resolve("test.txt");
        Files.write(testFile, "invalid".getBytes());
        
        assertThatThrownBy(() -> parser.parseFile(testFile))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Format invalide pour les dimensions");
    }
    
    @Test
    void should_throw_exception_for_missing_commands(@TempDir Path tempDir) throws IOException {
        Path testFile = tempDir.resolve("test.txt");
        String content = """
            5 5
            1 2 N
            """;
        Files.write(testFile, content.getBytes());
        
        assertThatThrownBy(() -> parser.parseFile(testFile))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Commandes manquantes");
    }
}
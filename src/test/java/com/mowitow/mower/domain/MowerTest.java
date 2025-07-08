package com.mowitow.mower.domain;

import com.mowitow.mower.domain.enums.Command;
import com.mowitow.mower.domain.enums.Direction;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class MowerTest {
    
    @Test
    void should_create_mower_at_initial_position() {
        Lawn lawn = new Lawn(5, 5);
        Position initialPos = new Position(1, 2);
        Direction initialDir = Direction.NORTH;
        
        Mower mower = new Mower(initialPos, initialDir, lawn);
        
        assertThat(mower.getPosition()).isEqualTo(initialPos);
        assertThat(mower.getDirection()).isEqualTo(initialDir);
    }
    
    @Test
    void should_throw_exception_for_invalid_initial_position() {
        Lawn lawn = new Lawn(5, 5);
        Position invalidPos = new Position(-1, 2);
        
        assertThatThrownBy(() -> new Mower(invalidPos, Direction.NORTH, lawn))
            .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    void should_turn_right_on_D_command() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(new Position(1, 2), Direction.NORTH, lawn);
        
        mower.executeCommands(List.of(Command.TURN_RIGHT));
        
        assertThat(mower.getDirection()).isEqualTo(Direction.EAST);
        assertThat(mower.getPosition()).isEqualTo(new Position(1, 2)); // Position inchangée
    }
    
    @Test
    void should_turn_left_on_G_command() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(new Position(1, 2), Direction.NORTH, lawn);
        
        mower.executeCommands(List.of(Command.TURN_LEFT));
        
        assertThat(mower.getDirection()).isEqualTo(Direction.WEST);
        assertThat(mower.getPosition()).isEqualTo(new Position(1, 2)); // Position inchangée
    }
    
    @Test
    void should_move_forward_on_A_command() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(new Position(1, 2), Direction.NORTH, lawn);
        
        mower.executeCommands(List.of(Command.MOVE_FORWARD));
        
        assertThat(mower.getPosition()).isEqualTo(new Position(1, 3));
        assertThat(mower.getDirection()).isEqualTo(Direction.NORTH); // Direction inchangée
    }
    
    @Test
    void should_not_move_outside_lawn_bounds() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(new Position(5, 5), Direction.NORTH, lawn);
        
        mower.executeCommands(List.of(Command.MOVE_FORWARD));
        
        // Ne doit pas bouger car hors limites
        assertThat(mower.getPosition()).isEqualTo(new Position(5, 5));
    }
    
    @Test
    void should_execute_test_case_first_mower() {
        // Premier cas de test: 1 2 N avec GAGAGAGAA
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(new Position(1, 2), Direction.NORTH, lawn);
        
        List<Command> commands = List.of(
            Command.TURN_LEFT,    // G
            Command.MOVE_FORWARD, // A
            Command.TURN_LEFT,    // G
            Command.MOVE_FORWARD, // A
            Command.TURN_LEFT,    // G
            Command.MOVE_FORWARD, // A
            Command.TURN_LEFT,    // G
            Command.MOVE_FORWARD, // A
            Command.MOVE_FORWARD  // A
        );
        
        mower.executeCommands(commands);
        
        assertThat(mower.getCurrentState()).isEqualTo("1 3 N");
    }
    
    @Test
    void should_execute_test_case_second_mower() {
        // Deuxième cas de test: 3 3 E avec AADAADADDA
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(new Position(3, 3), Direction.EAST, lawn);
        
        List<Command> commands = List.of(
            Command.MOVE_FORWARD, // A
            Command.MOVE_FORWARD, // A
            Command.TURN_RIGHT,   // D
            Command.MOVE_FORWARD, // A
            Command.MOVE_FORWARD, // A
            Command.TURN_RIGHT,   // D
            Command.MOVE_FORWARD, // A
            Command.TURN_RIGHT,   // D
            Command.TURN_RIGHT,   // D
            Command.MOVE_FORWARD  // A
        );
        
        mower.executeCommands(commands);
        
        assertThat(mower.getCurrentState()).isEqualTo("5 1 E");
    }
}
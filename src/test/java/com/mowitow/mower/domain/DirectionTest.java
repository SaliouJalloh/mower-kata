package com.mowitow.mower.domain;

import com.mowitow.mower.domain.enums.Direction;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class DirectionTest {
    
    @Test
    void should_turn_right_correctly() {
        assertThat(Direction.NORTH.turnRight()).isEqualTo(Direction.EAST);
        assertThat(Direction.EAST.turnRight()).isEqualTo(Direction.SOUTH);
        assertThat(Direction.SOUTH.turnRight()).isEqualTo(Direction.WEST);
        assertThat(Direction.WEST.turnRight()).isEqualTo(Direction.NORTH);
    }
    
    @Test
    void should_turn_left_correctly() {
        assertThat(Direction.NORTH.turnLeft()).isEqualTo(Direction.WEST);
        assertThat(Direction.WEST.turnLeft()).isEqualTo(Direction.SOUTH);
        assertThat(Direction.SOUTH.turnLeft()).isEqualTo(Direction.EAST);
        assertThat(Direction.EAST.turnLeft()).isEqualTo(Direction.NORTH);
    }
    
    @Test
    void should_find_direction_from_symbol() {
        assertThat(Direction.fromSymbol('N')).isEqualTo(Direction.NORTH);
        assertThat(Direction.fromSymbol('E')).isEqualTo(Direction.EAST);
        assertThat(Direction.fromSymbol('S')).isEqualTo(Direction.SOUTH);
        assertThat(Direction.fromSymbol('W')).isEqualTo(Direction.WEST);
    }
    
    @Test
    void should_throw_exception_for_invalid_symbol() {
        assertThatThrownBy(() -> Direction.fromSymbol('X'))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Direction inconnue: X");
    }
}
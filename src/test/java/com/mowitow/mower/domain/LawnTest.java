package com.mowitow.mower.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class LawnTest {
    
    @Test
    void should_validate_position_within_bounds() {
        Lawn lawn = new Lawn(5, 5);
        
        assertThat(lawn.isValidPosition(new Position(0, 0))).isTrue();
        assertThat(lawn.isValidPosition(new Position(5, 5))).isTrue();
        assertThat(lawn.isValidPosition(new Position(2, 3))).isTrue();
    }
    
    @Test
    void should_reject_position_outside_bounds() {
        Lawn lawn = new Lawn(5, 5);
        
        assertThat(lawn.isValidPosition(new Position(-1, 0))).isFalse();
        assertThat(lawn.isValidPosition(new Position(0, -1))).isFalse();
        assertThat(lawn.isValidPosition(new Position(6, 5))).isFalse();
        assertThat(lawn.isValidPosition(new Position(5, 6))).isFalse();
    }
    
    @Test
    void should_throw_exception_for_negative_dimensions() {
        assertThatThrownBy(() -> new Lawn(-1, 5))
            .isInstanceOf(IllegalArgumentException.class);
        
        assertThatThrownBy(() -> new Lawn(5, -1))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
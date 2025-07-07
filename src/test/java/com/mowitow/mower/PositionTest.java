package com.mowitow.mower;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class PositionTest {
    
    @Test
    void should_move_north_correctly() {
        Position position = new Position(1, 1);
        Position newPosition = position.move(Direction.NORTH);
        
        assertThat(newPosition.getX()).isEqualTo(1);
        assertThat(newPosition.getY()).isEqualTo(2);
    }
    
    @Test
    void should_move_south_correctly() {
        Position position = new Position(1, 1);
        Position newPosition = position.move(Direction.SOUTH);
        
        assertThat(newPosition.getX()).isEqualTo(1);
        assertThat(newPosition.getY()).isEqualTo(0);
    }
    
    @Test
    void should_move_east_correctly() {
        Position position = new Position(1, 1);
        Position newPosition = position.move(Direction.EAST);
        
        assertThat(newPosition.getX()).isEqualTo(2);
        assertThat(newPosition.getY()).isEqualTo(1);
    }
    
    @Test
    void should_move_west_correctly() {
        Position position = new Position(1, 1);
        Position newPosition = position.move(Direction.WEST);
        
        assertThat(newPosition.getX()).isEqualTo(0);
        assertThat(newPosition.getY()).isEqualTo(1);
    }
    
    @Test
    void should_be_equal_when_same_coordinates() {
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(1, 2);
        
        assertThat(pos1).isEqualTo(pos2);
        assertThat(pos1.hashCode()).isEqualTo(pos2.hashCode());
    }
}
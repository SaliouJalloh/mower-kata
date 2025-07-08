package com.mowitow.mower.domain;

import com.mowitow.mower.domain.enums.Command;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class CommandTest {
    
    @Test
    void should_find_command_from_symbol() {
        assertThat(Command.fromSymbol('D')).isEqualTo(Command.TURN_RIGHT);
        assertThat(Command.fromSymbol('G')).isEqualTo(Command.TURN_LEFT);
        assertThat(Command.fromSymbol('A')).isEqualTo(Command.MOVE_FORWARD);
    }
    
    @Test
    void should_throw_exception_for_invalid_command() {
        assertThatThrownBy(() -> Command.fromSymbol('X'))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Commande inconnue: X");
    }
}
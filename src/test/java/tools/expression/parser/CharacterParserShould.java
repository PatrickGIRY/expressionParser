package tools.expression.parser;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CharacterParserShould {

    private static final String NULL_INPUT = null;
    private static final String EMPTY_INPUT = "";

    @Nested
    class with_failure_parser {

        @Test
        void always_return_empty() {

            final var input = "any input";
            final var index = 0;
            final var parser = CharacterParser.failure();

            final var result = parser.tryParse(index, input);

            assertThat(result).isEmpty();
        }
    }

    @Nested
    class with_value_of_parser {

        @Test
        void always_return_success_with_given_code_point_and_input_at_index_0_as_remaining() {

            final int codePoint = 'a';
            final var parser = CharacterParser.valueOf(codePoint);
            final var input = "bcd";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).hasValue(codePoint);
        }
    }

    @Nested
    class  with_item_parser {

        @Test
        void return_empty_with_empty_input() {
            final var parser = CharacterParser.item();

            final var result = parser.tryParse(0, EMPTY_INPUT);

            assertThat(result).isEmpty();
        }

        @Test
        void return_first_character_and_remaining_when_input_is_not_empty() {
            final var parser = CharacterParser.item();
            final var input = "a";

            final var result = parser.tryParse(0, input);

            assertThat(result).hasValue('a');
        }

        @Test
        void throw_NullPointerException_when_input_is_null() {
            final var parser = CharacterParser.item();

            assertThatThrownBy(() -> parser.tryParse(0, NULL_INPUT)).isInstanceOf(NullPointerException.class);
        }
    }
}

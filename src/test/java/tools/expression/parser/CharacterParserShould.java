package tools.expression.parser;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CharacterParserShould {

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
}

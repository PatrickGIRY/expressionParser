package tools.expression.parser;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParserShould {

    private static final String NULL_INPUT = null;

    @Nested
    class with_failure_parser {

        @Test
        void always_return_empty() {

            final var input = "any input";
            final var parser = Parser.failure();

            final var result = parser.tryParse(input);

            assertThat(result).isEmpty();
        }
    }

    @Nested
    class with_value_of_parser {

        @Test
        void always_return_success_with_given_code_point_and_input_at_index_0_as_remaining() {

            final int codePoint = 'a';
            final var parser = Parser.valueOf(codePoint);
            final var input = "bcd";

            final var result = parser.tryParse(input);

            final var remaining = new Remaining(0, input);
            assertThat(result).hasValue(new IntSuccess(codePoint, remaining));
        }

        @Test
        void throw_NullPointException_when_input_is_null() {
            final int codePoint = 'a';
            final var parser = Parser.valueOf(codePoint);

            assertThatThrownBy(() -> parser.tryParse(NULL_INPUT));
        }
    }

    @Nested
    class  with_item_parser {

        @Test
        void return_empty_with_empty_input() {
            final var parser = Parser.item();

            final var result = parser.tryParse("");

            assertThat(result).isEmpty();
        }
    }
}

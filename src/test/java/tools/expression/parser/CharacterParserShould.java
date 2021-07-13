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
        void return_empty_with_index_equal_to_input_length() {
            final var parser = CharacterParser.item();
            final var input = "a";
            final var index = 1;

            final var result = parser.tryParse(index, input);

            assertThat(result).isEmpty();
        }

        @Test
        void return_empty_with_index_greater_than_input_length() {
            final var parser = CharacterParser.item();
            final var input = "a";
            final var index = 1;

            final var result = parser.tryParse(index, input);

            assertThat(result).isEmpty();
        }

        @Test
        void return_first_character_and_remaining_when_input_is_not_empty() {
            final var parser = CharacterParser.item();
            final var input = "a";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).hasValue('a');
        }

        @Test
        void throw_NullPointerException_when_input_is_null() {
            final var parser = CharacterParser.item();

            assertThatThrownBy(() -> parser.tryParse(0, NULL_INPUT)).isInstanceOf(NullPointerException.class);
        }

        @Test
        void return_the_second_chararter_when_sequence_two_item_parsers_with_an_input_of_length_2() {
            final var parser = CharacterParser.item().flatMap(c -> CharacterParser.item());

            final var input = "ab";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).hasValue('b');
        }

        @Test
        void return_empty_when_sequence_two_item_parsers_with_an_input_of_length_1() {
            final var parser = CharacterParser.item().flatMap(c -> CharacterParser.item());

            final var input = "a";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).isEmpty();
        }

        @Test
        void return_empty_when_sequence_two_item_parsers_with_an_input_of_length_0() {
            final var parser = CharacterParser.item().flatMap(c -> CharacterParser.item());

            final var input = "";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).isEmpty();
        }

        @Test
        void return_the_character_at_the_index_position_when_the_predicate_is_true() {
           final var parser = CharacterParser.item().satisfy(__ -> true);

            final var input = "a";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).hasValue(input.charAt(index));
        }

        @Test
        void return_empty_when_the_predicate_is_false() {
           final var parser = CharacterParser.item().satisfy(__ -> false);

            final var input = "a";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).isEmpty();
        }

        @Test
        void return_the_character_at_the_index_position_when_it_matches_the_expected_one() {
            final var parser = CharacterParser.item().character('a');

            final var input = "a";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).hasValue(input.charAt(index));
        }
    }
}

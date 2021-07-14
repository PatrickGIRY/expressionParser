package tools.expression.parser;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CharacterParserShould {

    private static final String NULL_INPUT = null;
    private static final String EMPTY_INPUT = "";
    private static final IntPredicate NULL_PREDICATE = null;
    private static final IntFunction<Object> NULL_MAP_TO_OBJ_MAPPER = null;
    private static final IntFunction<CharacterParser> NULL_FLAT_MAP_MAPPER = null;

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
        void return_character_at_the_index_position_when_the_index_position_is_in_input() {
            final var parser = CharacterParser.item();
            final var input = "a";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).hasValue('a');
        }

        @Test
        void throw_NullPointerException_when_input_is_null() {
            final var parser = CharacterParser.item();

            assertThatThrownBy(() -> parser.tryParse(0, NULL_INPUT))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("input required");
        }
    }

    @Nested
    class with_flatMap {
        @Test
        void return_the_result_of_the_second_parser_when_the_first_one_is_successful() {
            final var parser = CharacterParser.valueOf('y')
                    .flatMap(__ -> CharacterParser.valueOf('z'));

            final var input = "ab";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).hasValue('z');
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
        void throw_NullPointerException_when_mapper_is_null() {

            assertThatThrownBy(() -> CharacterParser.valueOf('f').flatMap(NULL_FLAT_MAP_MAPPER))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("mapper required");
        }

    }

    @Nested
    class with_satisfy {

        @Test
        void return_the_character_at_the_index_position_when_the_predicate_is_true() {
            final var parser = CharacterParser.satisfy(__ -> true);

            final var input = "a";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).hasValue(input.charAt(index));
        }

        @Test
        void throw_NullPointerException_when_predicate_is_null() {

            assertThatThrownBy(() -> CharacterParser.satisfy(NULL_PREDICATE))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("predicate required");
        }

        @Test
        void return_empty_when_the_predicate_is_false() {
            final var parser = CharacterParser.satisfy(__ -> false);

            final var input = "a";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).isEmpty();
        }
    }

    @Nested
    class with_character {
        @Test
        void return_the_character_at_the_index_position_when_it_matches_the_expected_one() {
            final var parser = CharacterParser.character('a');

            final var input = "a";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).hasValue(input.charAt(index));
        }
    }

    @Nested
    class with_mapToObj {
        @Test
        void transform_character_parser_result_to_object_parser_when_the_first_one_is_successfull() {
            final var parser = CharacterParser.valueOf('z').mapToObj(c -> "" + (char) c);

            final var input = "a";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).hasValue("z");
        }

        @Test
        void transform_character_parser_result_to_empty_object_parser_when_the_first_one_fail() {
            final var parser = CharacterParser.failure().mapToObj(c -> "" + (char) c);

            final var input = "a";
            final var index = 0;

            final var result = parser.tryParse(index, input);

            assertThat(result).isEmpty();
        }

        @Test
        void throw_NullPointerException_when_mapper_is_null() {

            assertThatThrownBy(() -> CharacterParser.valueOf('f').mapToObj(NULL_MAP_TO_OBJ_MAPPER))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("mapper required");
        }
    }
}

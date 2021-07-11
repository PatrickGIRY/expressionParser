package tools.expression.parser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParserShould {

    @Test
    void always_return_empty_with_failure_parser_factory() {

        final var input = "any input";
        final var parser = Parser.failure();

        final var result = parser.tryParse(input);

        assertThat(result).isEmpty();
    }

    @Test
    void always_return_success_with_given_code_point_ans_input_as_remaining_with_parser_value_of() {

        final int codePoint = 'a';
        final var parser = Parser.<CodePointSuccess>valueOf(codePoint);
        final var input = "bcd";

        final var result = parser.tryParse(input);

        final var remaining = new Remaining(0, input);
        assertThat(result).hasValue(new CodePointSuccess(codePoint, remaining));
    }
}

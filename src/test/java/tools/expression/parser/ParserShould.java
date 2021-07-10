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
}

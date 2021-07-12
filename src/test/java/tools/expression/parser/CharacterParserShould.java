package tools.expression.parser;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}

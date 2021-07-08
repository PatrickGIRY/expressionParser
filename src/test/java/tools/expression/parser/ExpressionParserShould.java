package tools.expression.parser;

import org.junit.jupiter.api.Test;
import tools.expression.domain.Value;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpressionParserShould {

    @Test
    void create_a_value_with_the_given_text() {
        final var expression = "2";

        var parser = ExpressionParser.create();

        final var result = parser.tryParse(expression);

        assertThat(result).hasValue(new Value(2));
    }
}

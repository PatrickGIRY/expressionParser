package tools.expression.parser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tools.expression.domain.Value;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpressionParserShould {

    @ParameterizedTest
    @ValueSource(strings = {"2", "3"})
    void create_a_value_with_the_given_text(String expression) {

        final var expectedValue = new Value(Integer.parseInt(expression));
        final ValueParser valueParser = input -> Optional.of(new Value(Integer.parseInt(input)));
        final var parser = ExpressionParser.create(valueParser);

        final var result = parser.tryParse(expression);

        assertThat(result).hasValue(expectedValue);
    }
}

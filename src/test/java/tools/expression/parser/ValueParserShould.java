package tools.expression.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tools.expression.domain.Value;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ValueParserShould {

    @ParameterizedTest
    @ValueSource(strings = {"2", "3"})
    void create_a_value_from_the_given_expression(String expression) {
        final ItemParser itemParser = input -> Optional.of(new Success(input.charAt(0)));
        final var parser = ValueParser.create(itemParser);

        final  var result = parser.tryParse(expression);

        assertThat(result).hasValue(new Value(Integer.parseInt(expression)));
    }
}

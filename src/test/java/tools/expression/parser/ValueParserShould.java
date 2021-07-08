package tools.expression.parser;

import org.junit.jupiter.api.Test;
import tools.expression.domain.Value;

import static org.assertj.core.api.Assertions.assertThat;

public class ValueParserShould {

    @Test
    void create_a_value_from_the_given_expression() {
        final var parser = ValueParser.create();

        final  var result = parser.tryParse("3");

        assertThat(result).hasValue(new Value(3));
    }
}

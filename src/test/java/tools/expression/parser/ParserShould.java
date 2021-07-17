package tools.expression.parser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ParserShould {

    @Test
    void return_an_ast_from_a_text() {
        final var parser = Parser.create();
        final var text = "42";

        final ASTNode.Expression ast = parser.parse(text);

        assertAll(
                () -> assertThat(ast).isNotNull(),
                () -> assertThat(ast.body()).isNotNull(),
                () -> assertThat(ast.body().value()).isEqualTo(42)
        );

    }

    @Test
    void throw_IllegalStateException_when_it_is_the_end_of_input_and_expected_a_token_type() {
        final var parser = Parser.create();
        final var text = "";

        assertThatThrownBy(() -> parser.parse(text))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Unexpected end of input, expected: \"NUMBER\"");
    }

    /*@Test
    void throw_IllegalStateException_when_it_is_not_the_expected_token_type() {
        final var parser = Parser.create();
        final var text = "foo";

        assertThatThrownBy(() -> parser.parse(text))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Unexpected end of input, expected: \"NUMBER\"");
    }*/
}
